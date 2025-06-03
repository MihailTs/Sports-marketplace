import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {PrimaryButtonComponent} from '../../buttons/primary-button.component';
import {SecondaryButtonComponent} from '../../buttons/secondary-button.component';
import {TextBoxComponent} from '../../text-input/text-box.component';
import {FormComponent} from '../form/form.component';
// import { AuthService } from '../auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  imports: [CommonModule, RouterModule, PrimaryButtonComponent, SecondaryButtonComponent, TextBoxComponent, FormComponent]
})
export class LoginComponent {
  loginForm: FormGroup;

  constructor(private fb: FormBuilder/*, private authService: AuthService*/) {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
    });
  }
  //
  // onSubmit() {
  //   if (this.loginForm.valid) {
  //     // this.authService.login(this.loginForm.value).subscribe({
  //       next: (token) => {
  //         localStorage.setItem('token', token);
  //         // Navigate to home or dashboard
  //       },
  //       error: (err) => alert('Login failed'),
  //     });
  //   }
  // }
}
