package com.hdstudents.hds.mapper;

import com.hdstudents.hds.dto.GroupDto;
import com.hdstudents.hds.dto.TeacherDto;
import com.hdstudents.hds.entity.Groups;
import com.hdstudents.hds.entity.Location;
import com.hdstudents.hds.entity.Teacher;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class GroupMapper {

    public static GroupDto mapToGroupDto(Groups group){
        return new GroupDto(
                group.getId(),
                group.getName(),
                group.getTeachers().stream().map(Teacher::getName).collect(Collectors.toList()),
                group.getStudents() != null ? group.getStudents().size() : 0,
                group.getLocation() != null ? group.getLocation().getName() : null
        );
    }

    public static Groups mapToGroup(GroupDto groupDto, Location location, Set<Teacher> teachers){
        return new Groups(
                groupDto.getId(),
                groupDto.getName(),
                teachers,
                location
        );
    }
}
