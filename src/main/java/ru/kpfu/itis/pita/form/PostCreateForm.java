package ru.kpfu.itis.pita.form;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 4/21/17 2:16 PM
 */
public class PostCreateForm {

    @NotEmpty
    private String text;

    //todo images;

    private String videoLink;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }
}
