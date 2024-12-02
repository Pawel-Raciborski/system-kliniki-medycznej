import { Injectable } from '@angular/core';
import {Observable, of} from 'rxjs';
import {Role} from '../domain/role';
import {RoleDetails} from '../domain/role-details';
import {Permission} from '../../permission/model/permission';

@Injectable({
  providedIn: 'root'
})
export class RoleService {

  constructor() { }

  get roleNames(): Observable<string[]>{
    return of(["DOCTOR",
      "RECEPTIONIST",
      "PATIENT",
      "GUEST_PATIENT"]);
  }

  createRolesForUser(roles: string[], username: string):Observable<Role[]> {
    let createdRoles: Role[] = roles.map(roles => this.createRole(roles, username));
    return of(createdRoles);
  }

  private createRole(role: string, username: string) : Role{
    return {
      id: 1,
      name: role,
      description: (username + role),
    };
  }

  findAll() : Observable<Role[]>{
    return of([
      {
        id: 1,
        name: "DOCTOR",
        description: "DOCTOR",
      },
      {
        id: 2,
        name: "RECEPTIONIST",
        description: "RECEPTIONIST",
      }
      ,
      {
        id: 3,
        name: "PATIENT",
        description: "PATIENT",
      }
    ]);

    //"RECEPTIONIST",
    //         "PATIENT",
    //         "GUEST_PATIENT"
  }

  remove(role: Role) : Observable<Role>{
    return of(role);
  }

  getRoleDetails(roleId: number): Observable<RoleDetails> {
    return of({
      role: {
        id: 1,
        name: "DOCTOR",
        description: "description",
      },
      permissions: [
        {
          id: 12,
          name: "add_cos",
          description: "opis"
        },
        {
          id: 15,
          name: "remove_calendar",
          description: "opis"
        }
      ]
    })
  }

  removePermissionFromRole(roleId: Role, permission: Permission) : Observable<Permission> {
    return of(permission);
  }

  addPermissionToRole(role: Role, permissionsToAdd: Permission[]) : Observable<Permission[]> {
    return of(permissionsToAdd);
  }
}
