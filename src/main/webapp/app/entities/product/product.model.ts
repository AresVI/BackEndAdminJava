import { BaseEntity } from './../../shared';

export class Product implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public picture_url?: string,
        public companyId?: number,
        public productTypeId?: number,
    ) {
    }
}
