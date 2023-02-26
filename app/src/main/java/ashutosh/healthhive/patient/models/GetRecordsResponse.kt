package ashutosh.healthhive.patient.models

data class GetRecordsResponse(
    val record: List<Record>,
    val lastPage: Boolean,
    val pageNumber: Int,
    val pageSize: Int,
    val totalElements: Int,
    val totalPage: Int
)