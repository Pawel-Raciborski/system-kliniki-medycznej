import {DoctorInfo} from '../../doctor/domain/doctor-info';
import {PrescriptionMedicineInfo} from './prescription-medicine-info';
import {PatientData} from '../../patient/model/patient-data';

export interface PrescriptionDetails {
  id: string;
  doctorInfo: DoctorInfo;
  description: string;
  createdAt: string;
  expirationDate: string;
  patient: PatientData;
  prescriptionMedicineInfoList: PrescriptionMedicineInfo[];
}
