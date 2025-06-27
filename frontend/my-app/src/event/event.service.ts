import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Event } from './event.model'; // Adjust path accordingly
import { EventDto } from './event-dto'; // Adjust path accordingly

@Injectable({
  providedIn: 'root',
})
export class EventService {
  private baseUrl = '/api/events';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Event[]> {
    return this.http.get<Event[]>(this.baseUrl);
  }

  getEventById(id: string): Observable<Event> {
    return this.http.get<Event>(`${this.baseUrl}/${id}`);
  }

  createEvent(eventDto: EventDto): Observable<Event> {
    return this.http.post<Event>(this.baseUrl, eventDto);
  }

  updateEvent(id: string, eventDto: Partial<EventDto>): Observable<Event> {
    return this.http.put<Event>(`${this.baseUrl}/${id}`, eventDto);
  }

  deleteEvent(id: string): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
