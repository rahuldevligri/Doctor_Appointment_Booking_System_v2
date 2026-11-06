package com.zumply.healthservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;


@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "doctor")
public class Doctor extends Audit{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_id", nullable = false,unique = true)
    private int id;

    @ManyToOne
    @JoinColumn(name = "doctor_specialization_id", nullable = false)
    @NotNull(message = "doctor_specialization_id cannot be null")
    private DoctorSpecialization doctorSpecialization;

    @ManyToOne
    @JoinColumn(name = "hospital_id",nullable = false)
    private Hospital hospital;

    @NotEmpty(message = "First Name required")
    @Size(min = 3, max = 50, message = "first Name must be between 2 and 50 characters")
    @Column(name = "full_name", nullable = false)
    private String fullName;

    @NotEmpty(message = "registration_number required")
    @Column(name = "registration_number", unique = true , nullable = false)
    private String registrationNumber;

    @Column(name = "mobile", unique = true)
//    @Size(min = 10, max = 10, message = "phone number should be 10 digit")
    private Long mobile;

    @NotEmpty(message = "email required")
    @Email
    @Column(name = "email")
    private String email;

    @Column(name = "gender")
    private String gender;

    @Column(name = "fees")
    private BigDecimal fees;

    @Column(name = "rating")
    private int rating;
}