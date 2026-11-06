package com.zumply.healthservice.controller;

import com.zumply.healthservice.entity.Payment;
import com.zumply.healthservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/{id}")
    public Payment getPayment(@PathVariable("id") Long id) {
        return paymentService.getPayment(id);
    }

    @PostMapping
    public Payment processPayment(@RequestParam Map<String, Object> data) throws Exception {
        return paymentService.processPayment(data);
    }

    @PostMapping("/success/{id}")
    public Payment paymentSuccess(@PathVariable("id") Long id) {
        return paymentService.updatePaymentStatus(id, "paid");
    }

    @PostMapping("/fail/{id}")
    public Payment paymentFail(@PathVariable("id") Long id) {
        return paymentService.updatePaymentStatus(id, "failed");
    }
}