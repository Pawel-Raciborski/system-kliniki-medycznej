import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {HospitalizationInfo} from '../../model/hospitalization-info';
import {FormBuilder, FormControl, FormGroup, ReactiveFormsModule} from '@angular/forms';

@Component({
  selector: 'app-update-hospitalization-dialog',
  standalone: true,
  imports: [
    ReactiveFormsModule
  ],
  templateUrl: './update-hospitalization-dialog.component.html',
  styleUrl: './update-hospitalization-dialog.component.css'
})
export class UpdateHospitalizationDialogComponent implements OnInit {
  form!: FormGroup;

  constructor(
    @Inject(MAT_DIALOG_DATA) public hospitalization: HospitalizationInfo,
    private fb: FormBuilder,
    private dialogRef: MatDialogRef<UpdateHospitalizationDialogComponent>
  ) {

  }

  ngOnInit(): void {
        this.buildForm();
    }

  private buildForm() {
    this.form = this.fb.group({
      cureDosage: new FormControl(this.hospitalization.cureDosage),
      notes: new FormControl(this.hospitalization.notes),
      finishDate: new FormControl(this.hospitalization.finishDate),
    });
  }

  update() {
    let formValues = this.form.value;

    this.hospitalization.notes = formValues.notes;
    this.hospitalization.cureDosage = formValues.cureDosage;
    this.hospitalization.finishDate = formValues.finishDate;

    this.dialogRef.close(this.hospitalization);
  }

  close() {
    this.dialogRef.close(this.hospitalization);
  }
}
