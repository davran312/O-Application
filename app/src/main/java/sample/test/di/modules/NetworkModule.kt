package sample.test.di.modules

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import sample.test.utils.Const
import sample.test.network.Repository
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

const val REQUEST_TIME_MINUTE = 1L
@Module
class NetworkModule{

    @Singleton
    @Provides
    internal fun provideRetrofit(client: OkHttpClient,  gsonFactory: GsonConverterFactory): Repository {
        return Retrofit.Builder()
            .baseUrl(Const.BASE_URL)
            .addConverterFactory(gsonFactory)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client).build()
            .create(Repository::class.java)
    }

    @Singleton
    @Provides
    internal fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        val client = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()
                val request = original.newBuilder()
                      .method(original.method(), original.body())
                    .build()
                chain.proceed(request)
            }.addInterceptor(interceptor)
            .writeTimeout(REQUEST_TIME_MINUTE, TimeUnit.MINUTES)
            .readTimeout(REQUEST_TIME_MINUTE, TimeUnit.MINUTES)
            .connectTimeout(REQUEST_TIME_MINUTE, TimeUnit.MINUTES)
        return client.build()
    }

    @Singleton
    @Provides
    internal fun provideLoginInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Singleton
    @Provides
    internal fun provideGson(): GsonConverterFactory {
        return GsonConverterFactory.create(GsonBuilder().setLenient().create())
    }

}