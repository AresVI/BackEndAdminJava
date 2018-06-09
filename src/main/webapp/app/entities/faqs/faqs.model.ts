import { BaseEntity } from './../../shared';

export class Faqs implements BaseEntity {
    constructor(
        public id?: number,
        public question?: string,
        public answer?: any,
    ) {
    }
}
