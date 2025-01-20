import {DiseaseDto} from './disease-dto';
import {MedicineDto} from '../../medicine/model/medicine-dto';

export interface CreatePatientDiseasePart {
  disease: DiseaseDto;
  description: string;
  hospitalizations: {
    medicine: MedicineDto,
    dosage: string,
    notes: string
  }[];
}
