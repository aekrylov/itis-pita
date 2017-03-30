package ru.kpfu.itis.pita.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import ru.kpfu.itis.pita.security.SecurityConfig;

import java.util.List;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 3/27/17 9:12 PM
 *
 * This enum contains all user roles that currently exist in the project.
 * Add roles and authorities here.
 * Constant names are used as Spring Security role names.
 * Note that role is just an authority with name prefixed with 'ROLE_'
 *
 * Spring Security role hierarchy is generated automatically on startup (see {@link SecurityConfig})
 */
public enum UserRole {

    ROLE_STUDENT(""),
    ROLE_WORKER(""),
    ROLE_DEAN("CREATE_LAB, CREATE_COURSE"),
    ROLE_SUPERUSER("ROLE_STUDENT, ROLE_WORKER, ROLE_DEAN");

    private List<GrantedAuthority> authorities;

    UserRole(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    /**
     * Construct a role by the list of implied roles and authorities
     * @param authorities Comma separated list of implied roles and authorities
     */
    UserRole(String authorities) {
        this(AuthorityUtils.commaSeparatedStringToAuthorityList(authorities));
    }

    public List<GrantedAuthority> getAuthorities() {
        return authorities;
    }
}
