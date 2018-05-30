import { BaseEntity } from './../../shared';
import {AuditProcess} from '../audit-process/audit-process.model';

export class ProductType implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public auditProcesses?: AuditProcess[],
    ) {
    }
}
