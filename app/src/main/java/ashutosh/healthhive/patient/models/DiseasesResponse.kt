package ashutosh.healthhive.patient.models

data class DiseasesResponse(
    val Description: String,
    val Disease: String,
    val Precautions: List<String>
)