import {Component, EventEmitter, Input, Output} from '@angular/core';
import {DatePipe} from "@angular/common";
import {HospitalizationInfo} from '../../../model/hospitalization-info';
import {HospitalizationService} from '../../../../patient-disease/services/hospitalization.service';
import {UserService} from '../../../../auth/services/user.service';
import {MatDialog} from '@angular/material/dialog';
import {
  UpdateHospitalizationDialogComponent
} from '../../../dialogs/update-hospitalization-dialog/update-hospitalization-dialog.component';

@Component({
  selector: 'app-hospitalization-history',
  standalone: true,
    imports: [
        DatePipe
    ],
  templateUrl: './hospitalization-history.component.html',
  styleUrl: './hospitalization-history.component.css'
})
export class HospitalizationHistoryComponent {
  @Input({required:true}) hospitalizationHistory!: HospitalizationInfo;
  @Output() emitter = new EventEmitter<HospitalizationInfo>();

  constructor(
    private hospService: HospitalizationService,
    public userService: UserService,
    private dialog: MatDialog
  ) {
  }

  public isBefore(){
    if(!this.hospitalizationHistory.finishDate){
      return true;
    }
    let now = Date.now();
    let date = new Date(this.hospitalizationHistory.finishDate).getTime();
    return now <= date;
  }

  updateHospitalization() {
    this.dialog.open(UpdateHospitalizationDialogComponent,{
      data: this.hospitalizationHistory
    }).afterClosed().subscribe((data: HospitalizationInfo) => {
      this.hospService.updateDiseaseHospitalization(data).subscribe(
        resp => {
          this.hospitalizationHistory = data;
        }
      )
    })
  }
}
