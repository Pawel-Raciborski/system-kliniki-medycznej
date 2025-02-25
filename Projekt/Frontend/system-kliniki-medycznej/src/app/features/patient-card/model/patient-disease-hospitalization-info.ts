import {HospitalizationInfo} from './hospitalization-info';
import {BasicDiseaseInfo} from '../../disease/model/basic-disease-info';

export interface PatientDiseaseHospitalizationInfo {
  diseaseId: number;
  description: string;
  doctorFullName: string;
  detectionDate: string;
  cureStatus: string;
  finishCureDate: string;
  currentHospitalization: HospitalizationInfo;
  diseaseInfo: BasicDiseaseInfo;
}
