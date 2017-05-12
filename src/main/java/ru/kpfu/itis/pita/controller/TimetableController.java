package ru.kpfu.itis.pita.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.pita.entity.*;
import ru.kpfu.itis.pita.form.TimetableClassForm;
import ru.kpfu.itis.pita.form.TimetableClassSimpleForm;
import ru.kpfu.itis.pita.misc.Helpers;
import ru.kpfu.itis.pita.misc.LessonStartTime;
import ru.kpfu.itis.pita.service.SemesterService;
import ru.kpfu.itis.pita.service.SubjectService;
import ru.kpfu.itis.pita.service.TimetableService;
import ru.kpfu.itis.pita.service.UserService;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 5/2/17 4:46 PM
 */
@Controller
@RequestMapping("/timetable")
public class TimetableController {

    private TimetableService timetableService;
    private UserService userService;
    private SubjectService subjectService;
    private SemesterService semesterService;

    @ModelAttribute("form")
    public TimetableClassForm classCreateForm() {
        return new TimetableClassForm();
    }

    @ModelAttribute("simpleForm")
    public TimetableClassSimpleForm classSimpleForm() {
        return new TimetableClassSimpleForm();
    }

    @ModelAttribute("daysOfWeek")
    public DayOfWeek[] daysOfWeek() {
        return DayOfWeek.values();
    }

    @ModelAttribute("lessonTimes")
    public List<String> lessonTimes() {
        return Arrays.stream(LessonStartTime.values()).map(LessonStartTime::getString).collect(Collectors.toList());
    }

    @Autowired
    public TimetableController(TimetableService timetableService, UserService userService, SubjectService subjectService, SemesterService semesterService) {
        this.timetableService = timetableService;
        this.userService = userService;
        this.subjectService = subjectService;
        this.semesterService = semesterService;
    }

    @GetMapping("/my")
    @ResponseBody
    @PreAuthorize("isFullyAuthenticated()")
    public Map viewMine() {
        User user = Helpers.getCurrentUser();
        List<TimetableClass> classes;
        if(user instanceof Student) {
            //todo
            classes = timetableService.findAllByAcademicGroup(((Student) user).getAcademicGroup());
        } else if(user.getRole() == UserRole.ROLE_WORKER) {
            //todo
            classes = timetableService.findAllByTeacher(user);
        } else {
            classes = timetableService.findAll();
        }

        List<TimetableDate> dates = classes.stream()
                .flatMap(c -> c.getDates().stream())
                .collect(Collectors.toList());

        Map<String, Object> map = new HashMap<>();
        map.put("dates", dates);
        return map;
    }

    @GetMapping("/create2")
    public String newClassGet() {
        return "timetable_newclass";
    }

    @PostMapping("/create2")
    public String newClassPost(@ModelAttribute("form") TimetableClassForm form, BindingResult result) {
        TimetableClass timetableClass = new TimetableClass();
        fillEntity(timetableClass, form);

        timetableService.saveClass(timetableClass, form.getGroups());
        return "redirect:/timetable/create2";
    }

    @GetMapping("/{id}/edit")
    public String editClassGet(@PathVariable("id") int id, ModelMap map) {
        map.put("form", getForm(timetableService.findOne(id)));
        //todo
        return "timetable_newclass";
    }

    @PostMapping("/{id}/edit")
    public String editClassPost(@ModelAttribute("form") TimetableClassForm form, BindingResult result,
                                @PathVariable("id") int id) {
        TimetableClass timetableClass = timetableService.findOne(id);
        fillEntity(timetableClass, form);

        timetableService.saveClass(timetableClass, form.getGroups());

        //todo
        return "redirect:/timetable";
    }

    @GetMapping("/create")
    public String createSimpleGet() {
        return "timetable_simple_newclass";
    }

    @PostMapping("/create")
    public String createSimplePost(@ModelAttribute("simpleForm") TimetableClassSimpleForm form, BindingResult result) {
        Subject subject = subjectService.findCurrentByName(form.getSubject());
        User teacher = userService.findById(form.getTeacherId());

        Semester semester = semesterService.getCurrentSemester();

        TimetableClass timetableClass = new TimetableClass();
        timetableClass.setSubject(subject);
        timetableClass.setTeacher(teacher);

        //genearate dates
        Set<TimetableDate> dates = new TreeSet<>();
        for(DayOfWeek dow: form.getDaysOfWeek()) {
            LocalDate startDate = LocalDate.from(dow.adjustInto(semester.getStartDate().plusWeeks(1)));
            for(LocalDate date = startDate; date.isBefore(semester.getEndDate()); date = date.plusDays(7)) {
                TimetableDate timetableDate = new TimetableDate();
                timetableDate.setStartTime(date.atTime(form.getTimeStart()));
                timetableDate.setPlace(form.getPlace());
                timetableDate.setTimetableClass(timetableClass);
                dates.add(timetableDate);
            }
        }

        timetableClass.setDates(dates);
        timetableService.saveClass(timetableClass, form.getGroups());

        return "timetable_simple_newclass";
    }

    private void fillEntity(TimetableClass entity, TimetableClassForm form) {
        entity.setTeacher(userService.findById(form.getTeacherId()));
        entity.setSubject(subjectService.findCurrentByName(form.getSubject()));

        entity.getDates().clear();
        for (int i = 0; i < form.getStartTimes().size(); i++) {
            LocalDateTime startTime = form.getStartTimes().get(i);
            String place = form.getPlaces().get(i);
            if(startTime == null || place == null)
                continue;

            TimetableDate newDate = new TimetableDate(entity, startTime, place);
            entity.getDates().add(newDate);
        }
    }

    private TimetableClassForm getForm(TimetableClass entity) {
        TimetableClassForm form = new TimetableClassForm();
        form.setSubject(entity.getSubject().getName());
        form.setTeacherId(entity.getTeacher().getId());
        form.setGroups(entity.getGroups().stream().map(AcademicGroup::getName).collect(Collectors.toList()));

        for (TimetableDate date: entity.getDates()) {
            form.getStartTimes().add(date.getStartTime());
            form.getPlaces().add(date.getPlace());
        }

        return form;
    }

}
