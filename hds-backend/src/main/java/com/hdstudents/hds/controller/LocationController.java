package com.hdstudents.hds.controller;

import com.hdstudents.hds.dto.GroupDto;
import com.hdstudents.hds.dto.LocationDto;
import com.hdstudents.hds.dto.StudentDto;
import com.hdstudents.hds.service.GroupService;
import com.hdstudents.hds.service.LocationService;
import com.hdstudents.hds.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/locations")
public class LocationController {
    private LocationService locationService;
    private GroupService groupService;
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<List<LocationDto>> getAllLocations() {
        List<LocationDto> locations = locationService.getAllLocations();
        return ResponseEntity.ok(locations);
    }

    @PostMapping
    public ResponseEntity<LocationDto> createLocation(@RequestBody LocationDto locationDto) {
        LocationDto savedLocation = locationService.createLocation(locationDto);
        return new ResponseEntity<>(savedLocation, HttpStatus.CREATED);
    }

    @GetMapping("/{locationName}/groups")
    public ResponseEntity<List<GroupDto>> getGroupsByLocationId(@PathVariable String locationName) {
        List<GroupDto> groups = groupService.getGroupsByLocation(locationName);
        return ResponseEntity.ok(groups);
    }

    @GetMapping("/{locationName}/students")
    public ResponseEntity<List<StudentDto>> getStudentsByLocationId(@PathVariable String locationName) {
        List<StudentDto> students = studentService.getStudentsByLocationName(locationName);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{locationName}/groups/{groupName}/students")
    public ResponseEntity<List<StudentDto>> getStudentsByGroup(@PathVariable String groupName, @PathVariable String locationName) {
        List<StudentDto> students = studentService.getStudentsByGroupNameAndLocation(groupName, locationName);
        return ResponseEntity.ok(students);
    }

}
