import { BaseEntity } from './../../shared';
import {User} from '../../shared/user/user.model';
import {TraceabilityAudit} from '../traceability-audit/traceability-audit.model';

export class CancellationTraceabilityAudit implements BaseEntity {
    constructor(
        public id?: number,
        public justification?: string,
        public traceabilityAuditId?: number,
        public traceabilityAudit?: TraceabilityAudit,
        public userId?: number,
        public user?: User,
        public traceabilityAuditName?: string
    ) {
    }
}
