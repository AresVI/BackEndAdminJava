import { BaseEntity } from './../../shared';

export class Recommendation implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public qualification?: number,
        public creationDate?: any,
        public traceabilityAuditId?: number,
    ) {
    }
}
