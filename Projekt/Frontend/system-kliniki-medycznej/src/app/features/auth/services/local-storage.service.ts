import {Injectable} from '@angular/core';
import {LoginUserData} from '../model/login-user-data';

@Injectable({
  providedIn: 'root'
})
export class LocalStorageService {

  constructor() {
  }

  public saveMockedData() {
    let user: any = {
      patientId: 997,
      username: 'jan_kowalski',
      email: 'jan.kowalski@mail.com',
      roles: [
        {
          name: 'DOCTOR',
          description: 'Rola lekarza'
        },
        // {
        //   name: 'ADMIN',
        //   description: 'Administrator'
        // }
      ]
    }

    localStorage.setItem('user', JSON.stringify(user));
  }

  public getLoggedUserJsonData() {
    let loggedUser = localStorage.getItem('user');

    if (loggedUser) {
      return JSON.parse(loggedUser);
    }
  }

  public getUsername(): string {
    return this.getLoggedUserJsonData().username;
  }

  public getEmail(): string {
    return this.getLoggedUserJsonData().email;
  }

  public getKeyValue(keyName: string) {
    return this.getLoggedUserJsonData()[keyName];
  }
}
