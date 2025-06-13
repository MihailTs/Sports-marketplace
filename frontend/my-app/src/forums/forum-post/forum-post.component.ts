import { Component, Input } from '@angular/core';
import {CommonModule, DatePipe} from '@angular/common';
import {Router, RouterLink} from '@angular/router';
import {ForumPost} from '../../app/services/forum-posts.service';

@Component({
  selector: 'forum-post',
  standalone: true,
  templateUrl: './forum-post.component.html',
  imports: [CommonModule],
  styleUrls: ['./forum-post.component.css']
})
export class ForumPostComponent {
  @Input() postContent!: ForumPost;
}
