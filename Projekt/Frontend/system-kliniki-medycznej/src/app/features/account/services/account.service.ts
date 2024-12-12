import { Injectable } from '@angular/core';
import {AccountInfo} from '../model/account-info';
import {Observable, of, throwError} from 'rxjs';
import {ChangePassword} from '../../model/change-password';
import {ChangePasswordForm} from '../../model/change-password-form';
import {CreateAccountRequest} from '../model/create-account-request';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  constructor() { }

  updateAccount(accountInfo: AccountInfo,username:string) :Observable<AccountInfo> {
    console.log(`Updating data for user: ${username}`)
    console.log(`Data: ${JSON.stringify(accountInfo)}`);

    let randomNumber = Math.floor(Math.random() * 11);
    console.log(randomNumber);
    if(randomNumber%2==0){
      console.log("dwada");
      return throwError(() => new Error("Mock backend error"));
    }

    return of(accountInfo);
  }

  updatePassword(changePasswordForm: ChangePasswordForm) {
    console.log(changePasswordForm);
  }

  createPatientAccount(accountToCreate: CreateAccountRequest): Observable<any> {
    console.log(accountToCreate);
    return of();
  }
}
