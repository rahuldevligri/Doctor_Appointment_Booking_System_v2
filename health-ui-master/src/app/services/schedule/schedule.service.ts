import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Schedule } from './schedule';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ScheduleService {
  // private baseURL = "http://zumplyhealth.ap-south-1.elasticbeanstalk.com"
  private baseURL = "http://localhost:5000"

  constructor(private httpClient: HttpClient) { }

  getScheduleList(doctorId: number): Observable<Schedule[]> {
    let token = localStorage.getItem('token');
    console.log(token);
    const headers = new HttpHeaders({

      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
  
    });
    const requestOptions = { headers: headers }; 
    return this.httpClient.get<Schedule[]>(`${this.baseURL}/api/v1/doctorId/${doctorId}`,requestOptions);
    
  }
}
