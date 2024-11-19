import { Component } from '@angular/core';
import {PatientMenuComponent} from "../../../patient/components/patient-menu/patient-menu.component";
import {RouterOutlet} from "@angular/router";
import {DoctorMenuComponent} from '../doctor-menu/doctor-menu.component';

@Component({
  selector: 'app-doctor-panel',
  standalone: true,
  imports: [
    PatientMenuComponent,
    RouterOutlet,
    DoctorMenuComponent
  ],
  templateUrl: './doctor-panel.component.html',
  styleUrl: './doctor-panel.component.css'
})
export class DoctorPanelComponent {

}
