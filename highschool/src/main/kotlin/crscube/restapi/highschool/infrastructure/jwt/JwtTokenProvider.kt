package crscube.restapi.highschool.infrastructure.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts.claims
import io.jsonwebtoken.io.Decoders
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import jakarta.annotation.PostConstruct
import org.apache.tomcat.util.net.openssl.ciphers.Authentication

import java.security.Key
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.util.Base64
import java.util.Date

@Component
class JwtTokenProvider(
    @Value("\${jwt.secret}") private val secretKey: String,
    @Value("\${jwt.expiration}") private val expiration: Long = 3600000
) {

    private val key: Key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey))

    fun generateToken(username: String, role: String): String {
        val claims = Jwts.claims() as Claims
        claims.put("role", role)
        claims.put("username", username)

        val now = ZonedDateTime.now()
        val expiryDate = now.plusSeconds(expiration)

        println(Decoders.BASE64.decode(secretKey).size)

        return Jwts.builder()
            .setClaims(claims)
            .setSubject(username)
            .setIssuedAt(Date.from(now.toInstant()))
            .setExpiration(Date.from(expiryDate.toInstant()))
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()

    }

    fun parseClaims(token: String): Claims {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody()
    }

    fun validateToken(token: String): Boolean {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token)
            return true
        } catch (e: JwtException) {
            return false
        } catch (e: IllegalArgumentException) {
            return false
        }
    }
}