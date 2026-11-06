package com.zumply.healthservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "appointment")
public class Appointment extends Audit{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointment_id",nullable = false)
    private int appointmentId;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "doctor_id",nullable = false)
    private Doctor doctor;

    @OneToOne
    @JoinColumn(name = "payment_id",nullable = false)
    private Payment payment;

    @Column( name = "status")
    @NotEmpty(message = "This field is required")
    private String status;

    @ManyToOne
    @JoinColumn(name = "doctor_schedule_id",nullable = false)
    private DoctorSchedule doctorSchedule;
}