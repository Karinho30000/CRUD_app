package com.hdstudents.hds.service;

import com.hdstudents.hds.dto.LocationDto;

import java.util.List;

public interface LocationService {
    LocationDto createLocation(LocationDto locationDto);

    List<LocationDto> getAllLocations();

}
