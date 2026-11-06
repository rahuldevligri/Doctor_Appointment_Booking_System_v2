import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Orderlist } from './orderlist';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class OrderlistService {
  // url = 'http://zumplyhealth.ap-south-1.elasticbeanstalk.com';
  url = 'http://localhost:5000';
  constructor(private http: HttpClient) { }

  getAppointment(): Observable<Orderlist[]> {
    return this.http.get<Orderlist[]>(`${this.url}/api/v1/userId/${localStorage.getItem('id')}`);
  }

  cancelOrder(AppointmentId:number): Observable<any> {
    const updateData = {appointmentstatus:"cancel",status:"done"};
    return this.http.put(`${this.url}/api/v1/appointmentstatus/${AppointmentId}`,updateData);
    // return this.http.put(url);
  }
  // ${localStorage.getItem('id')}
}
