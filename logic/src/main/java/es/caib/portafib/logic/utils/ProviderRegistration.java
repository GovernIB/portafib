package es.caib.portafib.logic.utils;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.Security;
import java.util.ArrayList;
import java.util.List;

public class ProviderRegistration {

    private final List<String> addedProviders = new ArrayList<String>();

    public void register() {
        if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
            Security.addProvider(new BouncyCastleProvider());
            addedProviders.add(BouncyCastleProvider.PROVIDER_NAME);
        }
    }

    public void unregister() {
        for (String providerName : addedProviders) {
            Security.removeProvider(providerName);
        }
    }
}
