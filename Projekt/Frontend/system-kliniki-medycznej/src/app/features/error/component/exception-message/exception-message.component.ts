import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA} from '@angular/material/dialog';

@Component({
  selector: 'app-exception-message',
  standalone: true,
  imports: [],
  templateUrl: './exception-message.component.html',
  styleUrl: './exception-message.component.css'
})
export class ExceptionMessageComponent {

  constructor(
    @Inject(MAT_DIALOG_DATA) public message: string
  ) {
  }
}
