package org.fundaciobit.apisib.apifirmasimple.v1.test.selenium;

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
            case MINIAPPLET:
                return new MiniAppletSignStrategyImpl();
            default:
                throw new UnsupportedOperationException("StrategyType " + type + " no suportada");
        }
    }

}
