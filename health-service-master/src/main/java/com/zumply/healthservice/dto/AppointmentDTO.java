package com.zumply.healthservice.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data

public class AppointmentDTO {
    private int AppointmentId;
    private int UserId;
    private int DoctorId;
    private int DoctorScheduleId;
    private int PaymentId;
    private String DoctorName;
    private float Amount;
    private LocalDateTime StartTime;
    private LocalDateTime EndTime;
    private String Status;
}