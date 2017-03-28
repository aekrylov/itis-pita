package ru.kpfu.itis.pita.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.AbstractSecurityExpressionHandler;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyAuthoritiesMapper;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyUtils;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.GrantedAuthority;
import ru.kpfu.itis.pita.entity.UserRole;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 3/28/17 12:01 PM
 *
 * This config class creates authority hierarchy from {@link UserRole} enum
 * and registers it for every {@link DaoAuthenticationProvider} and {@link SecurityExpressionHandler} available
 */

@Configuration
public class SecurityConfig {

    @Autowired
    private List<AbstractUserDetailsAuthenticationProvider> authenticationProviders;

    @Autowired
    private Map<String, AbstractSecurityExpressionHandler<?>> handlers;

    // (authority) -> (implied authorities)
    private Map<String, List<String>> hierarchyMap = new HashMap<>();

    public SecurityConfig() {
        //for every role in UserRole, add corresponding entry to map
        hierarchyMap.putAll(Arrays.stream(UserRole.values())
                .filter(userRole -> userRole.getAuthorities().size() > 0)
                .collect(Collectors.toMap(UserRole::name, userRole -> {
                            return userRole.getAuthorities().stream()
                                    .map(GrantedAuthority::getAuthority)
                                    .collect(Collectors.toList());
                        })
                )
        );
    }

    @Bean(name = "roleHierarchy")
    public RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        roleHierarchy.setHierarchy(RoleHierarchyUtils.roleHierarchyFromMap(hierarchyMap));

        for (AbstractUserDetailsAuthenticationProvider authenticationProvider : authenticationProviders) {
            authenticationProvider
                    .setAuthoritiesMapper(new RoleHierarchyAuthoritiesMapper(
                            roleHierarchy));
        }

        for (Map.Entry<String, AbstractSecurityExpressionHandler<?>> handler : handlers
                .entrySet()) {
            handler.getValue().setRoleHierarchy(roleHierarchy);
        }
        return roleHierarchy;
    }


}
