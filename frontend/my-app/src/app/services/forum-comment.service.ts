import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface ForumComment {
  id: string;
  content: string;
  name: string;
  createdAt: Date;
  userId:string;
}

@Injectable({ providedIn: 'root' })
export class ForumCommentsService {
  constructor(private http: HttpClient) {}

  getComments(forumId: string, postId: string): Observable<ForumComment[]> {
    return this.http.get<ForumComment[]>(
      `/api/forums/${forumId}/posts/${postId}/comments`
    );
  }

  createComment(forumId: string, postId: string, comment: { content: string }): Observable<ForumComment> {
    return this.http.post<ForumComment>(
      `/api/forums/${forumId}/posts/${postId}/comments`,
      comment
    );
  }

}
