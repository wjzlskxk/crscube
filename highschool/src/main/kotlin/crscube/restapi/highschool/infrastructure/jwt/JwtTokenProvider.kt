package crscube.restapi.highschool.infrastructure.jwt

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.JwtException
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

import java.security.Key
import java.util.Date

@Component
class JwtTokenProvider {

    @Value("\${jwt.secret}")
    private lateinit var secretKey: String

    @Value("\${jwt.expiration}")
    private val expiration: Long = 3600000

    private val key: Key by lazy { Keys.hmacShaKeyFor(secretKey.toByteArray()) }

    fun generateToken(username: String, role: String): String {
        val now = Date()
        val expiryDate = Date(now.time + expiration)

        return Jwts.builder()
            .setSubject(username)
            .claim("role", role)
            .setIssuedAt(now)
            .setExpiration(expiryDate)
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()
    }

    fun getUsernameFromToken(token: String): String {
        val claims = Jwts.parser()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .body
        return claims.subject
    }

    fun getRoleFromToken(token: String): String {
        val claims = Jwts.parser()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .body
        return claims["role"] as String
    }

    fun validateToken(token: String): Boolean {
        try {
            Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
            return true
        } catch (e: JwtException) {
            return false
        } catch (e: IllegalArgumentException) {
            return false
        }
    }
}