import { BaseEntity } from './../../shared';

export class Participant implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
    ) {
    }
}
