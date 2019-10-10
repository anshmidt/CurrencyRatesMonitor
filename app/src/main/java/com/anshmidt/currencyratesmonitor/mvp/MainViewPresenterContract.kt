package com.anshmidt.currencyratesmonitor.mvp

import com.anshmidt.currencyratesmonitor.data.CurrencyType

import java.util.Date

interface MainViewPresenterContract {

    interface View {
        fun displayRate(rate: Float, currencyType: CurrencyType)
        fun displayUpdateDate(date: Date, currencyType: CurrencyType)
        fun displayRateNotFoundError(currencyType: CurrencyType)
        fun displayUpdateDateNotAvailable(message: String, currencyType: CurrencyType)
    }

    interface Presenter {
        fun onViewAttached()
        fun onViewDetached()
        fun onRefreshButtonPressed()
    }

}
