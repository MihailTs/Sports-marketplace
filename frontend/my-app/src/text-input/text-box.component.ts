import { Component, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'text-box',
  templateUrl: './text-box.component.html',
  styleUrls: ['./text-box.component.css']
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
