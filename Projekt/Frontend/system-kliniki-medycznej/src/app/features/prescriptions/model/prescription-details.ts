import {DoctorInfo} from '../../doctor/domain/doctor-info';
import {PrescriptionMedicineInfo} from './prescription-medicine-info';

export interface PrescriptionDetails {
  id: string;
  doctorInfo: DoctorInfo;
  description: string;
  createdAt: string;
  expirationDate: string;
  prescriptionMedicineInfoList: PrescriptionMedicineInfo[];
}
