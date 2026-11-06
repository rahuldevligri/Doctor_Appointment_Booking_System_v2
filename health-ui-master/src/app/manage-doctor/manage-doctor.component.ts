import { HttpClient } from '@angular/common/http';
import { Component, OnInit} from '@angular/core';
import { Router } from '@angular/router';
import { MdbModalRef, MdbModalService} from 'mdb-angular-ui-kit/modal';
import { CreateDoctorComponent } from '../create-doctor/create-doctor.component';
import { ManageDoctor } from '../services/manage-doctor/manage-doctor';
import { ManageDoctorService } from '../services/manage-doctor/manage-doctor.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-manage-doctor',
  templateUrl: './manage-doctor.component.html',
  styleUrls: ['./manage-doctor.component.css']
})
export class ManageDoctorComponent implements OnInit{
  modalRef: MdbModalRef<ManageDoctorComponent> | null=null;
  manageDoctor: ManageDoctor[] = [];
  totalRecords: number;
  currentPage: number = 0;
  totalPages: number;
  constructor(private modalService: MdbModalService,
    private manageDoctorService: ManageDoctorService) { }

  ngOnInit(): void {
    this.getDoctorList();
  }

  private getDoctorList(){
    this.manageDoctorService.getDoctorList(this.currentPage).subscribe(data => {
    const responseData = data as any;
    this.manageDoctor = responseData.content;
    this.totalRecords = responseData.totalElements;
    this.totalPages = responseData.totalPages;
    });
  }

  showModal(){
    this.modalRef = this.modalService.open( CreateDoctorComponent, {
      modalClass: 'modal-lg'
    });
  }
  getPaginationRange(): number[] {
    const range = [];
    for (let i = 1; i <= this.totalPages; i++) {
      range.push(i);
    }
    return range;
  }
  goToPage(page: number) {
    const adjustedPage = page - 1;
    if (adjustedPage >= 0 && adjustedPage < this.totalPages) {
      this.currentPage = adjustedPage;
      this.getDoctorList();
    }
  }
  deleteDoctor(doctorId: number) {
    Swal.fire({
      title: 'Confirm Deletion',
      text: 'Are you sure you want to delete this doctor?',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Delete'
    }).then((result) => {
      if (result.isConfirmed) {
        this.manageDoctorService.deleteDoctor(doctorId).subscribe(
          (response: any) => {
            Swal.fire('Deleted!', response.message, 'success');
            this.getDoctorList();
          },
          (error) => {
            Swal.fire('Error', 'Failed to delete doctor', 'error');
          }
        );
      }
    });
  }
}
