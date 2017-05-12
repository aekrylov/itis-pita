package ru.kpfu.itis.pita.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.pita.entity.*;
import ru.kpfu.itis.pita.misc.Helpers;
import ru.kpfu.itis.pita.service.StudentScoreService;
import ru.kpfu.itis.pita.service.StudentService;
import ru.kpfu.itis.pita.service.SubjectService;
import ru.kpfu.itis.pita.service.UserService;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by volkov on 09.05.2017.
 */
@Controller
@RequestMapping("/student_result")
@PreAuthorize("isFullyAuthenticated()")
public class StudentResultController {

    private StudentScoreService studentScoreService;
    private UserService userService;
    private SubjectService subjectService;
    private StudentService studentService;

    @Autowired
    public StudentResultController(StudentService studentService, StudentScoreService studentScoreService, UserService userService, SubjectService subjectService) {
        this.studentScoreService = studentScoreService;
        this.userService = userService;
        this.subjectService = subjectService;
        this.studentService = studentService;
    }

    @GetMapping("/course={course}")
    public String viewWithCourse(ModelMap map,@PathVariable int course) {
        List<Subject> subjects = subjectService.findAllBySemesterNumber(course*2-1);
        subjects.addAll(subjectService.findAllBySemesterNumber(course*2));

        List<Student> students = studentService.findAllByStartYear(Helpers.getCurretStudyYear()-course);

        List<StudentScore> scores =  studentScoreService.findAllBySemesterNumber(course*2-1);
        scores.addAll(studentScoreService.findAllBySemesterNumber(course*2));

        map.put("subjects",subjects);

        map.put("scores", getStudentWithScores(students,subjects,Helpers.getCurretStudyYear()-course,scores));
        System.out.println(subjects.toString());
        return "student_result";
    }
    @GetMapping("")
    public String view(ModelMap map) {
        return "redirect:/student_result/course=1";
    }

    @GetMapping("/my")
    public String myView(ModelMap map) {
        User user = Helpers.getCurrentUser();
        user = userService.findById(user.getId());//yjdjt
        if(user instanceof Student) {
            Student student = (Student)user;
            int course = Helpers.getCurretStudyYear()- student.getAcademicGroup().getStart_year();
            map.put("subjects",subjectService.findAllBySemesterNumberLessThanOrderBySemesterNumber(course+1));

            List<StudentScore> scores = studentScoreService.findAllByStudent(student);
            student.setAverageScore(
                    scores
                    .stream()
                    .collect(Collectors.averagingInt(p -> p.getExamScore()+p.getPraxisScore()))
            );
            map.put("scores",scores);
            map.put("student", student);
        } else  {
            return view(map);
        }
        return "student_result";
    }

    private ArrayList getStudentWithScores(List<Student> students, List<Subject> subjects ,int year,List<StudentScore> scores){

        //sort Student by name
        students.stream()
                .sorted((s1, s2) -> {
                    return s1.getName().compareTo(s2.getName());
                });

        //map of all subject
        LinkedHashMap<Subject,Score> subjectScore = new LinkedHashMap<>();
        subjects.stream()
                .forEach(subject -> subjectScore.put(subject,new Score()) );
        //map of all student with all subject
        LinkedHashMap<Student,LinkedHashMap<Subject,Score>> result = new LinkedHashMap<>();
        students.stream()
                .forEach( student -> result.put(student,(LinkedHashMap<Subject,Score>)subjectScore.clone()));

        //fill table
        scores.stream()
                .forEach(sc -> result.get(sc.getStudent()).get(sc.getSubject()).setScore(sc.getExamScore()+sc.getPraxisScore()));

        //set average score to students
        for (Map.Entry entry : result.entrySet()) {
            int sum = 0;
            int n = 0;
            for (Map.Entry entry2: ((LinkedHashMap<Subject,Score>)entry.getValue()).entrySet()){
                if(((Score)entry2.getValue()).getScore()!= -1) {
                    sum += ((Score) entry2.getValue()).getScore();
                    n++;
                }
            }
            if (n != 0) ((Student)entry.getKey()).setAverageScore(sum/n);
        }
        //sort Student by average_score
        result.entrySet().stream()
                .sorted((s1, s2) -> {
                return Double.compare(s1.getKey().getAverageScore(),s2.getKey().getAverageScore());
                });
        //convert to arrayList because freemarker bad work with map
        ArrayList a = new ArrayList(result.entrySet());
        for (Object e :a){
            ((Map.Entry)e).setValue(((LinkedHashMap)((Map.Entry) e).getValue()).entrySet());
        }
        return a;
    }




}