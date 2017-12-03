import { BaseEntity } from './../../shared';

export class AuditProcessStandardObservation implements BaseEntity {
    constructor(
        public id?: number,
        public observation?: string,
    ) {
    }
}
