import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {
  // private baseURL = "http://zumplyhealth.ap-south-1.elasticbeanstalk.com"
  private baseURL ="http://localhost:5000"
  constructor(private httpClient: HttpClient) { }

  getPaymentList(id:number): Observable<any> {
    let token = localStorage.getItem('token');
    console.log(token);
    const headers = new HttpHeaders({

      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
  
    });
    const requestOptions = { headers: headers }; 
    
    return this.httpClient.get<any>(`${this.baseURL}/api/v1/paymentId/${id}`,requestOptions);

  }
}
