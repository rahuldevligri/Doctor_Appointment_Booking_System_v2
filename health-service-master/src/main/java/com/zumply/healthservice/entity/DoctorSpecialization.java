package com.zumply.healthservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "doctor_specialization")
public class DoctorSpecialization extends Audit{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_specialization_id", unique = true, nullable = false)
    private int id;

    @Column(name = "specialization_name", unique = true, nullable = false)
    private String specializationName;

    @Column(name = "specialization_description")
    private String specializationDescription;
}
