package com.hdstudents.hds.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class StudentDto {
    private long id;
    private String firstName;
    private String lastName;
    private String group;
    private String location;
    private String teacher;
}
