import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user/user.service';
import { ErrorDialogComponent } from '../utilities/error-dialog.component';


@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})


export class NavbarComponent implements OnInit{

    loggedIn: boolean = false;
    constructor(private userService: UserService, private router: Router, private dialog: MatDialog) { }
    ngOnInit(): void {
      this.loggedIn = this.userService.isLoggedIn();
    }
    logout() {
      this.userService.logout();
      location.reload();
      this.loggedIn = false;
      window.location.href="/"
      
    }
    username = this.userService.getUserName();

    navigateComponent() {
      if (this.userService.isAdmin()) {
        this.router.navigate(['/createdoctor']);
      }
      else {
        this.showErrorMessage('You are not authorized to access this feature.');
      }
    }
    
    showErrorMessage(message: string) {
      this.dialog.open(ErrorDialogComponent, {
        data: { errorMessage: message }
      });
    }
  }


