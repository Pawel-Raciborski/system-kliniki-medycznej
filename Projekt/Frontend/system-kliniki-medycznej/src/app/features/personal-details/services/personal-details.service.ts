import { Injectable } from '@angular/core';
import {PersonalDetails} from '../domain/personal-details';
import {Observable, of, throwError} from 'rxjs';
import {environment} from '../../../../environments/environment.dev';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PersonalDetailsService {
  url = `${environment.serverUrl}/personal-details`;

  constructor(
    private httpClient: HttpClient
  ) { }

  update(personalDetailsToUpdate: PersonalDetails): Observable<PersonalDetails> {
    return this.httpClient.put<PersonalDetails>(
      `${this.url}/update`,
      personalDetailsToUpdate
    );
  }
}
