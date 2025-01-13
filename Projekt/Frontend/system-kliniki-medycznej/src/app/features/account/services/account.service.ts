import { Injectable } from '@angular/core';
import {AccountInfo} from '../model/account-info';
import {Observable, of, throwError} from 'rxjs';
import {ChangePassword} from '../../model/change-password';
import {ChangePasswordForm} from '../../model/change-password-form';
import {CreateAccountRequest} from '../model/create-account-request';
import {environment} from '../../../../environments/environment.dev';
import {HttpClient, HttpResponse} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AccountService {
  url: string = `${environment.serverUrl}/account`;

  constructor(
    private httpClient: HttpClient
  ) { }

  updateAccount(accountInfo: AccountInfo,username:string) :Observable<AccountInfo> {
    return this.httpClient.put<AccountInfo>(
      `${this.url}/update-account-details`,
      accountInfo,
      {
        params: {
          username: username
        }
      }
    )
  }

  updatePassword(changePasswordForm: ChangePasswordForm) {
    return this.httpClient.put<void>(
      `${this.url}/change-password`,
      changePasswordForm,
      {
        observe: 'response'
      }
    );
  }


}
