package com.zumply.healthservice.repository;
import com.zumply.healthservice.entity.DoctorSchedule;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.stereotype.Repository;
@Repository
public interface DoctorScheduleRepository extends JpaRepository<DoctorSchedule, Integer> {


    @Query(value = "SELECT ds.doctor_schedule_id AS doctorScheduleId, ds.start_time AS startTime, ds.end_time AS endTime, d.full_name AS doctorName " +
            "FROM doctor_schedule ds " +
            "JOIN doctor d ON ds.doctor_id = d.doctor_id " +
            "WHERE ds.doctor_id = :doctorId", nativeQuery = true)
    List<Tuple> findDoctorSchedulesByDoctorId(@Param("doctorId") Integer doctorId);
}
