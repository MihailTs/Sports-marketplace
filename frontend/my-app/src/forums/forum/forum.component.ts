import { Component, Input } from '@angular/core';
import {DatePipe} from '@angular/common';
import {Router, RouterLink} from '@angular/router';
import {Forum} from '../../app/services/forum.service';

export interface ForumPost {
  id: string;
  content: string;
  createdAt: string;
  authorName: string;
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
