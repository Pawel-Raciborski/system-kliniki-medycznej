import {Component, Input} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MatMenu, MatMenuItem, MatMenuTrigger} from '@angular/material/menu';
import {OfficeHours} from '../../doctor/domain/office-hours';
import {UserService} from '../../auth/services/user.service';
import {DoctorOfficeHoursDialogData} from '../../doctor/dialogs/models/doctor-office-hours-dialog-data';
import {MatDialog} from '@angular/material/dialog';
import {
  OfficeHoursDetailsDialogComponent
} from '../../doctor/dialogs/office-hours-details-dialog/office-hours-details-dialog.component';
import {OfficeHoursService} from '../../doctor/services/office-hours.service';
import {of} from 'rxjs';

@Component({
  selector: 'app-doctor-office-hours',
  standalone: true,
  imports: [
    FormsModule,
    MatMenu,
    MatMenuItem,
    ReactiveFormsModule,
    MatMenuTrigger
  ],
  templateUrl: './doctor-office-hours.component.html',
  styleUrl: './doctor-office-hours.component.css'
})
export class DoctorOfficeHoursComponent {
  @Input({required: true}) doctorOfficeHours: OfficeHours[] = [];
  @Input({required: true}) doctorPwzNumber!: string;

  constructor(
    public userService: UserService,
    private officeHoursService: OfficeHoursService,
    private dialog: MatDialog
  ) {
  }

  addOfficeHours() {
    let doctorOfficeHoursDialogData: DoctorOfficeHoursDialogData = {
      isNewData: true
    };

    this.dialog.open(OfficeHoursDetailsDialogComponent, {
      data: doctorOfficeHoursDialogData
    }).afterClosed().subscribe((officeHours: OfficeHours) => {
      if(officeHours){
        this.officeHoursService.create(officeHours,this.doctorPwzNumber).subscribe(
          createdOfficeHours => {
            this.addNewOfficeHours(createdOfficeHours);
          }
        );
      }
    });

  }

  updateOfficeHourDetails(oldOfficeHours: OfficeHours) {
    let doctorOfficeHoursData: DoctorOfficeHoursDialogData = {
      officeHours: oldOfficeHours,
      isNewData: false
    }

    this.dialog.open(OfficeHoursDetailsDialogComponent, {
      data: doctorOfficeHoursData
    }).afterClosed().subscribe((officeHours: OfficeHours) => {
      if(officeHours){
        this.officeHoursService.update(officeHours).subscribe(
          updatedOfficeHours => {
            this.updateOfficeHours(oldOfficeHours, updatedOfficeHours)
          }
        );
      }
    });
  }

  delete(officeHours: OfficeHours) {
    this.officeHoursService.delete(officeHours).subscribe(value => {
      console.log(value);
      this.deleteFromArray(value);
    });
  }

  private updateOfficeHours(oldOfficeHours: OfficeHours, updatedOfficeHours: OfficeHours) {
    let index = this.getIndex(oldOfficeHours);

    if(index !== -1){
      this.doctorOfficeHours[index] = updatedOfficeHours;
    }
  }

  private getIndex(oldOfficeHours: OfficeHours) {
    return this.doctorOfficeHours.findIndex(officeHour => officeHour.day === oldOfficeHours.day);
  }

  private deleteFromArray(officeHours: OfficeHours) {
    let index = this.getIndex(officeHours);
    console.log(index);
    if(index !== -1){
      this.doctorOfficeHours = this.doctorOfficeHours.filter(d => d !== officeHours);
    }
  }

  private addNewOfficeHours(officeHours: OfficeHours) {
    this.doctorOfficeHours.push(officeHours);
  }
}
