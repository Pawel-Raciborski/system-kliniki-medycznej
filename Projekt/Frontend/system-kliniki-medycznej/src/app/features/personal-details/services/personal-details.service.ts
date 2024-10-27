import { Injectable } from '@angular/core';
import {PersonalDetails} from '../domain/personal-details';
import {Observable, of, throwError} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PersonalDetailsService {

  constructor() { }

  update(personalDetailsToUpdate: PersonalDetails): Observable<PersonalDetails> {
    console.log(personalDetailsToUpdate);

    let randomNumber = Math.floor(Math.random() * 11);
    if(randomNumber%2==0){
      console.log(`ERROR OCCUR!`);
      return throwError(() => new Error("Mock backend error"));
    }
    return of(personalDetailsToUpdate);
  }
}
