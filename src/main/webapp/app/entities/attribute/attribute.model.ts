import { BaseEntity } from './../../shared';
import {CategoryAttribute} from '../category-attribute/category-attribute.model';

export class Attribute implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public required?: boolean,
        public weightingId?: number,
        public categoryAttributeId?: number,
        public categoryAttribute?: CategoryAttribute
    ) {
        this.required = false;
    }
}
