package com.ersizer.kotlincountries.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ersizer.kotlincountries.model.Country
import com.ersizer.kotlincountries.service.CountryDatabase
import kotlinx.coroutines.launch
import java.util.*

class CountryViewModel(application: Application):BaseViewModel(application) {

    val countryLiveData=MutableLiveData<Country>()

    fun getDataFromRoom(uuid: Int){
        launch {

            val dao=CountryDatabase(getApplication()).countryDao()
            val country=dao.getCountry(uuid)
            countryLiveData.value=country

        }

    }

}