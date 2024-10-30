import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-toast',
  standalone: true,
  imports: [],
  templateUrl: './toast.component.html',
  styleUrl: './toast.component.css'
})
export class ToastComponent implements OnInit{
  @Input() notification!: {title:string, message: string};
  showToast = true;
  @Output() hideToast = new EventEmitter<boolean>();
  ngOnInit(): void {
    setTimeout(() => this.hide(),7000);
  }

  hide() {
    this.hideToast.emit(true);
  }
}
