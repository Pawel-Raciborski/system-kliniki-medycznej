import {DoctorInfo} from '../../doctor/domain/doctor-info';

export interface PrescriptionInfo {
  uuid:string;
  expirationDate: string;
  doctor: DoctorInfo;
  description: string;
}
