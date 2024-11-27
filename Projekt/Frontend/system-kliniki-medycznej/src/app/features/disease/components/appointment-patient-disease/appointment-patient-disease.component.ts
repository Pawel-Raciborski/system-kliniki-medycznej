import {Component, Input} from '@angular/core';
import {CreatePatientDiseaseRequest} from '../../../patient-disease/create-patient-disease-request';

@Component({
  selector: 'app-appointment-patient-disease',
  standalone: true,
  imports: [],
  templateUrl: './appointment-patient-disease.component.html',
  styleUrl: './appointment-patient-disease.component.css'
})
export class AppointmentPatientDiseaseComponent {
  @Input() patientDisease!: CreatePatientDiseaseRequest;
}
