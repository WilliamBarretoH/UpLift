package com.lift.up.domain.repository

import com.lift.up.domain.entity.Category
import com.lift.up.domain.entity.Exercise
import com.lift.up.domain.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User?, Long?> {

    @Query("SELECT u FROM User u WHERE u.userName =:userName")
    fun findByUserName(@Param("userName") userName: String?) : User?
}