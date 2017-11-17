import { BaseEntity } from './../../shared';
import {AuditTask} from '../audit-task/audit-task.model';
import {Attribute} from '../attribute/attribute.model';

export class CategoryAttribute implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public auditTaskId?: number,
        public auditTask?: AuditTask,
        public attributeSet?: Attribute[]
    ) {
    }
}
