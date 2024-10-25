import { Component } from '@angular/core';
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule} from '@angular/forms';
import {FormGeneratorService} from '../../../../services/form-generator.service';
import {MatFormField, MatLabel} from '@angular/material/form-field';
import {MatInput} from '@angular/material/input';
import {MatRadioButton, MatRadioGroup} from '@angular/material/radio';
import {MatDialogContent, MatDialogRef} from '@angular/material/dialog';
import {
  AddRoleToUserDialogComponent
} from '../../../admin/components/roles/dialogs/add-role-to-user-dialog/add-role-to-user-dialog.component';

@Component({
  selector: 'app-add-receptionist-dialog',
  standalone: true,
  imports: [
    FormsModule,
    ReactiveFormsModule,
    MatFormField,
    MatInput,
    MatLabel,
    MatRadioButton,
    MatRadioGroup,
    MatDialogContent
  ],
  templateUrl: './add-receptionist-dialog.component.html',
  styleUrl: './add-receptionist-dialog.component.css'
})
export class AddReceptionistDialogComponent {
  registerReceptionistForm!: FormGroup;

  constructor(private formGeneratorService: FormGeneratorService, private dialogRef: MatDialogRef<AddRoleToUserDialogComponent>) {
    this.registerReceptionistForm = this.formGeneratorService.generateReceptionistForm();
  }

  addReceptionist() {
    this.dialogRef.close(
    {...this.registerReceptionistForm.value}
    );
  }

  onCancel() {
    this.dialogRef.close();
  }
}
