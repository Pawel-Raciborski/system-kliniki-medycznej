import { Component } from '@angular/core';
import {FormControl, FormGroup, ReactiveFormsModule} from '@angular/forms';
import {MatDialogRef} from '@angular/material/dialog';

@Component({
  selector: 'app-create-permission-dialog',
  standalone: true,
  imports: [
    ReactiveFormsModule
  ],
  templateUrl: './create-permission-dialog.component.html',
  styleUrl: './create-permission-dialog.component.css'
})
export class CreatePermissionDialogComponent {
  form: FormGroup = new FormGroup({
    name: new FormControl(''),
    description: new FormControl('')
  });

  constructor(
    private dialogRef: MatDialogRef<CreatePermissionDialogComponent>
  ) {
  }

  addPermission() {
    this.dialogRef.close({
      ...this.form.value
    });
  }

  close() {
    this.dialogRef.close();
  }
}
