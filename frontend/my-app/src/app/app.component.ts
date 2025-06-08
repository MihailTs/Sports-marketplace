import {Component, HOST_TAG_NAME} from '@angular/core';
import {Router, RouterOutlet} from '@angular/router';
import {SecondaryButtonComponent} from '../buttons/secondary-button.component';
import {PrimaryButtonComponent} from '../buttons/primary-button.component';
import {TextBoxComponent} from '../text-input/text-box.component';
import {TextAreaComponent} from '../text-input/text-area.component';
import {ToggleButtonComponent} from '../buttons/toggle-button.component';
import {CommonModule} from '@angular/common';
import {HomeComponent} from './home/home.component';
import {FooterComponent} from './footer/footer.component';
import {HeaderComponent} from './header/header.component';

@Component({
  selector: 'app-root',
  imports: [
    HomeComponent,
    CommonModule,
    RouterOutlet,
    ToggleButtonComponent,
    PrimaryButtonComponent,
    TextAreaComponent,
    SecondaryButtonComponent,
    TextBoxComponent,
    FooterComponent,
    HeaderComponent
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'Sports-marketplace';
  hideHeader: boolean = false;

  constructor(private router: Router) {
    this.router.events.subscribe(() => {
      const hiddenRoutes = ['/login', '/register'];
      this.hideHeader = hiddenRoutes.includes(this.router.url);
    });
  }
}
