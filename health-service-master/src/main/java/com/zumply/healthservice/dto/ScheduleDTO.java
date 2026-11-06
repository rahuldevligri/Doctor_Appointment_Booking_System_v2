package com.zumply.healthservice.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ScheduleDTO {

    private Integer doctorScheduleId;


    private LocalDateTime startTime;

    private LocalDateTime endTime;
}
