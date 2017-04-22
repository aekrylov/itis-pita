package ru.kpfu.itis.pita.misc;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 4/17/17 11:30 AM
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException {

    private int entityId;

    public EntityNotFoundException(int entityId) {
        this.entityId = entityId;
    }
}
