package com.mohammed.taj.pintristpinboard.di.components

import com.mohammed.taj.pintristpinboard.di.modules.ContextModule
import com.mohammed.taj.pintristpinboard.presentaion_layer.MainActivity
import dagger.Component
import javax.inject.Singleton


/**
 * Created by Mohammed Sayed Taguldeen on 01,October,2019
 * Cairo, Egypt.
 */
@Singleton
@Component(modules = [
    ContextModule::class

])

interface AppComponent {
        fun inject(target :MainActivity)
}