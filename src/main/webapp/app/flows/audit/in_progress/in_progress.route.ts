import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot, Routes} from '@angular/router';
import {InProgressComponent} from './in_progress.component';
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

export const flowAuditInProgressRoute: Routes = [
    {
        path: 'process/audit/status/in_progress',
        component: InProgressComponent,
        resolve: {
            'pagingParams': TraceabilityAuditResolvePagingParams
        },
        data: {
            authorities: ['ROLE_ADMINISTRATOR', 'ROLE_ADMINISTRATIVE'],
            pageTitle: 'aresViApp.flow-audit.in_progress.home.title'
        }
    }
];
