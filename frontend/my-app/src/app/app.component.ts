import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {SecondaryButtonComponent} from '../buttons/secondary-button.component';
import {PrimaryButtonComponent} from '../buttons/primary-button.component';
import {TextBoxComponent} from '../text-input/text-box.component';
import {TextAreaComponent} from '../text-input/text-area.component';
import {ToggleButtonComponent} from '../buttons/toggle-button.component';
import {CommonModule} from '@angular/common';
import {HeaderComponent} from '../layout/header.component';
import {FooterComponent} from '../layout/footer.component';

@Component({
  selector: 'app-root',
  imports: [CommonModule, RouterOutlet, ToggleButtonComponent, HeaderComponent, FooterComponent, PrimaryButtonComponent, TextAreaComponent, SecondaryButtonComponent, TextBoxComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'my-app';
}
