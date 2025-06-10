import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthStateService, AuthUser } from '../app/state-services/authState.service';
import { SecondaryButtonComponent } from '../buttons/secondary-button.component';
import { PrimaryButtonComponent } from '../buttons/primary-button.component';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [CommonModule, RouterModule, SecondaryButtonComponent, PrimaryButtonComponent],
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  user: AuthUser | null = null;

  constructor(private authState: AuthStateService) {}

  ngOnInit() {
    this.authState.user$.subscribe(user => {
      this.user = user;
    });

    this.authState.loadUserFromStorage();
  }

  logout() {
    this.authState.logout();
  }
}
