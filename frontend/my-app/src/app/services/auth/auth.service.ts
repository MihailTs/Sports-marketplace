import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {AuthResponse} from './auth.response';

@Injectable({ providedIn: 'root' })
export class AuthService {

  //To be placed in an .env file in the future
  apiUrl = 'http://localhost:8080'

  constructor(private http: HttpClient) {}

  login(credentials: { email: string; password: string }): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.apiUrl}/api/users/auth/login`, credentials);
  }

  register(userData: any): Observable<string> {
    return this.http.post(`${this.apiUrl}/api/users/auth/register`, userData, { responseType: 'text' });
  }
}
