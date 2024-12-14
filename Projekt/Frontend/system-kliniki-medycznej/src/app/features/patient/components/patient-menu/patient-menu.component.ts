import {Component, OnInit} from '@angular/core';
import {Router, RouterLink} from '@angular/router';
import {UserService} from '../../../auth/services/user.service';

@Component({
  selector: 'app-patient-menu',
  standalone: true,
  imports: [
    RouterLink
  ],
  templateUrl: './patient-menu.component.html',
  styleUrl: './patient-menu.component.css'
})
export class PatientMenuComponent implements OnInit {
  patientId!: number;

  constructor(
    private userService: UserService,
    private router: Router
  ) {
  }

  ngOnInit(): void {
    this.patientId = this.userService.id;
  }

  logout() {
      this.userService.logout();
      this.router.navigate(['/auth']);
  }
}
