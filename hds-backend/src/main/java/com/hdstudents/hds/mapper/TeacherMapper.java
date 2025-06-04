package com.hdstudents.hds.mapper;

import com.hdstudents.hds.dto.GroupSummaryDto;
import com.hdstudents.hds.dto.TeacherDto;
import com.hdstudents.hds.entity.Groups;
import com.hdstudents.hds.entity.Teacher;

import java.util.List;
import java.util.stream.Collectors;

public class TeacherMapper {

    public static TeacherDto mapToTeacherDto(Teacher teacher) {
        List<GroupSummaryDto> groupSummaries = teacher.getGroups().stream()
                .map(TeacherMapper::mapToGroupSummary)
                .collect(Collectors.toList());

        return new TeacherDto(
                teacher.getId(),
                teacher.getName(),
                groupSummaries
        );
    }

    private static GroupSummaryDto mapToGroupSummary(Groups group) {
        return new GroupSummaryDto(
                group.getId(),
                group.getName(),
                group.getStudents() != null ? group.getStudents().size() : 0
        );
    }
}
