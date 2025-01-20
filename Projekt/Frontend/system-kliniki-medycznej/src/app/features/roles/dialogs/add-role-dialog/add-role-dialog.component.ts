import { Component } from '@angular/core';
import {FormControl, FormGroup, ReactiveFormsModule} from '@angular/forms';
import {MatDialogRef} from '@angular/material/dialog';

@Component({
  selector: 'app-add-role-dialog',
  standalone: true,
  imports: [
    ReactiveFormsModule
  ],
  templateUrl: './add-role-dialog.component.html',
  styleUrl: './add-role-dialog.component.css'
})
export class AddRoleDialogComponent {
  form: FormGroup = new FormGroup({
    name: new FormControl(''),
    description: new FormControl(''),
  });

  constructor(
    private dialogRef: MatDialogRef<AddRoleDialogComponent>
  ) {
  }

  addRole(){
    this.dialogRef.close({...this.form.value});
  }

  close() {
    this.dialogRef.close();
  }
}
