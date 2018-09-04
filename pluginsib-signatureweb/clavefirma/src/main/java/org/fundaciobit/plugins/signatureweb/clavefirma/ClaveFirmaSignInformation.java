package org.fundaciobit.plugins.signatureweb.clavefirma;

import java.security.cert.X509Certificate;
import java.util.Properties;

import es.gob.clavefirma.client.signprocess.HttpSignProcessConstants;
import es.gob.clavefirma.client.signprocess.LoadResult;
import es.gob.clavefirma.client.signprocess.HttpSignProcessConstants.SignatureAlgorithm;
import es.gob.clavefirma.client.signprocess.HttpSignProcessConstants.SignatureFormat;
import es.gob.clavefirma.client.signprocess.HttpSignProcessConstants.SignatureOperation;

/**
 * 
 * @author anadal
 *
 */
public class ClaveFirmaSignInformation {

  protected final String appId;
  protected final String subjectId;
  protected final HttpSignProcessConstants.SignatureOperation signOperation;
  protected final HttpSignProcessConstants.SignatureFormat signType;
  protected final HttpSignProcessConstants.SignatureAlgorithm signAlgorithm;
  protected final Properties signProperties;
  protected final X509Certificate certificate;
  protected final byte[] dataToSign;
  protected final Properties remoteConfProperties;

  protected LoadResult loadResult;

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
  public ClaveFirmaSignInformation(String appId, String subjectId, SignatureFormat signType,
      SignatureAlgorithm signAlgorithm, Properties signProperties,
      X509Certificate certificate, byte[] dataToSign, Properties remoteConfProperties) {
    super();
    this.appId = appId;
    this.subjectId = subjectId;
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
  public ClaveFirmaSignInformation(String appId, String subjectId,
      SignatureOperation signOperation, SignatureFormat signType,
      SignatureAlgorithm signAlgorithm, Properties signProperties,
      X509Certificate certificate, byte[] dataToSign, Properties remoteConfProperties) {
    super();
    this.appId = appId;
    this.subjectId = subjectId;
    this.signOperation = signOperation;
    this.signType = signType;
    this.signAlgorithm = signAlgorithm;
    this.signProperties = signProperties;
    this.certificate = certificate;
    this.dataToSign = dataToSign;
    this.remoteConfProperties = remoteConfProperties;
  }

  public String getAppId() {
    return appId;
  }

  public String getSubjectId() {
    return subjectId;
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

  public LoadResult getLoadResult() {
    return loadResult;
  }

  public void setLoadResult(LoadResult loadResult) {
    this.loadResult = loadResult;
  }

}

