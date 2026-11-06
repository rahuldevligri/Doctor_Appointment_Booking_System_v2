import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Hospital } from './hospital';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HospitalService {
  // private baseURL = "http://zumplyhealth.ap-south-1.elasticbeanstalk.com"
  private baseURL = "http://localhost:5000"
  constructor(private httpClient: HttpClient) { }

  getHospitalList(): Observable<Hospital[]>{
    let token = localStorage.getItem('token');
    const headers = new HttpHeaders({

      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
  
    });
    const requestOptions = { headers: headers }; 
    return this.httpClient.get<Hospital[]>(`${this.baseURL}/api/v1/hospital`,requestOptions);
  }
}
