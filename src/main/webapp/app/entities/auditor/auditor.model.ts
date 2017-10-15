import { BaseEntity, User } from './../../shared';

export class Auditor implements BaseEntity {
    constructor(
        public id?: number,
        public internal?: boolean,
        public user?: User,
        public companies?: BaseEntity[],
    ) {
        this.internal = false;
    }
}
