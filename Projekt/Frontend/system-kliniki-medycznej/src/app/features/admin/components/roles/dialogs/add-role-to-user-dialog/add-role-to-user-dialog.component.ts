import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {RoleService} from '../../../../../roles/services/role.service';
import {HeaderTitleComponent} from '../../../../../header-title/header-title.component';
import {MatDialogRef} from '@angular/material/dialog';

@Component({
  selector: 'app-add-role-to-user-dialog',
  standalone: true,
  imports: [
    HeaderTitleComponent
  ],
  templateUrl: './add-role-to-user-dialog.component.html',
  styleUrl: './add-role-to-user-dialog.component.css'
})
export class AddRoleToUserDialogComponent implements OnInit{
  availableRoles: string[] = [];
  selectedRolesToAdd: string[] = [];
  constructor(
    private roleService: RoleService,
    private dialogRef: MatDialogRef<AddRoleToUserDialogComponent>
  ) {

  }

  ngOnInit(): void {
    this.roleService.roleNames.subscribe(roles => {
      this.availableRoles = roles;
    });
  }

  addRole(availableRoleName: string) {
    this.availableRoles = this.availableRoles.filter(role => role !== availableRoleName);

    this.selectedRolesToAdd.push(availableRoleName);
  }

  removeRole(roleName: string) {
    this.selectedRolesToAdd = this.selectedRolesToAdd.filter(role => role !== roleName);

    this.availableRoles.push(roleName);
  }

  get hasSelectedRole():boolean{
    return this.selectedRolesToAdd.length > 0;
  }

  submitRoles() {
    this.dialogRef.close({
      selectedRoles: this.selectedRolesToAdd
    });
  }

  cancel() {
    this.dialogRef.close();
  }
}
