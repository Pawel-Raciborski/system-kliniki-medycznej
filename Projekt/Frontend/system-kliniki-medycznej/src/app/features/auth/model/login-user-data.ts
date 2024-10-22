import {Role} from '../../roles/domain/role';

export interface LoginUserData {
  username: string;
  email: string;
  roles: Role[]
}
