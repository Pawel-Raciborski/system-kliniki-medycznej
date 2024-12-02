import { Injectable } from '@angular/core';
import {Observable, of} from 'rxjs';
import {Permission} from '../model/permission';

@Injectable({
  providedIn: 'root'
})
export class PermissionService {

  constructor() { }

  findAllPermissions() : Observable<Permission[]>{
    return of([]);
  }

  create(permissionName: string) : Observable<Permission> {
    return of({
      id: 1,
      name: permissionName
    })
  }

  remove(permissionToRemove: Permission): Observable<Permission> {
    return of(permissionToRemove);
  }
}
