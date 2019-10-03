package com.anshmidt.currencyratesmonitor.network

import com.anshmidt.currencyratesmonitor.network.models.CurrencyRatesResponse

import io.reactivex.Single
import retrofit2.http.GET

interface CurrencyRatesApi {

    @GET(Constants.DAILY_JSON)
    fun getCurrencyRates(): Single<CurrencyRatesResponse>
}
