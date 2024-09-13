package org.unhcr.jobs.app.Model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Vacancy(
    @SerializedName("job_id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("company") val company: String,
    @SerializedName("location") val location: String,
    @SerializedName("description") val description: String,
    @SerializedName("long_description") val longDescription: String,
    @SerializedName("salary") val salary: String?,
    @SerializedName("date_posted") val datePosted: String,
    @SerializedName("image_url") val imageUrl: String
) : Parcelable