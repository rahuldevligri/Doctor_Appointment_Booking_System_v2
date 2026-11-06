import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ManageDoctor } from './manage-doctor';
import { Category } from '../category/category';

@Injectable({
  providedIn: 'root'
})
export class ManageDoctorService {
  // private baseURL = "http://zumplyhealth.ap-south-1.elasticbeanstalk.com"
  private baseURL ="http://localhost:5000"

  constructor(private httpClient: HttpClient) { }

  getDoctorList(pageNo: number): Observable<ManageDoctor[]>{
    let token = localStorage.getItem('token');
    const headers = new HttpHeaders({

      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
  
    });
    const requestOptions = { headers: headers }; 
    return this.httpClient.get<ManageDoctor[]>(`${this.baseURL}/api/v1/doctor?pageNo=${pageNo}`,requestOptions);
  }

  getSpecializationList(): Observable<Category[]>{
    let token = localStorage.getItem('token');
    const headers = new HttpHeaders({

      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
  
    });
    const requestOptions = { headers: headers }; 
    return this.httpClient.get<Category[]>(`${this.baseURL}/api/v1/getCategory`,requestOptions);
  }

  addDoctor(doctorData: any): Observable<any>{
    let token = localStorage.getItem('token');
    const headers = new HttpHeaders({

      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
  
    });
    const requestOptions = { headers: headers }; 
    return this.httpClient.post<any>(`${this.baseURL}/api/v1/createdoctor`,doctorData,requestOptions);
  }
  deleteDoctor(doctorId: number): Observable<any>{
    let token = localStorage.getItem('token');
    const headers = new HttpHeaders({

      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
  
    });
    const requestOptions = { headers: headers }; 
    return this.httpClient.delete<any>(`${this.baseURL}/api/v1/doctor/${doctorId}`, requestOptions);
  }
}
