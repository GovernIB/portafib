package org.fundaciobit.apisib.apifirmaasyncsimple.v2.test.actors;

public class Delegat extends Destinatari {

    public Delegat(String administrationId, String username, String password, String pin, String baseUrl) {
        super(administrationId, username, password, pin, baseUrl);
    }

    @Override
    protected String getRoleName() {
        return "ROLE_DELE";
    }

    @Override
    protected String getTasksPath() {
        return "/dele/estatDeFirmaPendent/list/1";
    }
}
