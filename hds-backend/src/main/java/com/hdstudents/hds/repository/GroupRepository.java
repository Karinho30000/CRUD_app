package com.hdstudents.hds.repository;

import com.hdstudents.hds.entity.Groups;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface GroupRepository extends JpaRepository<Groups, Long> {
    Optional<Groups> findByName(String name);
    List<Groups> findByLocationName(String locationName);
    Optional<Groups> findByNameAndLocationName(String name, String locationName);


}
