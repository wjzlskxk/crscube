package crscube.restapi.highschool.repository

import crscube.restapi.highschool.data.entity.User
import crscube.restapi.highschool.data.req.LoginReq
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findByUsername(username: String): LoginReq?
}