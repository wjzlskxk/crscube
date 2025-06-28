package crscube.restapi.highschool.data.req

import crscube.restapi.highschool.data.entity.User
import jakarta.validation.constraints.NotBlank

data class LoginReq(
    val username: String,
    val password: String
) {
    fun toEntity(): User {
        return User(
            username = this.username,
            password = this.password
        )
    }
}
