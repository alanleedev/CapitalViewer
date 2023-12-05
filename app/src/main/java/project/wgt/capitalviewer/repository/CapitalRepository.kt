package project.wgt.capitalviewer.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import project.wgt.capitalviewer.di.Dependencies
import project.wgt.capitalviewer.model.CountryItem
import project.wgt.capitalviewer.network.CapitalAPIService
import project.wgt.capitalviewer.network.model.CountryResponse

class CapitalRepository(
    private val service: CapitalAPIService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) {

    suspend fun getCountries(): List<CountryItem> {
        return withContext(ioDispatcher) {
            service.getCountries().toCountryItems()
        }
    }
}

fun List<CountryResponse>.toCountryItems(): List<CountryItem> =
    this.map { country ->
        CountryItem(
            country.name,
            country.region,
            country.code,
            country.capital,
        )
    }

fun main() {
    runBlocking {
        // quick test repository
        val countries = Dependencies.repository.getCountries()
        println(countries)
    }
}