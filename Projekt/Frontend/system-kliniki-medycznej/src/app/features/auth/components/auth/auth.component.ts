import { Component } from '@angular/core';
import {FormControl, FormGroup, ReactiveFormsModule} from '@angular/forms';
import {AuthService} from '../../services/auth.service';
import {TokenService} from '../../services/token.service';
import {LocalStorageService} from '../../services/local-storage.service';
import {UserService} from '../../services/user.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-auth',
  standalone: true,
  imports: [
    ReactiveFormsModule
  ],
  templateUrl: './auth.component.html',
  styleUrl: './auth.component.css'
})
export class AuthComponent {
  form: FormGroup = new FormGroup({
    login: new FormControl(''),
    password: new FormControl('')
  });

  constructor(
    private authService: AuthService,
    private tokenService: TokenService,
    private userService: UserService,
    private router: Router,
  ) {
  }

  login() {
    this.authService.login(this.form.value).subscribe(token => {
      this.tokenService.saveToken(token);
      this.userService.isAuthenticated = true;

      if(this.userService.hasRole("ADMIN")){
        this.router.navigate(['admin']);
      }else if(this.userService.hasRole("DOCTOR")){
        this.router.navigate(['doctor-panel']);
      }else if(this.userService.hasRole("RECEPTIONIST")){
        this.router.navigate(['receptionist-panel']);
      }else if(this.userService.hasRole("PATIENT")){
        this.router.navigate(['patient-panel']);
      }
    });
  }
}
