package ru.kpfu.itis.pita.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 4/21/17 6:42 PM
 *
 * Handles all permission evaluators
 */
@Component
public class GlobalPermissionEvaluator implements PermissionEvaluator {

    private List<AbstractPermissionEvaluator> evaluators;

    @Autowired
    public GlobalPermissionEvaluator(List<AbstractPermissionEvaluator> evaluators) {
        this.evaluators = evaluators;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        return evaluators.stream()
                .anyMatch(evaluator -> evaluator.hasPermission(authentication, targetDomainObject, permission));
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return evaluators.stream()
                .anyMatch(evaluator -> evaluator.hasPermission(authentication, targetId, targetType, permission));
    }
}
