import { BaseEntity } from './../../shared';

export class RecommendationAttributeRecommendation implements BaseEntity {
    constructor(
        public id?: number,
        public implemented?: boolean,
        public recommendationAttributeId?: number,
        public recommendationId?: number,
    ) {
        this.implemented = false;
    }
}
