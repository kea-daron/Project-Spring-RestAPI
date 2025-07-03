package com.example.API.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Course {
    private String id;
    private String code;
    private String title;
    private Double price;
    private Boolean status;
}
