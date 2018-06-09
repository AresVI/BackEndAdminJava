import { BaseEntity } from './../../shared';
import {Weighting} from '../weighting';

export class RecommendationAttribute implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public weightingId?: number,
        public weighting?: Weighting,
    ) {
    }
}
