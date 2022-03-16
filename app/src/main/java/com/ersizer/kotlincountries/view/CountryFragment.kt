package com.ersizer.kotlincountries.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.ersizer.kotlincountries.R
import com.ersizer.kotlincountries.databinding.FragmentCountryBinding
import com.ersizer.kotlincountries.util.downloadFromUrl
import com.ersizer.kotlincountries.util.placeHolderProgressBar
import com.ersizer.kotlincountries.viewmodel.CountryViewModel
import com.ersizer.kotlincountries.viewmodel.FeedViewModel
import kotlinx.android.synthetic.main.fragment_country.*


class CountryFragment : Fragment() {

    private lateinit var viewModel:CountryViewModel
    private var countryUuid=0
    private lateinit var dataBinding: FragmentCountryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding=DataBindingUtil.inflate(inflater,R.layout.fragment_country,container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            countryUuid=CountryFragmentArgs.fromBundle(it).countryUuid
        }

        viewModel=ViewModelProvider(this).get(CountryViewModel :: class.java)
        viewModel.getDataFromRoom(countryUuid)

        observeLiveData()
    }

    private fun observeLiveData(){
        viewModel.countryLiveData.observe(viewLifecycleOwner, Observer { country ->
            country?.let {
                dataBinding.selectedCountry=country
                /*
                countryName.text=country.countryName
                countryCapital.text=country.countryCapital
                countryCurrency.text=country.countryCurrency
                countryLanguage.text=country.countryLanguage
                countryRegion.text=country.countryRegion
                context?.let {
                    countryImage.downloadFromUrl(country.imageUrl, placeHolderProgressBar(it))
                }

                 */
            }
        })
    }

}