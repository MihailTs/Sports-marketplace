import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import {AuthService} from './app/services/auth/auth.service';
import {AuthStateService} from './app/services/state-services/authState.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private authService: AuthService, private authStateService: AuthStateService, private router: Router) {}

  canActivate(): boolean {
    if (this.authStateService.currentUser?.id) {
      return true;
    } else {
      this.router.navigate(['/login']);
      return false;
    }
  }
}
