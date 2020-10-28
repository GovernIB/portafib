package es.caib.portafib.app.client;

import java.util.List;

/**
 * Created by anadal on 05/09/2017.
 */
public class NotificacioRest {

    private final String rol;
    private final List<Long> peticions;

    public NotificacioRest(String rol, List<Long> peticions) {
        this.rol = rol;
        this.peticions = peticions;
    }

    public String getRol() {
        return rol;
    }

    public List<Long> getPeticions() {
        return peticions;
    }

}

