import {Component, Input} from '@angular/core';
import {ToastComponent} from '../toast/toast.component';

@Component({
  selector: 'app-toast-list',
  standalone: true,
  imports: [
    ToastComponent
  ],
  templateUrl: './toast-list.component.html',
  styleUrl: './toast-list.component.css'
})
export class ToastListComponent {
  @Input({required: true}) notifications: {title: string, message: string}[] = [];

  deleteNotification(toDelete: boolean, notification: {title: string; message: string}) {
    if(toDelete){
      this.notifications = this.notifications.filter(notif => notif !== notification);
    }
  }
}
