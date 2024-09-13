package org.unhcr.jobs.app.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.unhcr.jobs.app.Repository.VacancyRepository

class VacancyViewModelFactory(private val repository: VacancyRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VacancyViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return VacancyViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}