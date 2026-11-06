package com.zumply.healthservice.service;

import com.zumply.healthservice.dto.DoctorScheduleDTO;
import com.zumply.healthservice.dto.ScheduleDTO;
import com.zumply.healthservice.entity.DoctorSchedule;
import com.zumply.healthservice.errorhandler.ZumplyErrorCode;
import com.zumply.healthservice.errorhandler.ZumplyException;
import com.zumply.healthservice.repository.DoctorScheduleRepository;
import jakarta.persistence.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorScheduleService {

    private final DoctorScheduleRepository doctorScheduleRepository;

    @Autowired
    public DoctorScheduleService(DoctorScheduleRepository doctorScheduleRepository) {
        this.doctorScheduleRepository = doctorScheduleRepository;
    }

    public DoctorScheduleDTO getDoctorScheduleByDoctorId(Integer doctorId) throws ZumplyException {
        List<Tuple> tuples = doctorScheduleRepository.findDoctorSchedulesByDoctorId(doctorId);
        if (tuples.isEmpty()) {
            throw new ZumplyException(ZumplyErrorCode.DOCTOR_SCHEDULE_NOT_FOUND.getErrorCode(),
                    ZumplyErrorCode.DOCTOR_SCHEDULE_NOT_FOUND.getErrorMessage() + doctorId);
        }

        List<ScheduleDTO> scheduleDTOs = new ArrayList<>();
        String doctorName = "";
        for (Tuple tuple : tuples) {
            if (doctorName.equals("")) {
                doctorName = tuple.get("doctorName", String.class);
            }
            ScheduleDTO scheduleDTO = new ScheduleDTO();
            scheduleDTO.setStartTime(tuple.get("startTime", Timestamp.class).toLocalDateTime());
            scheduleDTO.setEndTime(tuple.get("endTime", Timestamp.class).toLocalDateTime());
            scheduleDTOs.add(scheduleDTO);
        }
        DoctorScheduleDTO doctorScheduleDTO = new DoctorScheduleDTO();
        doctorScheduleDTO.setDoctorId(doctorId);
        doctorScheduleDTO.setDoctorName(doctorName);
        doctorScheduleDTO.setSchedules(scheduleDTOs);
        return doctorScheduleDTO;
    }
    public Page<DoctorSchedule> getAllDoctorSchedule(Integer pageNo, Integer pageSize, String SortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("doctorScheduleId"));
        Page<DoctorSchedule> pagedResult = doctorScheduleRepository.findAll(pageable);
        return pagedResult;
    }
}
