package com.zumply.healthservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@MappedSuperclass
public abstract class Audit {
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "last_modified_date")
    private LocalDateTime lastModifiedDate;

    @Column(name = "last_modified_by")
    private String lastModifiedBy;
    @PrePersist
    protected void prePersist() {
        if (this.createdDate == null) createdDate = new Timestamp(System.currentTimeMillis()).toLocalDateTime();
        if (this.lastModifiedDate == null) lastModifiedDate= new Timestamp(System.currentTimeMillis()).toLocalDateTime();
    }

    @PreUpdate
    protected void preUpdate() {
        this.lastModifiedDate = new Timestamp(System.currentTimeMillis()).toLocalDateTime();
    }
    @PreRemove
    protected void preRemove() {
        this.lastModifiedDate = new Timestamp(System.currentTimeMillis()).toLocalDateTime();
    }
}
