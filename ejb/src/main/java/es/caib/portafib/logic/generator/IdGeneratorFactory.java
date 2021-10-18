package es.caib.portafib.logic.generator;

public class IdGeneratorFactory {


    private static final IdGenerator generator = new SecureRandomIdGenerator();

    public static IdGenerator getGenerator() {
        return generator;
    }
}
