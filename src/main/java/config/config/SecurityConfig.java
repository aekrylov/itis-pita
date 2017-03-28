package config.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.AbstractSecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyAuthoritiesMapper;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

import java.util.List;
import java.util.Map;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 3/28/17 12:01 PM
 */

@Configuration
public class SecurityConfig {

    @Autowired
    private List<DaoAuthenticationProvider> authenticationProviders;

    @Autowired
    private Map<String, AbstractSecurityExpressionHandler<?>> handlers;

    @Bean(name = "roleHierarchy")
    public RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        String hierarchy = "ROLE_SUPERUSER > ROLE_DEAN\n" +
                "                ROLE_SUPERUSER > ROLE_WORKER\n" +
                "                ROLE_SUPERUSER > ROLE_STUDENT\n" +
                "                ROLE_DEAN > LAB_CREATE";
        roleHierarchy.setHierarchy(hierarchy);

        for (DaoAuthenticationProvider authenticationProvider : authenticationProviders) {
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
