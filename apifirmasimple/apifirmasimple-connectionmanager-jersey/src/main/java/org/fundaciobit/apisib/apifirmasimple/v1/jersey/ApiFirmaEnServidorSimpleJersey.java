package org.fundaciobit.apisib.apifirmasimple.v1.jersey;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.List;

import org.fundaciobit.apisib.apifirmasimple.v1.ApiFirmaEnServidorSimple;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleAvailableProfile;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleError;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFile;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignDocumentRequest;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignatureResult;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleUpgradeRequest;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleUpgradeResponse;
import org.fundaciobit.apisib.apifirmasimple.v1.exceptions.CancelledUserException;
import org.fundaciobit.apisib.apifirmasimple.v1.exceptions.NoAvailablePluginException;
import org.fundaciobit.apisib.core.exceptions.AbstractApisIBException;
import org.fundaciobit.apisib.core.exceptions.ApisIBClientException;
import org.fundaciobit.apisib.core.exceptions.ApisIBTimeOutException;
import org.fundaciobit.apisib.jerseycore.AbstractApisIBConnectionManagerJersey;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;

/**
 * 
 * @author anadal(u80067)
 *
 */
public class ApiFirmaEnServidorSimpleJersey
    extends AbstractApisIBConnectionManagerJersey<FirmaSimpleError>
    implements ApiFirmaEnServidorSimple {

  /**
   * @param endPointBase
   */
  public ApiFirmaEnServidorSimpleJersey(String endPointBase) {
    super(endPointBase);
  }

  /**
   * @param endPointBase
   * @param username
   * @param password
   */
  public ApiFirmaEnServidorSimpleJersey(String endPointBase, String username,
      String password) {
    super(endPointBase, username, password);
  }

  @Override
  protected void processException(FirmaSimpleError simple, String tipus)
      throws AbstractApisIBException {
    if (tipus.equals(CancelledUserException.class.getName())) {

      throw new CancelledUserException(simple.getMessage(), simple.getStackTrace());

    } else if (tipus.equals(NoAvailablePluginException.class.getName())) {

      throw new NoAvailablePluginException(simple.getMessage(), simple.getStackTrace());

    } else {
      super.processException(simple, tipus);
    }
  }

  /**
   *
   * @param signaturesSet
   * @return
   * @throws Exception
   */
  @Override
  public FirmaSimpleSignatureResult signDocument(FirmaSimpleSignDocumentRequest signaturesSet)
      throws AbstractApisIBException {

    ClientResponse response = commonCall(signaturesSet, SIGNDOCUMENT);

    FirmaSimpleSignatureResult result = response.getEntity(FirmaSimpleSignatureResult.class);

    return result;
  }

  @Override
  public FirmaSimpleUpgradeResponse upgradeSignature(FirmaSimpleUpgradeRequest fsur)
      throws AbstractApisIBException {
    ClientResponse response = commonCall(fsur, UPGRADESIGNATURE);

    FirmaSimpleUpgradeResponse fullresult = response
        .getEntity(FirmaSimpleUpgradeResponse.class);

    FirmaSimpleFile result = fullresult.getUpgradedFile();
    if (result != null && result.getMime() != null) {
      if ("application/xml".equals(result.getMime())) {
        if (!isUTF8(result.getData())) {
          // Algunes firmes XAdes es retornen com a ANSI despres de passar per REST
          // S'han de convertir a UTF-8
          try {
            result.setData(transformEncoding(result.getData(), "ISO-8859-1", "UTF-8"));
          } catch (IOException e) {
            throw new ApisIBClientException(
                "Error desconegut transformant les dades de'un fitxer XAdES: "
                    + e.getMessage(),
                e);
          }
          // result.setMime(null);
        }
      }
    }

    return fullresult;

  }

  /**
   * 
   * @return
   * @throws Exception
   */
  @Override
  public List<FirmaSimpleAvailableProfile> getAvailableProfiles(String locale)
      throws AbstractApisIBException {

    ClientResponse response = commonCall(locale, AVAILABLEPROFILES);

    List<FirmaSimpleAvailableProfile> result = response
        .getEntity(new GenericType<List<FirmaSimpleAvailableProfile>>() {
        });

    return result;
  }

  // XYZ Moure a Utils
  private static byte[] transformEncoding(byte[] source, String srcEncoding,
      String tgtEncoding) throws IOException {
    BufferedReader br = null;
    BufferedWriter bw = null;

    ByteArrayOutputStream baos = new ByteArrayOutputStream();

    try {
      br = new BufferedReader(
          new InputStreamReader(new ByteArrayInputStream(source), srcEncoding));
      bw = new BufferedWriter(new OutputStreamWriter(baos, tgtEncoding));
      char[] buffer = new char[16384];
      int read;
      while ((read = br.read(buffer)) != -1)
        bw.write(buffer, 0, read);
    } finally {
      try {
        if (br != null)
          br.close();
      } finally {
        if (bw != null)
          bw.close();
      }
    }

    return baos.toByteArray();
  }

  // XYZ ZZZ Moure a Utils
  private static boolean isUTF8(byte[] barr) {

    CharsetDecoder decoder = Charset.forName("UTF-8").newDecoder();
    ByteBuffer buf = ByteBuffer.wrap(barr);

    try {
      decoder.decode(buf);

    } catch (CharacterCodingException e) {
      return false;
    }

    return true;
  }

  @Override
  protected Class<FirmaSimpleError> getErrorClass() {
    return FirmaSimpleError.class;
  }

}
