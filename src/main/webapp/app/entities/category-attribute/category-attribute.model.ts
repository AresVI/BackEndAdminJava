import { BaseEntity } from './../../shared';

export class CategoryAttribute implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public auditTaskId?: number,
    ) {
    }
}
