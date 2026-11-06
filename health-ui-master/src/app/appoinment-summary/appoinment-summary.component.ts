import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, ActivatedRouteSnapshot } from '@angular/router';
import { AppoinmentSummaryService } from '../services/appoinmentSummary/appoinment-summary.service';
import axios from 'axios';

@Component({
  selector: 'app-appoinment-summary',
  templateUrl: './appoinment-summary.component.html',
  styleUrls: ['./appoinment-summary.component.css']
})
export class AppoinmentSummaryComponent implements OnInit{
  selectedCategory: string;
  selectedDoctor: string;
  selectedSlot: string;
  selectedHospital: string;
  selectedFee: number;
  selectedScheduleId: number;
  selectedDoctorId: number;
  
  constructor(private route: ActivatedRoute,
     private appoinmentSummaryService: AppoinmentSummaryService
  ) { }

  ngOnInit(){

    this.selectedCategory = this.appoinmentSummaryService.getSelectedCategory();
    this.selectedDoctor = this.appoinmentSummaryService.getSelectedDoctor();
    this.selectedSlot = this.appoinmentSummaryService.getSelectedSlot();
    this.selectedHospital = this.appoinmentSummaryService.getSelectedHospital();
    this.selectedFee = this.appoinmentSummaryService.getSelectedFee();
    this.selectedScheduleId = this.appoinmentSummaryService.getSelectedScheduleId();
    this.selectedDoctorId = this.appoinmentSummaryService.getSelectedDoctorId();
    
  }
  pay(amount: any,id: any,doctorId: any){
    let formdata = new FormData();
    formdata.append("amount", amount);
    // axios.post('http://zumplyhealth.ap-south-1.elasticbeanstalk.com/payment', formdata
      axios.post('http://localhost:5000/payment', formdata

     
    ).then((response: { data: any; })=>{

      console.log(response.data);
      let paymentId = response.data.paymentId;
      this.paynow(amount, paymentId,id,doctorId);

    }).catch((error: any)=>{
      console.log(error);
    });
    
  }

  paynow(amount: any, paymentId: any,id: any,doctorId: number){
    var options={
      key:'rzp_test_QvfhRP9slPcend',
      amount: amount*100,
      currency: 'INR',
      name: 'Zumply Health',
      image: 'https://avatars.githubusercontent.com/u/128212455?v=4',
      // order_id:response.id,
      // method:response.method,
      handler:function(response: { razorpay_payment_id: any; razorpay_order_id: any; razorpay_signature: any; }){
        console.log(response.razorpay_payment_id);
        console.log(response.razorpay_order_id);
        console.log(response.razorpay_signature);
        console.log('payment successful !!');
        // axios.post('http://zumplyhealth.ap-south-1.elasticbeanstalk.com/payment/success/'+paymentId).then((response: { data: any; })=>{
          axios.post('http://localhost:5000/payment/success/'+paymentId).then((response: { data: any; })=>{  
        console.log(response.data);
          // axios.post('http://zumplyhealth.ap-south-1.elasticbeanstalk.com/api/v1/appointment', {
            axios.post('http://localhost:5000/api/v1/appointment', {
          "doctorScheduleId": id,
          "doctorId": doctorId,
          "paymentId": paymentId,
          "status": "booked",
          "userId": window.localStorage.getItem('id')
        }).then((response: { data: any; })=>{
          console.log(response.data);
        }).catch((error: any)=>{

          console.log(error);
        });

        }).catch((error: any)=>{
          console.log(error);
        });
        alert("congrates !! payment successful !!")
        window.location.href = "/payment-summary/"+paymentId;
      },
      "prefill": {
      "name": "",
      "email": "",
      "contact": ""
      },
      "notes": {
        "address": "Book Appointment"
      },
      "theme": {
        "color": "#F37254"
      },
    };
    var rzp=new window['Razorpay'](options);

    rzp.on('payment.failed', function (response: { error: { code: any; description: any; source: any; step: any; reason: any; metadata: { order_id: any; payment_id: any; }; }; }){
      console.log(response.error.code);
      console.log(response.error.description);
      console.log(response.error.source);
      console.log(response.error.step);
      console.log(response.error.reason);
      console.log(response.error.metadata.order_id);
      console.log(response.error.metadata.payment_id);
      // axios.post('http://zumplyhealth.ap-south-1.elasticbeanstalk.com/payment/fail/'+paymentId).then((response: { data: any; })=>{
        axios.post('http://localhost:5000/payment/fail/'+paymentId).then((response: { data: any; })=>{
          console.log(response.data);
        }).catch((error: any)=>{
          console.log(error);
        });
      alert("Oops payment failed !!");
    });
    rzp.open();
  }

}
