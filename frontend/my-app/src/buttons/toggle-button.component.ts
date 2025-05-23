import { Component, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'toggle-button',
  templateUrl: './toggle-button.component.html',
  styleUrls: ['./toggle-button.component.css']
})
export class ToggleButtonComponent {
  @Input() isOn: boolean = false;
  @Output() toggled = new EventEmitter<boolean>();

  toggle(): void {
    this.isOn = !this.isOn;
    this.toggled.emit(this.isOn);
  }
}
