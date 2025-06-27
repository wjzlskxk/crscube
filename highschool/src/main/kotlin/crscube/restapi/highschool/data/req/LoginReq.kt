package crscube.restapi.highschool.data.req

import jakarta.validation.constraints.NotBlank

data class LoginReq(
    @NotBlank
    val username: String,

    @NotBlank
    val password: String
)
