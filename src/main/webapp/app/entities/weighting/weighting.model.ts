import { BaseEntity } from './../../shared';

export class Weighting implements BaseEntity {
    constructor(
        public id?: number,
        public value?: number,
    ) {
    }
}
