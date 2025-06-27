import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { switchMap } from 'rxjs/operators';
import { UserDto } from './user.profile.model';

@Component({
  standalone: true,
  selector: 'app-user-profile',
  imports: [CommonModule],
  templateUrl: './user.profile.component.html',
})
export class UserProfileComponent implements OnInit {
  user!: UserDto;

  constructor(private route: ActivatedRoute, private http: HttpClient, private router:Router) {}

  ngOnInit(): void {
    this.route.paramMap.pipe(
      switchMap(params => {
        const userId = params.get('id');
        return this.http.get<UserDto>(`/api/users/${userId}`);
      })
    ).subscribe(user => {
      this.user = user;
    });
  }

  startChat() {
    // First try to find an existing chat
    this.http.get<{ id: string }>(`/api/chat/with-user/${this.user.id}`).subscribe({
      next: (chat) => {
        // Existing chat found
        this.router.navigate(['/chat', chat.id]);
      },
      error: (err) => {
        if (err.status === 404) {
          // No existing chat, create a new one
          this.http.post<{ id: string }>('/api/chat', { users: [this.user.id] }).subscribe({
            next: (newChat) => this.router.navigate(['/chat', newChat.id]),
            error: () => alert('Could not start chat. Please try again.')
          });
        } else {
          console.error('Unexpected error checking for existing chat:', err);
          alert('Something went wrong. Please try again.');
        }
      }
    });
  }
  
}
