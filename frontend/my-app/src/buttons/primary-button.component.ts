import { Component, Input, Output, EventEmitter } from '@angular/core';
import {RouterModule} from '@angular/router';
import {CommonModule} from '@angular/common';

@Component({
  selector: 'primary-button',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './primary-button.component.html',
  styleUrls: ['./primary-button.component.css']
})
export class PrimaryButtonComponent {
  @Input() text: string = 'Click Me';
  @Input() type: 'button' | 'submit' = 'button';
  @Input() routerLink?: string | any[];
  @Input() class : string = "";
  @Output() clicked = new EventEmitter<void>();

  handleClick() {
    this.clicked.emit();
  }
}
