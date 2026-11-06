package com.zumply.healthservice.service;

import com.zumply.healthservice.entity.DoctorSpecialization;
import com.zumply.healthservice.entity.Hospital;
import com.zumply.healthservice.errorhandler.ZumplyErrorCode;
import com.zumply.healthservice.errorhandler.ZumplyException;
import com.zumply.healthservice.repository.DoctorSpecializationRepository;
import com.zumply.healthservice.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HospitalService {
    private final HospitalRepository hospitalRepository;
    @Autowired
    public HospitalService(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    public List<Hospital> getHospitalList() throws ZumplyException {
        List<Hospital> hospitalList = hospitalRepository.findAll();
        if (hospitalList.isEmpty()){
            throw new ZumplyException(ZumplyErrorCode.TABLE_IS_EMPTY.getErrorCode(), ZumplyErrorCode.TABLE_IS_EMPTY.getErrorMessage());
        }
        return hospitalList;
    }
    public Hospital addHospital (Hospital hospital) throws ZumplyException {
        Optional<Hospital> foundHospital = hospitalRepository.findByName(hospital.getName());
        if (foundHospital.isPresent()) {
            throw new ZumplyException(ZumplyErrorCode.HOSPITAL_ALREADY_EXISTS.getErrorCode(), ZumplyErrorCode.HOSPITAL_ALREADY_EXISTS.getErrorMessage() + hospital.getName());
        }
        return hospitalRepository.save(hospital);
    }
}
