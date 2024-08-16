package es.caib.portafib.logic.apifluxcommon;

import java.util.Date;

import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleGetTransactionIdRequest;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleStartTransactionRequest;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleStatus;

import es.caib.portafib.persistence.UsuariAplicacioJPA;

/**
 * 
 * @author anadal
 *
 */
public class TransactionInfo {

  // 15 minuts
  public static final long MAX_TIME = 900000L;

  public static final int STATUS_IN_PROGRESS = 1;

  protected final String transactionID;

  protected final UsuariAplicacioJPA usuariAplicacio;

  protected final FlowTemplateSimpleGetTransactionIdRequest transactionInfo;

  protected FlowTemplateSimpleStartTransactionRequest startTransactionInfo;

  protected final Date startTime;

  protected final FlowTemplateSimpleStatus status;

  protected Long fluxDeFirmesID;

  public TransactionInfo(String transactionID, UsuariAplicacioJPA usuariAplicacio,
      FlowTemplateSimpleGetTransactionIdRequest transactionIInfo) {
    super();
    this.transactionID = transactionID;
    this.usuariAplicacio = usuariAplicacio;
    this.startTime = new Date();
    this.transactionInfo = transactionIInfo;
    this.status = new FlowTemplateSimpleStatus(FlowTemplateSimpleStatus.STATUS_RESERVED_ID);
  }

  public FlowTemplateSimpleStartTransactionRequest getStartTransactionInfo() {
    return startTransactionInfo;
  }

  public void setStartTransactionInfo(
      FlowTemplateSimpleStartTransactionRequest startTransactionInfo) {
    this.startTransactionInfo = startTransactionInfo;
  }

  public FlowTemplateSimpleStatus getStatus() {
    return status;
  }

  public Long getFluxDeFirmesID() {
    return fluxDeFirmesID;
  }

  public void setFluxDeFirmesID(Long fluxDeFirmesID) {
    this.fluxDeFirmesID = fluxDeFirmesID;
  }

  public UsuariAplicacioJPA getUsuariAplicacio() {
    return usuariAplicacio;
  }

  public String getTransactionID() {
    return transactionID;
  }

  public FlowTemplateSimpleGetTransactionIdRequest getTransactionInfo() {
    return transactionInfo;
  }

  public Date getStartTime() {
    return startTime;
  }

}
