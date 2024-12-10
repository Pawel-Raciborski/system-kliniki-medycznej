import {Role} from '../../roles/domain/role';

export interface LoginUserData {
  id: number;
  username: string;
  email: string;
  roles: Role[]
}
