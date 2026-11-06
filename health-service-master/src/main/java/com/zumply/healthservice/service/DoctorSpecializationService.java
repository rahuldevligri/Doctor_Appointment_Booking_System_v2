package com.zumply.healthservice.service;

import com.zumply.healthservice.entity.DoctorSpecialization;
import com.zumply.healthservice.errorhandler.ZumplyErrorCode;
import com.zumply.healthservice.errorhandler.ZumplyException;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.zumply.healthservice.repository.DoctorSpecializationRepository;

import java.util.List;
import java.util.Optional;


@Service
public class DoctorSpecializationService {
    private final DoctorSpecializationRepository doctorSpecializationRepository;
    @Autowired
    public DoctorSpecializationService(DoctorSpecializationRepository doctorSpecializationRepository) {
        this.doctorSpecializationRepository = doctorSpecializationRepository;
    }

    public Page<DoctorSpecialization> getDoctorSpecialization(int pageNo, int pageSize, String SortBy) throws ZumplyException{
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("id"));
        Page<DoctorSpecialization> doctorSpecializationList = doctorSpecializationRepository.findAll(pageable);
        if (doctorSpecializationList.isEmpty()){
            throw new ZumplyException(ZumplyErrorCode.TABLE_IS_EMPTY.getErrorCode(), ZumplyErrorCode.TABLE_IS_EMPTY.getErrorMessage());
        }
        return doctorSpecializationList;

    }

    public List<DoctorSpecialization> getAllDoctorSpecialization() throws ZumplyException{
        List<DoctorSpecialization> DoctorSpecializationList = doctorSpecializationRepository.findAll();
        if (DoctorSpecializationList.isEmpty()){
            throw new ZumplyException(ZumplyErrorCode.TABLE_IS_EMPTY.getErrorCode(), ZumplyErrorCode.TABLE_IS_EMPTY.getErrorMessage());
        }
        return DoctorSpecializationList;
    }

    public DoctorSpecialization getDoctorSpecializationById(int id) throws ZumplyException {
        Optional<DoctorSpecialization> specializationId = doctorSpecializationRepository.findById(id);
        if(specializationId.isEmpty()){
            throw new ZumplyException(ZumplyErrorCode.CATEGORY_NOT_FOUND.getErrorCode(), ZumplyErrorCode.CATEGORY_NOT_FOUND.getErrorMessage()+ id);
        }
        return specializationId.get();
    }
    public DoctorSpecialization addDoctorSpecialization(DoctorSpecialization doctorSpecialization) throws ZumplyException {
        Optional<DoctorSpecialization> foundSpecialization = doctorSpecializationRepository.findBySpecializationName(doctorSpecialization.getSpecializationName());
        if (foundSpecialization.isPresent()) {
            throw new ZumplyException(ZumplyErrorCode.CATEGORY_ALREADY_EXISTS.getErrorCode(), ZumplyErrorCode.CATEGORY_ALREADY_EXISTS.getErrorMessage() + doctorSpecialization.getSpecializationName());
        }
        return doctorSpecializationRepository.save(doctorSpecialization);
    }

    public DoctorSpecialization updateDoctorSpecialization(DoctorSpecialization doctorSpecialization, int id) throws ZumplyException {
        Optional<DoctorSpecialization> foundSpecialization = doctorSpecializationRepository.findById(id);
        if (foundSpecialization.isEmpty()) {
            throw new ZumplyException(ZumplyErrorCode.CATEGORY_NOT_FOUND.getErrorCode(), ZumplyErrorCode.CATEGORY_NOT_FOUND.getErrorMessage() + doctorSpecialization.getId());
        }
        if (doctorSpecialization.getId() == 0){
            doctorSpecialization.setId(foundSpecialization.get().getId());
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
            modelMapper.map(doctorSpecialization, foundSpecialization.get());
            return doctorSpecializationRepository.save(foundSpecialization.get());
        }
        else{
            return doctorSpecializationRepository.save(doctorSpecialization);
        }
    }
}