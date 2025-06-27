package crscube.restapi.highschool.data.res

data class LoginRes(
    val token: String,
    val username: String,
    val role: String
)
