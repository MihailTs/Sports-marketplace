import { Component, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'text-area',
  templateUrl: './text-area.component.html',
  styleUrls: ['./text-area.component.css']
})
export class TextAreaComponent {
  @Input() label: string = 'Enter text';
  @Input() placeholder: string = 'Your text ...';
  @Input() value: string = '';
  @Output() valueChange = new EventEmitter<string>();

  onInput(event: Event): void {
    const target = event.target as HTMLTextAreaElement;
    this.valueChange.emit(target.value);
  }
}
