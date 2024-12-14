import {Component, OnInit} from '@angular/core';
import {Router, RouterLink} from "@angular/router";
import {LocalStorageService} from '../../../auth/services/local-storage.service';
import {UserService} from '../../../auth/services/user.service';

@Component({
  selector: 'app-doctor-menu',
  standalone: true,
    imports: [
        RouterLink
    ],
  templateUrl: './doctor-menu.component.html',
  styleUrl: './doctor-menu.component.css'
})
export class DoctorMenuComponent implements OnInit{
  doctorId!: number;

  constructor(
    private userService: UserService,
    private router: Router
  ) {
  }

  ngOnInit(): void {
    this.doctorId = this.userService.getId("doctorId");
  }

  logout() {
    this.userService.logout();
    this.router.navigate(['/auth']);
  }
}
