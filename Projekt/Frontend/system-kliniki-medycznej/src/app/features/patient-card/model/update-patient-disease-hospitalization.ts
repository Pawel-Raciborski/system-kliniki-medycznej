import {HospitalizationInfo} from './hospitalization-info';

export interface UpdatePatientDiseaseHospitalization {
  hospitalization: HospitalizationInfo;
  patientDiseaseId: number;
}
