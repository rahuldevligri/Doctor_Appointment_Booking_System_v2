package com.zumply.healthservice.repository;

import com.zumply.healthservice.entity.Doctor;
import com.zumply.healthservice.entity.DoctorSpecialization;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
    Doctor findByRegistrationNumber(String registrationNumber);
    List<Doctor> findByDoctorSpecialization(DoctorSpecialization doctorSpecialization);
    List<Doctor> findDoctorByDoctorSpecializationId(int id);
    Doctor findByEmail(String email);
    Doctor findByMobile(Long mobile);

    Page<Doctor> findAll(Pageable pageable);


}