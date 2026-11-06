package com.zumply.healthservice.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "payment")
public class Payment extends Audit{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id",nullable = false,unique = true)
    private int paymentId;

    @Column(name = "order_id")
    private String orderId;

    @Column(name = "amount", nullable = false)
    private float amount;

    @Column(name = "currency")
    private String currency;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "mode")
    private String mode;

    @Column(name = "created_at")
    private String created_at;
}