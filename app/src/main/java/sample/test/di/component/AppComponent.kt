package sample.test.di.component

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import sample.test.BaseApplication
import sample.test.di.modules.AppModule
import sample.test.di.modules.NetworkModule
import sample.test.di.modules.ViewBuilder
import sample.test.di.modules.ViewModelFactory
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, AppModule::class
    , AndroidSupportInjectionModule::class,ViewBuilder::class,
    ViewModelFactory.ViewModelModule::class])
interface AppComponent: AndroidInjector<BaseApplication> {


    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<BaseApplication>()
    }
