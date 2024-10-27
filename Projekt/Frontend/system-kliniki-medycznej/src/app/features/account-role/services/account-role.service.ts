import { Injectable } from '@angular/core';
import {Observable, of} from 'rxjs';
import {AccountRole} from '../model/account-role';
import {Role} from '../../roles/domain/role';

@Injectable({
  providedIn: 'root'
})
export class AccountRoleService {

  constructor() { }

  public findUserRoles(username: string): Observable<Role[]>{
    const roles: Role[] = [
      // {
      //   name: 'DOCTOR',
      //   description: 'Rola lekarza'
      // },
      {
        name: 'ADMIN',
        description: 'Administrator'
      }
    ];

    return of(roles);
  }
}
