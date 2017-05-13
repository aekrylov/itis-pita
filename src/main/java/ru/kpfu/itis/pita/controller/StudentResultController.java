package ru.kpfu.itis.pita.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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

            List<Subject> subjects = subjectService.findAllBySemesterNumberLessThanOrderBySemesterNumber(course*2+1);
            map.put("subjects",subjects);
            List<StudentScore> scores = studentScoreService.findAllByStudent(student);

            List<Student> students= new ArrayList<Student>();
            students.add(student);

            map.put("scores",getStudentWithScores(students, subjects, Helpers.getCurretStudyYear() - course, scores));
            map.put("student", student);
        } else  {
            return view(map);
        }
        return "student_result";
    }

    private ArrayList getStudentWithScores(List<Student> students, List<Subject> subjects ,int year,List<StudentScore> scores){

        //sort Student by name
        students.sort(Comparator.comparing(User::getName));

        //map of all subject
        HashMap<Subject,Score> subjectScore = new LinkedHashMap<>();
        subjects.stream()
                .forEach(subject -> subjectScore.put(subject,new Score()) );

        //map of all student with all subject
        HashMap<Student, Map<Subject,Score>> result = new LinkedHashMap<>();
        students.stream()
                .forEach( student -> result.put(student,(HashMap<Subject,Score>)subjectScore.clone()));

        //fill table
        scores.stream()
                .forEach(sc -> result.get(sc.getStudent()).get(sc.getSubject()).setScore(sc.getExamScore()+sc.getPraxisScore()));

        //set average score to students
        for (Map.Entry<Student, Map<Subject, Score>> entry : result.entrySet()) {
            double averageScore = entry.getValue().values().stream()
                    .collect(Collectors.averagingInt(Score::getScore));

            entry.getKey().setAverageScore(averageScore);
        }
        //sort Student by average_score
//        result.entrySet().stream()
//                .sorted(Comparator.comparingDouble(s -> s.getKey().getAverageScore()));

        //convert to arrayList because freemarker bad work with map
        ArrayList<Map.Entry> a = new ArrayList<>(result.entrySet());
        for (Object e :a){
            ((Map.Entry)e).setValue(((Map)((Map.Entry) e).getValue()).entrySet());
        }
        return a;
    }




}