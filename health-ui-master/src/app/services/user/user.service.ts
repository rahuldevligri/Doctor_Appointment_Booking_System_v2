import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from './user';
import { Observable } from 'rxjs';
import { HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  // url = 'http://zumplyhealth.ap-south-1.elasticbeanstalk.com';
  url = 'http://localhost:5000';

  constructor(private http: HttpClient) {}

  generateToken(user: User): Observable<any> {
    return this.http.post<any>(`${this.url}/auth/signin`, JSON.stringify(user), {
      headers: new HttpHeaders().set('Content-Type', 'application/json'),
    });
  }

  loginUser(token:any) {
    localStorage.setItem('token', token);
  }
  isLoggedIn() {
    let token = localStorage.getItem('token');
    if (token == undefined || token == '' || token == null) {
      return false;
    } else {
      return true;
    }
  }
  logout() {
    localStorage.removeItem('token');
    localStorage.clear();
    return true;
  }
  getToken() {
    return localStorage.getItem('token');
  }
  addUser(user: User): Observable<any> {
    return this.http.post<any>(`${this.url}/auth/signup`, JSON.stringify(user), {
      headers: new HttpHeaders().set('Content-Type', 'application/json'),
      responseType: 'text' as 'json',
    });
  }
  
  getUserName() {
    let username = localStorage.getItem('user');
    if (username != null) {
      return username;
    }
    return 'Login';
  }

  isAdmin(): boolean {
    let username = localStorage.getItem('user');
    return (username == 'admin');
  }
}