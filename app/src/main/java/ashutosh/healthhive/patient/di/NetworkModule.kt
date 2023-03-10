package ashutosh.healthhive.patient.di

import android.content.Context
import ashutosh.healthhive.patient.Constants
import ashutosh.healthhive.patient.datastore.DataStoreManager
import ashutosh.healthhive.patient.api.AuthAuthenticator
import ashutosh.healthhive.patient.api.AuthInterceptor
import ashutosh.healthhive.patient.api.RetrofitAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit.Builder = Retrofit.Builder().baseUrl(Constants.baseUrl).addConverterFactory(GsonConverterFactory.create())


    @Singleton
    @Provides
    fun providesRetrofitAPI(retrofitBuilder: Retrofit.Builder, okHttpClient: OkHttpClient): RetrofitAPI =  retrofitBuilder.client(okHttpClient).build().create(RetrofitAPI::class.java)


    @Singleton
    @Provides
    fun providesOkHttpClient(authInterceptor: AuthInterceptor, authAuthenticator: AuthAuthenticator): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder().addInterceptor(authInterceptor).authenticator(authAuthenticator).build()
    }

    @Singleton
    @Provides
    fun providesDataStoreManager(@ApplicationContext context: Context): DataStoreManager = DataStoreManager(context)

    @Singleton
    @Provides
    fun providesAuthInterceptor(dataStoreManager: DataStoreManager): AuthInterceptor = AuthInterceptor(dataStoreManager)

    @Singleton
    @Provides
    fun providesAuthAuthenticator(dataStoreManager: DataStoreManager): AuthAuthenticator = AuthAuthenticator(dataStoreManager)



}