import { BaseEntity } from './../../shared';
import {ProductTypeProduct} from './product_type_product.model';

export class CompanyProduct implements BaseEntity {
    constructor(
        public id: number,
        public name: string,
        public productTypes: ProductTypeProduct[],
    ) {
    }
}
