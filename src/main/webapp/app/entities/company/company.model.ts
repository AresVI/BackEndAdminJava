import { BaseEntity } from './../../shared';
import {CompanyAddress} from '../company-address/company-address.model';

export class Company implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public identifier?: string,
        public companyAddress?: CompanyAddress
    ) {
    }
}
