import {Component, OnInit} from '@angular/core';
import {DoctorSearchBarComponent} from '../search-bar/doctor-search-bar/doctor-search-bar.component';
import {FormGroup} from '@angular/forms';
import {DoctorInfo} from '../../domain/doctor-info';
import {DoctorService} from '../../services/doctor.service';
import {TableOptionsComponent} from '../doctor-table/table-options/table-options.component';
import {DoctorsTableComponent} from '../doctor-table/doctors-table/doctors-table.component';
import {ToastComponent} from '../../../toast/components/toast/toast.component';
import {ToastListComponent} from '../../../toast/components/toast-list/toast-list.component';
import {DatePipe} from '@angular/common';

@Component({
  selector: 'app-available-doctors',
  standalone: true,
  imports: [
    DoctorSearchBarComponent,
    TableOptionsComponent,
    DoctorsTableComponent,
    ToastComponent,
    ToastListComponent
  ],
  templateUrl: './available-doctors.component.html',
  styleUrl: './available-doctors.component.css'
})
export class AvailableDoctorsComponent implements OnInit {
  doctors: DoctorInfo[] = [];
  page = 0;
  pageSize = 10;
  notification!: {title:string,message:string} | null;

  constructor(private doctorService: DoctorService) {
  }

  ngOnInit(): void {
    this.doctorService.getPagedDoctors(this.page,this.pageSize).subscribe(doctors => {
      this.doctors = doctors;
    })
  }

  searchDoctors(formGroup: FormGroup) {
    this.doctorService.getDoctorsWithSpecifiedOptions(formGroup.value).subscribe(
      filteredDoctorSpecializations => {
        this.doctors = filteredDoctorSpecializations;
      }
    )
  }

  updatePageSize(newPageSize: number) {
    this.pageSize = newPageSize;
  }

  addNotification(appointmentCreatedInfo: {appointmentInfo:{appointmentCreated: boolean, date: string, hour: string}, doctor: {name:string, surname: string}}) {
    console.log(appointmentCreatedInfo);
    if(appointmentCreatedInfo.appointmentInfo.appointmentCreated){
      this.notification = this.buildNotification(appointmentCreatedInfo.appointmentInfo.date,appointmentCreatedInfo.appointmentInfo.hour,appointmentCreatedInfo.doctor);
    }
  }

  private buildNotification(appointmentDate: string,hour: string, doctor: { name: string; surname: string }): {
    title: string,
    message: string
  } {
    let title = "Utworzono wizytę";
    let date = new DatePipe('pl').transform(appointmentDate,'d MMM y');

    let message = `<p class="text-dark">Utworzono wizytę na godzinę <b>${hour}</b>, dnia: <b>${date}</b> do lekarza <b>${doctor.name} ${doctor.surname}</b></p>`;

    return {title,message};
  }

  hideToast(hide: boolean) {
    if(hide){
      this.notification = null;
    }
  }
}
