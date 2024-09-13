package org.unhcr.jobs.app.Util

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import androidx.annotation.ColorInt
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.bumptech.glide.load.resource.bitmap.TransformationUtils
import java.security.MessageDigest

class CircleWithBorderTransformation(
    private val borderSize: Float,
    @ColorInt private val borderColor: Int
) : BitmapTransformation() {

    override fun updateDiskCacheKey(messageDigest: MessageDigest) {
        messageDigest.update("circle_border_transformation".toByteArray())
    }

    override fun transform(
        pool: BitmapPool,
        toTransform: Bitmap,
        outWidth: Int,
        outHeight: Int
    ): Bitmap {
        val transformed = TransformationUtils.circleCrop(pool, toTransform, outWidth, outHeight)
        return addBorder(pool, transformed)
    }

    private fun addBorder(pool: BitmapPool, bitmap: Bitmap): Bitmap {
        val size = bitmap.width
        val output = pool.get(size, size, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(output)

        val paint = Paint()
        paint.isAntiAlias = true

        // Draw the original image as circle
        canvas.drawBitmap(bitmap, 0f, 0f, paint)

        // Draw the border
        val borderPaint = Paint()
        borderPaint.isAntiAlias = true
        borderPaint.style = Paint.Style.STROKE
        borderPaint.color = borderColor
        borderPaint.strokeWidth = borderSize

        val halfBorderSize = borderSize / 2
        val rect = RectF(
            halfBorderSize,
            halfBorderSize,
            size - halfBorderSize,
            size - halfBorderSize
        )
        canvas.drawOval(rect, borderPaint)

        return output
    }
}
