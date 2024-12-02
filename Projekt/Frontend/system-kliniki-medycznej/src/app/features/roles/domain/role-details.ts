import {Permission} from '../../permission/model/permission';
import {Role} from './role';

export interface RoleDetails {
  role: Role;
  permissions: Permission[];
}
