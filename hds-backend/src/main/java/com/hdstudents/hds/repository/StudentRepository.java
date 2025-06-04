package com.hdstudents.hds.repository;

import com.hdstudents.hds.entity.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Students, Long> {
    List<Students> findByGroupName(String groupName);
    List<Students> findByGroupLocationName(String locationName);
    @Query("SELECT s FROM Students s WHERE s.group.name = :groupName AND s.group.location.name = :locationName")
    List<Students> findByGroupNameAndLocationName(@Param("groupName") String groupName, @Param("locationName") String locationName);

}
