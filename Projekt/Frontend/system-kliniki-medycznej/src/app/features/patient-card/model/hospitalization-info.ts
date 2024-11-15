import {MedicineDto} from '../../medicine/model/medicine-dto';

export interface HospitalizationInfo {
  id: number;
  medicine: MedicineDto;
  cureDosage: string;
  notes: string;
  medicineUpdateDate: string;
}
