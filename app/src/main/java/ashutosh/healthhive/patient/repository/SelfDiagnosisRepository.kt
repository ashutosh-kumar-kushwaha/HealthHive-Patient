package ashutosh.healthhive.patient.repository

import ashutosh.healthhive.patient.SingleLiveEvent
import ashutosh.healthhive.patient.api.MLApis
import ashutosh.healthhive.patient.api.NetworkResult
import ashutosh.healthhive.patient.models.PredictRequest
import ashutosh.healthhive.patient.models.PredictResponse
import javax.inject.Inject

class SelfDiagnosisRepository @Inject constructor(private val mlApis: MLApis) {

    val predictResponse = SingleLiveEvent<NetworkResult<PredictResponse>>()

    suspend fun predictResponse(predictRequest: PredictRequest){
        predictResponse.value = NetworkResult.Loading()
        try {
            val response = mlApis.predict(predictRequest)
            when (response.code()) {
                200 -> {
                    if(response.body()!=null){
                        predictResponse.postValue(NetworkResult.Success(200, response.body()!!))
                    }
                    else{
                        predictResponse.postValue(NetworkResult.Error(200, "Something went wrong\nError: Response is null"))
                    }
                }
                else -> predictResponse.postValue(NetworkResult.Error(response.code(), "Something went wrong\nError code : ${response.code()}"))
            }
        } catch (e: Exception) {
            predictResponse.value = NetworkResult.Error(-1, e.message)
        }
    }
}