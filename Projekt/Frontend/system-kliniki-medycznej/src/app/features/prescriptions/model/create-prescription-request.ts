export interface CreatePrescriptionRequest {
  doctorId:number;
  patientPesel:string;
  prescriptionMedicineList: {registryNumber: string, dosage: string}[],
  expirationDate: string;
  description: string;
}
