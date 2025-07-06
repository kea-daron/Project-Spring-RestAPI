package com.example.API.comtroller;

import com.example.API.dto.CourseResponse;
import com.example.API.dto.CreateCourseRequest;
import com.example.API.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // @Controller + @ResponseBody
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
public class CourseController {

    //Required dependency
    private final CourseService courseService;

    @DeleteMapping("/{code}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCourseByCode(@PathVariable String code) {
        courseService.deleteCourseByCode(code);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CourseResponse createCourse(@RequestBody CreateCourseRequest createCourseRequest) {
        return courseService.createCourse(createCourseRequest);
    }

    @GetMapping
    public List<CourseResponse> getCourses(@RequestParam(required = false, defaultValue = "true") Boolean status) {
        return courseService.getCourses(status);
    }

    @GetMapping("/search")
    public List<CourseResponse> searchByTitle(@RequestParam Boolean status, @RequestParam String title) {
        return courseService.getCourses(status, title);
    }

    @GetMapping("/by-code")
    public CourseResponse getByCode(@RequestParam String code) {
        return courseService.getCourseByCode(code);
    }

    @GetMapping("/by-id")
    public CourseResponse getById(@RequestParam String id) {
        return courseService.getCourseById(id);
    }

}
