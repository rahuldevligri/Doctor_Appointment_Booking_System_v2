package com.zumply.healthservice.service;

import com.zumply.healthservice.dto.AppointmentDTO;
import com.zumply.healthservice.entity.*;
import com.zumply.healthservice.errorhandler.ZumplyErrorCode;
import com.zumply.healthservice.errorhandler.ZumplyException;
import com.zumply.healthservice.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class AppointmentService {
    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    DoctorScheduleRepository doctorScheduleRepository;
    @Autowired
    PaymentRepository paymentRepository;

    public Appointment addAppointment(AppointmentDTO appointmentDTO) {
        Appointment appointment = new Appointment();
        appointment.setAppointmentId(appointmentDTO.getAppointmentId());
        appointment.setStatus(appointmentDTO.getStatus());
        appointment.setDoctorSchedule(doctorScheduleRepository.findById(appointmentDTO.getDoctorScheduleId()).get());
        appointment.setDoctor(doctorRepository.findById(appointmentDTO.getDoctorId()).get());
        appointment.setUser(userRepository.findById(appointmentDTO.getUserId()).get());
        appointment.setPayment(paymentRepository.findById((long) appointmentDTO.getPaymentId()).get());
        return appointmentRepository.save(appointment);





    }

    public Page<Appointment> getAllAppointment(Integer pageNo, Integer pageSize, String SortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("appointmentId"));
        return appointmentRepository.findAll(pageable);
    }

    public Appointment getAppointmentById(int id) throws ZumplyException {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);
        if (optionalAppointment.isEmpty()) {
            throw new ZumplyException(ZumplyErrorCode.APPOINTMENT_NOT_FOUND.getErrorCode(), ZumplyErrorCode.APPOINTMENT_NOT_FOUND.getErrorMessage() + id);
        }
        return optionalAppointment.get();
    }

    public List<LinkedHashMap<String, Object>> getAllAppointmentByUserId(int userId){
        return appointmentRepository.findAll()
                .stream()
                .filter(appointment -> appointment.getUser().getId() == userId)
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());

    }

    public List<Appointment> getAppointmentByPaymentId(long id) throws ZumplyException {
        Optional<Payment> payment = paymentRepository.findById(id);
        if (payment.isEmpty()) {
            throw new ZumplyException(ZumplyErrorCode.APPOINTMENT_NOT_FOUND.getErrorCode(),
                    ZumplyErrorCode.APPOINTMENT_NOT_FOUND.getErrorMessage() + id);
        }
        return appointmentRepository.findByPayment(payment.get());
    }
    private LinkedHashMap<String,Object> convertEntityToDto(Appointment appointment){
        LinkedHashMap<String,Object> appointmentDTO = new LinkedHashMap<>();
        appointmentDTO.put("AppointmentId",appointment.getAppointmentId());
        appointmentDTO.put("DoctorName",appointment.getDoctor().getFullName());
        appointmentDTO.put("Amount",appointment.getPayment().getAmount());
        appointmentDTO.put("StartTime",appointment.getDoctorSchedule().getStartTime());
        appointmentDTO.put("EndTime",appointment.getDoctorSchedule().getEndTime());

        return appointmentDTO;
    }

    public Appointment updateAppointment(int id, Appointment appointment) throws ZumplyException {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);
        if (optionalAppointment.isEmpty()) {
            throw new ZumplyException(ZumplyErrorCode.APPOINTMENT_NOT_FOUND.getErrorCode(), ZumplyErrorCode.APPOINTMENT_NOT_FOUND.getErrorMessage() + id);
        }
        Appointment existingAppointment = optionalAppointment.get();
        existingAppointment.setStatus(appointment.getStatus());

        existingAppointment.setCreatedDate(appointment.getCreatedDate());
        existingAppointment.setCreatedBy(appointment.getCreatedBy());
        existingAppointment.setLastModifiedDate(appointment.getLastModifiedDate());
        existingAppointment.setLastModifiedBy(appointment.getLastModifiedBy());

        existingAppointment.setDoctor(appointment.getDoctor());
        Optional<Doctor> doctor = doctorRepository.findById(existingAppointment.getDoctor().getId());
        if (doctor.isEmpty()) {
            throw new ZumplyException(ZumplyErrorCode.DOCTOR_NOT_FOUND.getErrorCode(), ZumplyErrorCode.DOCTOR_NOT_FOUND.getErrorMessage() + existingAppointment.getDoctor().getId());
        }

        existingAppointment.setUser(appointment.getUser());
        Optional<User> user = userRepository.findById(existingAppointment.getUser().getId());
        if (user.isEmpty()) {
            throw new ZumplyException(ZumplyErrorCode.USER_NOT_FOUND.getErrorCode(), ZumplyErrorCode.USER_NOT_FOUND.getErrorMessage() + existingAppointment.getUser().getId());
        }

        existingAppointment.setDoctorSchedule(appointment.getDoctorSchedule());
        Optional<DoctorSchedule> doctorSchedule = doctorScheduleRepository.findById(existingAppointment.getDoctorSchedule().getDoctorScheduleId());
        if (doctorSchedule.isEmpty()) {
            throw new ZumplyException(ZumplyErrorCode.DOCTOR_SCHEDULE_NOT_FOUND.getErrorCode(), ZumplyErrorCode.DOCTOR_SCHEDULE_NOT_FOUND.getErrorMessage() + existingAppointment.getDoctorSchedule().getDoctorScheduleId());
        }
        return appointmentRepository.save(existingAppointment);
    }
    public void deleteAppointment(int id) throws ZumplyException {
        Appointment appointment = getAppointmentById(id);
        if(appointment==null){
            throw new ZumplyException(ZumplyErrorCode.APPOINTMENT_NOT_FOUND.getErrorCode(),ZumplyErrorCode.APPOINTMENT_NOT_FOUND.getErrorMessage() + id );
        }
        appointmentRepository.delete(appointment);

    }
}