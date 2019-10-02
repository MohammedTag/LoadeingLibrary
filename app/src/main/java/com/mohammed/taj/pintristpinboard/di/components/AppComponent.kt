package com.mohammed.taj.pintristpinboard.di.components

import com.mohammed.taj.pintristpinboard.di.modules.ContextModule
import com.mohammed.taj.pintristpinboard.di.modules.RemoteDataSourceModule
import com.mohammed.taj.pintristpinboard.di.modules.RepositorySourceModule
import com.mohammed.taj.pintristpinboard.domain_layer.usecases.FeedsUseCaseDependenciesModule
import com.mohammed.taj.pintristpinboard.presentaion_layer.MainActivity
import com.mohammed.taj.pintristpinboard.presentaion_layer.fragments.FeedsFragment
import com.mohammed.taj.pintristpinboard.presentaion_layer.fragments.FeedsViewModelFactoryModule
import dagger.Component
import javax.inject.Singleton


/**
 * Created by Mohammed Sayed Taguldeen on 01,October,2019
 * Cairo, Egypt.
 */
@Singleton
@Component(
    modules = [
        ContextModule::class,
        RemoteDataSourceModule::class,
        RepositorySourceModule::class,
        FeedsUseCaseDependenciesModule::class,
        FeedsViewModelFactoryModule::class

    ]
)

interface AppComponent {
    fun inject(target: MainActivity)
    fun inject(target: FeedsFragment)
}