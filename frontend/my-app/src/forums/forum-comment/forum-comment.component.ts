import { Component, Input } from '@angular/core';
import {ForumComment} from '../../app/services/forum-comment.service';
import {CommonModule} from '@angular/common';
import {Router, RouterLink} from '@angular/router';


@Component({
  selector: 'forum-comment',
  standalone: true,
  templateUrl: './forum-comment.component.html',
  styleUrls: ['./forum-comment.component.css'],
  imports: [
    CommonModule,
    RouterLink
  ]
})
export class ForumCommentComponent {
  @Input() comment!: ForumComment;
}
