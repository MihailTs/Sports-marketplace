import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

export interface AuthUser {
  id: string;
  email: string;
  firstName: string;
  lastName: string;
  token: string;
}

@Injectable({ providedIn: 'root' })
export class AuthStateService {
  private userSubject = new BehaviorSubject<AuthUser | null>(null);
  user$ = this.userSubject.asObservable();

  loadUserFromStorage() {
    if (typeof window !== 'undefined') {
      const saved = localStorage.getItem('authUser');
      if (saved) {
        this.userSubject.next(JSON.parse(saved));
      }
    }
  }

  setUser(user: AuthUser) {
    this.userSubject.next(user);
    if (typeof window !== 'undefined') {
      localStorage.setItem('authUser', JSON.stringify(user));
    }
  }

  logout() {
    if (typeof window !== 'undefined') {
      localStorage.removeItem('authUser');
    }
    this.userSubject.next(null);
  }

  get currentUser(): AuthUser | null {
    return this.userSubject.value;
  }

}
