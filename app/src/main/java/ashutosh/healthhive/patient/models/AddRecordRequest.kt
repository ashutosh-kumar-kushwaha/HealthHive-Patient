package ashutosh.healthhive.patient.models

data class AddRecordRequest(
    val description: String,
    val from: String,
    val location: String,
    val name: String,
    val symptoms: String,
    val to: String
)