import { Injectable } from '@angular/core';
import {Credentials} from '../model/credentials';
import {environment} from '../../../../environments/environment.dev';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Token} from '../model/token';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  url: string = `${environment.serverUrl}/auth`;
  constructor(
    private httpClient: HttpClient
  ) { }

  public login(credentials: Credentials) : Observable<Token>{
    console.log(credentials);
    return this.httpClient.post<Token>(`${this.url}/login`, credentials);
  }
}
