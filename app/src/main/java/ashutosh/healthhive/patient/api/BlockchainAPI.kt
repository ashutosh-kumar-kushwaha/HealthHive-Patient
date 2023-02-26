package ashutosh.healthhive.patient.api

import ashutosh.healthhive.patient.models.GetRecordsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface BlockchainAPI {
    @Headers("isAuthorized: true")
    @GET("healthRecord/getRecord?pageNumber=0&pageSize=100")
    suspend fun getRecords() : Response<GetRecordsResponse>
}