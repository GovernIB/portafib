package org.fundaciobit.plugins.signatureweb.fire;

import java.security.cert.X509Certificate;
import java.util.Properties;

import es.gob.clavefirma.client.signprocess.HttpSignProcessConstants;
import es.gob.clavefirma.client.signprocess.HttpSignProcessConstants.SignatureAlgorithm;
import es.gob.clavefirma.client.signprocess.HttpSignProcessConstants.SignatureFormat;
import es.gob.clavefirma.client.signprocess.HttpSignProcessConstants.SignatureOperation;

/**
 * 
 * @author anadal
 *
 */
public class FIReFileInfoSignature {

  protected final HttpSignProcessConstants.SignatureOperation signOperation;
  protected final HttpSignProcessConstants.SignatureFormat signType;
  protected final HttpSignProcessConstants.SignatureAlgorithm signAlgorithm;
  protected final Properties signProperties;
  protected final X509Certificate certificate;
  protected final byte[] dataToSign;
  protected final Properties remoteConfProperties;

  protected String transactionID;

  // protected LoadResult loadResult;

  /**
   * @param signOperation
   * @param signType
   * @param signAlgorithm
   * @param signProperties
   * @param certificate
   * @param dataToSign
   * @param remoteConfProperties
   */
  public FIReFileInfoSignature(SignatureFormat signType, SignatureAlgorithm signAlgorithm,
      Properties signProperties, X509Certificate certificate, byte[] dataToSign,
      Properties remoteConfProperties) {
    super();
    this.signOperation = HttpSignProcessConstants.SignatureOperation.SIGN;
    this.signType = signType;
    this.signAlgorithm = signAlgorithm;
    this.signProperties = signProperties;
    this.certificate = certificate;
    this.dataToSign = dataToSign;
    this.remoteConfProperties = remoteConfProperties;
  }

  /**
   * @param appId
   * @param subjectId
   * @param signOperation
   * @param signType
   * @param signAlgorithm
   * @param signProperties
   * @param certificate
   * @param dataToSign
   * @param remoteConfProperties
   */
  public FIReFileInfoSignature(SignatureOperation signOperation, SignatureFormat signType,
      SignatureAlgorithm signAlgorithm, Properties signProperties,
      X509Certificate certificate, byte[] dataToSign, Properties remoteConfProperties) {
    super();
    this.signOperation = signOperation;
    this.signType = signType;
    this.signAlgorithm = signAlgorithm;
    this.signProperties = signProperties;
    this.certificate = certificate;
    this.dataToSign = dataToSign;
    this.remoteConfProperties = remoteConfProperties;
  }

  public HttpSignProcessConstants.SignatureOperation getSignOperation() {
    return signOperation;
  }

  public HttpSignProcessConstants.SignatureFormat getSignType() {
    return signType;
  }

  public HttpSignProcessConstants.SignatureAlgorithm getSignAlgorithm() {
    return signAlgorithm;
  }

  public Properties getSignProperties() {
    return signProperties;
  }

  public X509Certificate getCertificate() {
    return certificate;
  }

  public byte[] getDataToSign() {
    return dataToSign;
  }

  public Properties getRemoteConfProperties() {
    return remoteConfProperties;
  }

  public String getTransactionID() {
    return transactionID;
  }

  public void setTransactionID(String transactionID) {
    this.transactionID = transactionID;
  }

}
