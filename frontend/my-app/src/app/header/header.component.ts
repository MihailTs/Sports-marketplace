import { Component } from '@angular/core';
import {SecondaryButtonComponent} from '../../buttons/secondary-button.component';
import {PrimaryButtonComponent} from '../../buttons/primary-button.component';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
  imports: [SecondaryButtonComponent, PrimaryButtonComponent]
})
export class HeaderComponent {}
