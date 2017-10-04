import { BaseEntity } from './../../shared';

export class AuditTask implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public containerId?: number,
    ) {
    }
}
