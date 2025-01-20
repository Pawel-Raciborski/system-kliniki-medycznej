import {Component, EventEmitter, Input, Output} from '@angular/core';
import {Role} from '../../domain/role';
import {MatDialog} from '@angular/material/dialog';
import {RoleDetailsDialogComponent} from '../../dialogs/role-details-dialog/role-details-dialog.component';

@Component({
  selector: 'app-roles-table',
  standalone: true,
  imports: [],
  templateUrl: './roles-table.component.html',
  styleUrl: './roles-table.component.css'
})
export class RolesTableComponent {
  @Input() roles!: Role[];
  @Output() emitter = new EventEmitter<Role>();


  constructor(
    private dialog: MatDialog
  ) {
  }
  delete(role: Role) {
    this.emitter.emit(role);
  }

  showRoleDetails(role: Role) {
    this.dialog.open(RoleDetailsDialogComponent,{
      data: role.id
    });
  }
}
