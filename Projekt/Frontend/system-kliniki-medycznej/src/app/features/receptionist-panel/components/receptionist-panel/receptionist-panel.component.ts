import { Component } from '@angular/core';
import {PatientMenuComponent} from "../../../patient/components/patient-menu/patient-menu.component";
import {RouterOutlet} from "@angular/router";
import {ReceptionistMenuComponent} from '../receptionist-menu/receptionist-menu.component';

@Component({
  selector: 'app-receptionist-panel',
  standalone: true,
  imports: [
    PatientMenuComponent,
    RouterOutlet,
    ReceptionistMenuComponent
  ],
  templateUrl: './receptionist-panel.component.html',
  styleUrl: './receptionist-panel.component.css'
})
export class ReceptionistPanelComponent {

}
