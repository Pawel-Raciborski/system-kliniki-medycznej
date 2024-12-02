import {Component, Inject, OnInit} from '@angular/core';
import {RoleDetails} from '../../domain/role-details';
import {RoleService} from '../../services/role.service';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from '@angular/material/dialog';
import {Permission} from '../../../permission/model/permission';
import {
  AddPermissionsToRoleDialogComponent
} from '../add-permissions-to-role-dialog/add-permissions-to-role-dialog.component';

@Component({
  selector: 'app-role-details-dialog',
  standalone: true,
  imports: [],
  templateUrl: './role-details-dialog.component.html',
  styleUrl: './role-details-dialog.component.css'
})
export class RoleDetailsDialogComponent implements OnInit {
  roleDetails!: RoleDetails;

  constructor(
    @Inject(MAT_DIALOG_DATA) private roleId: number,
    private roleService: RoleService,
    private dialog: MatDialog,
    private dialogRef: MatDialogRef<RoleDetailsDialogComponent>
  ) {
  }

  ngOnInit(): void {
    this.roleService.getRoleDetails(this.roleId).subscribe(
      data => {
        this.roleDetails = data;
      }
    )
  }

  removePermissionRole(permission: Permission) {
    this.roleService.removePermissionFromRole(this.roleDetails.role, permission)
      .subscribe(removedPermission => {
        this.removePermissionFromArray(removedPermission);
      })
  }

  private removePermissionFromArray(permission: Permission) {
    this.roleDetails.permissions = this.roleDetails.permissions.filter(p => p.id !== permission.id);
  }

  addPermission() {
    this.dialog.open(AddPermissionsToRoleDialogComponent,{
      data: this.roleDetails.role
    }).afterClosed().subscribe((permissionsToAdd: Permission[]) => {
      this.roleService.addPermissionToRole(this.roleDetails.role, permissionsToAdd).subscribe(
        addedPermissions => {
          this.roleDetails.permissions.push(...addedPermissions);
        }
      )
    })
  }

  close() {
    this.dialogRef.close();
  }
}
