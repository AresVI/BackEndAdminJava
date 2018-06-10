import { BaseEntity } from './../../shared';
import {RecommendationAttribute} from '../recommendation-attribute';

export class RecommendationAttributeRecommendation implements BaseEntity {
    constructor(
        public id?: number,
        public implemented?: boolean,
        public recommendationAttributeId?: number,
        public recommendationId?: number,
        public recommendationAttributeName?: string,
        public recommendationAttribute?: RecommendationAttribute
    ) {
        this.implemented = false;
    }
}
