package com.mohammed.taj.pintristpinboard.di.modules

import com.mohammed.taj.loadinglib.library_components.parsing.JsonArrayParser
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * Created by Mohammed Sayed Taguldeen on 03,October,2019
 * Cairo, Egypt.
 */


@Module
class BaseParserModule {


    @Provides
    @Singleton
    fun provideBaseParser() = JsonArrayParser()
}