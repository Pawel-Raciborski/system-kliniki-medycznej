import { Component } from '@angular/core';
import {PatientMenuComponent} from '../patient-menu/patient-menu.component';
import {RouterOutlet} from '@angular/router';

@Component({
  selector: 'app-patient-panel',
  standalone: true,
  imports: [
    PatientMenuComponent,
    RouterOutlet
  ],
  templateUrl: './patient-panel.component.html',
  styleUrl: './patient-panel.component.css'
})
export class PatientPanelComponent {

}
