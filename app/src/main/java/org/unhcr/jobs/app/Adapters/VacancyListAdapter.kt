package org.unhcr.jobs.app.Adapters


import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import org.unhcr.jobs.app.Model.Vacancy
import org.unhcr.jobs.app.R
import org.unhcr.jobs.app.Util.CircleWithBorderTransformation

class VacancyListAdapter(
    private val vacancies: List<Vacancy>,
    private val onItemClick: (Vacancy) -> Unit
) : RecyclerView.Adapter<VacancyListAdapter.VacancyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VacancyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_vacancy, parent, false)
        return VacancyViewHolder(view)
    }

    override fun onBindViewHolder(holder: VacancyViewHolder, position: Int) {
        holder.bind(vacancies[position])
    }

    override fun getItemCount(): Int = vacancies.size

    inner class VacancyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.vacancyTitle)
        private val vacancyDescription: TextView = itemView.findViewById(R.id.vacancyDescription)
        private val company: TextView = itemView.findViewById(R.id.vacancyCompany)
        private val datePosted: TextView = itemView.findViewById(R.id.vacancyDate)
        private val thumbnail: ImageView = itemView.findViewById(R.id.vacancyThumbnail)

        fun bind(vacancy: Vacancy) {
            title.text = vacancy.title
            company.text = vacancy.company
            datePosted.text = vacancy.datePosted
            vacancyDescription.text = vacancy.description
            Glide.with(itemView.context)
                .load(vacancy.imageUrl)
                .transform(
                    CenterInside(),
                    CircleWithBorderTransformation(10f, Color.parseColor("#0077c0"))
                )
                .placeholder(R.drawable.un_logo)
                .into(thumbnail)

            itemView.setOnClickListener {
                onItemClick(vacancy)
            }
        }
    }
}