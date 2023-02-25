package ashutosh.healthhive.patient.api

import ashutosh.healthhive.patient.models.PredictRequest
import ashutosh.healthhive.patient.models.PredictResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface MLApis {
    @Headers("isAuthorized: false")
    @POST("predict")
    suspend fun predict(@Body predictRequest: PredictRequest) : Response<PredictResponse>
}