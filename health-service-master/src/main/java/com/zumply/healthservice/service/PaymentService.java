package com.zumply.healthservice.service;

import com.zumply.healthservice.entity.Payment;
import com.zumply.healthservice.repository.PaymentRepository;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment getPayment(Long id) {
        Optional<Payment> paymentEntity = paymentRepository.findById(id);
        return paymentEntity.orElse(null);
    }

    public Payment processPayment(Map<String, Object> data) throws Exception {
        Object amountObj = data.get("amount");
        if (amountObj == null) {
            throw new Exception("Amount is mandatory");
        }
        float amt = Float.parseFloat(amountObj.toString());
        RazorpayClient rj = new RazorpayClient("rzp_test_QvfhRP9slPcend", "mhCcqqxaDZ3wP3CKzBCC5XuB");

        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount", (int) (amt * 100)); // amount in the smallest currency unit
        orderRequest.put("currency", "INR");

        Order order = rj.orders.create(orderRequest);

        Payment paymentEntity = new Payment();
        paymentEntity.setOrderId(order.get("id") != null ? order.get("id").toString() : null);
        paymentEntity.setAmount(order.get("amount") != null ? Float.parseFloat(order.get("amount").toString()) / 100 : null);
        paymentEntity.setCurrency(order.get("currency") != null ? order.get("currency").toString() : null);
        paymentEntity.setMode(order.get("method") != null ? order.get("method").toString() : null);
        paymentEntity.setStatus(order.get("status") != null ? order.get("status").toString() : null);

        return paymentRepository.save(paymentEntity);
    }

    public Payment updatePaymentStatus(Long id, String status) {
        Optional<Payment> paymentEntityOptional = paymentRepository.findById(id);
        if (paymentEntityOptional.isPresent()) {
            Payment paymentEntity = paymentEntityOptional.get();
            paymentEntity.setStatus(status);
            return paymentRepository.save(paymentEntity);
        }
        return null;
    }
}
