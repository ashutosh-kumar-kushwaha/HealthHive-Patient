package ashutosh.healthhive.patient.models

data class Record(
    val description: String,
    val from: String,
    val id: Int,
    val location: String,
    val name: String,
    val prescriptionUrls: List<String>,
    val symptoms: String,
    val to: String
)