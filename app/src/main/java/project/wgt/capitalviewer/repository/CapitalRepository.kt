package project.wgt.capitalviewer.repository

import kotlinx.coroutines.runBlocking
import project.wgt.capitalviewer.di.Dependencies
import project.wgt.capitalviewer.model.CountryItem
import project.wgt.capitalviewer.network.CapitalAPIService

class CapitalRepository(private val service: CapitalAPIService) {

    suspend fun getCapital(): List<CountryItem> {
        return service.getCountries().map { country ->
            CountryItem(
                country.name,
                country.region,
                country.code,
                country.capital,
            )
        }
    }
}

fun main() {
    runBlocking {
        // quick test repository
        val countries = Dependencies.repository.getCapital()
        println(countries)
    }
}