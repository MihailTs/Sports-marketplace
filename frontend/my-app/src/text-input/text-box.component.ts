import { Component, Input, Output, EventEmitter } from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'text-box',
  standalone: true,
  templateUrl: './text-box.component.html',
  styleUrls: ['./text-box.component.css'],
  imports: [CommonModule, FormsModule]
})
export class TextBoxComponent {
  @Input() label: string = 'Label';
  @Input() placeholder: string = 'Enter text';
  @Input() value: string = '';
  @Input() type: string = 'text';
  @Input() required: boolean = false;

  @Output() valueChange = new EventEmitter<string>();

  onInput(event: Event): void {
    const target = event.target as HTMLInputElement;
    this.valueChange.emit(target.value);
  }
}
