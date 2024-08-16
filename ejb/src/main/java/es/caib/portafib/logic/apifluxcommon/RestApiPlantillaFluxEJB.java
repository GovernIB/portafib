package es.caib.portafib.logic.apifluxcommon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleGetTransactionIdRequest;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleStartTransactionRequest;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.jboss.logging.Logger;

import es.caib.portafib.logic.FluxDeFirmesLogicaLocal;
import es.caib.portafib.persistence.UsuariAplicacioJPA;

/**
 * @author anadal
 */
@Stateless(name = "RestApiPlantillaFluxEJB")
public class RestApiPlantillaFluxEJB implements RestApiPlantillaFluxLocal {

    protected Logger log = Logger.getLogger(getClass());

    public static final Map<String, TransactionInfo> currentTransactions = new HashMap<String, TransactionInfo>();

    @EJB(mappedName = FluxDeFirmesLogicaLocal.JNDI_NAME)
    protected FluxDeFirmesLogicaLocal fluxDeFirmesLogicaEjb;

    @Override
    public void storeTransactionInfo(String transactionID, UsuariAplicacioJPA usuariAplicacio,
            FlowTemplateSimpleGetTransactionIdRequest transactionIInfo) {

        currentTransactions.put(transactionID, new TransactionInfo(transactionID, usuariAplicacio, transactionIInfo));
    }

    @Override
    public void storeTransactionInfo(String transactionID, UsuariAplicacioJPA usuariAplicacio,
            FlowTemplateSimpleGetTransactionIdRequest transactionIInfo,
            FlowTemplateSimpleStartTransactionRequest startTransactionInfo, Long fluxDeFirmesID) {

        TransactionInfo info = new TransactionInfo(transactionID, usuariAplicacio, transactionIInfo);

        info.setStartTransactionInfo(startTransactionInfo);
        info.setFluxDeFirmesID(fluxDeFirmesID);

        currentTransactions.put(transactionID, info);
    }

    @Override
    public TransactionInfo readTransactionInfo(String transactionID) {
        log.info(" XYZ ZZZ startTransaction::currentTransactions.size() => " + currentTransactions.size());

        TransactionInfo ti = currentTransactions.get(transactionID);
        return ti;

    }

    @Override
    public void removeTransactionInfo(String transactionID) {
        currentTransactions.remove(transactionID);
    }

    @Override
    public void internalCloseTransaction(String transactionID) {

        TransactionInfo ti = currentTransactions.get(transactionID);

        if (!ti.getTransactionInfo().isSaveOnServer()) {
            try {
                fluxDeFirmesLogicaEjb.deleteFull(ti.getFluxDeFirmesID());
            } catch (I18NException e) {
                log.error("No s'ha pogut esborrar la plantilla de Flux de Firmes amb ID = " + ti.getFluxDeFirmesID(),
                        e);
            }
        }

        currentTransactions.remove(transactionID);

    }

    /**
     * Fer neteja de transaccions Obsoletes
     */
    @Override
    public void cleanExpiredTransactions() {

        final long now = System.currentTimeMillis();
        for (TransactionInfo info : new ArrayList<TransactionInfo>(currentTransactions.values())) {
            try {
                // 15 minutes
                if (info.getStartTime().getTime() + 900000 < now) {
                    internalCloseTransaction(info.getTransactionID());
                }
            } catch (Exception e) {
                log.error(
                        "Error desconegut" + " netejant transaccions expirades de l'APIFirmaSimple: " + e.getMessage(),
                        e);
            }
        }
    }

}
