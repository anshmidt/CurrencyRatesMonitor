package com.anshmidt.currencyratesmonitor.injection.module

import com.anshmidt.currencyratesmonitor.mvp.MainPresenter
import com.anshmidt.currencyratesmonitor.mvp.MainViewPresenterContract
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MvpModule(var view: MainViewPresenterContract.View) {

    @Provides
    fun provideView(): MainViewPresenterContract.View {
        return view
    }

}