import { Component } from '@angular/core';
import {Router, RouterLink} from '@angular/router';
import {UserService} from '../../../auth/services/user.service';

@Component({
  selector: 'app-admin-menu',
  standalone: true,
  imports: [
    RouterLink
  ],
  templateUrl: './admin-menu.component.html',
  styleUrl: './admin-menu.component.css'
})
export class AdminMenuComponent {

  constructor(
    private userService: UserService,
    private router: Router
  ) {
  }

  logout() {
    this.userService.logout();
    this.router.navigate(['/auth']);
  }
}
