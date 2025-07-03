package com.example.API.service.impl;

import com.example.API.domain.Course;
import com.example.API.dto.CourseResponse;
import com.example.API.repository.CourseRepository;
import com.example.API.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServerImpl implements CourseService {
    private final CourseRepository courseRepository;
    @Override
    public List<CourseResponse> getCourses(Boolean status) {

        // Filter
        List<Course> filteredCourses = courseRepository.getCourses()
                .stream()
                .filter(course -> course.getStatus().equals(status))
                .toList();

        // Map data from domain model to DTO
        List<CourseResponse> courseResponseList = filteredCourses
                .stream()
                .map(course -> CourseResponse.builder()
                        .code(course.getCode())
                        .title(course.getTitle())
                        .price(course.getPrice())
                        .status(course.getStatus())
                        .build())
                .toList();

        return courseResponseList;
    }

    @Override
    public List<CourseResponse> getCourses(Boolean status, String title) {
        List<Course> filteredCourses = courseRepository.getCourses()
                .stream()
                .filter(course -> course.getStatus().equals(status)
                        && course.getTitle().toLowerCase().contains(title.toLowerCase()))
                .toList();

        return filteredCourses.stream()
                .map(course -> CourseResponse.builder()
                        .code(course.getCode())
                        .title(course.getTitle())
                        .price(course.getPrice())
                        .status(course.getStatus())
                        .build())
                .toList();
    }

    @Override
    public CourseResponse getCourseByCode(String code) {
        return courseRepository.getCourses()
                .stream()
                .filter(course -> course.getCode().equalsIgnoreCase(code))
                .findFirst()
                .map(course -> CourseResponse.builder()
                        .code(course.getCode())
                        .title(course.getTitle())
                        .price(course.getPrice())
                        .status(course.getStatus())
                        .build())
                .orElse(null);
    }

    @Override
    public CourseResponse getCourseById(String id) {
        return courseRepository.getCourses()
                .stream()
                .filter(course -> course.getId().equals(id))
                .findFirst()
                .map(course -> CourseResponse.builder()
                        .code(course.getCode())
                        .title(course.getTitle())
                        .price(course.getPrice())
                        .status(course.getStatus())
                        .build())
                .orElse(null);
    }
}
