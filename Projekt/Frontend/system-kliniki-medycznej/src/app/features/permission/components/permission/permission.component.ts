import {Component, OnInit} from '@angular/core';
import {DoctorsTableComponent} from "../../../doctor/components/doctor-table/doctors-table/doctors-table.component";
import {PermissionsTableComponent} from '../permissions-table/permissions-table.component';
import {Permission} from '../../model/permission';
import {PermissionService} from '../../services/permission.service';
import {MatDialog} from '@angular/material/dialog';
import {
  CreatePermissionDialogComponent
} from '../../dialogs/create-permission-dialog/create-permission-dialog.component';
import {MessageDialogComponent} from '../../../message/message-dialog/message-dialog.component';

@Component({
  selector: 'app-permission',
  standalone: true,
  imports: [
    DoctorsTableComponent,
    PermissionsTableComponent
  ],
  templateUrl: './permission.component.html',
  styleUrl: './permission.component.css'
})
export class PermissionComponent implements OnInit{
  permissions: Permission[] = [];

  constructor(
    private permissionService: PermissionService,
    private dialog: MatDialog
  ) {
  }
  ngOnInit(): void {
    this.permissionService.findAllPermissions().subscribe(d => {
      this.permissions = d;
    });
  }

  openAddPermissionModal() {
    this.dialog.open(CreatePermissionDialogComponent,{
      minWidth: '450px'
    }).afterClosed().subscribe((permissionNameToCreate: Permission) => {
      if(permissionNameToCreate){
        this.permissionService.create(permissionNameToCreate).subscribe({
          next: p => {
            this.permissions.push(p);
            this.dialog.open(MessageDialogComponent,{
              data: {
                message: 'Pomyślnie utworzono permisję',
                type: 'success'
              }
            });
          },
          error: err => {
            this.dialog.open(MessageDialogComponent,{
              data: {
                message: err.error,
                type: 'error'
              }
            });
          }
          }
        )
      }
    })
  }

  removePermission(permissionToRemove: Permission) {
    this.permissionService.remove(permissionToRemove).subscribe(
      removedPermission => {
        this.removePermissionFromArray(removedPermission)
      }
    )
  }

  private removePermissionFromArray(removedPermission: Permission) {
    this.permissions = this.permissions.filter(p => p.id !== removedPermission.id);
  }
}
