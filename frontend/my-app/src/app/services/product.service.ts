import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {AbstractControl, ValidationErrors} from '@angular/forms';

export interface Product extends ProductSummary{
  categoryId : string;
  sportId : string;
}

export interface ProductSummary {
  sellerId : string;
  sellerName : string;
  name : string;
  description : string;
  condition : string;
  price : number;
  status : string;
}

@Injectable({ providedIn: 'root' })
export class ProductService {

  constructor(private http: HttpClient) {}

  getProducts(): Observable<ProductSummary[]> {
    return this.http.get<ProductSummary[]>(
      `/api/products`
    );
  }

  createProduct(product: Product): any {
    return this.http.post<Product>(
      `/api/products`,
      product
    );
  }

  queryProducts(filters: {
    minPrice?: number;
    maxPrice?: number;
    categoryId?: string;
    sportId?: string;
    condition?: string;
    status?: string;
  }): Observable<ProductSummary[]> {
    let params = new HttpParams();

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

    return this.http.get<ProductSummary[]>(`/api/products/filter`, { params });
  }


}
