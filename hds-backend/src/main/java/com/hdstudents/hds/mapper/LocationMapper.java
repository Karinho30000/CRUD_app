package com.hdstudents.hds.mapper;

import com.hdstudents.hds.dto.LocationDto;
import com.hdstudents.hds.entity.Location;

public class LocationMapper {
    public static LocationDto mapToLocationDto(Location location) {
        int totalStudents = 0;

        if (location.getGroups() != null) {
            totalStudents = location.getGroups().stream()
                    .filter(group -> group.getStudents() != null)
                    .mapToInt(group -> group.getStudents().size())
                    .sum();
        }

        return new LocationDto(
                location.getId(),
                location.getName(),
                totalStudents
        );
    }

    public static Location mapToLocation(LocationDto locationDto) {
        return new Location(
                locationDto.getId(),
                locationDto.getName()
        );
    }
}
