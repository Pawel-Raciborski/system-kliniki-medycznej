import {HospitalizationInfo} from '../patient-card/model/hospitalization-info';
import {MedicineDto} from '../medicine/model/medicine-dto';
import {DiseaseDto} from '../disease/model/disease-dto';

export interface CreatePatientDiseaseRequest {
  patientCardId: string;
  diseaseDto: DiseaseDto;
  doctorId: number;
  description: string;
  hospitalization: {
    medicine: MedicineDto,
    dosage: string,
    notes: string
  }[];
}
