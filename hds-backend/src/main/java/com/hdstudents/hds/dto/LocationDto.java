package com.hdstudents.hds.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class LocationDto {
    private Long id;
    private String name;
    private int studentCount;

}
