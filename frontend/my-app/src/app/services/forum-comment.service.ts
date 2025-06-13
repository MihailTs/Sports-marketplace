import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface ForumComment {
  id: string;
  content: string;
  userName: string;
  createdAt: Date;
}

@Injectable({ providedIn: 'root' })
export class ForumCommentsService {
  constructor(private http: HttpClient) {}

  getComments(forumId: string, postId: string): Observable<ForumComment[]> {
    return this.http.get<ForumComment[]>(
      `/api/forums/${forumId}/posts/${postId}/comments`
    );
  }
}
