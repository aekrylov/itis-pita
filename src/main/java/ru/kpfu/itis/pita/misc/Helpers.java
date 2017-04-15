package ru.kpfu.itis.pita.misc;

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
}
