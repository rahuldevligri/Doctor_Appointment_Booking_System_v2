package com.zumply.healthservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "hospital")
public class Hospital  extends Audit{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hospital_id" , nullable = false)
    private int hospitalId;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;
}

