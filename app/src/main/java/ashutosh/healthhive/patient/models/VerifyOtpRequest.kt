package ashutosh.healthhive.patient.models

data class VerifyOtpRequest(
    val email: String,
    val one_time_password: String
)