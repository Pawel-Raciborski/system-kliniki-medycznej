import {PersonalDetails} from '../../personal-details/domain/personal-details';
import {DoctorSpecialization} from './doctor-specialization';
import {OfficeHours} from './office-hours';

export interface DoctorDetails {
  username: string,
  email: string,
  personalDetails: PersonalDetails,
  description: string,
  doctorSpecializations: DoctorSpecialization[],
  doctorOfficeHours: OfficeHours[],
  pwzNumber: string,
  dateOfEmployment: string
}
