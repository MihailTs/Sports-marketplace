import {Component} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {PrimaryButtonComponent} from '../../buttons/primary-button.component';
import {SecondaryButtonComponent} from '../../buttons/secondary-button.component';
import {TextBoxComponent} from '../../text-input/text-box.component';

@Component({
  selector: 'add-product',
  standalone: true,
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css'],
  imports: [
    CommonModule,
    RouterModule,
    ReactiveFormsModule,
    PrimaryButtonComponent,
    SecondaryButtonComponent,
    TextBoxComponent
  ]
})
export class AddProductComponent {
  productForm: FormGroup;
  isSubmitting = false;
  categories = [
    { id: '1', name: 'Sports Equipment' },
    { id: '2', name: 'Apparel' },
    { id: '3', name: 'Accessories' }
  ];

  constructor(private fb: FormBuilder) {
    this.productForm = this.fb.group({
      name: ['', Validators.required],
      description: ['', Validators.required],
      categoryId: ['', Validators.required],
      condition: ['', Validators.required],
      price: ['', [Validators.required, Validators.min(0.01)]],
      status: ['', Validators.required]
    });
  }

  getFieldError(fieldName: string): string | null {
    const control = this.productForm.get(fieldName);
    if (control && control.touched && control.invalid) {
      if (!(control.errors) || control.errors['required']) {
        return 'This field is required';
      }
      if (control.errors['min']) {
        return 'Price must be a positive number';
      }
    }
    return null;
  }

  onSubmit(): void {
    if (this.productForm.invalid) {
      this.productForm.markAllAsTouched();
      return;
    }

    this.isSubmitting = true;

    const productData = this.productForm.value;
    console.log('Creating product:', productData);

    setTimeout(() => {
      this.isSubmitting = false;
      alert('Product created successfully!');
      this.productForm.reset();
    }, 1000);
  }
}
