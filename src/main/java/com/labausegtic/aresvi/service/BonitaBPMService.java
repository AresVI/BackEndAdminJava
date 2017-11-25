package com.labausegtic.aresvi.service;

import com.labausegtic.aresvi.config.ApplicationProperties;
import com.labausegtic.aresvi.domain.AuditProcessRecommendation;

import org.bonitasoft.engine.api.*;
import org.bonitasoft.engine.bpm.process.*;
import org.bonitasoft.engine.search.Order;
import org.bonitasoft.engine.search.SearchOptions;
import org.bonitasoft.engine.search.SearchOptionsBuilder;
import org.bonitasoft.engine.search.SearchResult;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.engine.util.APITypeManager;


import java.util.HashMap;
import java.util.Map;


public class BonitaBPMService {

    private String username;
    private String password;
    private String bonitaHost;
    private String bonitaPort;

    public BonitaBPMService(ApplicationProperties applicationProperties) {
        this.bonitaHost = applicationProperties.getBonita().getHost();
        this.bonitaPort = applicationProperties.getBonita().getPort();
        this.username = applicationProperties.getBonita().getUsername();
        this.password = applicationProperties.getBonita().getPassword();

        System.out.println("==========================================================================");
        System.out.println(this.bonitaHost);
        System.out.println(this.bonitaPort);
        System.out.println(this.username);
        System.out.println(this.password);
        System.out.println("==========================================================================");

    }

    public void startBPMProcess(AuditProcessRecommendation auditProcessRecommendation){

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
        } catch (Exception e) {
            e.printStackTrace();
            auditProcessRecommendation.setBonitaBpmCaseId(0L);
        }

    }

}
