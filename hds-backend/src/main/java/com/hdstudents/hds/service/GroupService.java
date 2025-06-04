package com.hdstudents.hds.service;

import com.hdstudents.hds.dto.GroupDto;

import java.util.List;

public interface GroupService {

    List<GroupDto> getAllGroups();

    GroupDto createGroup(GroupDto groupDto);

    List<GroupDto> getGroupsByLocation(String locationName);

    GroupDto updateGroup(Long groupId, GroupDto groupDto);

    GroupDto getGroupByName(String groupName);
}
