package com.labausegtic.aresvi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Properties specific to JHipster.
 * <p>
 * Properties are configured in the application.yml file.
 */
@ConfigurationProperties(prefix = "application")
@Component
public class ApplicationProperties {

    private Bonita bonita = new Bonita();

    private Drools drools = new Drools();

    public Bonita getBonita() {
        return bonita;
    }

    public void setBonita(Bonita bonita) {
        this.bonita = bonita;
    }

    public Drools getDrools() {
        return drools;
    }

    public void setDrools(Drools drools) {
        this.drools = drools;
    }

    public static class Bonita {
        private String host;
        private String port;
        private String username;
        private String password;

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public String getPort() {
            return port;
        }

        public void setPort(String port) {
            this.port = port;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public static class Drools {
        private String host;
        private String port;

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public String getPort() {
            return port;
        }

        public void setPort(String port) {
            this.port = port;
        }
    }
}
