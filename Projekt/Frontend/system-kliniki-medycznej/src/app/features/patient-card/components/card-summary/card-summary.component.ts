import {Component, Input, OnInit} from '@angular/core';
import {RouterLink} from '@angular/router';
import {PatientCardService} from '../../services/patient-card.service';
import {CardSummary} from '../../model/card-summary';
import {UserService} from '../../../auth/services/user.service';
import {MatDialog} from '@angular/material/dialog';
import {
  UpdatePatientDetailsDialogComponent
} from '../../../patient-details/dialogs/update-patient-details-dialog/update-patient-details-dialog.component';
import {PatientDetails} from '../../../patient/model/patient-details';
import {PatientDetailsService} from '../../../patient-details/services/patient-details.service';
import {DatePipe} from '@angular/common';

@Component({
  selector: 'app-card-summary',
  standalone: true,
  imports: [
    RouterLink,
    DatePipe
  ],
  templateUrl: './card-summary.component.html',
  styleUrl: './card-summary.component.css'
})
export class CardSummaryComponent implements OnInit{
  @Input({required:true}) patientCardId!: string;
  cardSummary!: CardSummary;
  constructor(
    private patientCardService: PatientCardService,
    public userService: UserService,
    private dialog: MatDialog,
    private patientDetailsService: PatientDetailsService
  ) {
  }

  ngOnInit(): void {
        this.patientCardService.getCardSummary(this.patientCardId).subscribe(
          data => {
            console.log(data);
            this.cardSummary = data;
          }
        );
    }

  editPatientDetails() {
    console.log(this.cardSummary.patientDetails);
    if(this.userService.hasRole("RECEPTIONIST") || this.userService.hasRole("DOCTOR")){
      this.dialog.open(UpdatePatientDetailsDialogComponent,{
        data: this.cardSummary.patientDetails
      }).afterClosed().subscribe((patientDetailsToUpdate: PatientDetails) => {
        if(patientDetailsToUpdate){

          if(patientDetailsToUpdate.id){
            this.patientDetailsService.updatePatientDetails(patientDetailsToUpdate).subscribe(
              updatedPatientDetails => {
                this.cardSummary.patientDetails = updatedPatientDetails;
              }
            );
          }else {
            this.patientDetailsService.updatePatientDetails(patientDetailsToUpdate, this.cardSummary.pesel).subscribe(
              updatedPatientDetails => {
                this.cardSummary.patientDetails = updatedPatientDetails;
              }
            );
          }

        }
      });
    }
  }
}
