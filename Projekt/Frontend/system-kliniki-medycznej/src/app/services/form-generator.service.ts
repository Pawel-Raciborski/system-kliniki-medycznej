import { Injectable } from '@angular/core';
import {FormBuilder, FormControl, FormGroup} from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class FormGeneratorService {

  constructor(private formBuilder: FormBuilder) { }

  public generateReceptionistForm(){
    return this.formBuilder.group({
      registerAccountData: this.createRegisterAccountForm(),
      personalDetails: this.createPersonalDetailsForm(),
      dateOfEmployment: new FormControl('')
    });
  }

  public createRegisterAccountForm(){
    return this.formBuilder.group({
      username: new FormControl(''),
      password: new FormControl(''),
      email: new FormControl(''),
    });
  }

  public createPersonalDetailsForm(){
    return this.formBuilder.group({
      pesel: new FormControl(''),
      name: new FormControl(''),
      surname: new FormControl(''),
      birthDate: new FormControl(''),
      gender: new FormControl(''),
      phoneNumber: new FormControl(''),
      address: this.createAddressForm()
    })
  }

  public createAddressForm(){
    return this.formBuilder.group({
      street: new FormControl(''),
      apartmentNumber: new FormControl(''),
      postalCode: new FormControl(''),
      city: new FormControl('')
    });
  }
}
