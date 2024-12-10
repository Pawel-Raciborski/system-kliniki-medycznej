import { Injectable } from '@angular/core';
import {jwtDecode} from 'jwt-decode';
import {Token} from '../model/token';
import {LocalStorageService} from './local-storage.service';

@Injectable({
  providedIn: 'root'
})
export class TokenService {

  constructor(
    private localStorageService: LocalStorageService
  ) { }

  public decodeJwt(token: string) {
    return jwtDecode(token);
  }

  saveToken(token: Token) {
    const decodedToken = this.decodeJwt(token.token);
    this.localStorageService.save("user",decodedToken);
  }
}
