import {Injectable} from '@angular/core';
import {Observable, of} from 'rxjs';
import {Role} from '../domain/role';
import {RoleDetails} from '../domain/role-details';
import {Permission} from '../../permission/model/permission';
import {environment} from '../../../../environments/environment.dev';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class RoleService {
  url = `${environment.serverUrl}/roles`;
  rolePermissionsUrl = `${environment.serverUrl}/role-permissions`;
  accountRoles = `${environment.serverUrl}/account-roles`;

  constructor(
    private httpClient: HttpClient
  ) {
  }

  get roleNames(): Observable<string[]> {
    return of(["DOCTOR",
      "RECEPTIONIST",
      "PATIENT",
      "GUEST_PATIENT"]);
  }

  createRolesForUser(roles: string[], username: string): Observable<Role[]> {
    let createdRoles: Role[] = roles.map(roles => this.createRole(roles, username));
    return of(createdRoles);
  }

  private createRole(role: string, username: string): Role {
    return {
      id: 1,
      name: role,
      description: (username + role),
    };
  }

  findAll(): Observable<Role[]> {
    return this.httpClient.get<Role[]>(`${this.url}`);

    //"RECEPTIONIST",
    //         "PATIENT",
    //         "GUEST_PATIENT"
  }

  remove(role: Role): Observable<Role> {
    return of(role);
  }

  getRoleDetails(roleId: number): Observable<RoleDetails> {
    return this.httpClient.get<RoleDetails>(
      `${this.url}/${roleId}/details`
    );
  }

  removePermissionFromRole(roleId: Role, permission: Permission): Observable<Permission> {
    return this.httpClient.delete<Permission>(`${this.rolePermissionsUrl}/delete`, {
      params: {
        role: roleId.name,
        permission: permission.name
      }
    });
  }

  addPermissionToRole(role: Role, permissionsToAdd: Permission[]): Observable<Permission[]> {
    return this.httpClient.post<Permission[]>(
      `${this.rolePermissionsUrl}/create`,
      {
        role: role,
        permissions: permissionsToAdd
      }
    );
  }

  createNewRole(roleToCreate: Role) {
    roleToCreate.id = 9900;
    return of(roleToCreate)
  }

  findAvailablePermissionsForRole(role: Role): Observable<Permission[]> {
    return this.httpClient.get<Permission[]>(
      `${this.url}/${role.id}/available-permissions`
    );
  }

  getAccountRoles(username: string): Observable<Role[]> {
    return this.httpClient.get<Role[]>(
      `${this.accountRoles}`,
      {
        params: {
          username: username
        }
      }
    );
  }
}
