package com.zumply.healthservice.service;
import com.zumply.healthservice.entity.DoctorSpecialization;
import com.zumply.healthservice.entity.Doctor;
import com.zumply.healthservice.entity.Hospital;
import com.zumply.healthservice.errorhandler.ZumplyErrorCode;
import com.zumply.healthservice.errorhandler.ZumplyException;
import com.zumply.healthservice.repository.DoctorRepository;
import com.zumply.healthservice.repository.DoctorSpecializationRepository;
import com.zumply.healthservice.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private DoctorSpecializationRepository doctorSpecializationRepository;
    @Autowired
    private HospitalRepository hospitalRepository;

    public List<Doctor> createdoctor(Doctor doctor) throws ZumplyException{
        Optional<DoctorSpecialization> doctorSpecialization = doctorSpecializationRepository.findById(doctor.getDoctorSpecialization().getId());
        if (!doctorSpecialization.isPresent()) {
            throw new ZumplyException(ZumplyErrorCode.CATEGORY_NOT_FOUND.getErrorCode(), ZumplyErrorCode.CATEGORY_NOT_FOUND.getErrorMessage() + doctor.getDoctorSpecialization().getId());
        }
        Optional<Hospital> hospital = hospitalRepository.findByHospitalId(doctor.getHospital().getHospitalId());
        if (!hospital.isPresent()) {
            throw new ZumplyException(ZumplyErrorCode.HOSPITAL_NOT_FOUND.getErrorCode(), ZumplyErrorCode.HOSPITAL_NOT_FOUND.getErrorMessage() + doctor.getHospital().getHospitalId());
        }
        Optional<Doctor> foundDoctor = Optional.ofNullable(doctorRepository.findByRegistrationNumber(doctor.getRegistrationNumber()));
        if (foundDoctor.isPresent()) {
            throw new ZumplyException(ZumplyErrorCode.DOCTOR_ALREADY_EXISTS.getErrorCode(), ZumplyErrorCode.DOCTOR_ALREADY_EXISTS.getErrorMessage() + doctor.getRegistrationNumber());
        }
        doctor.setDoctorSpecialization(doctorSpecialization.get());
        doctor.setHospital(hospital.get());
        return Collections.singletonList(doctorRepository.save(doctor));
    }

    public Doctor addDoctor(Doctor doctor) throws ZumplyException {
        if (doctorRepository.findByRegistrationNumber(doctor.getRegistrationNumber())!=null) {
            throw new ZumplyException(ZumplyErrorCode.DOCTOR_ALREADY_EXISTS.getErrorCode(), ZumplyErrorCode.DOCTOR_ALREADY_EXISTS.getErrorMessage() + doctor.getRegistrationNumber());
        }
        if (doctorRepository.findByEmail(doctor.getEmail())!=null) {
            throw new ZumplyException(ZumplyErrorCode.EMAIL_ALREADY_EXISTS.getErrorCode(), ZumplyErrorCode.EMAIL_ALREADY_EXISTS.getErrorMessage() + doctor.getEmail());
        }
        if (doctorRepository.findByMobile(doctor.getMobile())!=null) {
            throw new ZumplyException(ZumplyErrorCode.PHONE_ALREADY_EXISTS.getErrorCode(), ZumplyErrorCode.PHONE_ALREADY_EXISTS.getErrorMessage() + doctor.getMobile());
        }

        Optional<DoctorSpecialization> doctorSpecialization = doctorSpecializationRepository.findById(doctor.getDoctorSpecialization().getId());
        if (!doctorSpecialization.isPresent()) {
            throw new ZumplyException(ZumplyErrorCode.CATEGORY_NOT_FOUND.getErrorCode(), ZumplyErrorCode.CATEGORY_NOT_FOUND.getErrorMessage() + doctor.getDoctorSpecialization().getId());
        }
        doctor.setDoctorSpecialization(doctorSpecialization.get());
        return doctorRepository.save(doctor);
    }


    public Page<Doctor> getAllDoctors(Integer pageNo, Integer pageSize, String SortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("id"));
        Page<Doctor> pagedResult = doctorRepository.findAll(pageable);
        return pagedResult;
    }

    public Doctor getDoctorById(int id) throws ZumplyException{
        Optional<Doctor> optionalDoctor = doctorRepository.findById(id);
        if (optionalDoctor.isEmpty()) {
            throw new ZumplyException(ZumplyErrorCode.DOCTOR_NOT_FOUND.getErrorCode(),ZumplyErrorCode.DOCTOR_NOT_FOUND.getErrorMessage() + id );
        }
        return optionalDoctor.get();
    }

    public List<Doctor> getDoctorByDoctorSpecializationName(String specializationName) throws ZumplyException {
        Optional<DoctorSpecialization> category = doctorSpecializationRepository.findBySpecializationName(specializationName);
        if (!category.isPresent()) {
            throw new ZumplyException(ZumplyErrorCode.CATEGORY_NOT_FOUND.getErrorCode(), ZumplyErrorCode.CATEGORY_NOT_FOUND.getErrorMessage() + specializationName);
        }
        return doctorRepository.findByDoctorSpecialization(category.get());
    }

    public List<Doctor> getDoctorsByDoctorSpecializationId(int id) throws ZumplyException {
        Optional<DoctorSpecialization> doctorSpecialization = doctorSpecializationRepository.findById(id);
        if (!doctorSpecialization.isPresent()) {
            throw new ZumplyException(ZumplyErrorCode.CATEGORY_NOT_FOUND.getErrorCode(),
                    ZumplyErrorCode.CATEGORY_NOT_FOUND.getErrorMessage() + id);
        }
        return doctorRepository.findByDoctorSpecialization(doctorSpecialization.get());
    }

    public Doctor updateDoctor(int id, Doctor doctor) throws ZumplyException {
        Optional<Doctor> optionalDoctor = doctorRepository.findById(id);
        if (!optionalDoctor.isPresent()) {
            throw new ZumplyException(ZumplyErrorCode.DOCTOR_NOT_FOUND.getErrorCode(), ZumplyErrorCode.DOCTOR_NOT_FOUND.getErrorMessage() + id);
        }

        Doctor existingDoctor = optionalDoctor.get();
        existingDoctor.setFullName(doctor.getFullName());
        existingDoctor.setEmail(doctor.getEmail());
        existingDoctor.setMobile(doctor.getMobile());
        existingDoctor.setGender(doctor.getGender());
        existingDoctor.setRating(doctor.getRating());
        existingDoctor.setCreatedDate(doctor.getCreatedDate());
        existingDoctor.setCreatedBy(doctor.getCreatedBy());
        existingDoctor.setLastModifiedDate(doctor.getLastModifiedDate());
        existingDoctor.setLastModifiedBy(doctor.getLastModifiedBy());

        existingDoctor.setDoctorSpecialization(doctor.getDoctorSpecialization());
        Optional<DoctorSpecialization> doctorSpecialization = doctorSpecializationRepository.findById(existingDoctor.getDoctorSpecialization().getId());
        if (!doctorSpecialization.isPresent()) {
            throw new ZumplyException(ZumplyErrorCode.CATEGORY_NOT_FOUND.getErrorCode(), ZumplyErrorCode.CATEGORY_NOT_FOUND.getErrorMessage() + existingDoctor.getDoctorSpecialization().getId());
        }
        existingDoctor.setRegistrationNumber(doctor.getRegistrationNumber());
        if (doctorRepository.findByRegistrationNumber(existingDoctor.getRegistrationNumber())!=null) {
            throw new ZumplyException(ZumplyErrorCode.DOCTOR_ALREADY_EXISTS.getErrorCode(), ZumplyErrorCode.DOCTOR_ALREADY_EXISTS.getErrorMessage() + existingDoctor.getRegistrationNumber());
        }
        existingDoctor.setDoctorSpecialization(doctorSpecialization.get());
        return doctorRepository.save(existingDoctor);
    }


    public void deleteDoctor(int id) throws ZumplyException {
        Optional<Doctor> foundDoctor = doctorRepository.findById(id);
        if(foundDoctor.isEmpty()){
            throw new ZumplyException(ZumplyErrorCode.DOCTOR_NOT_FOUND.getErrorCode(),ZumplyErrorCode.DOCTOR_NOT_FOUND.getErrorMessage() + id );
        }
        Doctor doctor = foundDoctor.get();
        doctorRepository.delete(doctor);
    }


    public List<Doctor> getDoctorByCategoryId(int id) throws ZumplyException {
        List<Doctor> category = doctorRepository.findDoctorByDoctorSpecializationId(id);
        if (category.isEmpty()) {
            throw new ZumplyException(ZumplyErrorCode.CATEGORY_NOT_FOUND.getErrorCode(),
                    ZumplyErrorCode.CATEGORY_NOT_FOUND.getErrorMessage() + id);
        }
        return category;
    }
}