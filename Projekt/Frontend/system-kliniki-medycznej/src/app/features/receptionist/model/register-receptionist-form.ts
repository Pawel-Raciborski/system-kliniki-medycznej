import {Account} from '../../account/model/account';
import {PersonalDetails} from '../../personal-details/domain/personal-details';

export interface RegisterReceptionistForm {
  registerAccountData: Account;
  personalDetails: PersonalDetails;
  dateOfEmployment: string;
}
