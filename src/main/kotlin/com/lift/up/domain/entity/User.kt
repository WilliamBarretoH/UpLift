package com.lift.up.domain.entity

import com.lift.up.domain.model.AuthProvider
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "users")
class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Column(name = "user_name", unique = true)
    var userName: String? = null

    @Column(name = "full_name")
    var fullName: String? = null

    @Column(name = "email")
    var email: String = ""

    @Column(name = "image_url")
    var imageUrl: String? = ""

    @Column(name = "password")
    var password: String? = null
        get() = field
        set(value) { field = value }

//    @Column(name = "account_non_expired")
//    private var accountNonExpired: Boolean? = null
//
//    @Column(name = "account_non_locked")
//    private var accountNonLocked: Boolean? = null
//
//    @Column(name = "credential_non_expired")
//    private var credentialsNonExpired: Boolean? = null
//
//    @Column(name = "enabled")
//    private var enabled: Boolean? = null

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_permission",
        joinColumns = [JoinColumn(name = "id_user")],
        inverseJoinColumns = [JoinColumn(name = "id_permission")]
    )
    var permissions: List<Permission>? = null

    @Enumerated(EnumType.STRING)
    var provider: @NotNull AuthProvider? = null

    var providerId: String? = null

    val role: List<String?>
        get() {
            val roles: MutableList<String?> = ArrayList()
            for(permission in permissions!!){
                roles.add(permission.description)
            }
            return roles
        }
}