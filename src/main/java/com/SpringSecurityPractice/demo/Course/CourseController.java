package com.SpringSecurityPractice.demo.Course;

import com.SpringSecurityPractice.demo.Teacher.Teacher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/course/api/v1/courses")
public class CourseController {

    private final List<Course>courses = Arrays.asList(
            new Course("1", "maths"),
            new Course("2", "english")
    );

    @GetMapping
    public List<Course> getCourses(){
        return courses;
    }
    @GetMapping(path = "{courseId}")
    public Course getCourseById(@PathVariable("courseId") String id){
        return courses
                .stream()
                .filter(course -> id.equals(course.getCourseId()))
                .findFirst()
                .orElseThrow(()-> new IllegalStateException("Course with " + id + "does not found"));
    }

}
