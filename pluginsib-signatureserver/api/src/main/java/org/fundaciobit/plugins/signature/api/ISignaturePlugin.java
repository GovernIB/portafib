package org.fundaciobit.plugins.signature.api;

import java.util.List;
import java.util.Locale;

import org.fundaciobit.pluginsib.core.IPlugin;

/**
 * 
 * @author anadal
 *
 */
public interface ISignaturePlugin extends IPlugin {


  /**
   * 
   * @param locale
   *          idioma amb que es vol el nom del plugin
   * @return Nom del plugin
   */
  public String getName(Locale locale);
  
  /**
   * @return Le soperacions de firma suportades segons el tipus de firma
   * @param signType Tipus de Firma
   * @see FileInfoSignature.SIGNATURE_OPERATION_SIGN = FIRMA
   * @see FileInfoSignature.SIGNATURE_OPERATION_COSIGN = COFIRMA
   * @see FileInfoSignature.SIGNATURE_OPERATION_COUNTERSIGN = CONTRAFIRMA
   */
  public int[] getSupportedOperationsBySignType(String signType);

  /**
   * @return Els tipus de firma suportats. Actualment només es suporta PAdES.
   * @see FileInfoSignature.SIGN_TYPE_PADES = "PAdES";
   * @see FileInfoSignature.SIGN_TYPE_XADES = "XAdES";
   * @see FileInfoSignature.SIGN_TYPE_CADES = "CAdES";
   * @see FileInfoSignature.SIGN_TYPE_FACTURAE = "FacturaE";
   * @see FileInfoSignature.SIGN_TYPE_OOXML = "OOXML";
   * @see FileInfoSignature.SIGN_TYPE_ODF = "ODF";
   */
  public String[] getSupportedSignatureTypes();

  /**
   * @param signType
   *          Tipus de Firma
   * @return Retorna els algorismes suportats segons els tipus de firma passat
   *         per paràmetre
   */
  public String[] getSupportedSignatureAlgorithms(String signType);

  /**
   * @return Retorna els tipus de Barcode suportats per l'estampació del Codi
   *         Segur de Verificació (CSV). Per exemple, el tipus suportats pel
   *         plugins de PortaFIB són: BarCode128, Pdf417 i QrCode
   */
  public List<String> getSupportedBarCodeTypes();
  
  
  /**
   * 
   * @return null si no hi ha límit. Sinó el numero màxim de firmes per transacció.
   */
  public Integer getSupportedNumberOfSignaturesInBatch();


  /**
   * @param signType
   *          Tipus de Firma
   * @return true indica que el plugin accepta generadors de Segell de Temps
   *         definits dins FileInfoSignature.timeStampGenerator
   */
  public boolean acceptExternalTimeStampGenerator(String signType);

  /**
   * @param signType
   *          Tipus de Firma
   * @return true, indica que el plugin internament ofereix un generador de
   *         segellat de temps.
   */
  public boolean providesTimeStampGenerator(String signType);

  /**
   * 
   * @return true indica que el plugin accepta generadors del imatges de la
   *         Firma Visible PDF definits dins
   *         FileInfoSignature.pdfInfoSignature.rubricGenerator.
   */
  public boolean acceptExternalRubricGenerator();

  /**
   * 
   * @return true, indica que el plugin internament ofereix un generador de
   *         imatges de la Firma Visible PDF.
   */
  public boolean providesRubricGenerator();

  /**
   * 
   * @return true indica si el plugin accepta estampadors de Codi Segur de
   *         Verificació (missatge i/o codi de barres).
   */
  public boolean acceptExternalSecureVerificationCodeStamper();

  /**
   * 
   * @return true, indica que el plugin internament ofereix estampadors de Codi
   *         Segur de Verificació (missatge i/o codi de barres).
   */
  public boolean providesSecureVerificationCodeStamper();

  /**
   * @return llista de propietats disponibles per aquest plugin. 
   *    Si retorna null, es que no ha implementat aquest mètode
   */
  public List<PropertyInfo> getAvailableProperties(String propertyKeyBase);

}
