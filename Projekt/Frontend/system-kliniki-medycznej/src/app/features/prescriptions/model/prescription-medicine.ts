import {MedicineDto} from '../../medicine/model/medicine-dto';

export interface PrescriptionMedicine {
  medicine: MedicineDto;
  dosage: string
}
