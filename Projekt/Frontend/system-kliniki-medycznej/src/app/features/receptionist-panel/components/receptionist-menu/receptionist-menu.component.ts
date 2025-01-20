import {Component, OnInit} from '@angular/core';
import {Router, RouterLink} from "@angular/router";
import {UserService} from '../../../auth/services/user.service';

@Component({
  selector: 'app-receptionist-menu',
  standalone: true,
    imports: [
        RouterLink
    ],
  templateUrl: './receptionist-menu.component.html',
  styleUrl: './receptionist-menu.component.css'
})
export class ReceptionistMenuComponent implements OnInit{
  receptionistId!: number;
  username!: string;

  constructor(
    public userService: UserService,
    private router: Router
  ) {
  }

  ngOnInit(): void {
    this.receptionistId = this.userService.getId("receptionistId");
    this.username = this.userService.username;
  }

  logout() {
      this.userService.logout();
      this.router.navigate(['/auth']);
  }
}
