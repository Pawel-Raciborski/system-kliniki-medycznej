import {Component, OnInit} from '@angular/core';
import {HeaderTitleComponent} from '../../../../header-title/header-title.component';
import {Role} from '../../../../roles/domain/role';
import {UserService} from '../../../../auth/services/user.service';
import {MatDialog} from '@angular/material/dialog';
import {AddRoleToUserDialogComponent} from '../dialogs/add-role-to-user-dialog/add-role-to-user-dialog.component';
import {RoleService} from '../../../../roles/services/role.service';

@Component({
  selector: 'app-user-roles',
  standalone: true,
  imports: [
    HeaderTitleComponent
  ],
  templateUrl: './user-roles.component.html',
  styleUrl: './user-roles.component.css'
})
export class UserRolesComponent implements OnInit{
  roles: Role[] = [];
  constructor(
    private userService: UserService,
    private dialog: MatDialog,
    private roleService: RoleService,
  ) {
  }

  ngOnInit(): void {
    this.roles = this.userService.loggedUser.roles;
  }

  addRole() {
    this.dialog.open(AddRoleToUserDialogComponent,{
      width: '500px'
    })
      .afterClosed().subscribe((roles) => {
        if(roles){
          this.roleService.createRolesForUser(roles.selectedRoles,this.userService.username).subscribe(value => {
            this.roles.push(...value);
          })
        }
    })
  }

  remove(roleName: string) {
    this.roles = this.roles.filter(role => role.name !== roleName);
  }
}
