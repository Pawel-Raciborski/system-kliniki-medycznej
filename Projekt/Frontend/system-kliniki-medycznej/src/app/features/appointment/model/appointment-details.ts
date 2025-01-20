import {AppointmentInfo} from './appointment-info';
import {PatientData} from '../../patient/model/patient-data';
import {DoctorInfo} from '../../doctor/domain/doctor-info';

export interface AppointmentDetails {
  appointment: AppointmentInfo;
  patientData: PatientData;
  doctor: DoctorInfo;
  patientCardId: string;
}
