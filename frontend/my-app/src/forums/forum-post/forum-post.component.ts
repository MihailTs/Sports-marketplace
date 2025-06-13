import { Component, Input } from '@angular/core';
import {DatePipe} from '@angular/common';
import {Router, RouterLink} from '@angular/router';
import {ForumPost} from '../../app/services/forum-posts.service';

@Component({
  selector: 'forum-post',
  standalone: true,
  templateUrl: './forum-post.component.html',
  imports: [
    DatePipe,
    RouterLink
  ],
  styleUrls: ['./forum-post.component.css']
})
export class ForumPostComponent {
  @Input() postContent!: ForumPost;
  @Input() routerLink?: string | any[];

  constructor(private router: Router) {}

  goToPost() {
    this.router.navigate(['/forums/post', this.postContent.id]);
  }

}
