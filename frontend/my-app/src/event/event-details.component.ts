import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { EventService } from './event.service'; // adjust path
import { Event } from './event.model';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-event-details',
  templateUrl: './event-details.component.html',
  imports:[CommonModule]
})
export class EventDetailsComponent implements OnInit {
  event?: Event;
  loading = false;
  error = '';

  constructor(private route: ActivatedRoute, private eventService: EventService) {}

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id')!;
    if (id) {
      this.loading = true;
      this.eventService.getEventById(id).subscribe({
        next: (event) => {
          this.event = event;
          this.loading = false;
        },
        error: (err) => {
          this.error = 'Event not found';
          this.loading = false;
        }
      });
    }
  }
}
