import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/services/user/user';
import { UserService } from 'src/app//services/user/user.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  user:User=new User();
  constructor(private userService:UserService) { }

  ngOnInit(): void {
  }
  login(){
    this.userService.generateToken(this.user).subscribe(
      (response:any)=>{
        console.log(response);
        localStorage.setItem('token',response.token);
        localStorage.setItem('user',response.username);
        localStorage.setItem('id',response.id);
        this.userService.loginUser(response.token);
        Swal.fire({
          title: 'Success!',
          text: 'User Logged In Successfully!',
          icon: 'success',
          confirmButtonText: 'OK'
        })
        window.location.href="/home";
      },
      (error)=>{
        console.log(error);
        Swal.fire({
          title: 'Error!',
          text: 'Invalid Credentials!',
          icon: 'error',
          confirmButtonText: 'OK'
        })
      }
    );
    }

  signup(){
    window.location.href="/signup";
  }

}