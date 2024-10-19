import {Component, Input} from '@angular/core';

@Component({
  selector: 'app-header-title',
  standalone: true,
  imports: [],
  templateUrl: './header-title.component.html',
  styleUrl: './header-title.component.css'
})
export class HeaderTitleComponent {
  @Input({required: true}) title!: string;

}
