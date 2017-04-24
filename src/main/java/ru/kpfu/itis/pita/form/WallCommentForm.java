package ru.kpfu.itis.pita.form;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 4/23/17 7:24 PM
 */
public class WallCommentForm {

    @NotEmpty
    private String text;

    //todo image

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
