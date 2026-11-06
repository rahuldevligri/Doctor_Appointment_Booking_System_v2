import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AppoinmentSummaryService {

  private selectedCategory: string;
  private selectedDoctor: string;
  private selectedSlot: string;
  private selectedHospital: string;
  private selectedFee: number;
  private selectedScheduleId: number;
  private selectedDoctorId: number;


  constructor() { }

  setSelectedDoctorId(doctorId: number){
    this.selectedDoctorId = doctorId;
  }

  getSelectedDoctorId(){
    return this.selectedDoctorId;
  }

  setSelectedFee(fee:number){
    this.selectedFee=fee
  }

  getSelectedFee(){
    return this.selectedFee;
  }


  setSelectedCategory(category: string) {
    this.selectedCategory = category;
  }

  setSelectedDoctor(doctor: string) {
    this.selectedDoctor = doctor;
  }

  setSelectedSlot(slot: string) {
    this.selectedSlot = slot;
  }

  setSelectedHospital(hospital: string){
    this.selectedHospital = hospital
  }
  setSelectedScheduleId(scheduleId: number){
    this.selectedScheduleId = scheduleId;
  }
  

  getSelectedCategory() {
    return this.selectedCategory;
  }

  getSelectedDoctor() {
    return this.selectedDoctor;
  }

  getSelectedSlot() {
    return this.selectedSlot;
  }

  getSelectedHospital(){
    return this.selectedHospital;
  }

  getSelectedScheduleId(){
    return this.selectedScheduleId;
  }
}