import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Client, IMessage, Stomp } from '@stomp/stompjs';
import * as SockJS from 'sockjs-client';
import { Observable, Subject } from 'rxjs';
import { MessageDto, MessageResponseDto } from './message.model';

@Injectable({ providedIn: 'root' })
export class ChatService {
  private apiUrl = 'http://localhost:8080/api/chat';
  private socketUrl = 'http://localhost:8080/ws-chat';
  private stompClient: Client;
  private messageSubject = new Subject<MessageResponseDto>();

  constructor(private http: HttpClient) {}

  connect(token: string, chatId: string) {
    this.stompClient = new Client({
      webSocketFactory: () => new SockJS(this.socketUrl),
      connectHeaders: {
        Authorization: `Bearer ${token}`
      },
      debug: (str) => console.log(str),
      onConnect: () => {
        this.stompClient.subscribe(`/topic/chat.${chatId}`, (message: IMessage) => {
          const msg: MessageResponseDto = JSON.parse(message.body);
          this.messageSubject.next(msg);
        });
      }
    });
    this.stompClient.activate();
  }
  disconnect() {
    this.stompClient?.deactivate();
  }

  getMessages(chatId: string, before?: string, size: number = 50): Observable<MessageResponseDto[]> {
    let params = new HttpParams().set('size', size);
    if (before) params = params.set('before', before);
    return this.http.get<MessageResponseDto[]>(`${this.apiUrl}/${chatId}/messages`, { params });
  }

  sendMessage(message: MessageDto, token: string) {
    this.stompClient.publish({
      destination: '/api/chat.sendMessage',
      body: JSON.stringify(message),
      headers: { Authorization: `Bearer ${token}` }
    });
  }

  onMessage(): Observable<MessageResponseDto> {
    return this.messageSubject.asObservable();
  }
}
