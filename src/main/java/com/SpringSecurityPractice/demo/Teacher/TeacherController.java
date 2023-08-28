package com.SpringSecurityPractice.demo.Teacher;


import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/teachers")
public class TeacherController {
    private static final List<Teacher> teachers = Arrays.asList(
      new Teacher("1", "basit"),
      new Teacher("2", "muhammad Ali")
    );

    @PostMapping
    public void AddTeacher(@RequestBody Teacher teacher){
        System.out.println("Adding teacher " + teacher);

    }
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
