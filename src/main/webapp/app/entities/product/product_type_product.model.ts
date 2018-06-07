import { BaseEntity } from './../../shared';
import {Product} from './product.model';

export class ProductTypeProduct implements BaseEntity {
    constructor(
        public id: number,
        public name: string,
        public products: Product[],
    ) {
    }
}
