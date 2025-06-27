package crscube.restapi.highschool.controller

import crscube.restapi.highschool.data.req.LoginReq
import crscube.restapi.highschool.data.res.ApiResponse
import crscube.restapi.highschool.data.res.LoginRes
import crscube.restapi.highschool.infrastructure.jwt.JwtTokenProvider
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val jwtTokenProvider: JwtTokenProvider
) {

    @PostMapping("/login")
    fun login(@Valid @RequestBody req: LoginReq): ResponseEntity<ApiResponse<LoginRes>> {
        val token = jwtTokenProvider.generateToken(req.username, "STUDENT")
        val loginRes = LoginRes(
            token = token,
            username = req.username,
            role = "STUDENT"
        )
        return ResponseEntity.ok(
            ApiResponse(
                true,
                "로그인 성공",
                loginRes
            )
        )
    }
}