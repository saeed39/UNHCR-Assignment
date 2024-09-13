package org.unhcr.jobs.app

import org.unhcr.jobs.app.Adapters.VacancyListAdapter
import org.unhcr.jobs.app.Model.Vacancy
import org.unhcr.jobs.app.Network.RetrofitClient
import org.unhcr.jobs.app.Repository.VacancyRepository
import org.unhcr.jobs.app.ViewModel.VacancyViewModel
import org.unhcr.jobs.app.ViewModel.VacancyViewModelFactory

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import org.unhcr.jobs.app.Activities.VacancyDetailsActivity
import org.unhcr.jobs.app.Util.Networking
import org.unhcr.jobs.app.databinding.ActivityVacancyListBinding


class VacancyListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVacancyListBinding
    private val networking = Networking()

    private val viewModel: VacancyViewModel by viewModels {
        VacancyViewModelFactory(VacancyRepository(RetrofitClient.apiService))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVacancyListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        observeVacancies()
        viewModel.getVacancies()
    }

    private fun setupRecyclerView() {
        binding.recyclerViewVacancies.layoutManager = LinearLayoutManager(this)
    }

    private fun observeVacancies() {
        val isConnected = networking.isInternetAvailable(this)

        if (isConnected) {
            viewModel.vacancies.observe(this) { vacancies ->
                binding.recyclerViewVacancies.adapter = VacancyListAdapter(vacancies) { vacancy ->
                    navigateToDetails(vacancy)
                }
            }
        } else {
            Snackbar.make(binding.root, getString(R.string.general_error),
                Snackbar.LENGTH_LONG).show()
        }
    }

    private fun navigateToDetails(vacancy: Vacancy) {
        val intent = Intent(this, VacancyDetailsActivity::class.java).apply {
            putExtra("VACANCY_DETAILS", vacancy)
        }
        startActivity(intent)
    }
}
