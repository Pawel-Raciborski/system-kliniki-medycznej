export interface CreatePrescriptionRequest {
  doctorId:number;
  patientId:number;
  prescriptionMedicineList: {registryNumber: string, dosage: string}[],
  expirationDate: string;
  description: string;
}
