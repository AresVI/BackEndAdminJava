import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot, Routes} from '@angular/router';
import {FinishedComponent} from './finished.component';
import {JhiPaginationUtil} from 'ng-jhipster';
import {Injectable} from '@angular/core';
import {UserRouteAccessService} from '../../../shared/auth/user-route-access-service';
import {
    TraceabilityAuditCategorizeAgainAuditPopupComponent
} from './traceability-audit-categorize-again-audit-dialog.component';

@Injectable()
export class TraceabilityAuditResolvePagingParams implements Resolve<any> {

    constructor(private paginationUtil: JhiPaginationUtil) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const page = route.queryParams['page'] ? route.queryParams['page'] : '1';
        const sort = route.queryParams['sort'] ? route.queryParams['sort'] : 'id,desc';
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
            authorities: ['ROLE_ADMINISTRATOR', 'ROLE_ADMINISTRATIVE', 'ROLE_AUDITOR_EXTERNAL'],
            pageTitle: 'aresViApp.flow-audit.finished.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'traceability-audit/:id/categorize_again',
        component: TraceabilityAuditCategorizeAgainAuditPopupComponent,
        data: {
            authorities: ['ROLE_ADMINISTRATOR'],
            pageTitle: 'aresViApp.flow-audit.finished.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
];
