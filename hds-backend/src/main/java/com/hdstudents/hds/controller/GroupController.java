package com.hdstudents.hds.controller;


import com.hdstudents.hds.dto.GroupDto;
import com.hdstudents.hds.dto.StudentDto;
import com.hdstudents.hds.entity.Students;
import com.hdstudents.hds.service.GroupService;
import com.hdstudents.hds.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/groups")

public class GroupController {

    private GroupService groupService;
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<List<GroupDto>> getAllGroups() {
        List<GroupDto> groups = groupService.getAllGroups();
        return ResponseEntity.ok(groups);
    }

    @PostMapping
    public ResponseEntity<GroupDto> createGroup(@RequestBody GroupDto groupDto) {
        GroupDto savedGroup = groupService.createGroup(groupDto);
        return new ResponseEntity<>(savedGroup, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<GroupDto> updateGroup(@PathVariable("id") Long groupId, @RequestBody GroupDto groupDto) {
        GroupDto updatedGroup = groupService.updateGroup(groupId, groupDto);
        return ResponseEntity.ok(updatedGroup);
    }

    @GetMapping("{name}")
    public ResponseEntity<GroupDto> getGroupByName(@PathVariable("name") String groupName) {
        GroupDto groupDto = groupService.getGroupByName(groupName);
        return ResponseEntity.ok(groupDto);
    }
}
