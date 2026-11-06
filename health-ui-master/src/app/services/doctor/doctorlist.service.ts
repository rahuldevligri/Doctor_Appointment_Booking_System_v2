import { Injectable } from '@angular/core';
import { Doctor } from './doctor';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { DoctorlistComponent } from 'src/app/doctorlist/doctorlist.component';

@Injectable({
  providedIn: 'root'
})
export class DoctorListService {

  // private baseURL = "http://zumplyhealth.ap-south-1.elasticbeanstalk.com"
  private baseURL = "http://localhost:5000"

  constructor(private httpClient: HttpClient) { }
  getDoctorsList(id:number): Observable<Doctor[]> {
    let token = localStorage.getItem('token');
    console.log(token);
    const headers = new HttpHeaders({

      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
  
    });
    const requestOptions = { headers: headers };  
   
    return this.httpClient.get<Doctor[]>(`${this.baseURL}/api/v1/doctor/specializationId/${id}`,requestOptions);
    
  }

  getDoctorSchedule(id): Observable<Doctor[]> {
    let token = localStorage.getItem('token');
    console.log(token);
    const headers = new HttpHeaders({

      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
  
    });
    const requestOptions = { headers: headers };  
   
    return this.httpClient.get<Doctor[]>(`${this.baseURL}/doctorSchedule/doctorId/${id}`,requestOptions);
    
  }

  
  
  
}
