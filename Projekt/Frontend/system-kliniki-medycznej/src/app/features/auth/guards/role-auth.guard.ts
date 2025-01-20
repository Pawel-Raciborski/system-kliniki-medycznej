import {CanActivateFn, Router} from '@angular/router';
import {inject} from '@angular/core';
import {UserService} from '../services/user.service';

export const roleAuthGuard: CanActivateFn = (route, state) => {
  const router = inject(Router);
  const userService = inject(UserService);

  let allowedRole: string = route.data['role'];

  if(!userService.isAuthenticated){
    router.navigate(["auth"]);
    return false;
  }

  if(userService.hasRole(allowedRole)){
    return true;
  }

  router.navigate(["auth"]);
  return false;
};
