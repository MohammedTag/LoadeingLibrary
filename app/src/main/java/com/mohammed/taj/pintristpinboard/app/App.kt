package com.mohammed.taj.pintristpinboard.app

import android.content.Context
import androidx.multidex.MultiDexApplication
import com.mohammed.taj.pintristpinboard.di.components.AppComponent
import com.mohammed.taj.pintristpinboard.di.components.DaggerAppComponent
import com.mohammed.taj.pintristpinboard.di.modules.ContextModule


/**
 * Created by Mohammed Sayed Taguldeen on 01,October,2019
 * Cairo, Egypt.
 */
class App: MultiDexApplication() {

    lateinit var context: Context
    lateinit var componant: AppComponent

    private fun initDagger(appContext: Context): AppComponent =
        DaggerAppComponent.builder()
            .contextModule(ContextModule(appContext))
            .build()


    override fun onCreate() {
        super.onCreate()
        context = this
        componant = initDagger(this.applicationContext)
    }

}
