package com.anshmidt.currencyratesmonitor.activities

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView

import com.anshmidt.currencyratesmonitor.data.CurrencyType
import com.anshmidt.currencyratesmonitor.mvp.MainPresenter
import com.anshmidt.currencyratesmonitor.R
import com.anshmidt.currencyratesmonitor.injection.component.DaggerAppComponent
import com.anshmidt.currencyratesmonitor.injection.module.NetworkModule
import com.anshmidt.currencyratesmonitor.injection.module.MvpModule
import com.anshmidt.currencyratesmonitor.mvp.MainViewPresenterContract

import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainViewPresenterContract.View {

    lateinit var usdRateTextView: TextView
    lateinit var euroRateTextView: TextView
    lateinit var usdUpdateDateTextView: TextView
    lateinit var euroUpdateDateTextView: TextView

    @Inject
    lateinit var mainPresenter: MainPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        usdRateTextView = findViewById(R.id.usd_rate_textview)
        euroRateTextView = findViewById(R.id.euro_rate_textview)
        usdUpdateDateTextView = findViewById(R.id.usd_update_date_textview)
        euroUpdateDateTextView = findViewById(R.id.euro_update_date_textview)

        initDagger()

        mainPresenter.onViewAttached()
    }

    private fun initDagger() {
        DaggerAppComponent.builder()
                .networkModule(NetworkModule())
                .mvpModule(MvpModule(this))
                .build()
                .inject(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        val id = menuItem.itemId

        when (id) {
            R.id.action_refresh -> {
                for (currencyType in CurrencyType.values()) {
                    mainPresenter.getRateFromServer(currencyType)
                }
            }
        }

        return super.onOptionsItemSelected(menuItem)
    }

    override fun onStop() {
        super.onStop()
        mainPresenter.onViewDetached()
    }

    override fun displayRate(rate: Float, currencyType: CurrencyType) {
        val rateTextView = getRateTextView(currencyType)

        val currencySymbol = getCurrencySymbol(currencyType)
        rateTextView.text = getString(R.string.currency_rate, currencySymbol, rate)
    }

    override fun displayRateNotFoundError(currencyType: CurrencyType) {
        val rateTextView = getRateTextView(currencyType)

        val currencySymbol = getCurrencySymbol(currencyType)
        rateTextView.text = getString(R.string.currency_rate_not_found, currencySymbol)
    }

    override fun displayUpdateDate(date: Date, currencyType: CurrencyType) {
        val updateDateTextView = getUpdateDateTextView(currencyType)

        val DATE_PATTERN = "HH:mm:ss dd/MM/yyyy"
        val simpleDateFormat = SimpleDateFormat(DATE_PATTERN)
        val displayableDate = simpleDateFormat.format(date)
        updateDateTextView.text = displayableDate
    }

    override fun displayUpdateDateNotAvailable(message: String, currencyType: CurrencyType) {
        val updateDateTextView = getUpdateDateTextView(currencyType)
        updateDateTextView.text = message
    }

    private fun getRateTextView(currencyType: CurrencyType): TextView {
        when (currencyType) {
            CurrencyType.EUR -> return euroRateTextView
            CurrencyType.USD -> return usdRateTextView
        }
    }

    private fun getUpdateDateTextView(currencyType: CurrencyType): TextView {
        when (currencyType) {
            CurrencyType.EUR -> return euroUpdateDateTextView
            CurrencyType.USD -> return usdUpdateDateTextView
        }
    }

    private fun getCurrencySymbol(currencyType: CurrencyType): String {
        when (currencyType) {
            CurrencyType.EUR -> return getString(R.string.euro_symbol)
            CurrencyType.USD -> return getString(R.string.usd_symbol)
        }
    }
}
