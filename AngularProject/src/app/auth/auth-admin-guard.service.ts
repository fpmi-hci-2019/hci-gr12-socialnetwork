import {Injectable} from '@angular/core';
import {CanActivate, Router} from '@angular/router';
import {TokenStorageService} from './token-storage.service';

@Injectable()
export class AuthAdminGuardService implements CanActivate {
  constructor(public tokenStorage: TokenStorageService, public router: Router) {}

  canActivate(): boolean {
    if (!this.tokenStorage.getToken()) {
      this.router.navigate(['login']);
      return false;
    }
    if (!this.tokenStorage.isAdmin()) {
      this.router.navigate(['certificates']);
      return false;
    }
    return true;
  }
}
