import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { ChatService } from './chat.service';
import { MessageResponseDto } from './message.model';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';




@Component({
  standalone: true,
  imports: [CommonModule,
    FormsModule
  ],
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css'],
})
export class ChatComponent implements OnInit {
  messages: MessageResponseDto[] = [];
  chatId!: string;
  loading = false;
  hasMore = true;
  newMessage:string = '';

  @ViewChild('scrollContainer') scrollContainer?: ElementRef;


  constructor(private chatService: ChatService, private route:ActivatedRoute) {}

  ngOnInit() {
    this.chatId=this.route.snapshot.paramMap.get('id')!;
    this.chatService.connect(this.chatId);
    this.loadMessages();

    this.chatService.onMessage().subscribe(msg => {
      this.messages.push(msg);
      setTimeout(() => this.scrollToBottom(), 0);
    });
  }

  ngOnDestroy() {
    this.chatService.disconnect();
  }

  loadMessages() {
    if (this.loading || !this.hasMore) return;
    this.loading = true;
  
    const scrollEl = this.scrollContainer?.nativeElement;
    const oldScrollHeight = scrollEl ? scrollEl.scrollHeight : 0;
  
    const before = this.messages[0]?.sentAt;
    this.chatService.getMessages(this.chatId, before).subscribe(data => {
      if (data.length < 50) this.hasMore = false;
  
      this.messages = [...data.reverse(), ...this.messages];
      this.loading = false;
  
      // adjust scroll position to prevent jump
      setTimeout(() => {
        if (scrollEl) {
          const newScrollHeight = scrollEl.scrollHeight;
          scrollEl.scrollTop = newScrollHeight - oldScrollHeight;
        }
      }, 0);
    });
  }
  

  onScrollTop() {
    if (!this.scrollContainer) return;

    const scrollTop = this.scrollContainer?.nativeElement.scrollTop;
    if (scrollTop === 0) this.loadMessages();
  }

  async send() {
            if (!this.newMessage.trim()) return;
    
      const content = this.newMessage.trim();
    
      // ✅ Do NOT push the message manually — wait for the WebSocket to deliver it
      await this.chatService.sendMessage(this.chatId, content);
      this.newMessage = '';
    
  }
  

  scrollToBottom() {
    if (!this.scrollContainer?.nativeElement) return;
  
    const el = this.scrollContainer.nativeElement;
    el.scrollTop = el.scrollHeight;
  }
  
}
