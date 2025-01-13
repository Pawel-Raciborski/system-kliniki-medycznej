import {Component, Input, OnInit} from '@angular/core';
import {Role} from '../../../roles/domain/role';
import {UserService} from '../../../auth/services/user.service';
import {MatDialog} from '@angular/material/dialog';
import {RoleService} from '../../../roles/services/role.service';
import {AddRoleToUserDialogComponent} from '../../dialogs/add-role-to-user-dialog/add-role-to-user-dialog.component';

@Component({
  selector: 'app-account-roles',
  standalone: true,
  imports: [],
  templateUrl: './account-roles.component.html',
  styleUrl: './account-roles.component.css'
})
export class AccountRolesComponent implements OnInit{
  roles: Role[] = [];
  @Input() username!: string;
  constructor(
    private userService: UserService,
    private dialog: MatDialog,
    private roleService: RoleService,
  ) {
  }

  ngOnInit(): void {
    console.log(this.username);
    // this.roles = this.userService.loggedUser.roles;
    this.roleService.getAccountRoles(this.username).subscribe(data => {
      this.roles = data;
    })
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
