package com.example.API.service.impl;

import com.example.API.domain.Course;
import com.example.API.dto.CourseResponse;
import com.example.API.dto.CreateCourseRequest;
import com.example.API.repository.CourseRepository;
import com.example.API.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

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

    @Override
    public void deleteCourseByCode(String code) {
        List<Course> courses = courseRepository.getCourses();

        boolean isRemoved = courses.removeIf(course -> course.getCode().equalsIgnoreCase(code));

        if (!isRemoved) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Course code doesn't exist"
            );
        }
    }

    @Override
    public CourseResponse createCourse(CreateCourseRequest createCourseRequest) {

        // Validate course code
        boolean isCourseCodeExisted = courseRepository.getCourses()
                .stream()
                .anyMatch(course ->course.getCode().equals(createCourseRequest.code()));
        if (isCourseCodeExisted) {
            // conflict
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Course code already exists"
            );
        }
        // Map dto to domain model
        Course course = Course.builder()
                .id(UUID.randomUUID().toString())
                .code(createCourseRequest.code())
                .title(createCourseRequest.title())
                .price(createCourseRequest.price())
                .status(false) // business logic
                .build();

        courseRepository.getCourses().add(course);

        // Return : Map from domain model to dto
        return CourseResponse.builder()
                .code(course.getCode())
                .title(course.getTitle())
                .price(course.getPrice())
                .status(course.getStatus())
                .build();
    }
}
