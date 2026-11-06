import { Component, OnInit } from '@angular/core';
import { Doctor } from '../services/doctor/doctor';
import { DoctorListService } from '../services/doctor/doctorlist.service';
import { Router } from '@angular/router';
import { MdbModalRef, MdbModalService } from 'mdb-angular-ui-kit/modal';
import { ActivatedRoute } from '@angular/router'
import { ScheduleComponent } from '../schedule/schedule.component';
import { AppoinmentSummaryService } from '../services/appoinmentSummary/appoinment-summary.service';

@Component({
  selector: 'app-doctor-list',
  templateUrl: './doctorlist.component.html',
  styleUrls: ['./doctorlist.component.css'],
})
export class DoctorlistComponent implements OnInit {
  modalRef: MdbModalRef<ScheduleComponent> | null = null;
  doctors: Doctor[];
  id: string;
  selectedDoctor: Doctor;
  hospital: any;


  constructor(
    private doctorListService: DoctorListService,
    private route: ActivatedRoute,
    private modalService: MdbModalService,
    private appoinmentSummaryService: AppoinmentSummaryService
  ) {}
  ngOnInit(): void {
    // this.id = Number(params.get('id') );
    this.route.paramMap.subscribe((paramMap) => {
      this.id = paramMap.get('id');
    });
    console.log(this.id);
    this.getDoctorsList(this.id);
  }

  private getDoctorsList(id) {
    this.doctorListService.getDoctorsList(id).subscribe((data: Doctor[]) => {
      this.doctors = data;
      this.doctors.forEach((element) => {
        console.log(element.hospital['name']);
      });
    });
  }
  openModal(doctor) {
    this.selectedDoctor = doctor;
    console.log(doctor);
    console.log(doctor.hospital.name);
    this.appoinmentSummaryService.setSelectedDoctor(doctor["fullName"]);
    // this.appoinmentSummaryService.setSelectedHospital(doctor["hospital"]["name"]);
    this.appoinmentSummaryService.setSelectedHospital(doctor.hospital["name"]);
    this.appoinmentSummaryService.setSelectedFee(doctor["fees"]);
    this.modalRef = this.modalService.open(ScheduleComponent, {
      modalClass: 'modal-lg',
      data: {
        doctorId: doctor.id
      }
    });
  }

}