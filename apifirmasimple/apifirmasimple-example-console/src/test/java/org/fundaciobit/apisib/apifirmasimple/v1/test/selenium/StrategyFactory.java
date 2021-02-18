package org.fundaciobit.apisib.apifirmasimple.v1.test.selenium;

public class StrategyFactory {

    public static SignStrategy getSignStrategy(StrategyType type) {
        switch (type) {
            case FIRE:
                return new FIReSignStrategyImpl();
            case SIA:
                return new SIASignStrategyImpl();
            case MINIAPPLET:
                return new MiniAppletSignStrategyImpl();
            default:
                throw new UnsupportedOperationException("StrategyType " + type + " no suportada");
        }
    }

}
