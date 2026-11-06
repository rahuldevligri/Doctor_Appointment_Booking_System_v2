package com.zumply.healthservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class DoctorScheduleDTO {
    private Integer doctorId;
    private String doctorName;
    private List<ScheduleDTO> schedules;
}
