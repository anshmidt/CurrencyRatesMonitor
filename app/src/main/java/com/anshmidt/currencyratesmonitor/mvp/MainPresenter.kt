package com.anshmidt.currencyratesmonitor.mvp

import com.anshmidt.currencyratesmonitor.data.CurrencyType
import com.anshmidt.currencyratesmonitor.network.CurrencyRatesApi
import com.anshmidt.currencyratesmonitor.network.models.Currency
import com.anshmidt.currencyratesmonitor.network.models.CurrencyList
import com.anshmidt.currencyratesmonitor.network.models.CurrencyRatesResponse
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class MainPresenter @Inject constructor(private val view: MainViewPresenterContract.View) : MainViewPresenterContract.Presenter {

    @Inject
    lateinit var currencyRatesApi: CurrencyRatesApi

    private var subscriptions = CompositeDisposable()

    val DEFAULT_CURRENCY_RATE = 0f
    val UPDATE_DATE_NOT_AVAILABLE_MESSAGE = ""

    override fun onViewAttached() {
        for (currencyType in CurrencyType.values()) {
            view.displayRate(DEFAULT_CURRENCY_RATE, currencyType)
            view.displayUpdateDateNotAvailable(UPDATE_DATE_NOT_AVAILABLE_MESSAGE, currencyType)
        }
    }

    override fun onRefreshButtonPressed() {
        val subscription = getCurrencyRatesResponseSingle()
                .subscribe(
                        { currencyRatesResponse: CurrencyRatesResponse ->
                            val responseDate = Date(System.currentTimeMillis())
                            onResponseFromServer(currencyRatesResponse, responseDate)
                        },
                        { error: Throwable ->
                            val responseDate = Date(System.currentTimeMillis())
                            onErrorFromServer(error, responseDate)
                        }
                )
        subscriptions.add(subscription)
    }

    private fun getCurrencyRatesResponseSingle(): Single<CurrencyRatesResponse> {
        return currencyRatesApi.getCurrencyRates()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    private fun onResponseFromServer(currencyRatesResponse: CurrencyRatesResponse, responseDate: Date) {
        val allCurrenciesFromResponse = currencyRatesResponse.currencyList
        val displayableCurrencyTypes = CurrencyType.values()

        for (currencyType in displayableCurrencyTypes) {
            val currency = getCurrencyFromListByType(currencyType, allCurrenciesFromResponse)
            view.displayRate(currency.value, currencyType)
            view.displayUpdateDate(responseDate, currencyType)
        }
    }

    private fun getCurrencyFromListByType(currencyType: CurrencyType, currencyList: CurrencyList): Currency {
        when (currencyType) {
            CurrencyType.EUR -> return currencyList.eurCurrency
            CurrencyType.USD -> return currencyList.usdCurrency
        }
    }

    private fun onErrorFromServer(error: Throwable, responseDate: Date) {
        val displayableCurrencyTypes = CurrencyType.values()

        for (currencyType in displayableCurrencyTypes) {
            view.displayRateNotFoundError(currencyType)
            view.displayUpdateDate(responseDate, currencyType)
        }
    }


    override fun onViewDetached() {
        if (!subscriptions.isDisposed) {
            subscriptions.dispose()
        }
    }



}
