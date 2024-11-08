import {Component, Input} from '@angular/core';
import {DoctorSpecialization} from '../../doctor/domain/doctor-specialization';
import {MatMenu, MatMenuItem, MatMenuTrigger} from '@angular/material/menu';
import {MatDialog} from '@angular/material/dialog';
import {
  CreateDoctorSpecializationComponent
} from '../../admin/components/doctor-management/create-doctor-specialization/create-doctor-specialization.component';
import {DoctorSpecializationService} from '../services/doctor-specialization.service';
import {DatePipe} from '@angular/common';

@Component({
  selector: 'app-doctor-specializations',
  standalone: true,
  imports: [
    MatMenu,
    MatMenuItem,
    MatMenuTrigger,
    DatePipe
  ],
  templateUrl: './doctor-specializations.component.html',
  styleUrl: './doctor-specializations.component.css'
})
export class DoctorSpecializationsComponent {
  @Input({required:true}) doctorSpecializations: DoctorSpecialization[] = [];
  @Input({required:true}) doctorPwzNumber!: string;

  constructor(
    private dialog: MatDialog,
    private doctorSpecializationService: DoctorSpecializationService
  ) {
  }

  addDoctorSpecialization() {
    this.dialog.open(CreateDoctorSpecializationComponent).afterClosed()
      .subscribe((doctorSpecializationToCreate: DoctorSpecialization) => {
        if(doctorSpecializationToCreate){
          this.doctorSpecializationService.create(doctorSpecializationToCreate).subscribe(createdDoctorSpecialization => {
            this.addDoctorSpecializationToArray(createdDoctorSpecialization);
          });
        }
    });
  }

  updateDoctorSpecializationDetails(doctorSpecialization: DoctorSpecialization) {
    this.dialog.open(CreateDoctorSpecializationComponent, {
      data: doctorSpecialization
    }).afterClosed().subscribe(doctorSpecializationToUpdate => {
      if(doctorSpecializationToUpdate){
        this.doctorSpecializationService.update(doctorSpecializationToUpdate).subscribe(updatedDoctorSpecialization => {
          this.updateDoctorSpecializationArray(updatedDoctorSpecialization);
        })
      }
    })
  }

  deleteDoctorSpecialization(doctorSpecialization: DoctorSpecialization) {
    console.log(`Usuwanie specjalizacji`, doctorSpecialization);
    this.doctorSpecializationService.delete(doctorSpecialization).subscribe(v => {
      console.log(v);
      this.deleteFromArray(doctorSpecialization);
    });
  }

  private addDoctorSpecializationToArray(doctorSpecialization: DoctorSpecialization) {
    this.doctorSpecializations.push(doctorSpecialization);
  }

  private updateDoctorSpecializationArray(doctorSpecialization: DoctorSpecialization) {
    let index = this.getIndex(doctorSpecialization);

    if(index !== -1){
      this.doctorSpecializations[index] = doctorSpecialization;
    }
  }

  private deleteFromArray(doctorSpecialization: DoctorSpecialization) {
    this.doctorSpecializations = this.doctorSpecializations.filter(d => d.id !== doctorSpecialization.id);
    console.log(this.doctorSpecializations);
  }

  private getIndex(doctorSpecialization: DoctorSpecialization) {
    return this.doctorSpecializations.findIndex(dS => dS.id === doctorSpecialization.id);
  }
}
