package com.lift.up.api.resource

import com.lift.up.domain.entity.User
import com.lift.up.domain.repository.UserRepository
import com.lift.up.exception.ResourceNotFoundException
import com.lift.up.security.CurrentUser
import com.lift.up.security.UserPrincipal
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*


@RestController
class UserController {
    @Autowired
    private val userRepository: UserRepository? = null
    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    fun getCurrentUser(@CurrentUser userPrincipal: UserPrincipal): Optional<User?>? {
        return userRepository?.findById(userPrincipal.id)
    }
}
