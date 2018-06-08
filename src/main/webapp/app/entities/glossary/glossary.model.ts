import { BaseEntity } from './../../shared';

export class Glossary implements BaseEntity {
    constructor(
        public id?: number,
        public concept?: string,
        public definition?: any,
    ) {
    }
}
