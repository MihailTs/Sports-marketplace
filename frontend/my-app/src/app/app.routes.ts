import {AuthGuard} from '../authGuard';
import {ForumsPageComponent} from './forums/forums-page.component';
import {SingleForumComponent} from './forums/single-forum.component';
import { ChatComponent } from '../chat/chat.component';
import { UserProfileComponent } from '../user/user.profile.component';
import { EventListComponent } from '../event/event-list.component';
import { EventDetailsComponent } from '../event/event-details.component';
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
  ,
  { path: 'chat/:id', component: ChatComponent }
  ,
  { path: 'users/:id', component: UserProfileComponent },
  { path: 'events', component: EventListComponent },
  { path: 'events/:id', component: EventDetailsComponent },

];
