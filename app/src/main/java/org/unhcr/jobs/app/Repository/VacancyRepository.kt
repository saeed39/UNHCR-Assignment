package org.unhcr.jobs.app.Repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.unhcr.jobs.app.Model.Vacancy
import org.unhcr.jobs.app.Network.ApiService

class VacancyRepository(private val apiService: ApiService) {

    suspend fun fetchVacancies(): List<Vacancy> {
        return withContext(Dispatchers.IO) {
            try {
                apiService.getVacancies()
            } catch (e: Exception) {
                emptyList()
            }
        }
    }
}