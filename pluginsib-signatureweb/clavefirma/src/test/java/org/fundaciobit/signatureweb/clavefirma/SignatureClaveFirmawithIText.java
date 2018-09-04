package org.fundaciobit.signatureweb.clavefirma;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.cert.Certificate;

import org.fundaciobit.plugins.signatureweb.clavefirma.ClaveFirmaSignatureWebPlugin;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfSignatureAppearance;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.security.BouncyCastleDigest;
import com.itextpdf.text.pdf.security.ExternalDigest;
import com.itextpdf.text.pdf.security.ExternalSignature;
import com.itextpdf.text.pdf.security.MakeSignature;
import com.itextpdf.text.pdf.security.TSAClient;
import com.itextpdf.text.pdf.security.MakeSignature.CryptoStandard;


/**
 * 
 * @author anadal
 * 
 * Basat en C4_07_ClientServerSigning
 * 
 * http://itextpdf.com/examples/security/digital-signatures-white-paper/digital-signatures-chapter-4
 *
 */
public class SignatureClaveFirmawithIText {

  final ClaveFirmaSignatureWebPlugin plugin;

  final byte[] certBytes;

  final String hashAlgorithm;

  final String encryptionAlgorithm;

  final String callbackURL;

  final String username;

  final String administrationID;

  /**
   * @param plugin
   * @param certBytes
   * @param documents
   * @param hashAlgorithm
   * @param encryptionAlgorithm
   * @param callbackURL
   * @param username
   * @param administrationID
   */
  public SignatureClaveFirmawithIText(ClaveFirmaSignatureWebPlugin plugin,
      byte[] certBytes, String hashAlgorithm, String encryptionAlgorithm, String callbackURL,
      String username, String administrationID) {
    super();
    this.plugin = plugin;
    this.certBytes = certBytes;
    this.hashAlgorithm = hashAlgorithm;
    this.encryptionAlgorithm = encryptionAlgorithm;
    this.callbackURL = callbackURL;
    this.username = username;
    this.administrationID = administrationID;
  }

  public class ServerSignature implements ExternalSignature {
/*
    final DocumentsToSign docToSign;

    /**
     * @param plugin
     * @param hashAlgorithm
     * @param encryptionAlgorithm
     *
    public ServerSignature(DocumentsToSign docToSign) {
      this.docToSign = docToSign;
    }
*/
    @Override
    public String getHashAlgorithm() {
      return hashAlgorithm; // DigestAlgorithms.SHA256;
    }

    @Override
    public String getEncryptionAlgorithm() {
      return encryptionAlgorithm; // "RSA";
    }

    @Override
    public byte[] sign(byte[] message) throws GeneralSecurityException {
      return null;

      /*
      System.out.println(" Sign Message Length: " + message.length);

      MessageDigest messageDigest = MessageDigest.getInstance(getHashAlgorithm());
      messageDigest.update(message, 0, message.length);
      byte hash[] = messageDigest.digest();

      System.out.println(" Hash Length: " + hash.length);

      String id_transaction = null;
      

      try {
        
        docToSign.setData(hash);

        final ArrayList<DocumentsToSign> documents = new ArrayList<DocumentsToSign>();
        documents.add(docToSign);

        StartTransactionResult startT = plugin.startTransacion(certBytes, documents,
            hashAlgorithm, callbackURL, username, administrationID);
            

        id_transaction = startT.getIdTransaction();

        System.out.println(" id_transaction = " + id_transaction);

        String redireccionURL = startT.getRedirect();

        System.out.println("  id_transaction = " + id_transaction);

        Desktop.getDesktop().browse(new URI(redireccionURL));

        System.out
            .println("Quan hagis firmat el document torna a aquesta finestra i pitja Retorn ...");
        System.in.read();

        DataTransactionResult result = plugin.getResultTransaction(id_transaction);

        StateTransaction stateTrans = result.getStateTransaction();

        System.out.println(" --------  stateTrans.getResult() = ]"
            + stateTrans.getResult() + "[");
        System.out.println(" --------  stateTrans.getState() = ]" + stateTrans.getState()
            + "[");
        System.out.println(" --------  stateTrans.getCode_error() = ]"
            + stateTrans.getCode_error() + "[");
        System.out.println(" --------  stateTrans.getDescription() = ]"
            + stateTrans.getDescription() + "[");

        if (!"0".equals(stateTrans.getCode_error())) {

          if ("WEBCT00016".equals(stateTrans.getCode_error())) {
            throw new Exception("cancelled");

          } else {

            // ========= CAS ERROR
            String errorMsg = "Error en el servidor de SIA:\n" + " [ Codi: "
                + stateTrans.getCode_error() + "]\n" + " [ Descripcio: "
                + stateTrans.getDescription() + "]\n" + " [ Result: " + stateTrans.getResult()
                + "]\n" + " [ State: " + stateTrans.getState() + "]";

            throw new Exception(errorMsg);
          }

        } else {

          // ========= CAS OK

          List<SignsInfo> signsInfo = result.getSigns();

          byte[] signHash = null;

          for (SignsInfo signInfo : signsInfo) {

            if (signInfo.getIdData().equals(docToSign.getIdData())) {
              signHash = signInfo.getSign();
              break;
            }
          }

          if (signHash == null) {
            throw new Exception("No s'ha trobat document amb identificador "
                + docToSign.getIdData() + " entre les firmes");
          }

          return signHash;
        }

      } catch (IOException e) {
        throw new ExceptionConverter(e);
      } catch (SafeCertGateWayException e) {
        e.printStackTrace();
        throw new ExceptionConverter(e);
      } catch (Exception e) {
        e.printStackTrace();
        throw new ExceptionConverter(e);
      } finally {
        if (id_transaction != null) {
          // Imprimir final
          try {
            plugin.getGateWayAPI().endTransaction(id_transaction);

          } catch (Exception e) {
            System.err.println("Error finalitzant la transacció: " + e.getMessage());
            e.printStackTrace(System.err);
          }
        }
      }
      */
    }

  }

  /*
   * public class SignThread extends Thread {
   * 
   * final File src; final File dest; final Certificate[] chain; final
   * CryptoStandard subfilter; final DocumentsToSign docToSign;
   * 
   * /**
   * 
   * @param src
   * 
   * @param dest
   * 
   * @param chain
   * 
   * @param subfilter
   * 
   * @param docToSign
   * 
   * public SignThread(File src, File dest, Certificate[] chain, CryptoStandard
   * subfilter, DocumentsToSign docToSign) { super(); this.src = src; this.dest
   * = dest; this.chain = chain; this.subfilter = subfilter; this.docToSign =
   * docToSign; }
   * 
   * @Override public void run() { String reason = "Autofirma"; String location
   * = "Illes Balears"; try { sign(src, dest, chain, subfilter, reason,
   * location, docToSign); } catch (GeneralSecurityException e) { // TODO
   * Auto-generated catch block e.printStackTrace(); } catch (IOException e) {
   * // TODO Auto-generated catch block e.printStackTrace(); } catch
   * (DocumentException e) { // TODO Auto-generated catch block
   * e.printStackTrace(); } }
   */
  public void sign(File src, File dest, Certificate[] chain, CryptoStandard subfilter,
      String reason, String location
      //DocumentsToSign docToSign
  // , Semaphore generaredHashes, Semaphore generatedSigns
  ) throws GeneralSecurityException, IOException, DocumentException {

    // Creating the reader and the stamper
    PdfReader reader = new PdfReader(src.getAbsolutePath());
    FileOutputStream os = new FileOutputStream(dest);
    PdfStamper stamper = PdfStamper.createSignature(reader, os, '\0');
    // Creating the appearance
    PdfSignatureAppearance appearance = stamper.getSignatureAppearance();
    appearance.setReason(reason);
    appearance.setLocation(location);
    appearance.setVisibleSignature(new Rectangle(36, 748, 144, 780), 1, "sig");


    // Creating the signature
    ExternalDigest digest = new BouncyCastleDigest();
    ExternalSignature signature = new ServerSignature(/*docToSign*/);

    // TODO
    TSAClient tsaClient = null;
    MakeSignature.signDetached(appearance, digest, signature, chain, null, null, tsaClient, 0,
        subfilter);
  }

  // }

  public static void main(String[] args) throws GeneralSecurityException, IOException,
      DocumentException {

    try {
      /*
      Properties properties = new Properties();

      properties.load(new FileInputStream("test.properties"));

      String username = properties.getProperty("username");
      String administrationID = properties.getProperty("administrationID");

      ClaveFirmaSignatureWebPlugin plugin;
      plugin = new ClaveFirmaSignatureWebPlugin("es.ibsalut.example.", properties);

      final String filter = "";
      boolean filtered = true; // (plugin.filter(username, administrationID, filter) != 0);

      if (!filtered) {
        System.err.println("No ha passat el filtre del Plugin");
        return;
      } else {
        System.out.println("FILTRE OK");
      }

      // Document a firmar
      File pdfSource = new File(properties.getProperty("pdf.source")); // =./hola.pdf
      System.out.println(" Tamany PDF original: " + pdfSource.length());
      File pdfDest = new File(properties.getProperty("pdf.dest")); // =./hola.pdf

      // Certificat
      
      Map<String, CertificateInfo> certs = plugin.listCertificates(username, administrationID);

      System.out.println("Certificats Size " + certs.size());

      if (certs.size() == 0) {
        System.err.println("No s'han trobat certificats de tipus firma en el servidor.");
        return;
      }
      CertificateInfo cinfo = null;
      // Llegim el primer certificat
      for (CertificateInfo tmp : certs.values()) {
        cinfo = tmp;
        break;
      }
      *

      byte[] certBytes = cinfo.getCertificate();
      Certificate[] chain = new Certificate[] { CertificateUtils
          .decodeCertificate(new ByteArrayInputStream(certBytes)) };

      final String fileInfoname = pdfSource.getName();
      final String docID = String.valueOf(System.currentTimeMillis());

      DocumentsToSign doc = new DocumentsToSign();
      doc.setEncodeB64(false);
      // doc.setData(hash);
      doc.setNameDocument(fileInfoname);
      doc.setTitleDocument(fileInfoname);
      doc.setIdData(docID);

      SignatureSIAwithIText app = new SignatureSIAwithIText(plugin, certBytes,
          "SHA-1",
          "RSA",// RSA =>  OK i  DSA => 
          "http://www.google.com", username, administrationID);
      app.sign(pdfSource, pdfDest, chain,
          CryptoStandard.CMS,// CryptoStandard.CADES, => OK
          null, null, // "Autofirma", "Illes Balears",
          doc);
*/
      System.out.println("Final de Firma.");

    } catch (Exception e) {
      e.printStackTrace();
    }

  }

}
