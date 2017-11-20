package com.labausegtic.aresvi.service;

import com.labausegtic.aresvi.domain.AuditProcess;
import com.labausegtic.aresvi.domain.AuditProcessRecommendation;
import org.bonitasoft.engine.api.ApiAccessType;
import org.bonitasoft.engine.api.LoginAPI;
import org.bonitasoft.engine.api.ProcessAPI;
import org.bonitasoft.engine.api.TenantAPIAccessor;
import org.bonitasoft.engine.bpm.process.*;
import org.bonitasoft.engine.exception.BonitaHomeNotSetException;
import org.bonitasoft.engine.exception.SearchException;
import org.bonitasoft.engine.exception.ServerAPIException;
import org.bonitasoft.engine.exception.UnknownAPITypeException;
import org.bonitasoft.engine.platform.LoginException;
import org.bonitasoft.engine.platform.LogoutException;
import org.bonitasoft.engine.search.Order;
import org.bonitasoft.engine.search.SearchOptions;
import org.bonitasoft.engine.search.SearchOptionsBuilder;
import org.bonitasoft.engine.search.SearchResult;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.engine.session.SessionNotFoundException;
import org.bonitasoft.engine.util.APITypeManager;

import java.util.HashMap;
import java.util.Map;


public class BonitaBPMService {

    private static final String username = "system";
    private static final String password = "system";
    private static final String bonitaHost = "165.227.89.229";
    private static final String bonitaPort = "35407";

    public static void startBPMProcess(AuditProcessRecommendation auditProcessRecommendation){
        try {
            try {
                Map<String, String> settings = new HashMap<>();
                settings.put("server.url", "http://" + bonitaHost + ":" + bonitaPort);
                settings.put("application.name", "bonita");
                APITypeManager.setAPITypeAndParams(ApiAccessType.HTTP, settings);
                // get the LoginAPI using the TenantAPIAccessor
                LoginAPI loginAPI = TenantAPIAccessor.getLoginAPI();
                // log in to the tenant to create a session
                APISession apiSession = loginAPI.login(username, password);
                //List the deployed processes
                ProcessAPI processAPI = TenantAPIAccessor.getProcessAPI(apiSession);
                SearchOptions searchOptions = new SearchOptionsBuilder(0, 3).sort(ProcessDeploymentInfoSearchDescriptor.DEPLOYMENT_DATE, Order.DESC).done();
                SearchResult<ProcessDeploymentInfo> deploymentInfoResults = processAPI.searchProcessDeploymentInfos(searchOptions);
                System.out.println("Cantidad de procesos deployados: " + deploymentInfoResults.getResult().size());

                for (int i = 0; i <deploymentInfoResults.getResult().size(); i++) {
                    // start the process
                    if (auditProcessRecommendation.getAuditProcess().getName().equalsIgnoreCase(deploymentInfoResults.getResult().get(i).getDisplayName())) {
                        ProcessInstance processInstance = processAPI.startProcess(deploymentInfoResults.getResult().get(i).getProcessId());
                        System.out.println("Una nueva instancia del proceso " + processInstance.getName() + " fue iniciada correctamente con Id: " + processInstance.getId());
                        auditProcessRecommendation.setBonitaBpmCaseId(processInstance.getId());
                    }
                }
                // don't forget to log out:
                loginAPI.logout(apiSession);
            } catch (LoginException | SessionNotFoundException | LogoutException | SearchException | ProcessDefinitionNotFoundException | ProcessActivationException | ProcessExecutionException ignored) {}
        } catch (BonitaHomeNotSetException | ServerAPIException | UnknownAPITypeException ignored) {}
    }

}
