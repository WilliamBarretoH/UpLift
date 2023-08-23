package com.lift.up.domain.entity

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*

@Entity
@Table(name = "users")
class User : UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Column(name = "user_name", unique = true)
    var userName: String? = null

    @Column(name = "full_name")
    var fullName: String? = null

    @Column(name = "password")
    private var password: String? = null

    @Column(name = "account_non_expired")
    private var accountNonExpired: Boolean? = null

    @Column(name = "account_non_locked")
    private var accountNonLocked: Boolean? = null

    @Column(name = "credential_non_expired")
    private var credentialsNonExpired: Boolean? = null

    @Column(name = "enabled")
    private var enabled: Boolean? = null

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_permission",
        joinColumns = [JoinColumn(name = "id_user")],
        inverseJoinColumns = [JoinColumn(name = "id_permission")]
    )
    var permissions: List<Permission>? = null

    val role: List<String?>
        get() {
            val roles: MutableList<String?> = ArrayList()
            for(permission in permissions!!){
                roles.add(permission.description)
            }
            return roles
        }

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return permissions!!
    }

    override fun getPassword(): String {
        return password!!
    }

    override fun getUsername(): String {
        return userName!!
    }

    override fun isAccountNonExpired(): Boolean {
        return accountNonExpired!!
    }

    override fun isAccountNonLocked(): Boolean {
        return accountNonLocked!!
    }

    override fun isCredentialsNonExpired(): Boolean {
        return credentialsNonExpired!!
    }

    override fun isEnabled(): Boolean {
        return enabled!!
    }
}