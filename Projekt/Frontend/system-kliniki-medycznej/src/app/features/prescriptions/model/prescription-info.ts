import {DoctorInfo} from '../../doctor/domain/doctor-info';

export interface PrescriptionInfo {
  uuid:string;
  expiration_date: string;
  doctor: DoctorInfo
  description: string;
}
