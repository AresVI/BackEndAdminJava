import { BaseEntity } from './../../shared';

export class Report implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public iframe?: string,
    ) {
    }
}
