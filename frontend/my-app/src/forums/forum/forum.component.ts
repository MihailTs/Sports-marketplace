import { Component, Input } from '@angular/core';
import {DatePipe} from '@angular/common';
import {Router, RouterLink} from '@angular/router';

export interface Sport {
  id: string;
  name: string;
}

export interface ForumPost {
  id: string;
  content: string;
  createdAt: string;
  authorName: string;
}

export interface Forum {
  title: string;
  description: string;
  sport: Sport;
  createdAt: string;
  updatedAt: string;
  forumPosts: ForumPost[];
}

@Component({
  selector: 'forum',
  standalone: true,
  templateUrl: './forum.component.html',
  imports: [
    DatePipe,
    RouterLink
  ],
  styleUrls: ['./forum.component.css']
})
export class ForumComponent {
  @Input() forumContent!: Forum;
  @Input() routerLink?: string | any[];

  constructor(private router: Router) {}

  goToForum() {
    this.router.navigate(['/forums', this.forumContent.sport.id]);
  }

}
