import { BaseEntity } from './../../shared';

export class RecommendationAttribute implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public weightingId?: number,
        public recommendationId?: number,
    ) {
    }
}
