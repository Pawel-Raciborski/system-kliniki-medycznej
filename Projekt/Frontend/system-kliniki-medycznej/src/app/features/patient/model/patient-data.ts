import {PersonalDetails} from '../../personal-details/domain/personal-details';

export interface PatientData {
  id: number;
  personalDetails: PersonalDetails;
  parentPesel: string | null;
}
