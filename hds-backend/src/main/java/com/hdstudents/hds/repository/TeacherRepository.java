package com.hdstudents.hds.repository;

import com.hdstudents.hds.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    Optional<Teacher> findByName(String name);
    Optional<Teacher> findById(Long id);
    List<Teacher> findByGroupsName(String groupName);
}
