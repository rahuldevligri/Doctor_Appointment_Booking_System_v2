import { Component, OnInit } from '@angular/core';
import { CategoryService } from 'src/app/services/category/category.service';
import { Category } from '../services/category/category';
import { Router } from '@angular/router';
import { AppoinmentSummaryService } from '../services/appoinmentSummary/appoinment-summary.service';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']

})
export class CategoryComponent implements OnInit {

  // category: any[] = [];
  category: Category[];
  selectedCategory: any;

  constructor(private categoryService: CategoryService, private router: Router, private appoinmentSummaryService: AppoinmentSummaryService) {}

  ngOnInit() {
    this.categoryService.getCategories().subscribe((data: any[]) => {
      this.category = data;
    });
  }

  // getDoctorByCategory(id:number){
  //   console.log(id);
  //   // window.location.href="/doctor/"+id;
  //   this.categoryService.getDoctorByCategory(id);
  // }

  selectCategory(category: any) {
   
    this.selectedCategory = category.specializationName;
    console.log(this.selectCategory);
    this.router.navigate(['/doctorlist', category.id]);
    this.appoinmentSummaryService.setSelectedCategory(category["specializationName"]);
  }

}


