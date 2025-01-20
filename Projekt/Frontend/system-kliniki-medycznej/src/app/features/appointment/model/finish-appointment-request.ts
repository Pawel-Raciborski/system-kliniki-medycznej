import {CreatePrescriptionRequest} from '../../prescriptions/model/create-prescription-request';
import {CreatePatientDiseaseRequest} from '../../patient-disease/create-patient-disease-request';

export interface FinishAppointmentRequest {
  appointmentId: string;
  diagnosis: string;
  appointmentPrescriptions: CreatePrescriptionRequest[];
  patientDiseases: CreatePatientDiseaseRequest[];
}
