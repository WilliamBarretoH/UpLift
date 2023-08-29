package com.lift.up.domain.repository

import com.lift.up.domain.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<User?, Long?> {

    //@Query("SELECT u FROM Users u WHERE u.userName =:userName")
    fun findByUserName(userName: String) : User?

    fun findByEmail(email: String) : Optional<User>?

    fun existsByEmail(email: String?): Boolean
}