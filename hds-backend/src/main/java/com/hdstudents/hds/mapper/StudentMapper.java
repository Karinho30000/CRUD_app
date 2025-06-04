package com.hdstudents.hds.mapper;

import com.hdstudents.hds.dto.StudentDto;
import com.hdstudents.hds.entity.Groups;
import com.hdstudents.hds.entity.Students;
import com.hdstudents.hds.entity.Teacher;

public class StudentMapper {

    public static StudentDto mapToStudentDto(Students student) {
        return new StudentDto(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getGroup() != null ? student.getGroup().getName() : null,
                student.getGroup() != null && student.getGroup().getLocation() != null ?
                        student.getGroup().getLocation().getName() : null,
                student.getTeacher().getName()
        );
    }

    public static Students mapToStudents(StudentDto studentDto) {
        return new Students(
                studentDto.getId(),
                studentDto.getFirstName(),
                studentDto.getLastName(),
                null,
                null
        );
    }
}
