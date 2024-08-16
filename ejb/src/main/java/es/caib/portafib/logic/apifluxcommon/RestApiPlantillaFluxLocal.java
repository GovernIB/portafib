package es.caib.portafib.logic.apifluxcommon;

import es.caib.portafib.persistence.UsuariAplicacioJPA;

import javax.ejb.Local;

import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleGetTransactionIdRequest;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleStartTransactionRequest;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface RestApiPlantillaFluxLocal {

    String JNDI_NAME = "java:app/portafib-ejb/RestApiPlantillaFluxEJB";

    String PlantillaDeFluxDeFirmesRestController_CONTEXT = "/public/flowtemplate";

    /**
     * Fer neteja de transaccions Obsoletes
     */
    void cleanExpiredTransactions();

    void internalCloseTransaction(String transactionID);

    void storeTransactionInfo(String transactionID, UsuariAplicacioJPA usuariAplicacio,
            FlowTemplateSimpleGetTransactionIdRequest transactionIInfo);

    TransactionInfo readTransactionInfo(String transactionID);

    void removeTransactionInfo(String transactionID);

    void storeTransactionInfo(String transactionID, UsuariAplicacioJPA usuariAplicacio,
            FlowTemplateSimpleGetTransactionIdRequest transactionIInfo,
            FlowTemplateSimpleStartTransactionRequest startTransactionInfo, Long fluxDeFirmesID);

}
