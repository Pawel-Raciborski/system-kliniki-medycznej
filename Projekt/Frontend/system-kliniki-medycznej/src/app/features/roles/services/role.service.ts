import { Injectable } from '@angular/core';
import {Observable, of} from 'rxjs';
import {Role} from '../domain/role';

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
      name: role,
      description: (username + role),
    };
  }
}
