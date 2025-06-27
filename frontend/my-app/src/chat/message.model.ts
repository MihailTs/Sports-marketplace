export interface MessageDto {
  chatId: string;
  content: string;
}

export interface MessageResponseDto {
  chatId: string;
  senderName: string;
  content: string;
  sentAt: string;
}
