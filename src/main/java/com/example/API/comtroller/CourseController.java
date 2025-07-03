package com.example.API.comtroller;

import com.example.API.dto.CourseResponse;
import com.example.API.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // @Controller + @ResponseBody
@RequestMapping("/api/vi/courses")
@RequiredArgsConstructor
public class CourseController {
    //Required dependency
    private final CourseService courseService;
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
