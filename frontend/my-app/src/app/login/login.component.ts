import { Component } from '@angular/core';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { PrimaryButtonComponent } from '../../buttons/primary-button.component';
import { SecondaryButtonComponent } from '../../buttons/secondary-button.component';
import { TextBoxComponent } from '../../text-input/text-box.component';
import { FormComponent } from '../../form/form.component';
import { AuthService } from '../auth/auth.service';
import {AuthStateService} from '../state-services/authState.service';

@Component({
  selector: 'app-login',
  standalone: true,
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  imports: [
    CommonModule,
    RouterModule,
    PrimaryButtonComponent,
    SecondaryButtonComponent,
    TextBoxComponent,
    FormComponent,
    FormsModule,
    ReactiveFormsModule,
  ]
})
export class LoginComponent {
  loginForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router,
    private authStateService: AuthStateService
  ) {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
    });
  }

  errorMessage: string | null = null;
  isSubmitting = false;

  getFieldError(field: string): string | null {
    const control = this.loginForm.get(field);
    if (control && control.touched && control.invalid) {
      if (control.errors?.['required']) return 'This field is required.';
      if (control.errors?.['email']) return 'Invalid email format.';
    }
    return null;
  }

  onSubmit() {
    if (this.loginForm.valid) {
      this.isSubmitting = true;
      this.errorMessage = null;

      this.authService.login(this.loginForm.value).subscribe({
        next: (authResponse) => {
          localStorage.setItem('token', authResponse.token);
          this.authStateService.setUser(authResponse);
          this.router.navigate(['/']);
          this.isSubmitting = false;
        },
        error: (err) => {
          this.errorMessage = 'Login failed. Please check your credentials.';
          this.isSubmitting = false;
        },
      });
    } else {
      this.loginForm.markAllAsTouched();
    }
  }
}
