package com.SpringSecurityPractice.demo.Teacher;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/teachers")
public class TeacherController {
    private static final List<Teacher> teachers = Arrays.asList(
      new Teacher("1", "basit"),
      new Teacher("2", "muhammad Ali")
    );

    @GetMapping
    public List<Teacher> getTeachers(){
        return teachers;
    }
    @GetMapping(path = "{teacherId}")
    public Teacher getTeacherById(@PathVariable("teacherId") String id){
        return
                teachers.stream()
                        .filter(teacher -> id.equals(teacher.getTeacherId()))
                        .findFirst()
                        .orElseThrow(()-> new IllegalStateException("Teacher with " + id + "does not found") );
    }


}
