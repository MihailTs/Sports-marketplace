import {Component} from '@angular/core';
import {CommonModule} from '@angular/common';
import {Router, RouterModule} from '@angular/router';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {PrimaryButtonComponent} from '../../buttons/primary-button.component';
import {SecondaryButtonComponent} from '../../buttons/secondary-button.component';
import {TextBoxComponent} from '../../text-input/text-box.component';
import {Category, CategoryService} from '../services/category.service';
import {SportService} from '../services/sport.service';
import {Sport} from '../services/forum.service';
import {Product, ProductService} from '../services/product.service';
import {AuthStateService} from '../services/state-services/authState.service';

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
  sports: Sport[] = [];
  categories: Category[] = [];

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private sportService: SportService,
    private categoryService: CategoryService,
    private productService: ProductService,
    private currentUserService: AuthStateService
  ) {
    this.productForm = this.fb.group({
      name: ['', Validators.required],
      description: ['', Validators.required],
      categoryId: ['', Validators.required],
      sportId: ['', Validators.required],
      condition: ['', Validators.required],
      price: ['', [Validators.required, Validators.min(0.01)]],
      status: ['', Validators.required]
    });

    this.loadDropdowns();
  }

  loadDropdowns(): void {
    this.sportService.getSports().subscribe(data => this.sports = data);
    this.categoryService.getCategories().subscribe(data => this.categories = data);
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

    if (!this.currentUserService.currentUser?.id) {
      alert('Unable to create product: Seller ID is missing.');
      return;
    }

    this.isSubmitting = true;

    const formData = this.productForm.value;

    const product: Product = {
      sellerId: this.currentUserService.currentUser.id,
      sellerName:
        this.currentUserService.currentUser.firstName + " " +
        this.currentUserService.currentUser.firstName,
      name: formData.name as string,
      description: formData.description as string,
      categoryId: formData.categoryId as string,
      sportId: formData.sportId as string,
      condition: formData.condition as string,
      price: +formData.price,
      status: formData.status as string
    };

    this.productService.createProduct(product).subscribe({
      next: () => {
        this.isSubmitting = false;
        this.router.navigate(['/']);
      },
      error: (err : any) => {
        this.isSubmitting = false;
        alert('Failed to create product. Please try again.');
        console.error(err);
      }
    });
  }

}
