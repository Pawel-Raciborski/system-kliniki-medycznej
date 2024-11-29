import {PrescriptionMedicine} from './prescription-medicine';

export interface CreatePrescriptionForm {
  doctorId:number;
  patientId:number;
  prescriptionMedicineList: {registryNumber: string, dosage: string}[],
  expirationDate: string;
  description: string;
}
