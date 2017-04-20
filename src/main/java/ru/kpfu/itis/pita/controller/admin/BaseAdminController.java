package ru.kpfu.itis.pita.controller.admin;

import org.springframework.data.repository.CrudRepository;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 4/14/17 9:25 AM
 *
 * Base class for admin controllers. It handles all CRUD operations.
 * Subclasses must declare class base url, and override template methods if needed
 *
 * {@link #preSave(T)}
 */
public abstract class BaseAdminController<T> {
    //todo nested entities

    protected CrudRepository<T, Integer> repository;
    private List<String> fields;

    protected BaseAdminController(CrudRepository<T, Integer> repository) {
        this.repository = repository;
    }

    @GetMapping(path = "/create")
    public String createGet() {
        return "admin_edit";
    }

    @GetMapping(path = "/")
    public String listAll(ModelMap map) {
        List<T> entries = getList();

        map.put("entries", entries);

        return "admin_list";
    }

    @GetMapping(path = "/edit")
    public String updateGet(@RequestParam int id, ModelMap map) {
        map.put("entry", repository.findOne(id));
        return "admin_edit";
    }

    @PostMapping(path = "/edit")
    public String updatePost(@ModelAttribute T entry) {
        preSave(entry);
        repository.save(entry);
        return "redirect:./";
    }

    @RequestMapping(path = "/delete")
    public String delete(@RequestParam int id) {
        repository.delete(id);
        return "redirect:./";
    }

    /**
     * Method called right before passing updated entry to repository.
     * Child classes should override this to implement pre saving functionality (e.g. password hashing)
     * @param entry DB entry to be saved
     */
    protected void preSave(T entry) { }

    protected List<T> getList() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    /**
     * Fetches all singular fields of parameter class using reflection
     * @return all the fields of type parameter class that are not iterable
     */
    @ModelAttribute("fields")
    protected List<String> getFields() {
        //fields don't change at runtime, so perform lazy loading
        if(fields == null) {
            @SuppressWarnings("unchecked")
            Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            Field[] fieldsArray = clazz.getDeclaredFields();

            fields = Arrays.stream(fieldsArray)
                    .filter(field -> !Iterable.class.isAssignableFrom(field.getType()))
                    .map(Field::getName)
                    .collect(Collectors.toList());
        }
        return fields;
    }
}
