import { Injectable } from '@angular/core';
import {Observable, of} from 'rxjs';
import {PrescriptionInfo} from '../model/prescription-info';
import {LocalStorageService} from '../../auth/services/local-storage.service';
import {PrescriptionDetails} from '../model/prescription-details';

@Injectable({
  providedIn: 'root'
})
export class PrescriptionService {
  private prescriptions: PrescriptionInfo[] =[
    {
      uuid: "e51f28b7-1e75-4a90-bff4-1f77d9b2d001",
      expirationDate: "2024-11-30",
      doctor: {
        id: 1,
        name: "Alice",
        surname: "Smith",
        email: "alice.smith@example.com",
        pwzNumber: "1234567",
        phoneNumber: "+1234567890",
        description: "General Practitioner",
        profileImagePath: "/images/alice_smith.jpg",
      },
      description: "Prescription for blood pressure medication",
    },
    {
      uuid: "7a47a95b-3b89-41a4-9d68-e75c1d9a4f02",
      expirationDate: "2024-12-10",
      doctor: {
        id: 2,
        name: "John",
        surname: "Doe",
        email: "john.doe@example.com",
        pwzNumber: "2345678",
        phoneNumber: "+1234567891",
        description: "Cardiologist",
        profileImagePath: "/images/john_doe.jpg",
      },
      description: "Prescription for cholesterol control medication",
    },
    {
      uuid: "f8e3b1e2-4f2d-451a-89ed-fc19c5bff303",
      expirationDate: "2024-12-20",
      doctor: {
        id: 3,
        name: "Emma",
        surname: "Johnson",
        email: "emma.johnson@example.com",
        pwzNumber: "3456789",
        phoneNumber: "+1234567892",
        description: "Dermatologist",
        profileImagePath: "/images/emma_johnson.jpg",
      },
      description: "Prescription for skin allergy treatment",
    },
    {
      uuid: "a13c32e1-f023-41d8-98f4-d15c3a5a4f04",
      expirationDate: "2024-11-25",
      doctor: {
        id: 4,
        name: "Michael",
        surname: "Brown",
        email: "michael.brown@example.com",
        pwzNumber: "4567890",
        phoneNumber: "+1234567893",
        description: "Neurologist",
        profileImagePath: "/images/michael_brown.jpg",
      },
      description: "Prescription for migraine treatment",
    },
    {
      uuid: "d14f32a2-f523-4b8f-9bd7-b25a5a3c5f05",
      expirationDate: "2025-01-15",
      doctor: {
        id: 5,
        name: "Linda",
        surname: "Taylor",
        email: "linda.taylor@example.com",
        pwzNumber: "5678901",
        phoneNumber: "+1234567894",
        description: "Pediatrician",
        profileImagePath: "/images/linda_taylor.jpg",
      },
      description: "Prescription for pediatric allergy medication",
    },
    {
      uuid: "b72f28d6-e525-4c7a-9bdf-d29c4a5d6f06",
      expirationDate: "2024-12-01",
      doctor: {
        id: 6,
        name: "David",
        surname: "Moore",
        email: "david.moore@example.com",
        pwzNumber: "6789012",
        phoneNumber: "+1234567895",
        description: "Orthopedic Surgeon",
        profileImagePath: "/images/david_moore.jpg",
      },
      description: "Prescription for post-surgery pain relief",
    },
    {
      uuid: "c91f28f7-f725-4d8b-9cdf-e35c4a5f7f07",
      expirationDate: "2025-02-05",
      doctor: {
        id: 7,
        name: "Sarah",
        surname: "Williams",
        email: "sarah.williams@example.com",
        pwzNumber: "7890123",
        phoneNumber: "+1234567896",
        description: "Endocrinologist",
        profileImagePath: "/images/sarah_williams.jpg",
      },
      description: "Prescription for hormone therapy",
    },
    {
      uuid: "d15f28f8-0725-4e8c-9edf-f45c5a6f8f08",
      expirationDate: "2024-11-18",
      doctor: {
        id: 8,
        name: "Robert",
        surname: "Miller",
        email: "robert.miller@example.com",
        pwzNumber: "8901234",
        phoneNumber: "+1234567897",
        description: "Pulmonologist",
        profileImagePath: "/images/robert_miller.jpg",
      },
      description: "Prescription for asthma control medication",
    },
    {
      uuid: "e51f29b9-0825-4f9d-9fef-065c5b8f9f09",
      expirationDate: "2024-11-12",
      doctor: {
        id: 9,
        name: "Barbara",
        surname: "Garcia",
        email: "barbara.garcia@example.com",
        pwzNumber: "9012345",
        phoneNumber: "+1234567898",
        description: "Oncologist",
        profileImagePath: "/images/barbara_garcia.jpg",
      },
      description: "Prescription for cancer treatment medication",
    },
    {
      uuid: "f21f29d0-1925-4f0f-9ff0-275c5c9f0f10",
      expirationDate: "2025-03-01",
      doctor: {
        id: 10,
        name: "James",
        surname: "Martinez",
        email: "james.martinez@example.com",
        pwzNumber: "0123456",
        phoneNumber: "+1234567899",
        description: "Gastroenterologist",
        profileImagePath: "/images/james_martinez.jpg",
      },
      description: "Prescription for digestive health medication",
    }
  ];

  constructor(
    private localStorage: LocalStorageService,
  ) { }

  public getPrescriptions(paginationOptions: {page:number,pageSize:number}): Observable<PrescriptionInfo[]> {
    console.log(paginationOptions);
    let patientId = this.localStorage.getKeyValue("patientId");
    console.log(patientId);

    const slicedPrescriptions = this.prescriptions.slice(paginationOptions.page * paginationOptions.pageSize, (paginationOptions.page + 1) * paginationOptions.pageSize)
    return of(slicedPrescriptions);
  }

  getPrescriptionDetails(prescriptionId: string):Observable<PrescriptionDetails> {
    let prescriptionDetails : PrescriptionDetails = {
      id: prescriptionId,
      doctorInfo: {
        id: 10,
        name: "James",
        surname: "Martinez",
        email: "james.martinez@example.com",
        pwzNumber: "0123456",
        phoneNumber: "+1234567899",
        description: "Gastroenterologist",
        profileImagePath: "/images/james_martinez.jpg",
      },
      description: "Prescription for managing hypertension and cholesterol",
      createdAt: "2024-11-11",
      expirationDate: "2025-11-11",
      prescriptionMedicineInfoList: [
        {
          medicineName: "Lisinopril",
          dosage: "10 mg Once daily",
        },
        {
          medicineName: "Atorvastatin",
          dosage: "20 mg Once",
        },
        {
          medicineName: "Lisinopril",
          dosage: "10 mg Once daily",
        },
        {
          medicineName: "Atorvastatin",
          dosage: "20 mg Once",
        },
        {
          medicineName: "Lisinopril",
          dosage: "10 mg Once daily",
        },
        {
          medicineName: "Atorvastatin",
          dosage: "20 mg Once",
        },

      ]
    }
    return of(prescriptionDetails);
  }
}
