import {Component, OnInit} from '@angular/core';
import {RouterLink} from "@angular/router";
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

  constructor(
    private userService: UserService,
  ) {
  }

  ngOnInit(): void {
    this.receptionistId = this.userService.getId("receptionistId");
  }

}
