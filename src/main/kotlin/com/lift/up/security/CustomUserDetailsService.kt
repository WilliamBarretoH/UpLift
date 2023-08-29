package com.lift.up.security

import com.lift.up.domain.entity.User
import com.lift.up.domain.repository.UserRepository
import com.lift.up.exception.ResourceNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*


@Service
class CustomUserDetailsService : UserDetailsService {
    @Autowired
    var userRepository: UserRepository? = null

    @Transactional
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(email: String): UserDetails {
        val user: Optional<User> = userRepository?.findByEmail(email) ?: throw UsernameNotFoundException("User not found with email : $email")
        return UserPrincipal.create(user.get())
    }

    @Transactional
    fun loadUserById(id: Long): UserDetails {
        val user: Optional<User?> = userRepository?.findById(id) ?: throw ResourceNotFoundException("User", "id", id)
        return UserPrincipal.create(user.get())
    }
}