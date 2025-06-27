import {AuthGuard} from '../authGuard';
import {ForumsPageComponent} from './forums/forums-page.component';
import {SingleForumComponent} from './forums/single-forum.component';
import {ProductsListComponent} from './products/products-list.component';
import {AddProductComponent} from './products/add-product.component';
import {LoginComponent} from './login/login.component';
import {HomeComponent} from './home/home.component';
import {RegisterComponent} from './register/register.component';
import {Routes} from '@angular/router';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  {
    path: 'add-product',
    component: AddProductComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'see-products',
    component: ProductsListComponent,
  },
  {
    path: 'forums',
    canActivate: [AuthGuard],
    children: [
      { path: '', component: ForumsPageComponent },
      { path: ':id', component: SingleForumComponent }
    ]
  }
];
