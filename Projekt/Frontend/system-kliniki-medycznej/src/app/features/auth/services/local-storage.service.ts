import {Injectable} from '@angular/core';
import {LoginUserData} from '../model/login-user-data';
import {JwtPayload} from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class LocalStorageService {

  constructor() {
  }

  public saveMockedData() {
    let user: any = {
      doctorId: 997,
      // patientId: 23,
      username: 'jan_kowalski',
      email: 'jan.kowalski@mail.com',
      roles: [
        {
          name: 'DOCTOR',
          description: 'Rola lekarza'
        },
        // {
        //   name: 'PATIENT',
        //   description: 'Rola pacjenta'
        // },
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

  save(key: string, value: any) {
    localStorage.setItem(key,JSON.stringify(value));
  }

  clearMemory() {
    localStorage.clear();
  }
}
