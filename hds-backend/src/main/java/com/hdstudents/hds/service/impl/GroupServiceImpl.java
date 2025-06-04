package com.hdstudents.hds.service.impl;

import com.hdstudents.hds.dto.GroupDto;
import com.hdstudents.hds.entity.Groups;
import com.hdstudents.hds.entity.Location;
import com.hdstudents.hds.entity.Teacher;
import com.hdstudents.hds.exception.ResourceNotFoundException;
import com.hdstudents.hds.mapper.GroupMapper;
import com.hdstudents.hds.repository.GroupRepository;
import com.hdstudents.hds.repository.LocationRepository;
import com.hdstudents.hds.repository.TeacherRepository;
import com.hdstudents.hds.service.GroupService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GroupServiceImpl implements GroupService {
    private GroupRepository groupRepository;
    private LocationRepository locationRepository;
    private TeacherRepository teacherRepository;

    @Override
    public GroupDto createGroup(GroupDto groupDto) {
        if(groupRepository.findByNameAndLocationName(groupDto.getName(), groupDto.getLocation()).isPresent()){
            throw new RuntimeException("Group already exists");
        }
        Location location = locationRepository.findByName(groupDto.getLocation())
                .orElseThrow(() -> new RuntimeException("Location not found"));
        Set<Teacher> teachers = groupDto.getTeachers().stream()
                .map(name -> teacherRepository.findByName(name)
                        .orElseGet(() -> teacherRepository.save(new Teacher(name))))
                .collect(Collectors.toSet());
        Groups group = GroupMapper.mapToGroup(groupDto, location, teachers);
        Groups savedGroup = groupRepository.save(group);
        return GroupMapper.mapToGroupDto(savedGroup);
    }

    @Override
    public List<GroupDto> getGroupsByLocation(String locationName) {
        List<Groups> groups = groupRepository.findByLocationName(locationName);
        return groups.stream().map(GroupMapper::mapToGroupDto).collect(Collectors.toList());
    }

    @Override
    public GroupDto updateGroup(Long groupId, GroupDto updatedGroupDto) {
        Groups group = groupRepository.findById(groupId)
                .orElseThrow(() -> new ResourceNotFoundException("Group not found with id: " + groupId));
        group.setName(updatedGroupDto.getName());
        Set<Teacher> teachers = updatedGroupDto.getTeachers().stream()
                .map(name -> teacherRepository.findByName(name)
                        .orElseGet(() -> teacherRepository.save(new Teacher(name))))
                .collect(Collectors.toSet());

        group.setTeachers(teachers);
        Location location = locationRepository.findByName(updatedGroupDto.getLocation()).orElseThrow(() -> new RuntimeException("Location not found with name: " + updatedGroupDto.getLocation()));
        group.setLocation(location);
        Groups updatedGroup = groupRepository.save(group);
        return GroupMapper.mapToGroupDto(updatedGroup);

    }

    @Override
    public GroupDto getGroupByName(String groupName) {
        Groups group = groupRepository.findByName(groupName).orElseThrow(() -> new ResourceNotFoundException("Group not found with name: " + groupName));
        return GroupMapper.mapToGroupDto(group);
    }

    @Override
    public List<GroupDto> getAllGroups() {
        List<Groups> groups = groupRepository.findAll();
        return groups.stream().map(GroupMapper::mapToGroupDto).collect(Collectors.toList());
    }

}
