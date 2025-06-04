package com.hdstudents.hds.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "groups_hd", uniqueConstraints = @UniqueConstraint(columnNames = {"name", "location_id"}))

public class Groups {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(
            name = "group_teacher",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id")
    )
    private Set<Teacher> teachers;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<Students> students;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;


    public Groups(Long id, String name, Set<Teacher> teacher, Location location) {
        this.id = id;
        this.name = name;
        this.teachers = teacher;
        this.location = location;
    }

}
