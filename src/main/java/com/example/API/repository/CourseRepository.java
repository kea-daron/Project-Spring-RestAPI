package com.example.API.repository;

import com.example.API.domain.Course;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
@Getter
@Setter
public class CourseRepository {
    private List<Course> courses;
    public CourseRepository() {
        courses = new ArrayList<>();

        courses.add(Course.builder()
                .id(UUID.randomUUID().toString())
                .code("ISTAD-001")
                .title("Spring Framework")
                .price(650.00)
                .status(true)
                .build());
        courses.add(Course.builder()
                .id(UUID.randomUUID().toString())
                .code("ISTAD-002")
                .title("NextJS")
                .price(550.00)
                .status(false)
                .build());
        courses.add(Course.builder()
                .id(UUID.randomUUID().toString())
                .code("ISTAD-003")
                .title("Spring Boot")
                .price(250.00)
                .status(false)
                .build());
        courses.add(Course.builder()
                .id(UUID.randomUUID().toString())
                .code("ISTAD-004")
                .title("Spring Service")
                .price(300.00)
                .status(false)
                .build());
        courses.add(Course.builder()
                .id(UUID.randomUUID().toString())
                .code("ISTAD-005")
                .title("ReactJs")
                .price(100.00)
                .status(false)
                .build());
    }
}
