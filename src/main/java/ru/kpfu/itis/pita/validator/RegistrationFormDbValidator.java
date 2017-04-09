package ru.kpfu.itis.pita.validator;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.kpfu.itis.pita.form.RegistrationForm;
import ru.kpfu.itis.pita.service.UserService;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 4/9/17 12:47 PM
 */
@Component
public class RegistrationFormDbValidator implements Validator {

    private UserService userService;

    @Autowired
    public RegistrationFormDbValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return RegistrationForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RegistrationForm form = (RegistrationForm) target;

        //do nothing if already invalid
/*        if(errors.hasErrors()) {
            return;
        }*/

        //check email unique constraint
        if(userService.findByEmail(form.getEmail()) != null) {
            errors.rejectValue("email", "Duplicate.userForm.username");
        }
    }
}
