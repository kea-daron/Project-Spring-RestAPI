package com.example.API.service;

import com.example.API.dto.CourseResponse;

import java.util.List;

public interface CourseService {

    /**
     * ទាញព័ត៏៏មានវគ្គសិក្សាទាំងអស់
     * @author Daron
     * @return List<CourseResponse>
     */
    List<CourseResponse> getCourses(Boolean status);

    List<CourseResponse> getCourses(Boolean status, String title);

    CourseResponse getCourseByCode(String code);

    CourseResponse getCourseById(String id);

}
