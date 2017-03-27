package ru.kpfu.itis.pita.form;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 3/27/17 5:51 PM
 */

public class LabCreateForm {

    private String name;
    private String description;

    //private MultipartFile image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

/*
    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
*/
}
