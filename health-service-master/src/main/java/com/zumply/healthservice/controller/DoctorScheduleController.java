package com.zumply.healthservice.controller;

import com.zumply.healthservice.dto.DoctorScheduleDTO;
import com.zumply.healthservice.entity.Appointment;
import com.zumply.healthservice.entity.DoctorSchedule;
import com.zumply.healthservice.errorhandler.ZumplyException;
import com.zumply.healthservice.service.DoctorScheduleService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctorSchedule")
public class DoctorScheduleController {
    private DoctorScheduleService doctorScheduleService;

    public DoctorScheduleController(DoctorScheduleService doctorScheduleService) {
        this.doctorScheduleService = doctorScheduleService;
    }

    @GetMapping("/doctorSchedule")
    public ResponseEntity<Page<DoctorSchedule>> getAllDoctorSchedule(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "5") Integer pageSize,
            @RequestParam(defaultValue = "doctorScheduleId") String sortBy) {
        Page<DoctorSchedule> doctorSchedules = doctorScheduleService.getAllDoctorSchedule(pageNo, pageSize, sortBy);
        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(doctorSchedules.getTotalElements()))
                .header("X-Page-No", String.valueOf(doctorSchedules.getNumber()))
                .header("X-Page-Size", String.valueOf(doctorSchedules.getSize()))
                .body(doctorSchedules);

    }

    @GetMapping("doctorId/{doctorId}")
    public ResponseEntity<DoctorScheduleDTO> getDoctorScheduleByDoctorId(@PathVariable Integer doctorId) throws ZumplyException {
        DoctorScheduleDTO doctorScheduleDTO = doctorScheduleService.getDoctorScheduleByDoctorId(doctorId);
        return ResponseEntity.ok(doctorScheduleDTO);
    }
}
