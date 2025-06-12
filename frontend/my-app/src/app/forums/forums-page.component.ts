import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ForumComponent } from '../../forums/forum/forum.component';
import {Forum, ForumService} from '../services/forum.service';

@Component({
  selector: 'forums-page',
  standalone: true,
  templateUrl: './forums-page.component.html',
  styleUrls: ['./forums-page.component.css'],
  imports: [CommonModule, ForumComponent]
})
export class ForumsPageComponent implements OnInit {
  forums: Forum[] = [];
  currentPage: number = 0;
  totalPages: number = 0;
  loading = true;

  constructor(private forumService: ForumService) {}

  ngOnInit(): void {
    this.loadForums(this.currentPage);
  }

  loadForums(page: number): void {
    this.loading = true;
    this.forumService.getAllForums(page).subscribe({
      next: (response) => {
        this.forums = response.content;
        this.totalPages = response.totalPages;
        this.currentPage = response.page;
        this.loading = false;
      },
      error: (err) => {
        console.error('Failed to load forums:', err);
        this.loading = false;
      }
    });
  }

  goToPage(page: number): void {
    if (page >= 0 && page < this.totalPages) {
      this.loadForums(page);
    }
  }
}
