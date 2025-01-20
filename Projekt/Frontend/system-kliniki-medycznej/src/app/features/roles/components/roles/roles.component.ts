import {Component, OnInit} from '@angular/core';
import {Role} from '../../domain/role';
import {RoleService} from '../../services/role.service';
import {PermissionsTableComponent} from '../../../permission/components/permissions-table/permissions-table.component';
import {RolesTableComponent} from '../roles-table/roles-table.component';
import {MatDialog} from '@angular/material/dialog';
import {AddRoleDialogComponent} from '../../dialogs/add-role-dialog/add-role-dialog.component';

@Component({
  selector: 'app-roles',
  standalone: true,
  imports: [
    PermissionsTableComponent,
    RolesTableComponent
  ],
  templateUrl: './roles.component.html',
  styleUrl: './roles.component.css'
})
export class RolesComponent implements OnInit {
  roles!: Role[];

  constructor(
    private roleService: RoleService,
    private dialog: MatDialog
  ) {

  }


  ngOnInit(): void {
    this.roleService.findAll().subscribe(data => {
      this.roles = data;
    });
  }

  openAddRoleModal() {
    this.dialog.open(AddRoleDialogComponent).afterClosed().subscribe(
      (roleToCreate: Role) => {
        if (roleToCreate) {
          this.roleService.createNewRole(roleToCreate).subscribe(createdRole => {
            this.roles.push(createdRole);
          });
        }
      }
    );
  }

  removeRole(role: Role) {
    this.roleService.remove(role).subscribe(
      removedRole => {
        this.removeRoleFromArray(removedRole);
      }
    )
  }

  private removeRoleFromArray(role: Role) {
    this.roles = this.roles.filter(r => r.id !== role.id);
  }
}
