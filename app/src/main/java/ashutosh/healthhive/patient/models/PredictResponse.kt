package ashutosh.healthhive.patient.models

data class PredictResponse(
    val Description: String,
    val Disease: String,
    val Precautions: List<String>
)