package es.caib.portafib.it.signers;

public class SignStrategyFactory {

    public static SignStrategy getSignStrategy(SignStrategyType type) {
        switch (type) {
            case FIRE:
                return new FIReSignStrategyImpl();
            case SIA:
                return new SIASignStrategyImpl();
            case VIAFIRMA:
                return new ViafirmaSignStrategyImpl();
            case AUTOFIRMA:
                return new AutofirmaSignStrategyImpl();
            default:
                throw new UnsupportedOperationException("StrategyType " + type + " no suportada");
        }
    }

}
