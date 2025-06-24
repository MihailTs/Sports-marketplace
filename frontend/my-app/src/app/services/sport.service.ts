import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Sport {
  id: string;
  name: string;
}

@Injectable({
  providedIn: 'root'
})
export class SportService {
  private apiUrl = '/api/sports';

  constructor(private http: HttpClient) {}

  getSports(): Observable<Sport[]> {
    return this.http.get<Sport[]>(this.apiUrl);
  }
}
