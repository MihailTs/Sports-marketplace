// src/app/services/auth-state.service.ts
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

export interface AuthUser {
  id: number;
  email: string;
  firstName: string;
  lastName: string;
  token: string;
  imageUrl: string;
}

@Injectable({ providedIn: 'root' })
export class AuthStateService {
  private userSubject = new BehaviorSubject<AuthUser | null>(null);
  user$ = this.userSubject.asObservable();

  setUser(user: AuthUser) {
    this.userSubject.next(user);
    localStorage.setItem('authUser', JSON.stringify(user));
  }

  loadUserFromStorage() {
    const saved = localStorage.getItem('authUser');
    if (saved) {
      this.userSubject.next(JSON.parse(saved));
    }
  }

  logout() {
    localStorage.removeItem('authUser');
    this.userSubject.next(null);
  }

  get currentUser(): AuthUser | null {
    return this.userSubject.value;
  }
}
