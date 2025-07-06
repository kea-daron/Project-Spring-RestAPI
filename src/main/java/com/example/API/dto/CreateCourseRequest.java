package com.example.API.dto;

import lombok.Builder;

@Builder
public record CreateCourseRequest(
        String code,
        String title,
        Double price
) {
}
