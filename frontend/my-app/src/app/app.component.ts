import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {SecondaryButtonComponent} from '../buttons/secondary-button.component';
import {PrimaryButtonComponent} from '../buttons/primary-button.component';
import {TextBoxComponent} from '../text-input/text-box.component';
import {TextAreaComponent} from '../text-input/text-area.component';
import {ToggleButtonComponent} from '../buttons/toggle-button.component';
import {CommonModule} from '@angular/common';

@Component({
  selector: 'app-root',
  imports: [CommonModule, RouterOutlet, ToggleButtonComponent, PrimaryButtonComponent, TextAreaComponent, SecondaryButtonComponent, TextBoxComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'my-app';
}
