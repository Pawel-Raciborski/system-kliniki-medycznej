import {Component, OnInit} from '@angular/core';
import {RouterLink} from "@angular/router";
import {LocalStorageService} from '../../../auth/services/local-storage.service';

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
  doctorId!: string;

  constructor(
    private localStorageService: LocalStorageService
  ) {
  }

  ngOnInit(): void {
    this.doctorId = "1";
  }

}
