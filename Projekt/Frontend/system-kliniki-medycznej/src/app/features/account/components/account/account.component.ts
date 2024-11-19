import {Component, Input, OnInit, signal, WritableSignal} from '@angular/core';
import {FormGeneratorService} from '../../../../services/form-generator.service';
import {FormGroup, ReactiveFormsModule} from '@angular/forms';
import {AccountInfo} from '../../model/account-info';
import {AccountService} from '../../services/account.service';
import {ChangePasswordForm} from '../../../model/change-password-form';
import {ChangePassword} from '../../../model/change-password';

@Component({
  selector: 'app-account',
  standalone: true,
  imports: [
    ReactiveFormsModule
  ],
  templateUrl: './account.component.html',
  styleUrl: './account.component.css'
})
export class AccountComponent implements OnInit {
  @Input({required: true}) account!: AccountInfo;
  accountSignal!: WritableSignal<AccountInfo>;
  accountForm!: FormGroup;

  constructor(
    private formGeneratorService: FormGeneratorService,
    private accountService: AccountService
  ) {
  }

  ngOnInit(): void {
    this.accountSignal = signal(this.account);
    console.log(this.accountSignal());
    this.accountForm = this.formGeneratorService.patchAccountValues(this.account);
  }

  changePassword() {
    this.accountService.updatePassword(this.buildChangePasswordForm());
  }

  private buildChangePasswordForm(): ChangePasswordForm {
    let changePasswordForm: ChangePassword = this.accountForm.controls['changePasswordForm'].value;

    return {
      userEmail: this.accountSignal().email,
      newPassword: changePasswordForm.newPassword,
      previousPassword: changePasswordForm.currentPassword
    };
  }

  updateAccountData() {
    this.accountService.updateAccount(
      this.accountForm.controls['accountForm'].value,
      this.accountSignal().username).subscribe({
      next: value => {
        this.accountSignal.set(value);
        this.patchAccountValues();
      },
      error: err => {
        console.log(err);
        this.patchAccountValues();
      }
    });
  }

  private patchAccountValues() {
    console.log(this.accountSignal());
    this.accountForm.patchValue({
      accountForm: this.accountSignal()
    });
    console.log(this.accountForm);
  }

  cancel() {
    this.accountForm.patchValue({
      accountForm: this.accountSignal()
    });
  }

  cancelPasswordChange() {
    this.accountForm.get('changePasswordForm')?.reset();
  }
}
