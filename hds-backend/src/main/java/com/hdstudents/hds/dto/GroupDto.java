package com.hdstudents.hds.dto;

import com.hdstudents.hds.entity.Teacher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class GroupDto {
    private Long id;
    private String name;
    private List<String> teachers;
    private int studentCount;
    private String location;

}
