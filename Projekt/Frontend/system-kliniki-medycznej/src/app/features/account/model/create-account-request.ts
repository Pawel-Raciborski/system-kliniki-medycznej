import {Account} from './account';

export interface CreateAccountRequest {
  pesel: string;
  accountCredentials: Account;
}
