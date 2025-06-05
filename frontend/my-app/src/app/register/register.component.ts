import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { PrimaryButtonComponent } from '../../buttons/primary-button.component';
import { SecondaryButtonComponent } from '../../buttons/secondary-button.component';
import { TextBoxComponent } from '../../text-input/text-box.component';
import { FormComponent } from '../form/form.component';
import { HttpClient, provideHttpClient } from '@angular/common/http';

@Component({
  selector: 'app-register',
  standalone: true,
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
  imports: [
    CommonModule,
    RouterModule,
    PrimaryButtonComponent,
    ReactiveFormsModule,
    SecondaryButtonComponent,
    TextBoxComponent,
    FormComponent
  ]
})
export class RegisterComponent {
  registerForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private http: HttpClient
  ) {
    this.registerForm = this.fb.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      confirmPassword: ['', Validators.required],
      birthdate: ['', Validators.required],
      phone: [''],
      gender: ['', Validators.required],
      role: ['user']
    }, { validators: this.passwordsMatchValidator });
  }

  passwordsMatchValidator(group: FormGroup) {
    const password = group.get('password')?.value;
    const confirmPassword = group.get('confirmPassword')?.value;
    return password === confirmPassword ? null : { passwordsMismatch: true };
  }

  onSubmit() {
    if (this.registerForm.invalid) return;

    const formData = this.registerForm.value;

    this.http.post('/api/users/auth/register', formData, { responseType: 'text' }).subscribe({
      next: (token: string) => {
        console.log('Token:', token);
        localStorage.setItem('jwtToken', token);
        this.router.navigate(['/']);
      },
      error: (err) => {
        console.error('Registration failed:', err);
      }
    });
  }
}
