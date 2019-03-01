package sample.test

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import sample.test.di.component.DaggerAppComponent

class BaseApplication: DaggerApplication(){
    companion object {
        lateinit var INSTANCE:BaseApplication
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }


}