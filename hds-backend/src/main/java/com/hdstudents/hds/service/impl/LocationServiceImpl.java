package com.hdstudents.hds.service.impl;

import com.hdstudents.hds.dto.LocationDto;
import com.hdstudents.hds.entity.Location;
import com.hdstudents.hds.mapper.LocationMapper;
import com.hdstudents.hds.repository.LocationRepository;
import com.hdstudents.hds.service.LocationService;
import jakarta.persistence.EntityExistsException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LocationServiceImpl implements LocationService {
    private LocationRepository locationRepository;

    @Override
    public LocationDto createLocation(LocationDto locationDto) {
        if(locationRepository.findByName(locationDto.getName()).isPresent()){
            throw new EntityExistsException(locationDto.getName());
        }
        Location location = LocationMapper.mapToLocation(locationDto);
        Location locationSaved = locationRepository.save(location);
        return LocationMapper.mapToLocationDto(locationSaved);
    }

    @Override
    public List<LocationDto> getAllLocations() {
        List<Location> locations = locationRepository.findAll();
        return locations.stream().map(LocationMapper::mapToLocationDto).collect(Collectors.toList());
    }

}
