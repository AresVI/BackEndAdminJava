package com.labausegtic.aresvi.security;

/**
 * Constants for Spring Security authorities.
 */
public final class AuthoritiesConstants {


    public static final String AUDITOR = "ROLE_AUDITOR";
    public static final String ADMINISTRATOR = "ROLE_ADMINISTRATOR";
    public static final String ADMINISTRATIVE = "ROLE_ADMINISTRATIVE";
    public static final String NO_ROLE = "ROLE_NO_ROLE";

    public static final String ANONYMOUS = "ROLE_ANONYMOUS";

    private AuthoritiesConstants() {
    }
}
