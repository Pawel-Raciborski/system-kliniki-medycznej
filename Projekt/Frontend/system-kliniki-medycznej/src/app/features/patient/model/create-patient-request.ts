import {PersonalDetails} from '../../personal-details/domain/personal-details';

export interface CreatePatientRequest {
  parentPesel: string;
  personalDetails: PersonalDetails
}
