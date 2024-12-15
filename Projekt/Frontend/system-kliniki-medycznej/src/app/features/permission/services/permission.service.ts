import {Injectable} from '@angular/core';
import {Observable, of} from 'rxjs';
import {Permission} from '../model/permission';
import {Role} from '../../roles/domain/role';
import {environment} from '../../../../environments/environment.dev';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PermissionService {
  url = `${environment.serverUrl}/permissions`

  constructor(
    private httpClient: HttpClient
  ) {
  }

  findAllPermissions(): Observable<Permission[]> {
    return this.httpClient.get<Permission[]>(
      `${this.url}/all`
    );
  }

  create(permission: Permission): Observable<Permission> {
    return this.httpClient.post<Permission>(
      `${this.url}/create`,
      permission
    );
  }

  remove(permissionToRemove: Permission): Observable<Permission> {
    return this.httpClient.delete<Permission>(
      `${this.url}/delete`,
      {
        params: {
          name: permissionToRemove.name
        }
      }
    );
  }

}
