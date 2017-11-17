import { BaseEntity } from './../../shared';
import {AuditProcess} from '../audit-process/audit-process.model';
import {AuditTask} from '../audit-task/audit-task.model';

export class Container implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public participantId?: number,
        public auditProcessId?: number,
        public auditProcess?: AuditProcess,
        public auditTaskSet?: AuditTask[],
    ) {
    }
}
