package crscube.restapi.highschool.data.req

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Past
import java.time.LocalDate

data class StudentCreateReq(
    @NotBlank
    val studentNumber: String,

    @NotBlank
    val name: String,

    @Min(1)
    @Max(3)
    val grade: Int? = null,

    val classNumber: Int? = null,

    @Past
    val birthday: LocalDate,

    val email: String? = null,

    val address: String? = null,

    val phoneNumber: String? = null,
)