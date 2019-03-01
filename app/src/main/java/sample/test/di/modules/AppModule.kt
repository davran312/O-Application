package sample.test.di.modules

import android.content.Context
import android.preference.Preference
import dagger.Module
import dagger.Provides
import sample.test.BaseApplication
import javax.inject.Singleton


@Module
class AppModule {

    @Singleton
    @Provides
    internal fun provideContext(application: BaseApplication): Context = application







}