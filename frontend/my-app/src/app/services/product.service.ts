import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Product extends ProductSummary {
  categoryId: string;
  sportId: string;
}

export interface ProductSummary {
  sellerId: string;
  sellerName: string;
  name: string;
  description: string;
  condition: string;
  price: number;
  status: string;
}

export interface PagedResponse<T> {
  content: T[];
  page: number;
  size: number;
  totalElements: number;
  totalPages: number;
  last: boolean;
}

@Injectable({ providedIn: 'root' })
export class ProductService {
  apiUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {}

  getProducts(): Observable<PagedResponse<ProductSummary>> {
    return this.http.get<PagedResponse<ProductSummary>>(
      `${this.apiUrl}/api/products`
    );
  }

  createProduct(product: Product): any {
    return this.http.post<Product>(
      `${this.apiUrl}/api/products`,
      product
    );
  }

  queryProducts(
    filters: {
      minPrice?: number;
      maxPrice?: number;
      categoryId?: string;
      sportId?: string;
      condition?: string;
      status?: string;
    },
    page: number = 0,
    size: number = 10
  ): Observable<PagedResponse<ProductSummary>> {
    let params = new HttpParams()
      .set('page', page)
      .set('size', size);

    if (filters.minPrice !== undefined) {
      params = params.set('minPrice', filters.minPrice);
    }
    if (filters.maxPrice !== undefined) {
      params = params.set('maxPrice', filters.maxPrice);
    }
    if (filters.categoryId) {
      params = params.set('categoryId', filters.categoryId);
    }
    if (filters.sportId) {
      params = params.set('sportId', filters.sportId);
    }
    if (filters.condition) {
      params = params.set('condition', filters.condition);
    }
    if (filters.status) {
      params = params.set('status', filters.status);
    }

    return this.http.get<PagedResponse<ProductSummary>>(
      `${this.apiUrl}/api/products/filter`,
      { params }
    );
  }
}
