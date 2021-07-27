package es.caib.portafib.it.actors;

public class Delegat extends DestinatariUsuari {

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
