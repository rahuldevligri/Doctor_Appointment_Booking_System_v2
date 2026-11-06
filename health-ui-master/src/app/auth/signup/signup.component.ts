import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/services/user/user';
import { UserService } from 'src/app/services/user/user.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { SweetAlert2Module } from '@sweetalert2/ngx-sweetalert2';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  user:User=new User();

  constructor(private userService:UserService) { }
  registerForm=new FormGroup({
    firstName:new FormControl('',[Validators.required,Validators.minLength(3)]),
    // middleName:new FormControl('',[Validators.required,Validators.minLength(0)]),
    lastName:new FormControl('',[Validators.required,Validators.minLength(3)]),
    email:new FormControl('',[Validators.required,Validators.email]),
    password:new FormControl('',[Validators.required,Validators.minLength(6)]),
    mobile:new FormControl('',[Validators.required,Validators.minLength(10)]),
    gender:new FormControl('',[Validators.required]),
    username:new FormControl('',[Validators.required,Validators.minLength(3)]),
  });

  ngOnInit(): void {
  }
  addUser(){
    this.userService.addUser(this.user).subscribe(
      (response:any)=>{
        console.log(response);
        Swal.fire({
          title: 'Success!',
          text: 'User Registered Successfully!',
          icon: 'success',
          confirmButtonText: 'OK'
        })

        window.location.href="/login";
      },
      (error)=>{
        console.log(error);
        Swal.fire({
          title: 'Error!',
          text: 'Provide correct details!',
          icon: 'error',
          confirmButtonText: 'OK'
        })
      }
    )

  }
  get firstName(){
    return this.registerForm.get('firstName');
  }
  get lastName(){
    return this.registerForm.get('lastName');
  }
  get email(){
    return this.registerForm.get('email');
  }
  get password(){
    return this.registerForm.get('password');
  }
  get mobile(){
    return this.registerForm.get('mobile');
  }
  get username(){
    return this.registerForm.get('username');
  }
  get middleName(){
    return this.registerForm.get('middleName');
  }
  get gender(){
    return this.registerForm.get('gender')
  }
}
