package ashutosh.healthhive.patient.repository

import ashutosh.healthhive.patient.SingleLiveEvent
import ashutosh.healthhive.patient.api.RetrofitAPI
import ashutosh.healthhive.patient.api.NetworkResult
import ashutosh.healthhive.patient.models.Email
import ashutosh.healthhive.patient.models.DefaultResponse
import ashutosh.healthhive.patient.models.LoginResponse
import javax.inject.Inject

class GetStartedRepository @Inject constructor(private val retrofitAPI : RetrofitAPI) {

    val signUpEmailResponseLiveData =
        SingleLiveEvent<NetworkResult<DefaultResponse>>()

    val loginResponseLiveData =
        SingleLiveEvent<NetworkResult<LoginResponse>>()

    suspend fun signUpEmail(email : String){
        signUpEmailResponseLiveData.value = NetworkResult.Loading()
        try{
            val response = retrofitAPI.signUpEmail(Email(email))
            when(response.code()){
                200 -> {
                    if(response.body()!=null){
                        signUpEmailResponseLiveData.value = NetworkResult.Success(200, response.body()!!)
                    }
                    else{
                        signUpEmailResponseLiveData.value = NetworkResult.Error(200, "Something went wrong!\nError: Response is null")
                    }
                }
                400 -> signUpEmailResponseLiveData.value = NetworkResult.Error(400, "Email a valid email")
                409 -> signUpEmailResponseLiveData.value = NetworkResult.Error(409, "User Already Exist")
                503 -> signUpEmailResponseLiveData.value = NetworkResult.Error(503, "Unable to make your request")
                else -> signUpEmailResponseLiveData.value = NetworkResult.Error(response.code(), "Something went wrong\nError code: ${response.code()}")
            }
        }
        catch (e : Exception){
            signUpEmailResponseLiveData.value = NetworkResult.Error(-1, e.message)
        }
    }

    suspend fun signGoogle(token : String){
        loginResponseLiveData.value = NetworkResult.Loading()
        try{
            val response = retrofitAPI.signGoogle(token)
            when(response.code()){
                200 -> {
                    if(response.body() != null){
                        loginResponseLiveData.value = NetworkResult.Success(200, response.body()!!)
                    }
                    else{
                        loginResponseLiveData.value = NetworkResult.Error(200, "Something went wrong\nError: Response is null")
                    }
                }
                400 -> loginResponseLiveData.value = NetworkResult.Error(400, "Invalid token")
                403 -> loginResponseLiveData.value = NetworkResult.Error(403, "Either the token is expired or the token is not authorized")
                else -> loginResponseLiveData.value = NetworkResult.Error(response.code(), "Something went wrong\nError code : ${response.code()}")
            }
        }
        catch (e: Exception){
            loginResponseLiveData.value = NetworkResult.Error(-1, e.message)
        }
    }

}