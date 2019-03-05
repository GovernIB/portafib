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

import org.fundaciobit.apisib.apifirmasimple.v1.ApiFirmaEnServidorSimple;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleAvailableProfiles;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFile;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignDocumentRequest;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignDocumentResponse;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleUpgradeRequest;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleUpgradeResponse;

import com.sun.jersey.api.client.ClientResponse;

/**
 * 
 * @author anadal
 *
 */
public class ApiFirmaEnServidorSimpleJersey extends AbstractApiFirmaSimpleJersey implements
    ApiFirmaEnServidorSimple {

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
  public ApiFirmaEnServidorSimpleJersey(String endPointBase, String username, String password) {
    super(endPointBase, username, password);
  }

  /**
   *
   * @param signaturesSet
   * @return
   * @throws Exception
   */
  @Override
  public FirmaSimpleSignDocumentResponse signDocument(
      FirmaSimpleSignDocumentRequest signaturesSet) throws Exception {

    ClientResponse response = commonCall(signaturesSet, SIGNDOCUMENT);

    FirmaSimpleSignDocumentResponse result = response
        .getEntity(FirmaSimpleSignDocumentResponse.class);

    return result;
  }



  @Override
  public FirmaSimpleUpgradeResponse upgradeSignature(FirmaSimpleUpgradeRequest fsur)
      throws Exception {
    ClientResponse response = commonCall(fsur, UPGRADESIGNATURE);

    FirmaSimpleUpgradeResponse fullresult = response
        .getEntity(FirmaSimpleUpgradeResponse.class);

    FirmaSimpleFile result = fullresult.getUpgradedFile();
    if (result != null && result.getMime() != null) {
      if ("application/xml".equals(result.getMime())) {
        if (!isUTF8(result.getData())) {
          // Algunes firmes XAdes es retornen com a ANSI despres de passar per REST
          // S'han de convertir a UTF-8
          result.setData(transformEncoding(result.getData(), "ISO-8859-1", "UTF-8"));
          // result.setMime(null);
        }
      }
    }

    return fullresult;

    /*
     * XYZ ZZZ InputStream is = response.getEntityInputStream(); ByteArrayOutputStream os = new
     * ByteArrayOutputStream(); int n; byte[] buffer = new byte[1024]; while ((n =
     * is.read(buffer)) > -1) { os.write(buffer, 0, n); // Don't allow any extra bytes to creep
     * in, final write } os.close();
     * 
     * return os.toByteArray();
     */
  }

  /**
   * 
   * @return
   * @throws Exception
   */
  @Override
  public FirmaSimpleAvailableProfiles getAvailableProfiles(String locale) throws Exception {

    ClientResponse response = commonCall(locale, AVAILABLEPROFILES);

    FirmaSimpleAvailableProfiles result = response
        .getEntity(FirmaSimpleAvailableProfiles.class);

    return result;
  }

  // XYZ Moure a Utils
  private static byte[] transformEncoding(byte[] source, String srcEncoding, String tgtEncoding)
      throws IOException {
    BufferedReader br = null;
    BufferedWriter bw = null;

    ByteArrayOutputStream baos = new ByteArrayOutputStream();

    try {
      br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(source),
          srcEncoding));
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

}
