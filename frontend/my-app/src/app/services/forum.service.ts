import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PagedResponse } from './paged-response';

export interface Sport {
  id: string,
  name: string
}

export interface Forum {
  id: string,
  title: string,
  description: string,
  sport: Sport
}

@Injectable({ providedIn: 'root' })
export class ForumService {
  apiUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {}

  getAllForums(page: number = 0, size: number = 20): Observable<PagedResponse<Forum>> {
    const params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());

    return this.http.get<PagedResponse<Forum>>(`${this.apiUrl}/api/forums`, { params });
  }
}
