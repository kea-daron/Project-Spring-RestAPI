package com.example.API.service;

import com.example.API.dto.CourseResponse;
import com.example.API.dto.CreateCourseRequest;

import java.util.List;

public interface CourseService {

    //return type
    //data (parameter)
    // use for ?

    /**
     * លុបវគ្គសិក្សាតាមរយះ course code
     * delete success response 204
     * if code doesn't exist response 404, "Course code doesn't exist"
     * @param code of course
     */
    void deleteCourseByCode(String code);



    /**
     * បង្កើតទិន្នន័យវគ្គសិក្សាថ្មី
     * @param createCourseRequest ទិន្នន័យពី client
     * @return CourseResponse
     * @author KEA DARON
     */
    CourseResponse createCourse(CreateCourseRequest createCourseRequest);



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
