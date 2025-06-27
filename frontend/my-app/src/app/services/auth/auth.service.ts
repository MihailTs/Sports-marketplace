import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {AuthResponse} from './auth.response';

@Injectable({ providedIn: 'root' })
export class AuthService {

  constructor(private http: HttpClient) {}

  login(credentials: { email: string; password: string }): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`/api/users/auth/login`, credentials);
  }

  register(userData: any): Observable<any> {
    return this.http.post('/api/users/auth/register', userData, {
      headers: { 'Content-Type': 'application/json' }
    });
  }
  
}
