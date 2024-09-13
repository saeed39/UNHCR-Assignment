package org.unhcr.jobs.app.Network

import org.unhcr.jobs.app.Model.Vacancy
import retrofit2.http.GET

interface ApiService {
    @GET("jobs/api")
    suspend fun getVacancies(): List<Vacancy>
}