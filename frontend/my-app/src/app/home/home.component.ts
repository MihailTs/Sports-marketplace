import {Component} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {PrimaryButtonComponent} from '../../buttons/primary-button.component';
import {SecondaryButtonComponent} from '../../buttons/secondary-button.component';
import {TextBoxComponent} from '../../text-input/text-box.component';
import {FooterComponent} from '../../footer/footer.component';
import {ForumComponent} from '../../forums/forum/forum.component';

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
}
