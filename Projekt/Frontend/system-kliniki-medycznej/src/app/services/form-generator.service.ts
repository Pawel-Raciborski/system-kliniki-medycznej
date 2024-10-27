import {Injectable} from '@angular/core';
import {FormBuilder, FormControl, FormGroup} from '@angular/forms';
import {PersonalDetails} from '../features/personal-details/domain/personal-details';
import {AccountInfo} from '../features/account/model/account-info';
import formatDate from '../config/constants/utils';

@Injectable({
  providedIn: 'root'
})
export class FormGeneratorService {

  constructor(private formBuilder: FormBuilder) { }

  public generateReceptionistForm(){
    return this.formBuilder.group({
      registerAccountData: this.createRegisterAccountForm(),
      personalDetails: this.createPersonalDetailsForm(),
      dateOfEmployment: new FormControl(this.getCurrentDate())
    });
  }

  public createRegisterAccountForm(){
    return this.formBuilder.group({
      username: new FormControl(''),
      password: new FormControl(''),
      passwordConfirm: new FormControl(''),
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

  private getCurrentDate() {
    return new Date().toLocaleDateString().replaceAll(".","-");
  }

  patchPersonalDetails(formGroup:FormGroup, personalDetails: PersonalDetails) {
    formGroup.patchValue({
      pesel: personalDetails.pesel,
      name: personalDetails.name,
      surname: personalDetails.surname,
      phoneNumber: personalDetails.phoneNumber,
      birthDate:  formatDate(personalDetails.birthDate),
      gender: personalDetails.gender,
      address: personalDetails.address,
    });
  }

  patchAccountValues(account: AccountInfo) {
    let accountForm = this.createAccountFormWithPasswordChange();

    accountForm.patchValue({
      accountForm: {...account}
    });

    return accountForm;
  }

  createAccountForm(){
    return this.formBuilder.group({
      username: new FormControl(''),
      email: new FormControl('')
    })
  }

  createPasswordChangeForm(){
    return this.formBuilder.group({
      currentPassword: new FormControl(''),
      newPassword: new FormControl(''),
      confirmNewPassword: new FormControl(''),
    })
  }

  private createAccountFormWithPasswordChange() {
    return this.formBuilder.group({
      accountForm: this.createAccountForm(),
      changePasswordForm: this.createPasswordChangeForm()
    });
  }
}
