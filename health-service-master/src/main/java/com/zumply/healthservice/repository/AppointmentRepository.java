package com.zumply.healthservice.repository;
import com.zumply.healthservice.entity.Appointment;
import com.zumply.healthservice.entity.Doctor;
import com.zumply.healthservice.entity.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.List;
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    List<Doctor> findByDoctor(Doctor doctor);

    List<Appointment> findByPayment(Payment payment);
}
