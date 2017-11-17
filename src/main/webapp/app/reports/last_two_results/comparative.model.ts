import {AuditTask} from "../../entities/audit-task/audit-task.model";
import {CategoryAttribute} from "../../entities/category-attribute/category-attribute.model";
import {Attribute} from "../../entities/attribute/attribute.model";

export class ComparativeAttributeRecommendation {
    constructor(
        public attribute?: Attribute,
        public difference?: number,
        public implementedNew?: boolean,
        public implementedOld?: boolean
    ) {
    }
}

export class ComparativeCatAttrRecommendation {
    constructor(
        public categoryAttribute?: CategoryAttribute,
        public comparativeAttributeRecommendationList?: ComparativeAttributeRecommendation[]
    ) {
    }
}

export class ComparativeTaskRecommendation {
    constructor(
        public auditTask?: AuditTask,
        public comparativeCatAttrRecommendationList?: ComparativeCatAttrRecommendation[]
    ) {
    }
}
