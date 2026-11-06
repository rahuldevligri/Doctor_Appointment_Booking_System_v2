import { Component } from '@angular/core';
import { Orderlist } from '../services/orderlist/orderlist';
import { OrderlistService } from '../services/orderlist/orderlist.service';


@Component({
  selector: 'app-orderlist',
  templateUrl: './orderlist.component.html',
  styleUrls: ['./orderlist.component.css']
})
export class OrderlistComponent {
  orderlist: Orderlist[];


  constructor(private orderlistService: OrderlistService) {}
    ngOnInit() {

    this.orderlistService.getAppointment().subscribe((data: any[]) => {
      this.orderlist = data;
      // console.log(data)
    });
  }
  
  // cancelation order


  selectedOrderId: number = 0;
  showConfirmation: boolean = false;

  onOptionSelected() {
    if (this.selectedOrderId === 0) {
      this.showConfirmation = false;
    } else {
      this.showConfirmation = true;
    }
  }

  confirmCancellation() {
    this.orderlistService.cancelOrder(this.selectedOrderId).subscribe(
      () => {
        console.log(`Order ${this.selectedOrderId} cancelled`);
        this.selectedOrderId = 0;
        this.showConfirmation = false;
         // Optionally, reload the orders after cancellation
      },
      (error) => {
        console.error(`Error cancelling order ${this.selectedOrderId}`, error);
        // Handle error scenario as per your application's requirements
      }
    );
  }

  cancelCancellation() {
    this.selectedOrderId = 0;
    this.showConfirmation = false;
  }

  
}









