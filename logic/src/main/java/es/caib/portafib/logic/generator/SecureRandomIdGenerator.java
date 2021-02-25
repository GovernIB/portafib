package es.caib.portafib.logic.generator;

import org.fundaciobit.genapp.common.utils.Base64;

import java.io.IOException;
import java.security.SecureRandom;

/**
 * Genera identificadors únics a partir de la classe {@link SecureRandom}.
 * Empra 21 bytes aleatoris tot i que 20 serien més que suficents, perquè és múltiple de 3, i passat a Base64 surten
 * 28 caràcters exactes, per tant no cal fer padding amb "=".
 */
public class SecureRandomIdGenerator implements IdGenerator {

    private final SecureRandom random = new SecureRandom();

    protected SecureRandomIdGenerator() {
    }

    @Override
    public String generate() {
        byte[] buffer = new byte[21];
        random.nextBytes(buffer);
        try {
            return Base64.encodeBytes(buffer, Base64.URL_SAFE);
        } catch (IOException exception) {
            throw new RuntimeException("Error creant ID", exception);
        }
    }

}
