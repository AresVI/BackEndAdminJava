import { BaseEntity } from './../../shared';

export class Attribute implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public required?: boolean,
        public weightingId?: number,
        public categoryAttributeId?: number,
    ) {
        this.required = false;
    }
}
