import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MdbModalRef } from 'mdb-angular-ui-kit/modal';
import { ManageDoctorService } from '../services/manage-doctor/manage-doctor.service';
import { Category } from '../services/category/category';
import { HospitalService } from '../services/hospital/hospital.service';
import { Hospital } from '../services/hospital/hospital';
import { HttpClient } from '@angular/common/http';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-doctor',
  templateUrl: './create-doctor.component.html',
  styleUrls: ['./create-doctor.component.css']
})
export class CreateDoctorComponent implements OnInit{
  modalRef: MdbModalRef<CreateDoctorComponent> | null=null;
  specializations: Category[];
  hospitals: Hospital[];

  constructor(private injectedModalRef: MdbModalRef<CreateDoctorComponent>,
    private manageDoctorService: ManageDoctorService,
    private hospitalService: HospitalService,
    private httpClient: HttpClient,
    private router: Router) { 
    this.modalRef = injectedModalRef;
  }

  ngOnInit(): void {
    // throw new Error('Method not implemented.');
    this.fetchspecializations();
    this.fetchHospital();
  }
  
  doctorForm= new FormGroup({
    fullName: new FormControl('',[Validators.required, Validators.minLength(6), Validators.maxLength(20)]),
    gender: new FormControl('',[Validators.required]),
    mobile: new FormControl('',[Validators.required, Validators.pattern('^[0-9]{10}')]),
    email: new FormControl('',[Validators.required ,Validators.email]),
    rating: new FormControl('',[Validators.required, Validators.min(1), Validators.max(5)]),
    registrationNumber: new FormControl('',[Validators.required,Validators.maxLength(8), Validators.minLength(3)]),
    doctorSpecialization: new FormControl('',[Validators.required]),
    hospital: new FormControl('',[Validators.required]),
    fees: new FormControl('',[Validators.required, Validators.min(100), Validators.max(10000)]),
  });
  

  fetchspecializations(){
    this.manageDoctorService.getSpecializationList().subscribe(data => {
      this.specializations = data;
    },
    error => {
      console.error('Error fetching specializations:', error);
    });
  }
  fetchHospital(){
    this.hospitalService.getHospitalList().subscribe(data =>{
      this.hospitals = data;
    },
    error => {
      console.error('Error fetching specializations:', error);
    }); 
  }

  closeModal(){
    this.modalRef?.close();
  }
  saveDoctor() {
    this.manageDoctorService.addDoctor(this.doctorForm.value).subscribe(
      (response) => {
        Swal.fire({
          title: 'Doctor has been successfully created',
          icon: 'success',
          showConfirmButton: false,
          timer: 1500,
          customClass: {
            container: 'swal-message-container'
          }
        }).then(() => {
          this.closeModal();
          this.router.navigate(['/createdoctor']);
        });
        const swalContainer = document.getElementsByClassName('swal2-container')[0] as HTMLElement;
        swalContainer.style.zIndex = '9999';
      },
      (error) => {
        Swal.fire('Error', 'Failed to create doctor', 'error');
      }
    );
  }
}