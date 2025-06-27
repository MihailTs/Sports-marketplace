import { UserDto } from "../user/user.profile.model";

export interface Event {
  id: string;
  title: string;
  description?: string;
  location: string;
  startTime: string;  // ISO string
  endTime: string;    // ISO string
  createdAt: string;  // ISO string
  capacity: number;
  sport: string;
  createdBy: UserDto;
}
