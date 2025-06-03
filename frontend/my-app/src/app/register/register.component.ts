import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
// import { AuthService } from '../auth.service'; // Replace with actual service path
import {Router, RouterModule} from '@angular/router';
import {CommonModule} from '@angular/common';
import {PrimaryButtonComponent} from '../../buttons/primary-button.component';
import {SecondaryButtonComponent} from '../../buttons/secondary-button.component';
import {TextBoxComponent} from '../../text-input/text-box.component';
import {FormComponent} from '../form/form.component';

@Component({
  selector: 'app-register',
  standalone: true,
  templateUrl: './register.component.html',
  // styleUrls: ['./register.component.css']
  imports: [CommonModule, RouterModule, PrimaryButtonComponent, SecondaryButtonComponent, TextBoxComponent, FormComponent]
})
export class RegisterComponent {
  registerForm: FormGroup;

  constructor(private fb: FormBuilder, /*private authService: AuthService,*/ private router: Router) {
    this.registerForm = this.fb.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      confirmPassword: ['', Validators.required],
      birthdate: ['', Validators.required],
      phone: [''],
      gender: ['', Validators.required],
      role: ['user'] // optional field
    }, { validators: this.passwordsMatchValidator });
  }

  // Custom validator to check if passwords match
  passwordsMatchValidator(group: FormGroup) {
    const password = group.get('password')?.value;
    const confirmPassword = group.get('confirmPassword')?.value;
    return password === confirmPassword ? null : { passwordsMismatch: true };
  }

  onSubmit() {
    if (this.registerForm.invalid) {
      return;
    }

    const formData = this.registerForm.value;

    // this.authService.register(formData).subscribe({
    //   next: (response) => {
    //     console.log('Registered successfully:', response);
    //     this.router.navigate(['/']); // Redirect to homepage or login
    //   },
    //   error: (err) => {
    //     console.error('Registration failed:', err);
    //     // Optionally show a toast or error message
    //   }
    // });
  }
}
