import { Component } from '@angular/core';
import {AdminMenuComponent} from '../admin-menu/admin-menu.component';
import {RouterOutlet} from '@angular/router';

@Component({
  selector: 'app-admin-page',
  standalone: true,
  imports: [
    AdminMenuComponent,
    RouterOutlet
  ],
  templateUrl: './admin-page.component.html',
  styleUrl: './admin-page.component.css'
})
export class AdminPageComponent {

}
