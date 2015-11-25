package es.caib.portafib.logic.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.security.Security;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;

import javax.activation.DataHandler;
import javax.mail.util.ByteArrayDataSource;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.fundaciobit.plugins.certificate.ICertificatePlugin;
import org.fundaciobit.plugins.certificate.InformacioCertificat;
import org.fundaciobit.plugins.certificate.ResultatValidacio;
import org.fundaciobit.plugins.documentconverter.IDocumentConverterPlugin;
import org.fundaciobit.plugins.documentconverter.InputDocumentNotSupportedException;
import org.fundaciobit.plugins.documentconverter.OutputDocumentNotSupportedException;
import org.fundaciobit.plugins.utils.CertificateUtils;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.utils.Utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.RectangleReadOnly;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PRStream;
import com.itextpdf.text.pdf.PdfAWriter;
import com.itextpdf.text.pdf.PdfArray;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfFileSpecification;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfString;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.text.pdf.PdfAConformanceLevel;
import com.itextpdf.text.pdf.security.PdfPKCS7;

import es.caib.portafib.model.bean.FitxerBean;
import es.caib.portafib.model.entity.Fitxer;
import es.caib.portafib.utils.Configuracio;
import es.caib.portafib.utils.Constants;
import es.caib.portafib.versio.Versio;

/**
 * 
 * @author anadal
 * 
 */
public class PdfUtils implements Constants {

  protected static Logger log = Logger.getLogger(PdfUtils.class);

  public static final byte[] EOF_PDF;

  static {
    byte[] tmp;
    try {
      tmp = "%%EOF".getBytes("UTF-8");
    } catch (Throwable e) {
      tmp = "%%EOF".getBytes();
    }
    EOF_PDF = tmp;

    log.info("Numero màxim de firmants per taula de firmes: "
        + Constants.MAX_FIRMES_PER_TAULA);
  }


  public static Fitxer convertToPDF(File fileToConvert, Fitxer fileToConvertInfo) throws I18NException {

    String mime = fileToConvertInfo.getMime();
    boolean isDebug = log.isDebugEnabled(); 
    if (isDebug) {
      log.debug("convertToPDF(): Name = " + fileToConvertInfo.getNom());
      log.debug("convertToPDF(): File = " + fileToConvert.getAbsolutePath());
      log.debug("convertToPDF(): File Exists?= " + fileToConvert.exists());
      log.debug("convertToPDF(): MIME = " + mime);
    }

    if (PDF_MIME_TYPE.equals(mime)) {
      return fileToConvertInfo;
    } else {
      try {
        IDocumentConverterPlugin docPlugin = null;

        ByteArrayOutputStream baos;

        docPlugin = PortaFIBPluginsManager.getDocumentConverterPluginInstance();

        String newFileName;
        if (docPlugin != null && docPlugin.isMimeTypeSupported(mime)) {
          FileInputStream fis = new FileInputStream(fileToConvert);
          try {
            baos = new ByteArrayOutputStream();
            docPlugin.convertDocumentByMime(fis, mime, baos, PDF_MIME_TYPE);
            newFileName = fileToConvertInfo.getNom() + "." + PDF_FILE_EXTENSION;
          } finally {
            fis.close();
          }
        } else {

          String extensio = null;
          String nom = fileToConvertInfo.getNom();
          if (nom != null && nom.length() != 0 && nom.lastIndexOf('.') != -1) {
            extensio = nom.substring(nom.lastIndexOf('.') + 1);
          } else {
            throw new I18NException("formatfitxer.conversio.errorinput", fileToConvertInfo.getNom(),
                mime);
          }
          if (PDF_FILE_EXTENSION.equals(extensio.toLowerCase())) {
            newFileName = fileToConvertInfo.getNom();
            baos = new ByteArrayOutputStream();
            baos.write(FileUtils.readFileToByteArray(fileToConvert));
          } else {
            if (docPlugin != null && docPlugin.isFileExtensionSupported(extensio)) {
              FileInputStream fis = new FileInputStream(fileToConvert);
              try {
                if (isDebug) {
                  log.debug("convertToPDF(): Convert using extension: " + extensio);
                }
                baos = new ByteArrayOutputStream();
                docPlugin.convertDocumentByExtension(fis, extensio, baos, PDF_FILE_EXTENSION);
              } finally {
                fis.close();
              }
              newFileName = fileToConvertInfo.getNom() + "." + PDF_FILE_EXTENSION;
            } else {
              throw new I18NException("formatfitxer.conversio.errorinput", fileToConvertInfo.getNom(),
                  mime);
            }
          }
        }

        FitxerBean fileConvertedInfo = new FitxerBean();

        fileConvertedInfo.setMime(PDF_MIME_TYPE);
        fileConvertedInfo.setNom(newFileName);
        fileConvertedInfo.setTamany(baos.size());
        fileConvertedInfo.setData(new DataHandler(new ByteArrayDataSource(baos.toByteArray(),
            newFileName)));

        return fileConvertedInfo;
      } catch (InputDocumentNotSupportedException idnse) {
        throw new I18NException("formatfitxer.conversio.errorinput", fileToConvertInfo.getNom(),
            mime);
      } catch (OutputDocumentNotSupportedException odnse) {
        throw new I18NException("formatfitxer.conversio.erroroutput", fileToConvertInfo.getNom() + "."
            + PDF_FILE_EXTENSION, PDF_MIME_TYPE);
      } catch (Exception e) {
        log.error("Error converting document a PDF:" + e.getMessage(), e);
        throw new I18NException("error.unknown", e.getMessage());
      }

    }

  }

  public static InformacioCertificat checkCertificatePADES(Long fitxerOriginalID,
      Map<Integer, Long> fitxersByNumFirma, File pdf, int numFirma)
      throws I18NException, FileNotFoundException, IOException, Exception {

    long start = 0;
    final boolean isDebug = log.isDebugEnabled();
    
    if (isDebug) {
      start = System.currentTimeMillis();
    }

    byte[] pdfData;
    {
      FileInputStream fis = new FileInputStream(pdf);
      pdfData = PdfUtils.toByteArray(fis);
     
      try {
        fis.close();
      } catch (Exception e) {
      }
    }

    Security.addProvider(new BouncyCastleProvider());

    PdfReader reader = new PdfReader(pdfData);
    AcroFields af = reader.getAcroFields();
    ArrayList<String> names = af.getSignatureNames();

    if (names == null || names.size() == 0) {
      // TODO traduir
      throw new Exception("No hi ha informació de signatures en aquest document: "
          + pdf.getAbsolutePath());
    }
    if (names.size() != numFirma) {
      throw new Exception("S´esperaven " + numFirma
          + " firmes , però el document pujat des de l´applet en conté " + names.size());
    }

    // TODO fields.getTotalRevisions()
    if (isDebug) {
      long now = System.currentTimeMillis();
      log.debug("checkCertificatePADES - Final init: " + (now - start));
      start = now;
    }
    

    // === Comprovar que el fitxer no s'ha modificat ===
    if (fitxerOriginalID != null) {
      if (numFirma == 1) {
        // Comprovar fitxer original i pujat
        
        //FileSystemManager.getChecksum(fitxerOriginalID);
        File file = FileSystemManager.getFile(fitxerOriginalID);
        InputStream fitxerOriginalIS = new FileInputStream(file);
        boolean isOK;
        try { 
          isOK = checkDocumentWhenFirstSign(fitxerOriginalIS, pdfData);
        } finally {
          try { fitxerOriginalIS.close(); } catch(Exception e) {};
        }
        if (!isOK) {
          log.warn(" hashDocOriginalRepositori(LEN) == " + file.length());
        
          log.warn("DOC. REPOSITORY SIZE = " + file.length());
  
          // TODO traduir
          Exception e = new Exception(
              "El document original s'ha modificat durant el procés de firma.");
    
          log.error("Abans de realitzar la primera firma del document "
              + "que s'acaba de pujar des de l'applet, el document PDF original amb ID "
              + fitxerOriginalID + " s´ha modificat.", e);
    
          // Si estam en desenvolupament no llançar aquest error
          if (!Configuracio.isDesenvolupament()) {
            throw e;
          }
        }
        
        if (isDebug) {
          long now = System.currentTimeMillis();
          log.debug("checkCertificatePADES - Primera Firma: " + (now - start));
          start = now;
        }
  
      } else {
        // =========================================================
        // Comprovar checksum de totes les revisions (amb firma)
        // i comparar-les amb els fitxers guardats
        // =========================================================
  
        Long fitxerID = null;
        for (String nomSignatura : names) {
  
          // Obtenim el hash del fitxer del repositori amb numero de firma numRev
          int numRev = af.getRevision(nomSignatura);
          if (numRev == numFirma) {
            // ignoram la versió actual que s'ha pujat
            continue;
          }
          fitxerID = fitxersByNumFirma.get(numRev);
          String hashDocRepositori = FileSystemManager.getChecksum(fitxerID);
  
          // obtenim el hash del fitxer dins el doc
          InputStream is = af.extractRevision(nomSignatura);
          String hashDocFirmat = Utils.getChecksum(is);
  
          if (!hashDocRepositori.equals(hashDocFirmat)) {
            // TODO traduir
            Exception e = new Exception("El document s'ha modificat durant el procés de firma.");
  
            log.error("La signatura " + nomSignatura + " de la revisió " + numRev
                + " del document que s'acaba de pujar des de l'applet, no correspon  "
                + " amb el fitxer amb ID " + fitxerID + " que té signatura número " + numRev, e);
  
            // Si estam en desenvolupament no llançar aquest error
            if (!Configuracio.isDesenvolupament()) {
              throw e;
            }
          }
  
          if (isDebug) {
            long now = System.currentTimeMillis();
            log.debug("checkCertificatePADES - Firma N=" + (fitxersByNumFirma.size() + 1) + ": "
              + (now - start));
            start = now;
          }
  
        }
      

        // =========================================================
        // Revisar que no s'hagi creat una revisió (no de firma) entre firmes
        // Es a dir, no volem que mofifiquin el document ni amb revisions
        // =========================================================
  
        if (fitxerID != null) {
          /*
           * La diferencia del numero de revisions internes PDF entre la penultima
           * firma i aquesta darrera firma ha de ser de 1. Si aquest numero es
           * major significa que s'ha modificat ekl document amb revisions entre
           * les firmes.
           */
  
          int[] revPenultim = PdfUtils.splitPDFRevisions(PdfUtils
              .toByteArray(new FileInputStream(FileSystemManager.getFile(fitxerID))));
  
          int[] revDarrer = PdfUtils.splitPDFRevisions(pdfData);
  
          if ((revPenultim.length + 1) != revDarrer.length) {
            // Han afegit una nova revisió
  
            // TODO traduir
            String msg = "S´ha modificat el document abans de la darrera firma.";
            Exception e = new Exception(msg);
            log.error(msg + "\n - Penultim (Final Revisions): " + Arrays.toString(revPenultim)
                + "\n - Darrer (Final Revisions): " + Arrays.toString(revDarrer), e);
  
            if (!Configuracio.isDesenvolupament()) {
              // TODO traduir
              throw e;
            }
          }
  
          if (isDebug) {
            long now = System.currentTimeMillis();
            log.debug("checkCertificatePADES - Estructura PDF: " + (now - start));
            start = now;
          }
        }
  
      }
    }

    // ================ Validar el certificat de la darrera firma
    // ===============
    String name = names.get(numFirma - 1); // names.size() - 1

    PdfPKCS7 pk = af.verifySignature(name);
    // Calendar cal = pk.getSignDate();

    X509Certificate cert = pk.getSigningCertificate();

    ResultatValidacio validacio = validateCertificat(cert);

    if (isDebug) { 
      long now = System.currentTimeMillis();
      log.debug("checkCertificatePADES - Validar Certificat remotament: " + (now - start));
      start = now;
    }

    InformacioCertificat info = validacio.getInformacioCertificat();
    if (info == null) {
      info = new InformacioCertificat();
    }

    // Obtenir informació del certificat
    if (info.getNumeroSerie() == null) {
      info.setNumeroSerie(cert.getSerialNumber());
    }

    if (info.getEmissorOrganitzacio() == null) {
      info.setEmissorOrganitzacio(cert.getIssuerDN().getName());
    }

    if (info.getSubject() == null) {
      info.setSubject(cert.getSubjectDN().getName());
    }

    if (info.getNifResponsable() == null) {
      info.setNifResponsable(CertificateUtils.getDNI(cert));
    }

    if (log.isDebugEnabled()) {
      long now = System.currentTimeMillis();
      log.debug("checkCertificatePADES - Executed Info Certificat in " + (now - start) + " ms");
      log.debug("Numero Serie: " + info.getNumeroSerie());
      log.debug("Emissor: " + info.getEmissorOrganitzacio());
      log.debug("Subject: " + info.getSubject());
      log.debug("DNI: " + info.getNifResponsable());
    }

    return info;

  }

  public static ResultatValidacio validateCertificat(X509Certificate cert)
      throws I18NException, Exception {
    ICertificatePlugin certPlugin = PortaFIBPluginsManager.getCertificatePluginInstance();
    
    ResultatValidacio validacio = certPlugin.getInfoCertificate(cert);

    if (validacio.getResultatValidacioCodi() != ResultatValidacio.RESULTAT_VALIDACIO_OK) {
      if (!Configuracio.isDesenvolupament()) {
        throw new Exception("No s'ha validat el certificat: "
            + validacio.getResultatValidacioDescripcio());
      }
    }
    return validacio;
  }

  public static boolean checkDocumentWhenFirstSign(InputStream fitxerOriginalData, byte[] pdfData)
      throws Exception {
    log.debug(" Comprovar fitxer original i pujat quan és la primera firma");


    int revisionLimit = PdfUtils.indexOf(pdfData, PdfUtils.EOF_PDF);
    
    byte[] docOriginalData = Arrays.copyOf(pdfData, revisionLimit + PdfUtils.EOF_PDF.length + 1);

    String hashDocOriginalRebut = Utils.getChecksum(new ByteArrayInputStream(
        docOriginalData));

    String hashDocOriginalRepositori = Utils.getChecksum(fitxerOriginalData);
    
    

    if (!hashDocOriginalRebut.equals(hashDocOriginalRepositori)) {
      
      if (log.isDebugEnabled()) {
        log.debug(" INDEX OF FIST %%EOF IN APPLET FILE = " + revisionLimit);
        log.debug(" hashDocOriginalRebut == " + hashDocOriginalRebut );
        log.debug(" hashDocOriginalRebut(LEN) == " + docOriginalData.length );
        log.debug(" hashDocOriginalRepositori == " + hashDocOriginalRepositori);
      }

      return false;
    } else {
      return true;
    }
    

  }

  /**
   * 
   * @param srcPDF
   * @param dstPDF
   * @param numFirmes
   * @param posicioTaulaDeFirmes
   * @param signantLabel
   * @param resumLabel
   * @param descLabel
   * @param desc
   * @param titolLabel
   * @param titol
   * @param urlLogo
   * @param attachments
   * @param attachmentsNames
   * @throws Exception
   */
  public static void add_TableSign_Attachments_CustodyInfo(File srcPDF, File dstPDF,
      final List<AttachedFile> attachmentsOrig, Long maxSize,
      StampTaulaDeFirmes taulaDeFirmesInfo, StampCustodiaInfo custodiaInfo) throws Exception,
      I18NException {
    
    List<AttachedFile> attachments = new ArrayList<AttachedFile>();
    
    if (attachmentsOrig != null && attachmentsOrig.size() != 0) {
      for (AttachedFile attach : attachmentsOrig) { 
        if (attach != null) {
         attachments.add(attach);
        }
      }
    }

    // 0.- Check
    if (maxSize != null) {
      long sum = srcPDF.length();
      if (attachments != null) {
        for (AttachedFile attach : attachments) {
          sum = sum + attach.getContent().length();
        }
      }

      if (sum > maxSize) {
        throw new I18NException("tamanyfitxeradaptatsuperat", String.valueOf(sum),
            String.valueOf(maxSize));
      }

    }

    // 1. Modificar Contingut del PDF
    // Llegir PDF
    File fileTmp1 = null;
    FileOutputStream output1 = null;
    File fileTmp2 = null;
    FileOutputStream output2 = null;
    try {
    PdfReader reader = new PdfReader(new FileInputStream(srcPDF));
    //ByteArrayOutputStream output = new ByteArrayOutputStream();
    fileTmp1 = File.createTempFile("portafib_pdfutils_1_", ".pdf");
    fileTmp1.deleteOnExit();
        
    output1 = new FileOutputStream(fileTmp1);
    
    List<AttachedFile> attachmentsOriginalPDF = new ArrayList<AttachedFile>();
    {
      PdfStamper stamper = new PdfStamper(reader, output1);
      // 1.0.- Llegir documents Adjunts del PDF original
      // (Quan es converteix a PDF/A s'eliminen els adjunts)
      attachmentsOriginalPDF.addAll(extractAttachments(reader));
      for (AttachedFile fileAttached : attachmentsOriginalPDF) {
        fileAttached.getContent().deleteOnExit();
      }
      attachments.addAll(attachmentsOriginalPDF);

      // 1.1.- Afegir taula de firmes (si escau)
      int posicioTaulaFirmes = Constants.TAULADEFIRMES_SENSETAULA;
      if (taulaDeFirmesInfo != null) {
        addTaulaDeFirmes(reader, stamper, taulaDeFirmesInfo);
        posicioTaulaFirmes = taulaDeFirmesInfo.posicioTaulaDeFirmes;
      }

      // 1.1.- Afegir Informacio de Custodia
      if (custodiaInfo != null) {
        addCustodiaInfo(reader, stamper, custodiaInfo, posicioTaulaFirmes);
      }

      // 1.3.- Guardar PDF
      stamper.close();
    }

    // 
    output1.flush();
    output1.close();
    output1 = null;
    
    
    // 5.- Convertir PDF anterior a PDF/A
    PdfReader reader2 = new PdfReader(new FileInputStream(fileTmp1));
    //ByteArrayOutputStream output2 = new ByteArrayOutputStream();
    
    
    fileTmp2 = File.createTempFile("portafib_pdfutils_2_", ".pdf");
    fileTmp2.deleteOnExit();
        
    output2 = new FileOutputStream(fileTmp2);
    
    
    convertirPdfToPdfa(reader2, output2, attachments);
    // Borrar del directori temporal els fitxers adjunts originals
    for (AttachedFile fileAttached : attachmentsOriginalPDF) {
      fileAttached.getContent().delete();
    }

    output2.flush();
    output2.close();
    output2 = null;

    // 6.- Afegir propietats inicials
    //InputStream input3 = new ByteArrayInputStream(output2.toByteArray());
    
    InputStream input3 = new FileInputStream(fileTmp2);
    
    PdfReader reader3 = new PdfReader(input3);
    PdfStamper stamper3 = new PdfStamper(reader3, new FileOutputStream(dstPDF));

    Map<String, String> info = reader.getInfo();
    info.put("PortaFIB.versio", Versio.VERSIO);
    stamper3.setMoreInfo(info);
    stamper3.close();
    
  } finally {
    try { if (output1 != null) { output1.close(); } } catch (Exception e) { };
    try { if (fileTmp1 != null) { fileTmp1.delete(); } } catch (Exception e) { };
    
    try { if (output2 != null) { output2.close(); } } catch (Exception e) { };
    try { if (fileTmp2 != null) { fileTmp2.delete(); } } catch (Exception e) { };
    
  }

    if (maxSize != null) {
      if (dstPDF.length() > maxSize) {
        if (!dstPDF.delete()) {
          log.error("Error borrant fitxer adaptat " + dstPDF.getAbsolutePath());
          dstPDF.deleteOnExit();
        }
        throw new I18NException("tamanyfitxeradaptatsuperat", String.valueOf(dstPDF.length()),
            String.valueOf(maxSize));
      }

    }

  }

  public static void addTaulaDeFirmes(PdfReader reader, PdfStamper stamper,
      StampTaulaDeFirmes taulaDeFirmes) throws Exception {
    if (taulaDeFirmes.posicioTaulaDeFirmes != TAULADEFIRMES_SENSETAULA) {
      float heightSignBoxInch = SIGNBOX_HEIGHT / 72f; // 0.5f;
      float boxLogoSide = LOGO_SIDE / 72f; // 1.1f;
      float startSignTableInch = SIGNBOX_START / 72f; // 1.25f;

      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      {

        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, baos);
        document.open();

        XMLWorkerHelper worker = XMLWorkerHelper.getInstance();

        if (taulaDeFirmes.desc == null) {
          taulaDeFirmes.desc = "";
        } else {

          if (taulaDeFirmes.desc.length() > 143) {
            taulaDeFirmes.desc = taulaDeFirmes.desc.substring(0, 140) + "...";
          }
        }

        if (taulaDeFirmes.titol == null) {
          taulaDeFirmes.titol = "";
        } else {
          if (taulaDeFirmes.titol.length() > 43) {
            taulaDeFirmes.titol = taulaDeFirmes.titol.substring(0, 40) + "...";
          }
        }

        StringBuffer html = new StringBuffer("<html><head></head><body>"
            + "<div style='display:block;width:100%;" + "height:"
            + startSignTableInch
            + "in; overflow:visible'>"
            + "<table style='width:100%;' >" // border: 1px solid
            + "  <tr>" // style='height:" + heightHeaderInch + "in'
            + "    <td style='width:"
            + boxLogoSide
            + "in;margin-right:7in;'>" // border: 1px solid;
            + "       &nbsp;"
            // + "        <img src=\"" + urlLogo + "\" />"
            + "     </td>"
            + "    <td >"
            + "      <b><u>"
            + taulaDeFirmes.resumLabel
            + "</u></b><br/>"
            + "      <b>"
            + taulaDeFirmes.titolLabel
            + ": </b>"
            + taulaDeFirmes.titol
            + "<br/>"
            + "      <b>"
            + taulaDeFirmes.descLabel
            + ": </b>"
            + taulaDeFirmes.desc
            + "    </td>"
            + "  </tr>"
            + "</table>"
            + "</div>"
            + "<table style='width:100%'>");

        for (int r = 0; r < taulaDeFirmes.numFirmes; r++) {
          html.append("  <tr style='height:" + heightSignBoxInch + "in;' >"
              + "<td style='border-style: solid;border-width: 1px;'>" + "&nbsp;"
              + taulaDeFirmes.signantLabel + " " + (r + 1) + "</td>" + "</tr>");
        }
        html.append("</table>");
        html.append("</body></html>");

        worker.parseXHtml(writer, document, new StringReader(html.toString()));

        // Afegir Logo
        Image img = Image.getInstance(taulaDeFirmes.logoFile.getAbsolutePath());
        float x = 0.53f * 72;
        float y = A4_ALT - 1.64f * 72;
        img.setAbsolutePosition(x, y);

        // img.scaleAbsolute(100, 100); // Code 2
        final float rectAlt = LOGO_SIDE - 10; // 1.25f * 72;
        final float rectAmple = LOGO_SIDE - 10; // 1.25f *
                                                       // 72;
        Rectangle rectangle = new Rectangle(x, y, x + rectAmple, y + rectAlt);

        img.scaleToFit(rectangle);

        document.add(img);

        /*
         * PdfContentByte cb = writer.getDirectContent(); cb.setLineWidth(2.0f);
         * // Make a bit thicker than 1.0 default cb.setGrayStroke(0.95f); // 1
         * = black, 0 = white cb.moveTo(x, y); cb.lineTo(x + rectAmple, y +
         * rectAlt); cb.stroke();
         */

        document.close();
        writer.close();
      }

      int page;
      switch (taulaDeFirmes.posicioTaulaDeFirmes) {
      case (int) TAULADEFIRMES_PRIMERAPAGINA:
        page = 1;
        break;

      case (int) TAULADEFIRMES_DARRERAPAGINA:
        page = reader.getNumberOfPages() + 1;
        break;
      default:
        // TODO
        throw new Exception("Posicio de pàgina desconeguda !!!!!");
      }

      stamper.insertPage(page, new RectangleReadOnly(A4_AMPLE, A4_ALT));

      PdfContentByte content = stamper.getOverContent(page);

      PdfImportedPage ip = stamper.getImportedPage(new PdfReader(baos.toByteArray()), 1);

      content.addTemplate(ip, 0, 0);
    }
  }
  
  


  /**
   * Search the data byte array for the first occurrence of the byte array
   * pattern.
   */
  public static int indexOf(byte[] data, byte[] pattern) {
    int[] failure = computeFailure(pattern);

    int j = 0;

    for (int i = 0; i < data.length; i++) {
      while (j > 0 && pattern[j] != data[i]) {
        j = failure[j - 1];
      }
      if (pattern[j] == data[i]) {
        j++;
      }
      if (j == pattern.length) {
        return i - pattern.length + 1;
      }
    }
    return -1;
  }

  /**
   * Computes the failure function using a boot-strapping process, where the
   * pattern is matched against itself.
   */
  private static int[] computeFailure(byte[] pattern) {
    int[] failure = new int[pattern.length];

    int j = 0;
    for (int i = 1; i < pattern.length; i++) {
      while (j > 0 && pattern[j] != pattern[i]) {
        j = failure[j - 1];
      }
      if (pattern[j] == pattern[i]) {
        j++;
      }
      failure[i] = j;
    }

    return failure;
  }

  public static int indexOf(byte[] data, byte[] pattern, int startOffset) {
    int[] failure = computeFailure(pattern);

    int j = 0;

    for (int i = startOffset; i < data.length; i++) {
      while (j > 0 && pattern[j] != data[i]) {
        j = failure[j - 1];
      }
      if (pattern[j] == data[i]) {
        j++;
      }
      if (j == pattern.length) {
        return i - pattern.length + 1;
      }
    }
    return -1;
  }

  public static byte[] toByteArray(InputStream input) throws IOException {

    ByteArrayOutputStream output = new ByteArrayOutputStream();

    FileSystemManager.copy(input, output);

    return output.toByteArray();

  }

  public static int[] splitPDFRevisions(byte[] data) throws Exception {
    int offSet = 0;
    List<Integer> list = new ArrayList<Integer>();

    int value;

    while ((value = PdfUtils.indexOf(data, EOF_PDF, offSet)) != -1) {
      list.add(value);
      offSet = value + EOF_PDF.length;
    }

    return toIntArray(list);

  }

  public static int[] toIntArray(List<Integer> list) {
    int[] ret = new int[list.size()];
    int i = 0;
    for (Integer e : list)
      ret[i++] = e.intValue();
    return ret;
  }

  public static final PdfAConformanceLevel PDFA_CONFORMANCE_LEVEL = PdfAConformanceLevel.PDF_A_3B;

  public static void convertirPdfToPdfa(PdfReader reader, OutputStream destiPDFA,
      List<AttachedFile> attachments) throws Exception {

    
    {
      Document document = new Document(); //PageSize.A4);
     
      //OutputStream  destitmpPDFA = new FileOutputStream(tmpPDFA);
  
      PdfAWriter writer = PdfAWriter.getInstance(document, destiPDFA, PDFA_CONFORMANCE_LEVEL);
      
      
      //PdfAWriter writer = PdfAWriter.getInstance(document, destiPDFA, PdfAConformanceLevel.PDF_A_1B);
      
     // writer.setPDFXConformance(PdfAWriter.PDFX1A2001);
      // PDF_A_3B
      // PdfAConformanceLevel.PDF_A_1B);
  
      writer.createXmpMetadata();
  
      int numberPages = reader.getNumberOfPages();
  
      document.setMargins(0, 0, 0, 0);
      
  
      PdfDictionary outi = new PdfDictionary(PdfName.OUTPUTINTENT);
      outi.put(PdfName.OUTPUTCONDITIONIDENTIFIER, new PdfString("sRGB IEC61966-2.1"));
      outi.put(PdfName.INFO, new PdfString("sRGB IEC61966-2.1"));
      outi.put(PdfName.S, PdfName.GTS_PDFA1);
      writer.getExtraCatalog().put(PdfName.OUTPUTINTENTS, new PdfArray(outi));
  
      
      
      PdfImportedPage p = null;
      Image image;
      
      for (int i = 0; i < numberPages; i++) {
        int pageNumber = i + 1;
        p = writer.getImportedPage(reader, pageNumber);                
        image = Image.getInstance(p);

        Rectangle rect = reader.getPageSize(pageNumber);

        //show(reader.getPageSize(pageNumber));
        //show(reader.getPageSizeWithRotation(pageNumber));

        if(rect.getWidth() > rect.getHeight()) {
          //System.out.println("Horitzontal: " + rect.getHeight() + " | " + rect.getWidth() );
          Rectangle newrect = new Rectangle(0.0f, 0.0f,rect.getWidth(),  rect.getHeight());
          document.setPageSize(newrect);
        } else {
          //System.out.println("Vertical: ");
          document.setPageSize(rect);
        }

        if (i == 0) {
          document.open();
        }
        document.newPage();
        document.add(image);
        
        
      }
  
      // 3.- Attach Files
      if (attachments != null && attachments.size() != 0) {
  
        // PdfWriter writer = stamper.getWriter();
        for (AttachedFile fa : attachments) {
          File src = fa.getContent();
          if (src != null && src.exists()) {
            String name = fa.getName();
            PdfFileSpecification fs = PdfFileSpecification.fileEmbedded(writer,
                src.getAbsolutePath(), name, null);
            writer.addFileAttachment(name.substring(0, name.indexOf('.')), fs);
          }
        }
      }
  
      document.close();      
  
      writer.flush();
      writer.close();

    }

  }
  
  
  public static void show(Rectangle rect) {
    System.out.print("llx: ");
    System.out.print(rect.getLeft());
    System.out.print(", lly: ");
    System.out.print(rect.getBottom());
    System.out.print(", urx: ");
    System.out.print(rect.getRight());
    System.out.print(", lly: ");
    System.out.print(rect.getTop());
    System.out.print(", rotation: ");
   System.out.println(rect.getRotation());
}
  
  
  /**
   * Extracts attachments from an existing PDF.
   * @param src   the path to the existing PDF
   * @param dest  where to put the extracted attachments
   * @throws IOException
   */
  /*
  public static List<FileAttached>  extractAttachments(PdfReader reader) throws IOException {
      PdfArray array;
      PdfDictionary annot;
      PdfDictionary fs;
      PdfDictionary refs;
      String filename;
      List<FileAttached> files = new ArrayList<FileAttached>();
      for (int i = 1; i <= reader.getNumberOfPages(); i++) {
          array = reader.getPageN(i).getAsArray(PdfName.ANNOTS);
          if (array == null) continue;
          for (int j = 0; j < array.size(); j++) {
              annot = array.getAsDict(j);
              if (PdfName.FILEATTACHMENT.equals(annot.getAsName(PdfName.SUBTYPE))) {
                  fs = annot.getAsDict(PdfName.FS);
                  refs = fs.getAsDict(PdfName.EF);
                  for (PdfName name : refs.getKeys()) {
                      File output = File.createTempFile("portafib_pdf_attached_", ".file");
                      FileOutputStream fos = new FileOutputStream(output);
                      fos.write(PdfReader.getStreamBytes((PRStream)refs.getAsStream(name)));
                      fos.flush();
                      fos.close();
                      filename = fs.getAsString(name).toString();
                      System.out.println(" FILENAME = " + filename);
                      files.add(new FileAttached(filename, output));
                  }
              }
          }
      }
      
      
      return files;
      
      
  }
  
  */
  
  
  
  public static List<AttachedFile> extractAttachments(PdfReader reader) throws IOException {
   
    PdfDictionary root = reader.getCatalog();
    PdfDictionary names = root.getAsDict(PdfName.NAMES);
    List<AttachedFile> files = new ArrayList<AttachedFile>();
    if (names != null) {
      PdfDictionary embedded = names.getAsDict(PdfName.EMBEDDEDFILES);
      if (embedded != null) {
        PdfArray filespecs = embedded.getAsArray(PdfName.NAMES);
        
        if (filespecs != null) {
          for (int i = 0; i < filespecs.size(); ) {
            try {
              files.addAll(extractAttachment(reader, filespecs.getAsString(i++),
              filespecs.getAsDict(i++)));
            } catch(Exception e) {
              log.error("Error desconegut extraient attachments del PDF: "
                 + e.getMessage(), e);
            }
          }
        }
      }
    }
    
    return files;
  }

  
  
  private static List<AttachedFile> extractAttachment(PdfReader reader,
      PdfString name, PdfDictionary filespec) throws IOException {
    PRStream stream;
    FileOutputStream fos;
    String filename;
    PdfDictionary refs = filespec.getAsDict(PdfName.EF);
    
    List<AttachedFile> files = new ArrayList<AttachedFile>();
    
    for (PdfName key : refs.getKeys()) {
      stream = (PRStream)PdfReader.getPdfObject(refs.getAsIndirectObject(key));
      filename = filespec.getAsString(key).toString();
      File output = File.createTempFile("portafib_pdf_attached_", ".file");
      fos = new FileOutputStream(output);
      fos.write(PdfReader.getStreamBytes(stream));
      fos.flush();
      fos.close();
      files.add(new AttachedFile(filename, output));
      break; // Només en volem un (ja que la resta són el mateix)
    }
    
    return files;
  }

  
  

  public static boolean isSignedPDF(File pdf) throws Exception {
    PdfReader reader = new PdfReader(new FileInputStream(pdf));
    AcroFields fields = reader.getAcroFields();
    return fields.getSignatureNames().size() != 0;
  }

  public static Long selectMin(Long long1, Long long2) {
    if (long1 == null) {
      if (long2 == null) {
        return null;
      } else {
        return long2;
      }
    } else {
      if (long2 == null) {
        return long1;
      } else {
        return Math.min(long1, long2);
      }
    }
  }

  private static final int SIGNATURE_HORIZONTAL_SPLIT_LEN = 65;
  private static final int SIGNATURE_VERTICAL_SPLIT_LEN = 100;
  private static final int SIGNATURE_SPLIT_RANGE = 10;
  private static final int SIGNATURE_SPLIT_MARGIN = 5;

  /**
   * 
   * @param reader
   * @param stamp
   * @param custodiaInfo
   * @throws Exception
   */
  public static void addCustodiaInfo(PdfReader reader, PdfStamper stamp,
      StampCustodiaInfo custodiaInfo, int posicioTaulaDeFirmes) throws Exception, I18NException {



    int positioCustodiaInfoGeneral = custodiaInfo.posicioCustodiaInfo;

    if (positioCustodiaInfoGeneral == POSICIO_PAGINA_CAP) {
      return;
    }
    
    int numberOfPages = reader.getNumberOfPages();

    BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, BaseFont.EMBEDDED);

    /*
     * Indica en quines pàgines s'ha de mostrar el missatge i el codi de barres.
     * NULL = no mostrar '*'= totes les pàgines '0'=primera pàgina (taula de
     * firmes) '-1' = darrera pàgina (taula de firmes) '1,3, 4-5' =format
     * Imprimir
     */
    Set<Integer> pagines = processarPagines(custodiaInfo.getPagines(), posicioTaulaDeFirmes,
        numberOfPages);

    if (pagines.isEmpty()) {
      return;
    }
    



    float barcode_X = 20;
    float barcode_Y = 20;
    int textAlign = PdfContentByte.ALIGN_LEFT;
    float text_X = 30;
    float text_Y = 150;
    float rotation = 90;

    Image image = (Image)custodiaInfo.getBarcodePlugin().generateTextBarCodeIText(
        custodiaInfo.barcodeText);
    
    //  PAGINES
    for (Integer pagina : pagines) {
      PdfContentByte over = stamp.getOverContent(pagina);

      Rectangle pageSize = reader.getPageSize(pagina);
      float width = pageSize.getWidth();
      float height = pageSize.getHeight();

      int SIGNATURE_SPLIT_LEN = 0;
      if (height > width) {
        SIGNATURE_SPLIT_LEN = (positioCustodiaInfoGeneral == POSICIO_PAGINA_ESQUERRA || positioCustodiaInfoGeneral == POSICIO_PAGINA_DRETA) ? SIGNATURE_VERTICAL_SPLIT_LEN
            : SIGNATURE_HORIZONTAL_SPLIT_LEN;
      } else {
        SIGNATURE_SPLIT_LEN = (positioCustodiaInfoGeneral == POSICIO_PAGINA_ESQUERRA || positioCustodiaInfoGeneral == POSICIO_PAGINA_DRETA) ? SIGNATURE_HORIZONTAL_SPLIT_LEN
            : SIGNATURE_VERTICAL_SPLIT_LEN;
      }

      Vector<String> lines = new Vector<String>();
      int counter = 0;
      String missatge = custodiaInfo.missatgeCustodia;
      while (counter != -1) {
        if (counter + SIGNATURE_SPLIT_LEN < missatge.length()) {
          int prev = missatge.substring(counter, counter + SIGNATURE_SPLIT_LEN).lastIndexOf(
              " "); //$NON-NLS-1$
          String next_string = missatge.substring(counter + SIGNATURE_SPLIT_LEN,
              missatge.length());
          int next = next_string.indexOf(" "); //$NON-NLS-1$
          // prev [-1,SPLIT_LEN]
          // next[-1,inf]

          int splitPos = prev + 1;
          // splitpos [-1,SPLIT_LEN+1]

          if (next < SIGNATURE_SPLIT_RANGE && next != -1) {
            splitPos = SIGNATURE_SPLIT_LEN + next;
            // next[0,inf]
            // splitpos [0,SPLIT_LEN+SPLIT_RANGE]

          } else if (prev == -1 && next > SIGNATURE_SPLIT_RANGE) {
            splitPos = SIGNATURE_SPLIT_LEN;
            // prev [0,SPLIT_LEN]
            // splitpos [0,SPLIT_LEN+SPLIT_RANGE]
          } else if (prev == -1 && next == -1) {
            splitPos = SIGNATURE_SPLIT_LEN;
          }

          lines.add(missatge.substring(counter, counter + splitPos));
          counter += splitPos;

        } else {
          lines.add(missatge.substring(counter, missatge.length()));
          counter = -1;
        }
      }

      final float SEP_LINE = 5.0f + SIGNATURE_SPLIT_MARGIN;
      float lineX, lineY;

      
      switch (positioCustodiaInfoGeneral) {
      default:
      case (POSICIO_PAGINA_ESQUERRA):
        lineX = 24;
        lineY = 20;
        barcode_X = lineX - image.getPlainHeight()/2.0f; 
        barcode_Y = lineY;
        textAlign = PdfContentByte.ALIGN_LEFT;
        text_X = lineX - ((lines.size() - 1) * SEP_LINE / 2.0f) + (SEP_LINE / 2.0f);
        text_Y = lineY + image.getPlainWidth() + 15;
        rotation = 90;
        break;
      case (POSICIO_PAGINA_DRETA):
        lineX = width - 29;
        lineY = 20;
        barcode_X = lineX - image.getPlainHeight()/2.0f; 
        barcode_Y = lineY;
        textAlign = PdfContentByte.ALIGN_LEFT;
        text_X = lineX - ((lines.size() - 1) * SEP_LINE / 2.0f) + (SEP_LINE / 2.0f);
        text_Y = lineY + image.getPlainWidth() + 15;
        rotation = 90;
        break;
      case (POSICIO_PAGINA_ADALT):
        lineX = 20;
        lineY = height - 22;
        barcode_X = 20;
        barcode_Y = lineY - image.getPlainHeight()/2.0f;
        textAlign = PdfContentByte.ALIGN_LEFT;
        text_X = lineX + image.getPlainWidth() + 10;
        text_Y = lineY + ((lines.size() - 1) * SEP_LINE / 2.0f) - (SEP_LINE / 2.0f);
        rotation = 0;
        break;
      case (POSICIO_PAGINA_ABAIX):
        lineX = 20;
        lineY = 27;
        barcode_X = lineX;
        barcode_Y = lineY - image.getPlainHeight()/2.0f;
        textAlign = PdfContentByte.ALIGN_LEFT;
        text_X = lineX + image.getPlainWidth() + 10;
        text_Y = lineY + ((lines.size() - 1) * SEP_LINE / 2.0f) - (SEP_LINE / 2.0f);
        rotation = 0;
        break;
      }
      
      
      
      // TODO
      /*
      over.setLineWidth(1.0f);   // Make a bit thicker than 1.0 default 
      over.setGrayStroke(0.85f); // 1 = black, 0 = white 
 
      over.moveTo(lineX,         0); 
      over.lineTo(lineX, 800); 
      
      over.moveTo(0,lineY);
      over.lineTo(600, lineY);
      over.stroke(); 
      */
      

      image.setAbsolutePosition(barcode_X, barcode_Y);
      if (custodiaInfo.getBarcodePlugin().requireRotation()) {
        image.setRotationDegrees(rotation);
      }
      over.addImage(image);

      counter = 0;
      
      // Descentramos en función del número de líneas de la firma
      /*
      text_X = ((positioCustodiaInfo == POSICIO_PAGINA_ESQUERRA || positioCustodiaInfo == POSICIO_PAGINA_DRETA) ? text_X
          - (lines.size() / 2.0f) * SEP_LINE
          : text_X);
      text_Y = (positioCustodiaInfo == POSICIO_PAGINA_ADALT || positioCustodiaInfo == POSICIO_PAGINA_ABAIX) ? text_Y
          + (lines.size() / 2.0f) * SEP_LINE
          : text_Y;
          */

      // Imprimimos las líneas de la firma
      for (counter = 0; counter < lines.size(); counter++) {

        over.beginText();
        over.setFontAndSize(bf, 10);
        float x = ((positioCustodiaInfoGeneral == POSICIO_PAGINA_ESQUERRA || positioCustodiaInfoGeneral == POSICIO_PAGINA_DRETA) ? text_X
            + counter * SEP_LINE
            : text_X); 
        float y = (positioCustodiaInfoGeneral == POSICIO_PAGINA_ADALT || positioCustodiaInfoGeneral == POSICIO_PAGINA_ABAIX) ? text_Y
            - counter * SEP_LINE
            : text_Y;
        over.showTextAligned(textAlign, lines.get(counter), x, y, rotation);
        over.endText();

      }
    }
  }
  
  
  

  /*
   * Indica en quines pàgines s'ha de mostrar el missatge i el codi de barres.
   * NULL o buit = no estampar a cap pagina '*'= totes les pàgines '0'=primera
   * pàgina (taula de firmes) '-1' = darrera pàgina (taula de firmes) '-1, 0, 1,
   * 3, 4-5, 8-' =format Imprimir
   */
  public static Set<Integer> processarPagines(String pagines, int posicioTaulaDeFirmes,
      int lastPage) throws I18NException {

    Set<Integer> pages = new TreeSet<Integer>();
    if (pagines == null || pagines.trim().length() == 0) {
      return pages;
    }

    // Check Chars allowed
    final String permesos = " *,-0123456789";
    for (int i = 0; i < pagines.length(); i++) {
      if (permesos.indexOf(pagines.charAt(i)) == -1) {
        throw new I18NException("custodiainfo.pagines.errorformat", String.valueOf(pagines
            .charAt(i)));
      }
    }

    int shift;
    if (posicioTaulaDeFirmes == TAULADEFIRMES_PRIMERAPAGINA) {
      shift = 1;
    } else {
      shift = 0;
    }

    if (pagines.contains("*")) {
      for (int i = 1; i <= lastPage; i++) {
        pages.add(i);
      }
      return pages;
    }

    pagines = pagines.trim();
    String[] groups = pagines.split(",");
    
    int page;
    for (int i = 0; i < groups.length; i++) {
      String group = groups[i].trim();
      group = group.trim();
      if (group.trim().length() == 0) {
        throw new I18NException("custodiainfo.pagines.errorformat", pagines);
      }
      try {
        page = Integer.parseInt(group);
        
        if (page == 0) {
          pages.add(1);
          continue;
        }
        if (page < 0) {
          pages.add(lastPage);
          continue;
        }

        pages.add(Math.min(lastPage, page + shift));

      } catch (NumberFormatException nfe) {
        // És un grup: 3-4 o 3-
        int sep = group.lastIndexOf('-');

        if (sep == -1) {
          throw new I18NException("custodiainfo.pagines.errorformat", group);
        }

        String subgroup1 = group.substring(0, sep);

        String subgroup2 = group.substring(sep + 1, group.length());

        int from;
        try {
          from = Integer.parseInt(subgroup1);
        } catch (NumberFormatException nfe1) {
          throw new I18NException("custodiainfo.pagines.errorformat", subgroup1);
        }

        int to;
        if (subgroup2.isEmpty()) {
          to = lastPage;
        } else {
          try {
            to = Math.min(Integer.parseInt(subgroup2), lastPage);
          } catch (NumberFormatException nfe2) {
            throw new I18NException("custodiainfo.pagines.errorformat", subgroup2);
          }
        }

        if (from < 0 || from > to) {
          throw new I18NException("custodiainfo.pagines.errorformat", group);
        }
        if (from > lastPage) {
          continue;
        }
        
        for (int j = from; j <= to; j++) {
          int p = j + shift;
          if (p > lastPage) {
            break;
          }
          pages.add(p);
        }
      }
    }
    
    if (pagines.trim().length() != 0 && pages.isEmpty()) {
      throw new I18NException("custodiainfo.pagines.errorformat", pagines);
    }
    
    return pages;
  }
}
