import { Component } from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {FormGeneratorService} from '../../../../services/form-generator.service';

@Component({
  selector: 'app-add-receptionist-dialog',
  standalone: true,
  imports: [],
  templateUrl: './add-receptionist-dialog.component.html',
  styleUrl: './add-receptionist-dialog.component.css'
})
export class AddReceptionistDialogComponent {
  registerReceptionistForm!: FormGroup;

  constructor(private formGeneratorService: FormGeneratorService) {
    this.registerReceptionistForm = formGeneratorService.generateReceptionistForm();
  }
}
