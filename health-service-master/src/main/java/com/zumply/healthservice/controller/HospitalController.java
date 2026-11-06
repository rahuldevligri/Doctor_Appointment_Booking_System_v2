package com.zumply.healthservice.controller;

import com.zumply.healthservice.entity.DoctorSpecialization;
import com.zumply.healthservice.entity.Hospital;
import com.zumply.healthservice.errorhandler.ErrorResponse;
import com.zumply.healthservice.errorhandler.ZumplyException;
import com.zumply.healthservice.service.HospitalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class HospitalController {
    @Autowired
    private HospitalService hospitalService;

    @GetMapping("/hospital")
    public ResponseEntity<?> getHospitalList(@Valid Hospital hospital) throws ZumplyException {
        try{
            List<Hospital> hospitals = hospitalService.getHospitalList();

            return ResponseEntity.ok().body(hospitals);
        }
        catch (ZumplyException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode(), e.getErrorMessage());
            return ResponseEntity.ok().body(errorResponse);
        }
    }

    @PostMapping("/hospital")
    public ResponseEntity<?> addHospital(@Valid @RequestBody Hospital hospital) {
        try {
            Hospital addHospital = hospitalService.addHospital(hospital);
            return ResponseEntity.ok().body(addHospital);
        } catch (ZumplyException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode(), e.getErrorMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
}
