import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import {ForumsPageComponent} from './forums/forums-page.component';
import {SingleForumComponent} from './forums/single-forum.component';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  {
    path: 'forums',
    children: [
      { path: '', component: ForumsPageComponent },
      { path: ':id', component: SingleForumComponent }
    ]
  }
];
