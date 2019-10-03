package com.anshmidt.currencyratesmonitor.mvp

import com.anshmidt.currencyratesmonitor.data.CurrencyType
import com.anshmidt.currencyratesmonitor.network.CurrencyRatesApi
import com.anshmidt.currencyratesmonitor.network.models.Currency
import com.anshmidt.currencyratesmonitor.network.models.CurrencyList
import com.anshmidt.currencyratesmonitor.network.models.CurrencyRatesResponse
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class MainPresenter @Inject constructor(private val view: MainViewPresenterContract.View) : MainViewPresenterContract.Presenter {

    @Inject
    lateinit var currencyRatesApi: CurrencyRatesApi


    private lateinit var subscription: Disposable


    private fun getCurrencyRatesResponseSingle(): Single<CurrencyRatesResponse> {
        return currencyRatesApi.getCurrencyRates()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun onStart() {
        val DEFAULT_CURRENCY_RATE = 0f
        val DEFAULT_UPDATE_DATE = ""
        for (currencyType in CurrencyType.values()) {
            view.displayRate(DEFAULT_CURRENCY_RATE, currencyType)
            view.displayUpdateDateNotAvailable(DEFAULT_UPDATE_DATE, currencyType)
        }
    }

    override fun getRateFromServer(currencyType: CurrencyType) {
        subscription = getCurrencyRatesResponseSingle()
                .subscribe({ currencyRatesResponse: CurrencyRatesResponse ->
                    val currencyList = currencyRatesResponse.currencyList
                    val currency = getCurrencyByType(currencyType, currencyList)
                    view.displayRate(currency!!.value, currencyType)

                    val currentDate = Date(System.currentTimeMillis())
                    view.displayUpdateDate(currentDate, currencyType)
                }, {error ->
                    view.displayRateNotFoundError(currencyType)

                    val currentDate = Date(System.currentTimeMillis())
                    view.displayUpdateDate(currentDate, currencyType)
                })

    }

    private fun getCurrencyByType(currencyType: CurrencyType, currencyList: CurrencyList?): Currency? {
        when (currencyType) {
            CurrencyType.EUR -> return currencyList!!.eurCurrency
            CurrencyType.USD -> return currencyList!!.usdCurrency
        }
    }
}
