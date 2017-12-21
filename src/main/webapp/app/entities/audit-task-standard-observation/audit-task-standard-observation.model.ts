import { BaseEntity } from './../../shared';

export class AuditTaskStandardObservation implements BaseEntity {
    constructor(
        public id?: number,
        public observation?: string,
    ) {
    }
}
