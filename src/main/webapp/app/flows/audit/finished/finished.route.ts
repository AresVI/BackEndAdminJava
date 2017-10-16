import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot, Routes} from '@angular/router';
import {FinishedComponent} from './finished.component';
import {JhiPaginationUtil} from 'ng-jhipster';
import {Injectable} from '@angular/core';

@Injectable()
export class TraceabilityAuditResolvePagingParams implements Resolve<any> {

    constructor(private paginationUtil: JhiPaginationUtil) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const page = route.queryParams['page'] ? route.queryParams['page'] : '1';
        const sort = route.queryParams['sort'] ? route.queryParams['sort'] : 'id,asc';
        return {
            page: this.paginationUtil.parsePage(page),
            predicate: this.paginationUtil.parsePredicate(sort),
            ascending: this.paginationUtil.parseAscending(sort)
        };
    }
}

export const flowAuditFinishedRoute: Routes = [
    {
        path: 'process/audit/status/finished',
        component: FinishedComponent,
        resolve: {
            'pagingParams': TraceabilityAuditResolvePagingParams
        },
        data: {
            authorities: ['ROLE_ADMINISTRATOR', 'ROLE_ADMINISTRATIVE'],
            pageTitle: 'aresViApp.flow-audit.finished.home.title'
        }
    }
];
