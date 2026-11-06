package com.zumply.healthservice.controller;
import com.zumply.healthservice.dto.AppointmentDTO;
import com.zumply.healthservice.entity.Appointment;
import com.zumply.healthservice.errorhandler.ZumplyException;
import com.zumply.healthservice.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.zumply.healthservice.errorhandler.ErrorResponse;
import java.util.*;
@RestController
@RequestMapping("/api/v1")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;
    @PostMapping("/appointment")
    public ResponseEntity<?> addAppointment(@RequestBody AppointmentDTO appointment) {
            Appointment savedAppointment = appointmentService.addAppointment(appointment);
            return ResponseEntity.ok().body(savedAppointment);
    }

    @GetMapping("/appointment")
        public ResponseEntity<Page<Appointment>> getAllAppointment(
                @RequestParam(defaultValue = "0") Integer pageNo,
                @RequestParam(defaultValue = "5") Integer pageSize,
                @RequestParam(defaultValue = "appointmentId") String sortBy) {
            Page<Appointment> appointments = appointmentService.getAllAppointment(pageNo, pageSize, sortBy);
            return ResponseEntity.ok()
                    .header("X-Total-Count", String.valueOf(appointments.getTotalElements()))
                    .header("X-Page-No", String.valueOf(appointments.getNumber()))
                    .header("X-Page-Size", String.valueOf(appointments.getSize()))
                    .body(appointments);

    }
    @GetMapping("/paymentId/{id}")
    public ResponseEntity<?> getAppointmentByPaymentId(@PathVariable int id) {
        try {
            List<Appointment> appointments = appointmentService.getAppointmentByPaymentId(id);
            return ResponseEntity.ok().body(appointments);

        } catch (ZumplyException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode(), e.getErrorMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }


    @GetMapping("/userId/{id}")
    public List<LinkedHashMap<String, Object>> getAllAppointmentByUserid(@PathVariable int id) {
        List<LinkedHashMap<String, Object>> appointmentDTOList = appointmentService.getAllAppointmentByUserId(id);
        return ResponseEntity.ok(appointmentDTOList).getBody();
    }




    @PutMapping("/appointmentId/{id}")
    public ResponseEntity<?> updateAppointment(@PathVariable int id, @RequestBody @Valid Appointment appointment) {
        try {
            Appointment updatedAppointment = appointmentService.updateAppointment(id, appointment);
            return ResponseEntity.ok().body(updatedAppointment);
        } catch (ZumplyException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode(), e.getErrorMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @DeleteMapping("/appointmentId/{id}")
    public ResponseEntity<Object> deleteAppointment(@PathVariable int id) {
        try {
            appointmentService.deleteAppointment(id);
            String s = "successfully deleted appointment with id: " + id;

            return ResponseEntity.ok().body(s);

        } catch (ZumplyException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode(), e.getErrorMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }
}

