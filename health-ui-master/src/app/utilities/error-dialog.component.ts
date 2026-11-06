import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-error-dialog',
  template: `
    <h1 mat-dialog-title>Error</h1>
    <div mat-dialog-content class="dialog-content">
      <p>{{ errorMessage.errorMessage}}</p>
    </div>
    <div mat-dialog-actions class="dialog-actions">
      <button mat-button [mat-dialog-close]="'close'" >Close</button>
    </div>
  `,
    styles: [`
    .dialog-content {
      max-width: 300px;
      word-wrap: break-word;
    }

    .dialog-actions {
      justify-content: flex-end;
    }
  `],
})
export class ErrorDialogComponent {
  constructor(@Inject(MAT_DIALOG_DATA) public errorMessage: any) {}
}

