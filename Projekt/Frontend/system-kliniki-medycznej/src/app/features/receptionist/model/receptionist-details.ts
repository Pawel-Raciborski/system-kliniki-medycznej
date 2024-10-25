import {PersonalDetails} from '../../personal-details/domain/personal-details';

export interface ReceptionistDetails {
  username: string,
  email: string,
  personalDetails: PersonalDetails,
  dateOfEmployment: string
}
