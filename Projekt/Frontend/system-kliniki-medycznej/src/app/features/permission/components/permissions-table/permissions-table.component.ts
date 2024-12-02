import {Component, EventEmitter, Input, Output} from '@angular/core';
import {Permission} from '../../model/permission';

@Component({
  selector: 'app-permissions-table',
  standalone: true,
  imports: [],
  templateUrl: './permissions-table.component.html',
  styleUrl: './permissions-table.component.css'
})
export class PermissionsTableComponent {
  @Input() permissions!: Permission[];
  @Output() emitter = new EventEmitter<Permission>();
  delete(permission: Permission) {
    this.emitter.emit(permission);
  }
}
