import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

class Product {
  sellerId : string;
  name : string;
  description : string;
  categoryID : string;
  sportID : string;
  condition : string;
  price : number;
  status : string;
}

@Injectable({ providedIn: 'root' })
export class ProductService {

  constructor(private http: HttpClient) {}

  getProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(
      `/api/products/`
    );
  }

  createProduct(product : Product): Observable<Product> {
    return this.http.post<Product>(
      `/api/products`,
      product
    );
  }

}
