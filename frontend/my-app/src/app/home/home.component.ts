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
    {
      sellerId: '1',
      sellerName: 'John Doe',
      name: 'Adidas Football Shoes',
      description: 'Great condition, worn only a few times.',
      condition: 'Like New',
      price: 59.99,
      status: 'Available'
    }
  ];

  currentPage = 1;
  itemsPerPage = 10;

  get paginationButtons(): (number | string)[] {
    const pages: (number | string)[] = [];
    const total = this.totalPages;
    const current = this.currentPage;

    if (total <= 5) {
      for (let i = 1; i <= total; i++) pages.push(i);
    } else {
      pages.push(1);

      if (current > 3) pages.push('...');

      const start = Math.max(2, current - 1);
      const end = Math.min(total - 1, current + 1);

      for (let i = start; i <= end; i++) pages.push(i);

      if (current < total - 2) pages.push('...');

      pages.push(total);
    }

    return pages;
  }

  prevPage() {
    if (this.currentPage > 1) this.currentPage--;
  }

  nextPage() {
    if (this.currentPage < this.totalPages) this.currentPage++;
  }

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

  protected readonly Number = Number;
}
