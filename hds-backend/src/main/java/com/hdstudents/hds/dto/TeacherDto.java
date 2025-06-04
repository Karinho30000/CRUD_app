package com.hdstudents.hds.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDto {
    private long id;
    private String name;
    private List<GroupSummaryDto> groups;
}
