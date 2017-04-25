package ru.kpfu.itis.pita.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.kpfu.itis.pita.misc.EntityNotFoundException;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 4/25/17 12:06 PM
 */
@ControllerAdvice
public class ErrorsController {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleGlobal404() {
        return "404";
    }
}
