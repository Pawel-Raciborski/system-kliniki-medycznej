import {MedicineDto} from '../../medicine/model/medicine-dto';

export interface CreateHospitalizationRequest {
  patientDiseaseId: number;
  medicine: MedicineDto;
  dosage: string;
  notes: string;
  finishDate: string;
}
