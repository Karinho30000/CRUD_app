package com.hdstudents.hds.service;

import com.hdstudents.hds.dto.StudentDto;

import java.util.List;

public interface StudentService {
    StudentDto createStudent(StudentDto studentDto);

    StudentDto getStudentById(Long studentId);

    List<StudentDto> getAllStudents();

    StudentDto updateStudent(Long studentId, StudentDto updatedStudentDto);

    void deleteStudent(Long studentId);

    List<StudentDto> getStudentsByGroupNameAndLocation(String groupName, String locationName);

    List<StudentDto> getStudentsByLocationName(String locationName);
}
