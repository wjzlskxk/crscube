package crscube.restapi.highschool.data.req

import java.time.LocalDate

data class StudentUpdateReq(
    val name: String? = null,
    val grade: Int? = null,
    val classNumber: Int? = null,
    val birthday: LocalDate? = null,
    val email: String? = null,
    val phoneNumber: String? = null,
    val address: String? = null
)
