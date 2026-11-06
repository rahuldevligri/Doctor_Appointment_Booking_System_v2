import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { FooterComponent } from './footer/footer.component';
import { LoginComponent } from './auth/login/login.component';
import { SignupComponent } from './auth/signup/signup.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CategoryComponent } from './category/category.component';
import { NavbarComponent } from './navbar/navbar.component';
import { DoctorlistComponent } from './doctorlist/doctorlist.component';
import { ScheduleComponent } from './schedule/schedule.component';
import { MdbModalRef, MdbModalService } from 'mdb-angular-ui-kit/modal';
import { PaymentComponent } from './payment/payment/payment.component';
import { ManageDoctorComponent } from './manage-doctor/manage-doctor.component';
import { MdbModalModule } from 'mdb-angular-ui-kit/modal';
import { CreateDoctorComponent } from './create-doctor/create-doctor.component';
import { AppoinmentSummaryComponent } from './appoinment-summary/appoinment-summary.component';
import { MatDialogModule } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { ErrorDialogComponent } from './utilities/error-dialog.component';
import { MatFormFieldModule } from '@angular/material/form-field';
import { PaymentSummaryComponent } from './payment-summary/payment-summary.component';
import { OrderlistComponent } from './orderlist/orderlist.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    FooterComponent, 
    LoginComponent,
    SignupComponent,
    CategoryComponent,
    NavbarComponent,
    DoctorlistComponent,
    ScheduleComponent,
    ManageDoctorComponent,
    CreateDoctorComponent,
    PaymentComponent,
    AppoinmentSummaryComponent,
    PaymentSummaryComponent,
    ErrorDialogComponent,
    OrderlistComponent
  ],

  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    MdbModalModule,
    MatDialogModule,
    MatFormFieldModule,
    MatButtonModule,
],

  providers: [
    MdbModalService
  ],
  schemas: [
    CUSTOM_ELEMENTS_SCHEMA,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
