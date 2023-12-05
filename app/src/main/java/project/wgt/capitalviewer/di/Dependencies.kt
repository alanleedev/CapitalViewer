package project.wgt.capitalviewer.di

import com.google.gson.GsonBuilder
import project.wgt.capitalviewer.network.CapitalAPIService
import project.wgt.capitalviewer.repository.CapitalRepository
import project.wgt.capitalviewer.util.Constants
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


// lazily create and store global dependencies used in the app
object Dependencies {

    private val retrofit: Retrofit by lazy {
        val gson = GsonBuilder().create()
        Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(Constants.BASE_URL)
            .build()
    }

    val service: CapitalAPIService by lazy {
        retrofit.create(CapitalAPIService::class.java)
    }

    val repository: CapitalRepository by lazy {
        CapitalRepository(service)
    }
}