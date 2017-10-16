import { BaseEntity } from './../../shared';
import {AuditProcess} from '../audit-process/audit-process.model';

export class Container implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public participantId?: number,
        public auditProcessId?: number,
        public auditProcess?: AuditProcess,
    ) {
    }
}
