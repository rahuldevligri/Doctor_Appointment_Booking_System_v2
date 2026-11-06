import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router'
import { PaymentService } from '../services/payment/payment.service';
import { Payment } from '../services/payment/payment';

@Component({
  selector: 'app-payment-summary',
  templateUrl: './payment-summary.component.html',
  styleUrls: ['./payment-summary.component.css']
})
export class PaymentSummaryComponent implements OnInit {
    id:any;
    paymentList: any[] = [];

constructor(private route: ActivatedRoute,
    private paymentService: PaymentService
  ) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe((paramMap) => {
      this.id = paramMap.get('id');
    });
    console.log(this.id);
    this.getPaymentList(this.id);
  }

  private getPaymentList(id) {
    this.paymentService.getPaymentList(id).subscribe((data: any[]) => {
     this.paymentList = data;
      console.log(this.paymentList);
    });
  }

}
