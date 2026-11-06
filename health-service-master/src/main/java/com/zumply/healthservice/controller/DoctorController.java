package com.zumply.healthservice.controller;

import com.zumply.healthservice.entity.Doctor;

import com.zumply.healthservice.errorhandler.ErrorResponse;
import com.zumply.healthservice.errorhandler.ZumplyException;

import com.zumply.healthservice.service.DoctorService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.*;

@RestController
@RequestMapping("/api/v1")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping("/doctor")
    public ResponseEntity<?> addDoctor(@RequestBody @Valid Doctor doctor) {
        try {
            Doctor savedDoctor = doctorService.addDoctor(doctor);
            return ResponseEntity.ok().body(savedDoctor);
        } catch (ZumplyException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode(), e.getErrorMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
    @PostMapping("/createdoctor")
    public ResponseEntity<?> createdoctor(@RequestBody @Valid Doctor doctor) {
        try {
            List<Doctor> addDoctor = doctorService.createdoctor(doctor);
            return ResponseEntity.ok().body(addDoctor);
        } catch (ZumplyException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode(), e.getErrorMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
    @GetMapping("/doctor")
    public ResponseEntity<Page<Doctor>> getAllDoctors(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "5") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {
        Page<Doctor> doctors = doctorService.getAllDoctors(pageNo, pageSize, sortBy);
        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(doctors.getTotalElements()))
                .header("X-Page-No", String.valueOf(doctors.getNumber()))
                .header("X-Page-Size", String.valueOf(doctors.getSize()))
                .body(doctors);
    }

@GetMapping("/doctorList/{id}")
public  ResponseEntity<?> getDoctorByCategoryId(@PathVariable int id){
        try{
            List<Doctor>doctors =doctorService.getDoctorByCategoryId(id);
            List<Map<String,Object>> response = new ArrayList<>();
            for(Doctor doctor : doctors){
                Map<String,Object> category = new LinkedHashMap<>();
                category.put("id", doctor.getId());
                category.put("fullName", doctor.getFullName());
                category.put("hospital", doctor.getHospital().getName());
                category.put("rating", doctor.getRating());
                category.put("registrationNumber", doctor.getRegistrationNumber());
                response.add(category);
            }
            return ResponseEntity.ok().body(response);
        }catch (ZumplyException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode(), e.getErrorMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
}


    @GetMapping("/doctor/{id}")
    public ResponseEntity<?> getDoctorById(@PathVariable int id) {
        try {
            Doctor doctor = doctorService.getDoctorById(id);
            return new ResponseEntity<>(doctor, HttpStatus.OK);
        } catch (ZumplyException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode(), e.getErrorMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @GetMapping("/doctor/specializationId/{specializationId}")
    public ResponseEntity<?> getDoctorsByDoctorSpecializationId(@PathVariable int specializationId) {
        try {
            List<Doctor> doctors = doctorService.getDoctorsByDoctorSpecializationId(specializationId);
//            List<Map<String,Object>> response = new ArrayList<>();
//            for(Doctor doctor : doctors){
//                Map<String,Object> category = new HashMap<>();
//                category.put("Id", doctor.getDoctor().getId());
//                category.put("Name", doctor.getDoctor().getFullName());
//                category.put("Hospital", doctor.getDoctor().getHospital());
//                category.put("Rating", doctor.getDoctor().getRating());
//                category.put("RegistrationNumber", doctor.getDoctor().getRegistrationNumber());
//
//                response.add(category);
//            }
            return ResponseEntity.ok().body(doctors);

        } catch (ZumplyException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode(), e.getErrorMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @GetMapping("/doctor/specializationName/{specializationName}")
    public ResponseEntity<?> getDoctorByDoctorSpecializationName(@PathVariable String specializationName) {
        try {
            List<Doctor> doctors = doctorService.getDoctorByDoctorSpecializationName(specializationName);
            return ResponseEntity.ok().body(doctors);
        } catch (ZumplyException e) {

            ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode(), e.getErrorMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @PutMapping("/doctor/{id}")
    public ResponseEntity<?> updateDoctor(@PathVariable int id, @RequestBody @Valid Doctor doctor) {
        try {
            Doctor updatedDoctor = doctorService.updateDoctor(id, doctor);
            return ResponseEntity.ok().body(updatedDoctor);
        } catch (ZumplyException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode(), e.getErrorMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @DeleteMapping("/doctor/{id}")
    public ResponseEntity<?> deleteDoctor(@PathVariable int id) {
        try {
            doctorService.deleteDoctor(id);
            String message = "successfully deleted doctor with id: " + id;
            Map<String, Object> response = new HashMap<>();
            response.put("message", message);
            return ResponseEntity.ok().body(response);

        } catch (ZumplyException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode(), e.getErrorMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }



}
