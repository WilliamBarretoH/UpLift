package com.lift.up.api.resource

import com.lift.up.api.payload.ApiResponse
import com.lift.up.api.payload.AuthResponse
import com.lift.up.api.payload.LoginRequest
import com.lift.up.api.payload.SignUpRequest
import com.lift.up.domain.entity.User
import com.lift.up.domain.model.AuthProvider
import com.lift.up.domain.repository.UserRepository
import com.lift.up.exception.BadRequestException
import com.lift.up.security.TokenProvider
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import javax.validation.Valid


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
class AuthController(
    private val authenticationManager: AuthenticationManager,
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val tokenProvider: TokenProvider
) {

    @PostMapping("/login")
    fun authenticateUser(@RequestBody loginRequest: @Valid LoginRequest?): ResponseEntity<*> {
        val authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                loginRequest?.email,
                loginRequest?.password
            )
        )
        SecurityContextHolder.getContext().authentication = authentication
        val token: String = tokenProvider.createToken(authentication)
        return ResponseEntity.ok<Any>(AuthResponse(token))
    }

    @PostMapping("/signup")
    fun registerUser(@RequestBody signUpRequest: @Valid SignUpRequest?): ResponseEntity<*> {
        if (userRepository.existsByEmail(signUpRequest?.email)) {
            throw BadRequestException("Email address already in use.")
        }

        // Creating user's account
        val user = User()
        user.fullName = (signUpRequest?.name)
        user.email = (signUpRequest?.email.toString())
        user.password = (signUpRequest?.password)
        user.provider = (AuthProvider.local)
        user.password = (passwordEncoder.encode(user.password))
        val result: User = userRepository.save(user)
        val location = ServletUriComponentsBuilder
            .fromCurrentContextPath().path("/user/me")
            .buildAndExpand(result.id).toUri()
        return ResponseEntity.created(location)
            .body<Any>(ApiResponse(true, "User registered successfully@"))
    }
}
