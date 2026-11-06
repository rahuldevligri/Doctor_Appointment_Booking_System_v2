package com.zumply.healthservice.controller;

import com.zumply.healthservice.entity.DoctorSpecialization;
import com.zumply.healthservice.errorhandler.ErrorResponse;

import com.zumply.healthservice.errorhandler.ZumplyException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.zumply.healthservice.service.DoctorSpecializationService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class DoctorSpecializationController {
    @Autowired
    private DoctorSpecializationService doctorSpecializationService;


    @GetMapping("/getCategory")
    public ResponseEntity<?> getAllDoctorSpecialization(@Valid DoctorSpecialization DoctorSpecialization) throws ZumplyException{
        try{
            List<DoctorSpecialization> doctorSpecializationList = doctorSpecializationService.getAllDoctorSpecialization();

            return ResponseEntity.ok().body(doctorSpecializationList);
        }
        catch (ZumplyException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode(), e.getErrorMessage());
            return ResponseEntity.ok().body(errorResponse);
        }
    }

    @GetMapping("/category")
    public ResponseEntity<?> getDoctorSpecialization(@RequestParam(defaultValue = "0") int pageNo,
                                                     @RequestParam(defaultValue = "5") int pageSize,
                                                     @RequestParam(defaultValue = "id") String sortBy,
                                                     @Valid DoctorSpecialization doctorSpecialization) throws ZumplyException{
        try{
            Page<DoctorSpecialization> doctorSpecializationList = doctorSpecializationService.getDoctorSpecialization(pageNo, pageSize, sortBy);
            return ResponseEntity.ok()
                    .header("X-Total-Count", String.valueOf(doctorSpecializationList.getTotalElements()))
                    .header("X-Page-No", String.valueOf(doctorSpecializationList.getNumber()))
                    .header("X-Page-Size", String.valueOf(doctorSpecializationList.getSize()))
                    .body(doctorSpecializationList);
        }
        catch (ZumplyException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode(), e.getErrorMessage());
            return ResponseEntity.ok().body(errorResponse);
        }
    }



    @GetMapping("category/{id}")
    public ResponseEntity<?> getDoctorSpecializationById(@Valid @PathVariable int id) {
        try {
            return ResponseEntity.ok().body(doctorSpecializationService.getDoctorSpecializationById(id));
        }
        catch (ZumplyException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode(), e.getErrorMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
    @PostMapping("/category")
    public ResponseEntity<?> addDoctorSpecialization(@Valid @RequestBody DoctorSpecialization doctorSpecialization) {
        try {
            DoctorSpecialization addDoctorSpecialization = doctorSpecializationService.addDoctorSpecialization(doctorSpecialization);
            return ResponseEntity.ok().body(addDoctorSpecialization);
        } catch (ZumplyException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode(), e.getErrorMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PutMapping("/category/{id}")
    public ResponseEntity<?> updateDoctorSpecialization(@Valid @RequestBody DoctorSpecialization doctorSpecialization, @PathVariable int id) {
        try {
            DoctorSpecialization updateDoctorSpecialization = doctorSpecializationService.updateDoctorSpecialization(doctorSpecialization, id);
            return ResponseEntity.ok().body(updateDoctorSpecialization);
        } catch (ZumplyException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode(), e.getErrorMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
}
