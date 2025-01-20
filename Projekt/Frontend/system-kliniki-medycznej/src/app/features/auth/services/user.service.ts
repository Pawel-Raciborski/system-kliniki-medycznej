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
    console.log(this.loggedUser);
  }

  public hasRole(roleName: string){
    // if(roleName ==="DOCTOR"){
    //   return true;
    // }
    return this.getUserRoles().some(value => value === roleName);
    // return false;
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

  logout() {
    this.isAuthenticated = false;

    this.localStorageService.clearMemory();
  }
}
