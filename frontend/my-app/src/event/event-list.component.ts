import { Component, OnInit } from '@angular/core';
import { EventService } from './event.service'; // adjust path
import { Router } from '@angular/router';
import { Event } from './event.model'; // your Event model interface/class
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-event-list',
  templateUrl: './event-list.component.html',
  imports:[CommonModule]
})
export class EventListComponent implements OnInit {
  events: Event[] = [];
  loading = false;

  constructor(private eventService: EventService, private router: Router) {}

  ngOnInit() {
    this.loadEvents();
  }

  loadEvents() {
    this.loading = true;
    this.eventService.getAll().subscribe({
      next: (data) => {
        this.events = data;
        this.loading = false;
      },
      error: (err) => {
        console.error('Failed to load events', err);
        this.loading = false;
      }
    });
  }

  openEvent(event: Event) {
    this.router.navigate(['/events', event.id]);
  }
}
