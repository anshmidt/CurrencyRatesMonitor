package com.anshmidt.currencyratesmonitor.network.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CurrencyList (
        @SerializedName("AUD")
        @Expose
        val audCurrency: Currency,
    
        @SerializedName("AZN")
        @Expose
        val aznCurrency: Currency,
    
        @SerializedName("GBP")
        @Expose
        val gbpCurrency: Currency,
    
        @SerializedName("AMD")
        @Expose
        val amdCurrency: Currency,
    
        @SerializedName("BYN")
        @Expose
        val bynCurrency: Currency,
    
        @SerializedName("BGN")
        @Expose
        val bgnCurrency: Currency,
    
        @SerializedName("BRL")
        @Expose
        val brlCurrency: Currency,
    
        @SerializedName("HUF")
        @Expose
        val hufCurrency: Currency,
    
        @SerializedName("HKD")
        @Expose
        val hkdCurrency: Currency,
    
        @SerializedName("DKK")
        @Expose
        val dkkCurrency: Currency,
    
        @SerializedName("USD")
        @Expose
        val usdCurrency: Currency,
    
        @SerializedName("EUR")
        @Expose
        val eurCurrency: Currency,
    
        @SerializedName("INR")
        @Expose
        val inrCurrency: Currency,
    
        @SerializedName("KZT")
        @Expose
        val kztCurrency: Currency,
    
        @SerializedName("CAD")
        @Expose
        val cadCurrency: Currency,
    
        @SerializedName("KGS")
        @Expose
        val kgsCurrency: Currency,
    
        @SerializedName("CNY")
        @Expose
        val cnyCurrency: Currency,
    
        @SerializedName("MDL")
        @Expose
        val mdlCurrency: Currency,
    
        @SerializedName("NOK")
        @Expose
        val nokCurrency: Currency,
    
        @SerializedName("PLN")
        @Expose
        val plnCurrency: Currency,
    
        @SerializedName("RON")
        @Expose
        val ronCurrency: Currency,
    
        @SerializedName("XDR")
        @Expose
        val xdrCurrency: Currency,
    
        @SerializedName("SGD")
        @Expose
        val sgdCurrency: Currency,
    
        @SerializedName("TJS")
        @Expose
        val tjsCurrency: Currency,
    
        @SerializedName("TRY")
        @Expose
        val tryCurrency: Currency,
    
        @SerializedName("TMT")
        @Expose
        val tmtCurrency: Currency,
    
        @SerializedName("UZS")
        @Expose
        val uzsCurrency: Currency,
    
        @SerializedName("UAH")
        @Expose
        val uahCurrency: Currency,
    
        @SerializedName("CZK")
        @Expose
        val czkCurrency: Currency,
    
        @SerializedName("SEK")
        @Expose
        val sekCurrency: Currency,
    
        @SerializedName("CHF")
        @Expose
        val chfCurrency: Currency,
    
        @SerializedName("ZAR")
        @Expose
        val zarCurrency: Currency,
    
        @SerializedName("KRW")
        @Expose
        val krwCurrency: Currency,
    
        @SerializedName("JPY")
        @Expose
        val jpyCurrency: Currency
)
