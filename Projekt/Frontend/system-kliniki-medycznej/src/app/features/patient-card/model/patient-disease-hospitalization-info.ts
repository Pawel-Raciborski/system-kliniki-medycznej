import {HospitalizationInfo} from './hospitalization-info';

export interface PatientDiseaseHospitalizationInfo {
  id: number;
  description: string;
  doctorFullName: string;
  detectionDate: string;
  cureStatus: string;
  finishCureDate: string;
  currentHospitalization: HospitalizationInfo;
}
