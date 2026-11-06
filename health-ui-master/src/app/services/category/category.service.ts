import { Injectable } from '@angular/core';
import { User } from 'src/app/services/user/user';
import { Observable } from 'rxjs';
import { HttpHeaders } from '@angular/common/http';
import { HttpClient } from '@angular/common/http';
import { DoctorListService } from '../doctor/doctorlist.service';

@Injectable({
  providedIn: 'root'
})
export class CategoryService{
  // url = 'http://zumplyhealth.ap-south-1.elasticbeanstalk.com';
  url = 'http://localhost:5000';

  constructor(private http: HttpClient,private doctorListService: DoctorListService) {}
  getCategories():Observable<User[]>{
    let token = localStorage.getItem('token');
    console.log(token);
    const headers = new HttpHeaders({

      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
  
    });
    const requestOptions = { headers: headers };
    console.log("ayushi");
    console.log(`${this.url}/api/v1/getCategory`);
   
    return this.http.get<any>(`${this.url}/api/v1/getCategory`,requestOptions);
  }
  getDoctorByCategory(id:number){
    // console.log(id);
    // window.location.href="/doctor/"+id;
    console.log("ayushiss");
    console.log(`${this.url}/api/v1/doctor/${id}`);
    this.doctorListService.getDoctorsList(id);
    // return this.http.get<any>(`${this.url}/api/v1/doctor/${id}`);
    
  }


}
