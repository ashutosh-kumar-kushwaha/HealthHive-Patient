package ashutosh.healthhive.patient.repository

import android.util.Log
import ashutosh.healthhive.patient.SingleLiveEvent
import ashutosh.healthhive.patient.api.RetrofitAPI
import ashutosh.healthhive.patient.api.NetworkResult
import ashutosh.healthhive.patient.models.LoginRequest
import ashutosh.healthhive.patient.models.LoginResponse
import ashutosh.healthhive.patient.models.SignUpRequest
import javax.inject.Inject

class SignUpRepository @Inject constructor(private val retrofitAPI: RetrofitAPI) {

    val signUpResponseLiveData = SingleLiveEvent<NetworkResult<LoginResponse>>()

    suspend fun signUp(dob: String, bloodGroup: String, email : String, otp : String, firstName : String, lastName : String, gender : String, password : String){
        signUpResponseLiveData.value = NetworkResult.Loading()
        try {
            val response = retrofitAPI.signUp(SignUpRequest(dob, bloodGroup, email, firstName, gender, lastName, otp, password))
//            Log.d("Ashu", .toString())
            when(response.code()){
                200 -> {
                    login(email, password)
                }
                401 -> signUpResponseLiveData.value = NetworkResult.Error(401, "Invalid OTP")
                408 -> signUpResponseLiveData.value = NetworkResult.Error(408, "Session Time-out")
                503 -> signUpResponseLiveData.value = NetworkResult.Error(503, "Invalid Action")
                else -> signUpResponseLiveData.value = NetworkResult.Error(response.code(),"Something went wrong\nError code: ${response.code()}")
            }
        }
        catch (e : Exception){
            signUpResponseLiveData.value = NetworkResult.Error(-1, e.message)
            e.printStackTrace()
        }
    }

    suspend fun login(email: String, password: String) {
        try {
            val response = retrofitAPI.login(LoginRequest(email, password))
            when (response.code()) {
                200 -> {
                    if(response.body()!=null){
                        signUpResponseLiveData.postValue(NetworkResult.Success(200, response.body()!!))
                    }
                    else{
                        signUpResponseLiveData.postValue(NetworkResult.Error(response.code(),"Something went wrong\nError : Response is null"))
                    }
                }
                404 -> signUpResponseLiveData.postValue(NetworkResult.Error(404, "User does not exist"))

                400 -> signUpResponseLiveData.postValue(NetworkResult.Error(400, "Invalid Format of email or password"))

                401 -> signUpResponseLiveData.postValue(NetworkResult.Error(401, "Wrong Password"))

                else -> signUpResponseLiveData.postValue(NetworkResult.Error(response.code(),"Something went wrong\nError code : ${response.code()}"))
            }
        } catch (e: Exception) {
            signUpResponseLiveData.value = NetworkResult.Error(-1, e.message)
            e.printStackTrace()
        }
    }

}