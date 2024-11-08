import {Account} from '../../account/model/account';
import {PersonalDetails} from '../../personal-details/domain/personal-details';
import {DoctorSpecialization} from './doctor-specialization';

export interface RegisterDoctorForm {
  registerAccountData: Account;
  personalDetails: PersonalDetails;
  pwzNumber: string;
  description: string;
  dateOfEmployment: string;
  doctorSpecializations: DoctorSpecialization[];
}
