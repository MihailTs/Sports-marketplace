import { Component, OnDestroy, OnInit } from '@angular/core';
import { ChatService } from './chat.service';
import { MessageDto, MessageResponseDto } from './message.model';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit, OnDestroy {
  chatId = 'chat-uuid-here'; // Replace with actual ID
  token = 'jwt-token-here'; // Replace with real JWT token
  messages: MessageResponseDto[] = [];
  newMessage = '';
  loading = false;
  hasMore = true;

  constructor(private chatService: ChatService) {}

  ngOnInit() {
    this.loadMessages();
    this.chatService.connect(this.token, this.chatId);
    this.chatService.onMessage().subscribe(msg => {
      this.messages.unshift(msg);
    });
  }

  ngOnDestroy() {
    this.chatService.disconnect();
  }

  loadMessages() {
    if (this.loading || !this.hasMore) return;
    this.loading = true;
    const before = this.messages.length ? this.messages[this.messages.length - 1].sentAt : undefined;
    this.chatService.getMessages(this.chatId, before).subscribe(data => {
      this.messages.push(...data.reverse());
      if (data.length < 50) this.hasMore = false;
      this.loading = false;
    });
  }

  sendMessage() {
    const message: MessageDto = {
      chatId: this.chatId,
      content: this.newMessage.trim()
    };
    this.chatService.sendMessage(message, this.token);
    this.newMessage = '';
  }
}
