package com.learning.youtube.LearningRestApis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {


    private long id;
    private String name;
    private String email;

//    public StudentDto(long id, String name, String email) {
//        this.id = id;
//        this.name = name;
//        this.email = email;
//    }
}
