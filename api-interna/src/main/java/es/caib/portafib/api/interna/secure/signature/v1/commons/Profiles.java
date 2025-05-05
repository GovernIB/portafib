package es.caib.portafib.api.interna.secure.signature.v1.commons;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author anadal
 * 18 dic 2024 11:01:59
 */
public class Profiles {

    public List<Profile> profiles;

    public Profiles() {
        this.profiles = new ArrayList<Profile>();
    }

    public Profiles(List<Profile> profiles) {
        this.profiles = profiles;
    }

    public List<Profile> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<Profile> profiles) {
        this.profiles = profiles;
    }

}
