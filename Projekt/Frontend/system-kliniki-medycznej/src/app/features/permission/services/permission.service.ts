import {Injectable} from '@angular/core';
import {Observable, of} from 'rxjs';
import {Permission} from '../model/permission';
import {Role} from '../../roles/domain/role';

@Injectable({
  providedIn: 'root'
})
export class PermissionService {

  constructor() {
  }

  findAllPermissions(): Observable<Permission[]> {
    return of([]);
  }

  create(permission: Permission): Observable<Permission> {
    return of({
      id: 1,
      name: permission.name,
      description: permission.description
    })
  }

  remove(permissionToRemove: Permission): Observable<Permission> {
    return of(permissionToRemove);
  }

  findAvailablePermissionsForRole(role: Role) : Observable<Permission[]> {
    return of([
      {
        id: 29,
        name:"modify_1",
        description:"modify_1",
      },
      {
        id: 30,
        name:"modify_2",
        description:"modify_2",
      }
    ])
  }
}
