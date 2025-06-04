package com.hdstudents.hds.service;

import com.hdstudents.hds.dto.TeacherDto;
import com.hdstudents.hds.entity.Teacher;

import java.util.List;

public interface TeacherService {
    List<TeacherDto> getAllTeachers();
    TeacherDto getTeacherById(Long id);
    List<TeacherDto> getTeacherByGroup(String groupName);
    void deleteTeacherById(Long id);
}
