import { Injectable } from '@angular/core';
import {LocalStorageService} from './local-storage.service';
import {LoginUserData} from '../model/login-user-data';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  loggedUser!: any
  isAuthenticated = false;
  constructor(private localStorageService: LocalStorageService) {
    this.loggedUser = this.localStorageService.getLoggedUserJsonData();
  }

  public hasRole(roleName: string){
    return this.getUserRoles().some(value => value === roleName);
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

  getId(key: string) : number {
    return this.localStorageService.getKeyValue(key);
  }

  getUserRoles(): string[] {
    return this.localStorageService.getKeyValue("roles");
  }
}
