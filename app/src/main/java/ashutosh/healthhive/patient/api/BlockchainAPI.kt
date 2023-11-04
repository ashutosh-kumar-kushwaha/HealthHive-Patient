package ashutosh.healthhive.patient.api

import ashutosh.healthhive.patient.models.DefaultResponse
import ashutosh.healthhive.patient.models.GetRecordsResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface BlockchainAPI {
    @Headers("isAuthorized: true")
    @GET("api/healthRecord/getRecord?pageNumber=0&pageSize=100")
    suspend fun getRecords() : Response<GetRecordsResponse>

    @Multipart
    @Headers("isAuthorized: true")
    @POST("api/healthRecord/saveRecord")
    suspend fun addRecord(@Part images: List<MultipartBody.Part>, @Part("healthRecordDto") healthRecord: RequestBody) : Response<DefaultResponse>
}