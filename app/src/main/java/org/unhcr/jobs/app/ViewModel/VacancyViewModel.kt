package org.unhcr.jobs.app.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.unhcr.jobs.app.Model.Vacancy
import org.unhcr.jobs.app.Repository.VacancyRepository

class VacancyViewModel(private val repository: VacancyRepository) : ViewModel() {

    private val vacanciesLiveData = MutableLiveData<List<Vacancy>>()
    val vacancies: LiveData<List<Vacancy>> get() = vacanciesLiveData

    fun getVacancies() {
        viewModelScope.launch {
            vacanciesLiveData.value = repository.fetchVacancies()
        }
    }
}