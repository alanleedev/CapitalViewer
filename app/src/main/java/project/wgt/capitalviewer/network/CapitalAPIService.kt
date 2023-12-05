package project.wgt.capitalviewer.network

import kotlinx.coroutines.runBlocking
import project.wgt.capitalviewer.di.Dependencies
import project.wgt.capitalviewer.network.model.CountryResponse
import retrofit2.http.GET


interface CapitalAPIService {
    @GET("peymano-wmt/32dcb892b06648910ddd40406e37fdab/raw/db25946fd77c5873b0303b858e861ce724e0dcd0/countries.json")
    suspend fun getCountries(): List<CountryResponse>
}

fun main() {
    // quick test service function
    runBlocking {
        val countries = Dependencies.service.getCountries()
        println(countries)
    }
}
