package ru.kpfu.itis.pita.form;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 4/26/17 9:46 PM
 *
 * Base interface for forms that store information of the specific entity (i.e. create or edit form).
 */
public interface InfoForm<E> {

    /**
     * Dumps form information into specified entity, replacing existing values
     * @param entity entity to dump information into
     */
    void toEntity(E entity);

    /**
     * Fills form fields from specified entity, replacing all form values.
     * Normally, this method should fill all the fields in the form
     * @param entity entity to load information from
     * @return {@code this }
     */
    InfoForm<E> fromEntity(E entity);
}
