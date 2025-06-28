package crscube.restapi.highschool.service

import crscube.restapi.highschool.data.req.LoginReq
import crscube.restapi.highschool.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val userRepository: UserRepository
) {
    fun saveUser(req: LoginReq) {
        userRepository.save(req.toEntity())
    }
}