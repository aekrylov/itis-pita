package ru.kpfu.itis.pita.misc;

import org.springframework.security.core.context.SecurityContextHolder;
import ru.kpfu.itis.pita.entity.User;
import ru.kpfu.itis.pita.security.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 4/15/17 2:09 PM
 */
public class Helpers {

    public static <T> List<T> filterByType(Collection<? super T> collection, Class<T> type) {
        return collection.stream()
                .filter(item -> type.isAssignableFrom(item.getClass()))
                .map(item -> (T)item)
                .collect(Collectors.toList());
    }

    public static User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!(principal instanceof UserDetails)) {
            return null;
        }

        return ((UserDetails) principal).getUser();
    }
}
