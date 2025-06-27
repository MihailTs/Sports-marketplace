import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {PagedResponse} from './paged-response';
import {Forum} from './forum.service';

export interface ForumPost {
  id: string;
  title: string;
  content: string;
  userName: string;
  userId: string;
}

@Injectable({ providedIn: 'root' })
export class ForumPostsService {
  apiUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {}

  getAllPosts(forumId: string, page: number = 0, size: number = 30): Observable<PagedResponse<ForumPost>> {
    const params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());

    return this.http.get<PagedResponse<ForumPost>>(`${this.apiUrl}/api/forums/${forumId}/posts`, { params });
  }

  createPost(forumId: string, post: {
    title: string;
    userId: string | undefined;
    content: string
  }): Observable<ForumPost> {
    return this.http.post<ForumPost>(`${this.apiUrl}/api/forums/${forumId}/posts`, post);
  }

}
