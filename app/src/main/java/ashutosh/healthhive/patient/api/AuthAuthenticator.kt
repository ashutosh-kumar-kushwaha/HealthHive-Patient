package ashutosh.healthhive.patient.api

import android.util.Log
import ashutosh.healthhive.patient.Constants
import ashutosh.healthhive.patient.datastore.DataStoreManager
import ashutosh.healthhive.patient.models.LoginResponse
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class AuthAuthenticator @Inject constructor(private val dataStoreManager: DataStoreManager) :
    Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        val refreshToken = runBlocking {
            dataStoreManager.getLogInInfo().first()
        }.refreshToken



        return runBlocking {
            val apiResponse = regenerateAccessToken(refreshToken.toString())

            if (!apiResponse.isSuccessful || apiResponse.body() == null) {
                dataStoreManager.deleteAccessToken()
            }

            apiResponse.body()?.let {
                dataStoreManager.saveAccessToken(it.accessToken)

                response.request
                    .newBuilder()
                    .header("Authorization", "Bearer ${it.accessToken}")
                    .build()
            }
        }
    }

    private suspend fun regenerateAccessToken(refreshToken: String): retrofit2.Response<LoginResponse> {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()

        val retrofit = Retrofit.Builder().
        baseUrl(Constants.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        val retrofitAPI = retrofit.create(RetrofitAPI::class.java)
        Log.d("Ashu", "Access Token Generated")
        return retrofitAPI.regenerateAccessToken(refreshToken)
    }
}