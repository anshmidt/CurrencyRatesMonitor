package com.anshmidt.currencyratesmonitor

import com.anshmidt.currencyratesmonitor.data.CurrencyType
import com.anshmidt.currencyratesmonitor.mvp.MainPresenter
import com.anshmidt.currencyratesmonitor.mvp.MainViewPresenterContract
import com.anshmidt.currencyratesmonitor.network.CurrencyRatesApi
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class MainPresenterTest {

    @Mock
    lateinit var mainView: MainViewPresenterContract.View

    @Mock
    lateinit var currencyRatesApi: CurrencyRatesApi

    lateinit var mainPresenter: MainPresenter


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        mainPresenter = MainPresenter(mainView)
    }

    @Test
    fun defaultValuesShouldBeDisplayed_WhenViewAppears() {
        mainPresenter.onViewAttached()

        verify(mainView, Mockito.times(1))
                .displayUpdateDateNotAvailable(mainPresenter.UPDATE_DATE_NOT_AVAILABLE_MESSAGE, CurrencyType.USD)
        verify(mainView, Mockito.times(1))
                .displayUpdateDateNotAvailable(mainPresenter.UPDATE_DATE_NOT_AVAILABLE_MESSAGE, CurrencyType.EUR)
        verify(mainView, Mockito.times(1))
                .displayRate(mainPresenter.DEFAULT_CURRENCY_RATE, CurrencyType.USD)
        verify(mainView, Mockito.times(1))
                .displayRate(mainPresenter.DEFAULT_CURRENCY_RATE, CurrencyType.EUR)
    }

    @Test
    fun infoFromServerShouldBeDisplayed_WhenRefreshButtonPressed() {
        mainPresenter.onRefreshButtonPressed()

        verify(currencyRatesApi, Mockito.times(1))
                .getCurrencyRates()
    }
}