import { BaseEntity } from './../../shared';

export class Container implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public participantId?: number,
        public auditProcessId?: number,
    ) {
    }
}
