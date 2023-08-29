package com.lift.up.security

import com.lift.up.config.AppProperties
import io.jsonwebtoken.*
import lombok.RequiredArgsConstructor
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import java.util.*


@Service
@RequiredArgsConstructor
class TokenProvider(@Autowired val appProperties: AppProperties) {


    fun createToken(authentication: Authentication): String {
        val userPrincipal = authentication.principal as UserPrincipal
        val now = Date()
        val expiryDate: Date = Date(now.time + appProperties.auth.tokenExpirationMsec)
        return Jwts.builder()
            .setSubject(userPrincipal.id.toString())
            .setIssuedAt(Date())
            .setExpiration(expiryDate)
            .signWith(SignatureAlgorithm.HS512, appProperties.auth.tokenSecret)
            .compact()
    }

    fun getUserIdFromToken(token: String?): Long {
        val claims: Claims = Jwts.parser()
            .setSigningKey(appProperties.auth.tokenSecret)
            .parseClaimsJws(token)
            .body
        return claims.subject.toLong()
    }

    fun validateToken(authToken: String?): Boolean {
        try {
            Jwts.parser().setSigningKey(appProperties.auth.tokenSecret).parseClaimsJws(authToken)
            return true
        } catch (ex: SignatureException) {
            logger.error("Invalid JWT signature")
        } catch (ex: MalformedJwtException) {
            logger.error("Invalid JWT token")
        } catch (ex: ExpiredJwtException) {
            logger.error("Expired JWT token")
        } catch (ex: UnsupportedJwtException) {
            logger.error("Unsupported JWT token")
        } catch (ex: IllegalArgumentException) {
            logger.error("JWT claims string is empty.")
        }
        return false
    }

    companion object {
        private val logger = LoggerFactory.getLogger(TokenProvider::class.java)
    }
}

