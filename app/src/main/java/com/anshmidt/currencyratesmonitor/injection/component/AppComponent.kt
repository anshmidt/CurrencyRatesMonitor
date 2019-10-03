package com.anshmidt.currencyratesmonitor.injection.component

import com.anshmidt.currencyratesmonitor.activities.MainActivity
import com.anshmidt.currencyratesmonitor.injection.module.NetworkModule
import com.anshmidt.currencyratesmonitor.injection.module.MvpModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class), (MvpModule::class)])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}