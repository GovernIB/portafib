package es.caib.portafib.app.client;

import java.util.List;

import es.caib.portafib.app.Rol;

/**
 * Created by anadal on 05/09/2017.
 */
public class NotificacioRest {

    private final Rol rol;
    private final List<Long> peticions;

    public NotificacioRest(Rol rol, List<Long> peticions) {
        this.rol = rol;
        this.peticions = peticions;
    }

    public Rol getRol() {
        return rol;
    }

    public List<Long> getPeticions() {
        return peticions;
    }

}

