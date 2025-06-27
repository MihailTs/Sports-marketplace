import { Component, Input, Output, EventEmitter, forwardRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ControlValueAccessor, NG_VALUE_ACCESSOR } from '@angular/forms';

@Component({
  selector: 'text-box',
  standalone: true,
  templateUrl: './text-box.component.html',
  styleUrls: ['./text-box.component.css'],
  imports: [CommonModule, FormsModule],
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => TextBoxComponent),
      multi: true
    }
  ]
})
export class TextBoxComponent implements ControlValueAccessor {
  @Input() label: string = 'Label';
  @Input() placeholder: string = 'Enter text';
  @Input() value: string = '';
  @Input() type: string = 'text';
  @Input() required: boolean = false;

  @Output() valueChange = new EventEmitter<string>();

  private onChange = (value: string) => {};
  private onTouched = () => {};

  onInput(event: Event): void {
    const target = event.target as HTMLInputElement;
    this.value = target.value;
    this.valueChange.emit(target.value);
    this.onChange(target.value);
  }

  onBlur(): void {
    this.onTouched();
  }

  writeValue(value: string): void {
    this.value = value || '';
  }

  registerOnChange(fn: (value: string) => void): void {
    this.onChange = fn;
  }

  registerOnTouched(fn: () => void): void {
    this.onTouched = fn;
  }
}
