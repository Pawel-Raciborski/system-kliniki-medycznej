import { Injectable } from '@angular/core';
import {LocalStorageService} from './local-storage.service';
import {LoginUserData} from '../model/login-user-data';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  loggedUser!: LoginUserData
  constructor(private localStorageService: LocalStorageService) {
    this.loggedUser = this.localStorageService.getLoggedUserJsonData();
  }

  public hasRole(roleName: string){
    return this.userRoles.some(value => value === roleName);
  }

  get userRoles(): string[]{
    return this.loggedUser.roles;
  }
}
