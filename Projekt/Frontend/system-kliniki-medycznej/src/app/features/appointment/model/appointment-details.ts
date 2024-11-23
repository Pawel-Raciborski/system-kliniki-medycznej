import {AppointmentInfo} from './appointment-info';
import {PatientData} from '../../patient/model/patient-data';

export interface AppointmentDetails {
  appointment: AppointmentInfo;
  patientData: PatientData;
  patientCardId: string;
}
