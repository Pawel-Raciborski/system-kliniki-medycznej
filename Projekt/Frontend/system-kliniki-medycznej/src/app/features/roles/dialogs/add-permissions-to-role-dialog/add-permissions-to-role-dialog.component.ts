import {Component, Inject, OnInit} from '@angular/core';
import {Permission} from '../../../permission/model/permission';
import {RoleService} from '../../services/role.service';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {Role} from '../../domain/role';
import {PermissionService} from '../../../permission/services/permission.service';
import {PermissionsTableComponent} from '../../../permission/components/permissions-table/permissions-table.component';

@Component({
  selector: 'app-add-permissions-to-role-dialog',
  standalone: true,
  imports: [
    PermissionsTableComponent
  ],
  templateUrl: './add-permissions-to-role-dialog.component.html',
  styleUrl: './add-permissions-to-role-dialog.component.css'
})
export class AddPermissionsToRoleDialogComponent implements OnInit {
  availablePermissions!: Permission[];
  permissionsToAdd: Permission[] = [];

  constructor(
    @Inject(MAT_DIALOG_DATA) public role: Role,
    private permissionService: PermissionService,
    private dialogRef: MatDialogRef<AddPermissionsToRoleDialogComponent>
  ) {
    console.log(role);
  }

  ngOnInit(): void {
    this.permissionService.findAvailablePermissionsForRole(this.role).subscribe(data => {
      this.availablePermissions = data;
    })
  }

  removePermission(permission: Permission) {
    this.permissionsToAdd = this.permissionsToAdd.filter(p => p.id !== permission.id);
    this.availablePermissions.unshift(permission);
  }

  addPermission(permission: Permission) {
    this.availablePermissions = this.availablePermissions.filter(p => p.id !== permission.id);
    this.permissionsToAdd.push(permission);
  }

  cancel() {
    this.dialogRef.close();
  }

  submit() {
    this.dialogRef.close([...this.permissionsToAdd]);
  }
}
