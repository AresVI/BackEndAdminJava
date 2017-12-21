import { BaseEntity } from './../../shared';

export class AuditAttributeAnalysis implements BaseEntity {
    constructor(
        public id?: number,
        public percentageNotRequired?: number,
        public percentageLevel1?: number,
        public percentageLevel2?: number,
        public percentageLevel3?: number,
        public percentageLevel4?: number,
        public percentageLevel5?: number,
        public traceabilityAuditId?: number,
    ) {
    }
}
