import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { PrimaryButtonComponent } from '../../buttons/primary-button.component';
import { SecondaryButtonComponent } from '../../buttons/secondary-button.component';
import { TextBoxComponent } from '../../text-input/text-box.component';
import { FooterComponent } from '../../footer/footer.component';
import { ForumComponent } from '../../forums/forum/forum.component';
import {ProductSummary} from '../services/product.service';
import {ProductCardComponent} from '../../product-card/product-card.component';

@Component({
  selector: 'home',
  standalone: true,
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  imports: [
    CommonModule,
    RouterModule,
    PrimaryButtonComponent,
    SecondaryButtonComponent,
    TextBoxComponent,
    FooterComponent,
    ForumComponent,
    ProductCardComponent
  ]
})
export class HomeComponent {
  products: ProductSummary[] = [
    {
      sellerId: '1',
      sellerName: 'John Doe',
      name: 'Adidas Football Shoes',
      description: 'Great condition, worn only a few times.',
      condition: 'Like New',
      price: 59.99,
      status: 'Available'
    },
    {
      sellerId: '2',
      sellerName: 'Sarah Smith',
      name: 'Wilson Tennis Racket',
      description: 'Used racket with some wear but fully functional.',
      condition: 'Good',
      price: 25.00,
      status: 'Available'
    },
    // Add more mock items if needed
  ];

  currentPage = 1;
  itemsPerPage = 4;

  get paginatedProducts(): ProductSummary[] {
    const start = (this.currentPage - 1) * this.itemsPerPage;
    return this.products.slice(start, start + this.itemsPerPage);
  }

  get totalPages(): number {
    return Math.ceil(this.products.length / this.itemsPerPage);
  }

  goToPage(page: number) {
    this.currentPage = page;
  }
}
