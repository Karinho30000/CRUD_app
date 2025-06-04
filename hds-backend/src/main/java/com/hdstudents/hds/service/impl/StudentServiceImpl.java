package com.hdstudents.hds.service.impl;

import com.hdstudents.hds.dto.StudentDto;
import com.hdstudents.hds.entity.Groups;
import com.hdstudents.hds.entity.Students;
import com.hdstudents.hds.entity.Teacher;
import com.hdstudents.hds.exception.ResourceNotFoundException;
import com.hdstudents.hds.mapper.StudentMapper;
import com.hdstudents.hds.repository.GroupRepository;
import com.hdstudents.hds.repository.StudentRepository;
import com.hdstudents.hds.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;
    private GroupRepository groupRepository;

    @Override
    public StudentDto createStudent(StudentDto studentDto) {

        Students student = StudentMapper.mapToStudents(studentDto);
        student.setId(null);

        Groups group = groupRepository.findByNameAndLocationName(studentDto.getGroup(), studentDto.getLocation())
                .orElseThrow(() -> new RuntimeException("Group not found for name=" + studentDto.getGroup() + " and location=" + studentDto.getLocation()));

        Teacher matchedTeacher = group.getTeachers().stream()
                        .filter(t -> t.getName().equals(studentDto.getTeacher()))
                .findFirst().orElseThrow(() -> new RuntimeException("Teacher not found"));

        student.setGroup(group);
        student.setTeacher(matchedTeacher);

        Students savedStudent = studentRepository.save(student);
        return StudentMapper.mapToStudentDto(savedStudent);
    }

    @Override
    public StudentDto getStudentById(Long studentId) {
        Students student = studentRepository.findById(studentId).
                orElseThrow(() -> new ResourceNotFoundException("Student with id " + studentId + " not found"));

        return StudentMapper.mapToStudentDto(student);
    }

    @Override
    public List<StudentDto> getAllStudents() {
        List<Students> students = studentRepository.findAll();
        return students.stream().map(StudentMapper::mapToStudentDto).collect(Collectors.toList());
    }

    @Override
    public StudentDto updateStudent(Long studentId, StudentDto updatedStudentDto) {
        Students student = studentRepository.findById(studentId).orElseThrow(() -> new ResourceNotFoundException("Student with id " + studentId + " not found"));
        student.setFirstName(updatedStudentDto.getFirstName());
        student.setLastName(updatedStudentDto.getLastName());
        Groups group = groupRepository.findByNameAndLocationName(updatedStudentDto.getGroup(), updatedStudentDto.getLocation())
                .orElseThrow(() -> new RuntimeException("Group not found with name"));
        Teacher matchedTeacher = group.getTeachers().stream()
                        .filter(t -> t.getName().equals(updatedStudentDto.getTeacher()))
                .findFirst().orElseThrow(() -> new RuntimeException("Teacher not found"));
        student.setGroup(group);
        student.setTeacher(matchedTeacher);
        Students updatedStudent = studentRepository.save(student);

        return StudentMapper.mapToStudentDto(updatedStudent);
    }

    @Override
    public void deleteStudent(Long studentId) {
        Students student = studentRepository.findById(studentId).orElseThrow(() -> new ResourceNotFoundException("Student with id " + studentId + " not found"));
        studentRepository.deleteById(studentId);
    }

    @Override
    public List<StudentDto> getStudentsByGroupNameAndLocation(String groupName, String locationName) {
        List<Students> students = studentRepository.findByGroupNameAndLocationName(groupName, locationName);
        return students.stream().map(StudentMapper::mapToStudentDto).collect(Collectors.toList());
    }

    @Override
    public List<StudentDto> getStudentsByLocationName(String locationName) {
        List<Students> students = studentRepository.findByGroupLocationName(locationName);
        return students.stream().map(StudentMapper::mapToStudentDto).collect(Collectors.toList());
    }
}
