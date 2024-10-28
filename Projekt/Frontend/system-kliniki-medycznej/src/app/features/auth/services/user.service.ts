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
    return this.userRoleNames.some(value => value === roleName);
  }

  get userRoleNames(): string[]{
    return this.loggedUser.roles.map((role) => role.name);
  }

  get username(): string{
    return this.loggedUser.username;
  }

  get email(): string{
    return this.loggedUser.email;
  }

  get id(): number{
    return 1;
  }
}
