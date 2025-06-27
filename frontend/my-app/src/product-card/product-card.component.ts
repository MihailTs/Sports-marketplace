import { Component, Input } from '@angular/core';
import {ProductSummary} from '../app/services/product.service';
import {SlicePipe} from '@angular/common';

@Component({
  selector: 'product-card',
  standalone: true,
  templateUrl: './product-card.component.html',
  imports: [
    SlicePipe
  ],
  styleUrls: ['./product-card.component.css']
})
export class ProductCardComponent {
  @Input() product!: ProductSummary;
}
