import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import {Forum, ForumComponent} from '../../forums/forum/forum.component';

@Component({
  selector: 'forums-page',
  standalone: true,
  templateUrl: './forums-page.component.html',
  styleUrls: ['./forums-page.component.css'],
  imports: [CommonModule, ForumComponent]
})
export class ForumsPageComponent {
  forums: Forum[] = [
    {
      title: 'Football Discussions',
      description: 'Talk about all things football.',
      sport: { id: '1', name: 'Football' },
      createdAt: '2024-01-01T12:00:00Z',
      updatedAt: '2024-01-03T09:00:00Z',
      forumPosts: []
    },
    {
      title: 'Basketball Talk',
      description: 'NBA, Euroleague and more.',
      sport: { id: '2', name: 'Basketball' },
      createdAt: '2024-02-10T15:00:00Z',
      updatedAt: '2024-02-12T18:30:00Z',
      forumPosts: []
    },
    {
      title: 'Tennis Fans',
      description: 'Wimbledon, US Open, and beyond.',
      sport: { id: '3', name: 'Tennis' },
      createdAt: '2024-03-01T09:30:00Z',
      updatedAt: '2024-03-05T11:45:00Z',
      forumPosts: []
    }
  ];
}
