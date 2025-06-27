import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { PrimaryButtonComponent } from '../../buttons/primary-button.component';
import { SecondaryButtonComponent } from '../../buttons/secondary-button.component';
import { TextBoxComponent } from '../../text-input/text-box.component';
import { FooterComponent } from '../../footer/footer.component';
import { ForumComponent } from '../../forums/forum/forum.component';
import { ProductCardComponent } from '../../product-card/product-card.component';
import { ProductService, ProductSummary, PagedResponse } from '../services/product.service';
import {CategoryService} from '../services/category.service';
import {SportService} from '../services/sport.service';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'products-list',
  standalone: true,
  templateUrl: './products-list.component.html',
  styleUrls: ['./products-list.component.css'],
  imports: [
    CommonModule,
    RouterModule,
    PrimaryButtonComponent,
    SecondaryButtonComponent,
    TextBoxComponent,
    FooterComponent,
    ForumComponent,
    ProductCardComponent,
    FormsModule
  ]
})
export class ProductsListComponent {
  products: ProductSummary[] = [];
  currentPage = 0;
  itemsPerPage = 20;
  totalProducts = 0;
  totalPages = 0;
  loading = false;

  minPrice?: number;
  maxPrice?: number;
  selectedCategoryId?: string;
  selectedSportId?: string;
  selectedCondition?: string;
  selectedStatus?: string;

  categories: { id: string, name: string }[] = [];
  sports: { id: string, name: string }[] = [];
  conditions = ['new', 'used'];
  statuses = ['active', 'inactive'];

  constructor(
    private productService: ProductService,
    private categoryService: CategoryService,
    private sportService: SportService
  ) {}

  ngOnInit(): void {
    this.loadFilterOptions();
    this.loadProducts(this.currentPage);
  }

  loadFilterOptions() {
    this.categoryService.getCategories().subscribe(data => this.categories = data);
    this.sportService.getSports().subscribe(data => this.sports = data);
  }

  loadProducts(page: number): void {
    this.loading = true;
    const filters = {
      minPrice: this.minPrice,
      maxPrice: this.maxPrice,
      categoryId: this.selectedCategoryId,
      sportId: this.selectedSportId,
      condition: this.selectedCondition,
      status: this.selectedStatus
    };

    this.productService.queryProducts(filters, page, this.itemsPerPage).subscribe({
      next: (response) => {
        this.products = response.content;
        this.totalProducts = response.totalElements;
        this.totalPages = response.totalPages;
        this.currentPage = response.page;
        this.loading = false;
      },
      error: (err) => {
        console.error('Failed to load products:', err);
        this.loading = false;
      }
    });
  }

  applyFilters() {
    this.currentPage = 0;
    this.loadProducts(this.currentPage);
  }

  clearFilters() {
    this.minPrice = undefined;
    this.maxPrice = undefined;
    this.selectedCategoryId = undefined;
    this.selectedSportId = undefined;
    this.selectedCondition = undefined;
    this.selectedStatus = undefined;
    this.applyFilters();
  }
  goToPage(page: number) {
    if (page >= 0 && page < this.totalPages && page !== this.currentPage) {
      this.loadProducts(page);
    }
  }

  prevPage() {
    if (this.currentPage > 0) {
      this.loadProducts(this.currentPage - 1);
    }
  }

  nextPage() {
    if (this.currentPage < this.totalPages - 1) {
      this.loadProducts(this.currentPage + 1);
    }
  }

  get paginationButtons(): (number | string)[] {
    const pages: (number | string)[] = [];
    const total = this.totalPages;
    const current = this.currentPage + 1;

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

  protected readonly Number = Number;
}
