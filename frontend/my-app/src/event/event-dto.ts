
export interface EventDto {
  title: string;
  description?: string;
  location: string;
  startTime: string;  // ISO string, e.g. "2025-06-26T12:00:00"
  endTime: string;    // ISO string
  sport: string;
  capacity: number;
}
