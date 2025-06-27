package crscube.restapi.highschool.service

import crscube.restapi.highschool.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
    private val userRepository: UserRepository
): UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByUsername(username) ?: throw UsernameNotFoundException("사용자를 찾을 수 없습니다.")

        return org.springframework.security.core.userdetails.User(
            user.username,
            user.password,
            listOf()
        )

    }
}