package ru.kpfu.itis.pita.form;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 4/26/17 9:46 PM
 */
public interface InfoForm<E> {

    void toEntity(E entity);
    InfoForm<E> fromEntity(E entity);
}
