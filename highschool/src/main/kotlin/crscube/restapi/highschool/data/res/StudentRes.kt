package crscube.restapi.highschool.data.res

import java.time.LocalDate
import java.time.LocalDateTime

data class StudentRes(
    val id: Long,
    val studentNumber: Int,
    val name: String,
    val grade: Int,
    val classNumber: Int,
    val birthDate: LocalDate,
    val email: String?,
    val phoneNumber: String?,
    val address: String?,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
