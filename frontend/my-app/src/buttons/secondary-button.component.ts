import { Component, Input, Output, EventEmitter } from '@angular/core';
import {RouterModule} from '@angular/router';
import {CommonModule} from '@angular/common';

@Component({
  selector: 'secondary-button',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './secondary-button.component.html',
  styleUrls: ['./secondary-button.component.css']
})
export class SecondaryButtonComponent {
  @Input() text: string = 'Click Me';
  @Input() type: 'button' | 'submit' = 'button';
  @Input() routerLink?: string | any[];

  @Output() clicked = new EventEmitter<void>();

  handleClick() {
    this.clicked.emit();
  }
}
