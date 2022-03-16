package com.ersizer.kotlincountries.service

import com.ersizer.kotlincountries.model.Country
import io.reactivex.Single
import retrofit2.http.GET

interface CountryAPI {

    @GET("API")
    fun getCountries():Single<List<Country>>

}