package com.mohammed.taj.pintristpinboard.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * Created by Mohammed Sayed Taguldeen on 01,October,2019
 * Cairo, Egypt.
 */
@Module
class ContextModule(private val appContext: Context) {

    @Provides
    @Singleton
    fun provideContext() = appContext
}