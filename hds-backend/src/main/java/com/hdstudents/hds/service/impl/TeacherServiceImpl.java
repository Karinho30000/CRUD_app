package com.hdstudents.hds.service.impl;

import com.hdstudents.hds.dto.TeacherDto;
import com.hdstudents.hds.entity.Teacher;
import com.hdstudents.hds.exception.ResourceNotFoundException;
import com.hdstudents.hds.mapper.TeacherMapper;
import com.hdstudents.hds.repository.TeacherRepository;
import com.hdstudents.hds.service.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private TeacherRepository teacherRepository;

    @Override
    public List<TeacherDto> getAllTeachers() {
        List<Teacher> teachers = teacherRepository.findAll();
        return teachers.stream()
                .map(TeacherMapper::mapToTeacherDto)
                .collect(Collectors.toList());
    }

    @Override
    public TeacherDto getTeacherById(Long id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found with id: " + id));
        return TeacherMapper.mapToTeacherDto(teacher);
    }

    @Override
    public List<TeacherDto> getTeacherByGroup(String groupName) {
        List<Teacher> teachers = teacherRepository.findByGroupsName(groupName);
        return teachers.stream().map(TeacherMapper::mapToTeacherDto).collect(Collectors.toList());
    }

    @Override
    public void deleteTeacherById(Long id) {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Teacher not found with id: " + id));
        teacherRepository.delete(teacher);
    }
}
