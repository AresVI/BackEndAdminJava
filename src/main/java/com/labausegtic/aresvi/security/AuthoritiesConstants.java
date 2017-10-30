package com.labausegtic.aresvi.security;

/**
 * Constants for Spring Security authorities.
 */
public final class AuthoritiesConstants {


    public static final String AUDITOR_EXTERNAL = "ROLE_AUDITOR_EXTERNAL";
    public static final String AUDITOR_INTERNAL = "ROLE_AUDITOR_INTERNAL";
    public static final String ADMINISTRATOR = "ROLE_ADMINISTRATOR";
    public static final String ADMINISTRATIVE = "ROLE_ADMINISTRATIVE";
    public static final String NO_ROLE = "ROLE_NO_ROLE";

    public static final String ANONYMOUS = "ROLE_ANONYMOUS";

    private AuthoritiesConstants() {}
}
