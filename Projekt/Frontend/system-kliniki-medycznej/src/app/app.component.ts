import {Component, OnInit} from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {LocalStorageService} from './features/auth/services/local-storage.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit{
  title = 'system-kliniki-medycznej';


  ngOnInit(): void {
    }
}
