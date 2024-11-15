import {PatientDetails} from '../../patient/model/patient-details';

export interface CardSummary {
  fullName:string;
  pesel:string;
  birthDate:string;
  gender:string;
  patientDetails: PatientDetails,
  numberOfDoctors:number;
  numberOfAppointments:number;
  numberOfPrescriptions:number;
  numberOfHospitalizations:number;
  nextAppointmentDate:string;
}
