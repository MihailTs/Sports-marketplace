import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Client, IMessage, Stomp } from '@stomp/stompjs';
import SockJS from 'sockjs-client';
import { Observable, Subject } from 'rxjs';
import { MessageResponseDto } from './message.model';

@Injectable({ providedIn: 'root' })
export class ChatService {
  private stompClient?: Client;
  private messageSubject = new Subject<MessageResponseDto>();
  private connected = false;

  constructor(private http: HttpClient) {}

  private connectionPromise: Promise<void> | null = null;

  connect(chatId: string): void {
    if (this.connected) return;
  
    const token = localStorage.getItem('token'); // Get your JWT token
  
    this.connectionPromise = new Promise((resolve) => {
      this.stompClient = new Client({
        webSocketFactory: () => {
          // Attach the token as a query param to the SockJS URL
          return new SockJS(`/ws-chat?token=${token}`);
        },
        reconnectDelay: 5000,
        onConnect: () => {
          this.connected = true;
          console.log('WebSocket connected');
          this.stompClient?.subscribe(`/topic/chat.${chatId}`, (message: IMessage) => {
            console.log('Received WS message:', message.body);
            const msg: MessageResponseDto = JSON.parse(message.body);
            this.messageSubject.next(msg);
          });
          resolve();
        },
        onStompError: (frame) => {
          console.error('Broker error:', frame.headers['message']);
        },
      });
  
      this.stompClient.activate();
    });
  }
  

async sendMessage(chatId: string, content: string): Promise<void> {
  if (this.connectionPromise) await this.connectionPromise;

  if (this.connected && this.stompClient) {
    console.log('Sending WS message:', {chatId, content});
    this.stompClient.publish({
      destination: '/api/chat.sendMessage',
      body: JSON.stringify({ chatId, content }),
    });
  } else {
    console.warn('Tried to send message while not connected');
  }
}

disconnect(): void {
  this.stompClient?.deactivate();
  this.connected = false;
  this.connectionPromise = null;
}

  getMessages(chatId: string, before?: string, size: number = 50): Observable<MessageResponseDto[]> {
    let params = new HttpParams().set('size', size);
    if (before) params = params.set('before', before);

    return this.http.get<MessageResponseDto[]>(`/api/chat/${chatId}/messages`, { params });
  }

  onMessage(): Observable<MessageResponseDto> {
    return this.messageSubject.asObservable();
  }
}
