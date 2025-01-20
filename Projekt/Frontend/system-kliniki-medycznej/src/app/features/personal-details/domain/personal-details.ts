import {Address} from './address';

export interface PersonalDetails {
  id: number;
  pesel: string;
  name: string;
  surname: string;
  birthDate: string,
  gender: string | null;
  phoneNumber: string;
  address: Address
}
