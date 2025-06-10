import {Component} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {PrimaryButtonComponent} from '../../buttons/primary-button.component';
import {SecondaryButtonComponent} from '../../buttons/secondary-button.component';
import {TextBoxComponent} from '../../text-input/text-box.component';
import {FooterComponent} from '../../footer/footer.component';
import {Forum, ForumComponent} from '../../forums/forum/forum.component';

@Component({
  selector: 'home',
  standalone: true,
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  imports: [
    CommonModule,
    RouterModule,
    PrimaryButtonComponent,
    SecondaryButtonComponent,
    TextBoxComponent,
    FooterComponent,
    ForumComponent
  ]
})
export class HomeComponent {
  hardcodedForum: Forum = {
    title: 'Football Discussions',
    description: 'Discuss everything about football here.',
    sport: {
      id: '1',
      name: 'Football'
    },
    createdAt: '2024-01-01T12:00:00Z',
    updatedAt: '2024-01-02T12:00:00Z',
    forumPosts: [
      {
        id: 'p1',
        content: 'Who do you think will win the Champions League?',
        createdAt: '2024-01-01T13:00:00Z',
        authorName: 'John Doe'
      },
      {
        id: 'p2',
        content: 'Real Madrid has a strong squad this year!',
        createdAt: '2024-01-01T14:00:00Z',
        authorName: 'Jane Smith'
      }
    ]
  };
}
