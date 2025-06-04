package com.hdstudents.hds.controller;

import com.hdstudents.hds.dto.TeacherDto;
import com.hdstudents.hds.service.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/teachers")
@CrossOrigin("*")
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping
    public ResponseEntity<List<TeacherDto>> getAllTeachers() {
        return ResponseEntity.ok(teacherService.getAllTeachers());
    }

    @GetMapping("{id}")
    public ResponseEntity<TeacherDto> getTeacherById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(teacherService.getTeacherById(id));
    }

    @GetMapping("/group/{groupName}")
    public ResponseEntity<List<TeacherDto>> getTeachersByGroupId(@PathVariable("groupName") String groupName) {
        return ResponseEntity.ok(teacherService.getTeacherByGroup(groupName));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTeacherById(@PathVariable("id") Long id) {
        teacherService.deleteTeacherById(id);
        return ResponseEntity.ok("Deleted teacher with id " + id);
    }
}
