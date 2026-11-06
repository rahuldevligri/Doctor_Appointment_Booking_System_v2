import { NgModule } from '@angular/core';
import { FooterComponent } from './footer/footer.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './auth/login/login.component';
import { SignupComponent } from './auth/signup/signup.component';
import { CategoryComponent } from './category/category.component';
import { Routes, RouterModule} from '@angular/router';
import { DoctorlistComponent } from './doctorlist/doctorlist.component';
import { ScheduleComponent } from './schedule/schedule.component';
import { PaymentComponent } from './payment/payment/payment.component';
import { ManageDoctorComponent } from './manage-doctor/manage-doctor.component';
import { AppoinmentSummaryComponent } from './appoinment-summary/appoinment-summary.component';
import { PaymentSummaryComponent } from './payment-summary/payment-summary.component';
import { OrderlistComponent } from './orderlist/orderlist.component';

import { AuthGuard } from './auth.guard';


const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: SignupComponent },
  { path: 'category', component:CategoryComponent,canActivate:[AuthGuard]},
  { path: 'doctorlist/:id', component: DoctorlistComponent},
  { path: 'payment', component: PaymentComponent},
  { path: 'createdoctor', component: ManageDoctorComponent},
  { path: 'schedule', component: ScheduleComponent},
  { path: 'summary', component: AppoinmentSummaryComponent},
  { path: 'payment-summary/:id', component: PaymentSummaryComponent},
  {path:'orderlist',component:OrderlistComponent}
  // { path: 'summary/:category/:doctor/:slot', component: AppoinmentSummaryComponent}


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }