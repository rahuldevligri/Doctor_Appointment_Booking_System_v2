package com.zumply.healthservice.repository;

import com.zumply.healthservice.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital,Integer> {
    Optional<Hospital> findByName(String name);
    Optional<Hospital> findByHospitalId(int hospitalId);
}
