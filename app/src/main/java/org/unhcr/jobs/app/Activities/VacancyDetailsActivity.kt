package org.unhcr.jobs.app.Activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import org.unhcr.jobs.app.Model.Vacancy
import org.unhcr.jobs.app.R
import org.unhcr.jobs.app.databinding.ActivityVacancyDetailsBinding

class VacancyDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVacancyDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVacancyDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val vacancy = intent.getParcelableExtra<Vacancy>("VACANCY_DETAILS")
        vacancy?.let {
            displayDetails(it)
        }
    }

    private fun displayDetails(vacancy: Vacancy) {
        //Activity Title
        title = vacancy.title
        //Job Details
        binding.textViewDetailID.text = vacancy.id
        binding.textViewDetailTitle.text = vacancy.title
        binding.textViewDetailCompany.text = vacancy.company
        binding.textViewDetailLocation.text = vacancy.location
        binding.textViewDetailDescription.text = vacancy.description
        binding.textViewDetailLongDescription.text = vacancy.longDescription
        Glide.with(this)
            .load(vacancy.imageUrl)
            .placeholder(R.drawable.un_logo)
            .into(binding.imageViewDetailThumbnail)
    }
}
