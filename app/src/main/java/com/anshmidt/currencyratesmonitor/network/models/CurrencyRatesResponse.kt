package com.anshmidt.currencyratesmonitor.network.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CurrencyRatesResponse (

    @SerializedName("Date")
    @Expose
    val date: String,

    @SerializedName("PreviousDate")
    @Expose
    val previousDate: String,

    @SerializedName("PreviousURL")
    @Expose
    val previousURL: String,

    @SerializedName("Timestamp")
    @Expose
    val timestamp: String,

    @SerializedName("Valute")
    @Expose
    val currencyList: CurrencyList


)
