package es.caib.portafib.logic.utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.RectangleReadOnly;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PRStream;
import com.itextpdf.text.pdf.PdfArray;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfFileSpecification;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.security.PdfPKCS7;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import es.caib.portafib.commons.utils.StaticVersion;
import es.caib.portafib.model.bean.FitxerBean;
import es.caib.portafib.model.entity.Fitxer;
import es.caib.portafib.utils.ConstantsV2;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.i18n.I18NException;
//import org.fundaciobit.pluginsib.validatecertificate.ICertificatePlugin;
//import org.fundaciobit.pluginsib.validatecertificate.ResultatValidacio;
import org.fundaciobit.pluginsib.documentconverter.IDocumentConverterPlugin;
import org.fundaciobit.pluginsib.documentconverter.InputDocumentNotSupportedException;
import org.fundaciobit.pluginsib.documentconverter.OutputDocumentNotSupportedException;

import javax.activation.DataHandler;
import javax.mail.util.ByteArrayDataSource;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;

/**
 * 
 * @author anadal(u80067)
 *
 */
public class PdfUtils implements ConstantsV2 {

    protected static Logger log = Logger.getLogger(PdfUtils.class);

    public static Fitxer convertToPDF(File fileToConvert, Fitxer fileToConvertInfo) throws I18NException {

        String mime = fileToConvertInfo.getMime();
        boolean isDebug = log.isDebugEnabled();
        if (isDebug) {
            log.debug("convertToPDF(): Name = " + fileToConvertInfo.getNom());
            log.debug("convertToPDF(): File = " + fileToConvert.getAbsolutePath());
            log.debug("convertToPDF(): File Exists?= " + fileToConvert.exists());
            log.debug("convertToPDF(): MIME = " + mime);
        }

        if (MIME_TYPE_PDF.equals(mime)) {
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
                        docPlugin.convertDocumentByMime(fis, mime, baos, MIME_TYPE_PDF);
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
                        throw new I18NException("formatfitxer.conversio.errorinput", fileToConvertInfo.getNom(), mime);
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

                fileConvertedInfo.setMime(MIME_TYPE_PDF);
                fileConvertedInfo.setNom(newFileName);
                fileConvertedInfo.setTamany(baos.size());
                fileConvertedInfo.setData(new DataHandler(new ByteArrayDataSource(baos.toByteArray(), newFileName)));

                return fileConvertedInfo;
            } catch (InputDocumentNotSupportedException idnse) {
                throw new I18NException("formatfitxer.conversio.errorinput", fileToConvertInfo.getNom(), mime);
            } catch (OutputDocumentNotSupportedException odnse) {
                throw new I18NException("formatfitxer.conversio.erroroutput",
                        fileToConvertInfo.getNom() + "." + PDF_FILE_EXTENSION, MIME_TYPE_PDF);
            } catch (Exception e) {
                log.error("Error converting document a PDF:" + e.getMessage(), e);
                throw new I18NException("error.unknown", e.getMessage());
            }

        }

    }

    /*
    public static ResultatValidacio validateCertificat(X509Certificate cert)
            throws I18NException, Exception {
        ICertificatePlugin certPlugin = PortaFIBPluginsManager.getCertificatePluginInstance();
    
        ResultatValidacio validacio = certPlugin.getInfoCertificate(cert);
    
        if (validacio.getResultatValidacioCodi() != ResultatValidacio.RESULTAT_VALIDACIO_OK) {
            // TODO XYZ ZZZ Traduir
            throw new Exception(
                    "No s'ha validat el certificat: " + validacio.getResultatValidacioDescripcio());
        }
        return validacio;
    }
    */

    public static int add_TableSign_Attachments_CustodyInfo_PDF(File srcPDF, File dstPDF,
            final List<AttachedFile> attachmentsOrig, Long maxSize, StampTaulaDeFirmes taulaDeFirmesInfo,
            StampCustodiaInfo custodiaInfo, boolean acceptTransformPDFA) throws Exception, I18NException {

        InputStream isPDF = null;
        final int originalNumberOfSigns;
        try {
            isPDF = new FileInputStream(srcPDF);
            originalNumberOfSigns = getNumberOfSignaturesInPDF(isPDF);

        } finally {
            if (isPDF != null) {
                isPDF.close();
            }
        }

        log.debug("originalNumberOfSigns = " + originalNumberOfSigns);

        // EL FITXER ORIGINAL JA ESTA FIRMAT
        if (originalNumberOfSigns != 0) {

            // 12/06/2017 Permetem Refirmar Fitxers amb Firmes amb les condicions següents:
            // a) No permetem taula de firmes
            // b) No permetem adjunt incrustats en el PDF
            // c) No permetem custòdia si duu estampació

            // a.- Check No permetem taula de firmes
            if (taulaDeFirmesInfo != null && taulaDeFirmesInfo.getPosicioTaulaDeFirmes() != TAULADEFIRMES_SENSETAULA) {
                // El fitxer original conté firmes però s'ha demanat Taula de Firmes:
                // aquests dos requeriments són incompatibles.
                throw new I18NException("error.checkpdf.firmattaulafirmes");
            }

            // b.- No permetem adjunts incrustats en el PDF
            if (attachmentsOrig != null && attachmentsOrig.size() != 0) {
                // El fitxer original conté firmes però s'ha demanat adjuntar i firmar annexes:
                // aquests dos requeriments són incompatibles."
                throw new I18NException("error.checkpdf.firmatafegirannexes");
            }

            // c.- Nom permetem custòdia si duu estampació
            if (custodiaInfo != null && custodiaInfo.getPosicioCustodiaInfo() != POSICIO_PAGINA_CAP) {
                // El fitxer original conté firmes però s'ha demanat estampar
                // el PDF amb informació de custòdia: aquests dos requeriments són
                // incompatibles."
                throw new I18NException("error.checkpdf.firmatestampar");
            }

            // TODO XYZ ZZZ Fa falta validar Firmes !!!!!!!!!!!!!

            // Copiar dins dstFile el fitxer Original
            FileUtils.copyFile(srcPDF, dstPDF);

            return originalNumberOfSigns;
        }

        final boolean requereixTaula_o_Custodia_o_Annexes = requereixTaula_o_Custodia_o_Annexes(attachmentsOrig,
                taulaDeFirmesInfo, custodiaInfo);

        if (!requereixTaula_o_Custodia_o_Annexes) {

            log.debug("PdfUtils.add_TableSign_Attachments_CustodyInfo_PDF:: NO TENIM TAULA NI CUSTODIA NI ANNEXES");
            log.debug("PdfUtils.add_TableSign_Attachments_CustodyInfo_PDF:: NO ADAPTACIO. RETORNA ORIGINAL");
            // Copiar dins dstFile el fitxer Original
            FileUtils.copyFile(srcPDF, dstPDF);

            return originalNumberOfSigns;
        } // Final IF requereixTaula_o_Custodia_o_Annexes

        // Es requereix Taula de Firmes o estampacio o anexes

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
                throw new I18NException("tamanyfitxeradaptatsuperat", String.valueOf(sum), String.valueOf(maxSize));
            }

        }

        // 1. Modificar Contingut del PDF
        // Llegir PDF
        File fileTmp1 = null;
        FileOutputStream output1 = null;

        // File input3 = null;
        FileInputStream readerInputStream = null;
        try {
            readerInputStream = new FileInputStream(srcPDF);
            PdfReader reader = new PdfReader(readerInputStream);

            // Check PDF-A1 o A2 o A3
            log.info("XYZ ZZZ Comprovant si es PDF/Ax ...");
            final byte[] metadata = reader.getMetadata();
            if (isPdfA1(metadata) || isPdfAx(metadata)) {
                if (!acceptTransformPDFA) {

                    // acceptTransformPDFA => Nou a la versió 2.0.1 Opcional. Per defecte false.
                    // En firmes PAdES,
                    // si el tipus de PDF és PDF/A1 o PDF/A2 o PDF/A3 i si a més es requereix
                    // Estampar o Afegir Taula de Firmes o Annexar Documents, llavors això
                    // implica una transformació del PDF que a la vegada implica una pèrdua
                    // de la condició de PDF/A. Si val true s'accepta transformar el PDF/A
                    // i perdre a la condició de PDF/A. Si val false es llança una
                    // excepció indicant que no es permeten Estampacions o Taules de Firmes
                    // o Annexes en PDF/A.

                    // El PDF és de tipus PDF/A, el que significa que no se li pot afegir
                    // taula de firmes, estampar custòdia o afegir annexes a no ser que es
                    // transformi el PDF, però la transformació està desactivada.
                    // Consulti amb l´administrador (Veure propietat es.caib.portafib.transformpdfa)
                    throw new I18NException("error.checkpdf.pdfa_no_modificar", "PDF/A");
                }
            }

            fileTmp1 = File.createTempFile("portafib_pdfutils_1_", ".pdf");
            fileTmp1.deleteOnExit();

            output1 = new FileOutputStream(fileTmp1);

            List<AttachedFile> attachmentsOriginalPDF = new ArrayList<AttachedFile>();
            {
                PdfStamper stamper = new PdfStamper(reader, output1);

                // PdfAWriter writer = PdfAWriter.getInstance(document, destiPDFA,
                // PDFA_CONFORMANCE_LEVEL);

                // 1.0.- Llegir documents Adjunts del PDF original
                // (Quan es converteix a PDF/A s'eliminen els adjunts)
                attachmentsOriginalPDF.addAll(extractAttachments(reader));
                for (AttachedFile fileAttached : attachmentsOriginalPDF) {
                    fileAttached.getContent().deleteOnExit();
                }
                // attachments.addAll(attachmentsOriginalPDF);

                // 1.1.- Afegir taula de firmes (si escau)
                int posicioTaulaFirmes = ConstantsV2.TAULADEFIRMES_SENSETAULA;
                if (taulaDeFirmesInfo != null) {
                    addTaulaDeFirmes(reader, stamper, taulaDeFirmesInfo);
                    posicioTaulaFirmes = taulaDeFirmesInfo.getPosicioTaulaDeFirmes();
                }

                // 1.2.- Afegir Informacio de Custodia
                if (custodiaInfo != null) {
                    addCustodiaInfo(reader, stamper, custodiaInfo, posicioTaulaFirmes);
                }

                // 1.3.- Attach Files
                if (attachments != null && attachments.size() != 0) {

                    // PdfWriter writer = stamper.getWriter();
                    for (AttachedFile fa : attachments) {
                        File src = fa.getContent();
                        if (src != null && src.exists()) {
                            String name = fa.getName();
                            PdfFileSpecification fs = PdfFileSpecification.fileEmbedded(stamper.getWriter(),
                                    src.getAbsolutePath(), name, null);
                            stamper.getWriter().addFileAttachment(fa.getDescription(), fs);
                        }
                    }
                }

                // 1.3.- Guardar PDF
                stamper.close();
            }

            output1.flush();
            output1.close();
            output1 = null;

            addPortaFIBVersionToPdf(dstPDF, fileTmp1, reader);

        } finally {
            try {
                if (output1 != null) {
                    output1.close();
                }
            } catch (Exception e) {
            }
            try {
                if (fileTmp1 != null) {
                    fileTmp1.delete();
                }
            } catch (Exception e) {
            }
            try {
                if (readerInputStream != null) {
                    try {
                        readerInputStream.close();
                    } catch (Exception e) {
                    }

                }
            } catch (Exception e) {
            }
        }

        if (maxSize != null) {
            if (dstPDF.length() > maxSize) {
                if (!dstPDF.delete()) {
                    log.error("Error esborrant fitxer adaptat " + dstPDF.getAbsolutePath());
                    dstPDF.deleteOnExit();
                }
                throw new I18NException("tamanyfitxeradaptatsuperat", String.valueOf(dstPDF.length()),
                        String.valueOf(maxSize));
            }

        }

        return originalNumberOfSigns;

    }

    protected static boolean requereixTaula_o_Custodia_o_Annexes(final List<AttachedFile> attachmentsOrig,
            StampTaulaDeFirmes taulaDeFirmesInfo, StampCustodiaInfo custodiaInfo) {
        // a.- Requereix taula de firmes
        if ((taulaDeFirmesInfo != null) && (taulaDeFirmesInfo.getPosicioTaulaDeFirmes() != TAULADEFIRMES_SENSETAULA)) {
            return true;
        }
        ;

        // b.- Ha d'incloure adjunts incrustats en el PDF
        if ((attachmentsOrig != null) && (attachmentsOrig.size() != 0)) {
            return true;
        }

        // c.- necessita estampació de custòdia
        if ((custodiaInfo != null) && (custodiaInfo.getPosicioCustodiaInfo() != POSICIO_PAGINA_CAP)) {
            return true;
        }

        return false;
    }

    /**
     * 
     * @param dstPDF
     * @param input3
     * @param reader
     * @throws IOException
     * @throws FileNotFoundException
     * @throws DocumentException
     */
    protected static void addPortaFIBVersionToPdf(File dstPDF, File input3, PdfReader reader)
            throws IOException, FileNotFoundException, DocumentException {
        PdfReader reader3 = new PdfReader(new FileInputStream(input3));
        PdfStamper stamper3 = new PdfStamper(reader3, new FileOutputStream(dstPDF));

        Map<String, String> info = reader.getInfo();
        info.put("PortaFIB.versio", StaticVersion.getVersio());
        stamper3.setMoreInfo(info);
        stamper3.close();
    }

    /**
     * 
     * @param reader
     * @param stamper
     * @param taulaDeFirmes
     * @throws Exception
     */
    public static void addTaulaDeFirmes(PdfReader reader, PdfStamper stamper, StampTaulaDeFirmes taulaDeFirmes)
            throws Exception {
        if (taulaDeFirmes.getPosicioTaulaDeFirmes() != TAULADEFIRMES_SENSETAULA) {
            float heightSignBoxInch = SIGNBOX_HEIGHT / 72f; // 0.5f;
            float boxLogoSide = LOGO_SIDE / 72f; // 1.1f;
            float startSignTableInch = SIGNBOX_START / 72f; // 1.25f;

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            {

                Document document = new Document();
                PdfWriter writer = PdfWriter.getInstance(document, baos);
                document.open();

                XMLWorkerHelper worker = XMLWorkerHelper.getInstance();

                if (taulaDeFirmes.getDesc() == null) {
                    taulaDeFirmes.setDesc("");
                } else {

                    if (taulaDeFirmes.getDesc().length() > 143) {
                        taulaDeFirmes.setDesc(taulaDeFirmes.getDesc().substring(0, 140) + "...");
                    }
                }

                if (taulaDeFirmes.getTitol() == null) {
                    taulaDeFirmes.setTitol("");
                } else {
                    if (taulaDeFirmes.getTitol().length() > 43) {
                        taulaDeFirmes.setTitol(taulaDeFirmes.getTitol().substring(0, 40) + "...");
                    }
                }

                StringBuffer html = new StringBuffer("<html><head></head><body>"
                        + "<div style='display:block;width:100%;" + "height:" + startSignTableInch
                        + "in; overflow:visible'>" + "<table style='width:100%;' >" // border: 1px
                                                                                                                                                                                                                                // solid
                        + "  <tr>" // style='height:" + heightHeaderInch + "in'
                        + "    <td style='width:" + boxLogoSide + "in;margin-right:7in;'>" // border:
                                                                                           // 1px
                                                                                           // solid;
                        + "       &nbsp;"
                        // + " <img src=\"" + urlLogo + "\" />"
                        + "     </td>" + "    <td >" + "      <b><u>" + taulaDeFirmes.getResumLabel() + "</u></b><br/>"
                        + "      <b>" + taulaDeFirmes.getTitolLabel() + ": </b>" + taulaDeFirmes.getTitol() + "<br/>"
                        + "      <b>" + taulaDeFirmes.getDescLabel() + ": </b>" + taulaDeFirmes.getDesc() + "    </td>"
                        + "  </tr>" + "</table>" + "</div>" + "<table style='width:100%'>");

                for (int r = 0; r < taulaDeFirmes.getNumFirmes(); r++) {
                    html.append("  <tr style='height:" + heightSignBoxInch + "in;' >"
                            + "<td style='border-style: solid;border-width: 1px;'>" + "&nbsp;"
                            + taulaDeFirmes.getSignantLabel() + " " + (r + 1) + "</td>" + "</tr>");
                }
                html.append("</table>");
                html.append("</body></html>");

                worker.parseXHtml(writer, document, new StringReader(html.toString()));

                // Afegir Logo
                Image img = Image.getInstance(taulaDeFirmes.getLogoFile());
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
                 * PdfContentByte cb = writer.getDirectContent(); cb.setLineWidth(2.0f); // Make
                 * a bit thicker than 1.0 default cb.setGrayStroke(0.95f); // 1 = black, 0 =
                 * white cb.moveTo(x, y); cb.lineTo(x + rectAmple, y + rectAlt); cb.stroke();
                 */

                document.close();
                writer.close();
            }

            int page;
            switch (taulaDeFirmes.getPosicioTaulaDeFirmes()) {
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

    public static List<AttachedFile> extractAttachments(PdfReader reader) throws IOException {

        PdfDictionary root = reader.getCatalog();
        PdfDictionary names = root.getAsDict(PdfName.NAMES);
        List<AttachedFile> files = new ArrayList<AttachedFile>();
        if (names != null) {
            PdfDictionary embedded = names.getAsDict(PdfName.EMBEDDEDFILES);
            if (embedded != null) {
                PdfArray filespecs = embedded.getAsArray(PdfName.NAMES);

                if (filespecs != null) {
                    // El diccinari FileSpec sempre està en les posicions parells.
                    for (int i = 1; i < filespecs.size(); i += 2) {
                        try {
                            files.add(extractAttachment(filespecs.getAsDict(i)));
                        } catch (Exception e) {
                            log.error("Error desconegut extraient attachments del PDF: " + e.getMessage(), e);
                        }
                    }
                }
            }
        }

        return files;
    }

    private static AttachedFile extractAttachment(PdfDictionary filespec) throws IOException {
        String filename = filespec.getAsString(PdfName.F).toString();
        String description = filespec.getAsString(PdfName.DESC).toString();

        PdfDictionary contentDict = filespec.getAsDict(PdfName.EF);
        PRStream stream = (PRStream) PdfReader.getPdfObject(contentDict.getAsIndirectObject(PdfName.F));

        File output = File.createTempFile("portafib_pdf_attached_", ".file");
        FileOutputStream fos = new FileOutputStream(output);
        fos.write(PdfReader.getStreamBytes(stream));
        fos.flush();
        fos.close();

        return new AttachedFile(filename, description, output);
    }

    /**
     * 
     * @param pdf
     * @return Si el fitxer no és PDF llavors retorna 0.
     */
    public static int getNumberOfSignaturesInPDF(File pdf) throws I18NException {

        InputStream is = null;
        try {
            is = new FileInputStream(pdf);
            return PdfUtils.getNumberOfSignaturesInPDF(is);
        } catch (FileNotFoundException e) {
            log.error("No s'ha trobat el fitxer " + pdf.getAbsolutePath() + ":" + e.getMessage(), e);
            throw new I18NException("fitxer.notfound", pdf.getAbsolutePath(), e.getMessage());
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    log.error("Error tancant InputStream del fitxer " + pdf.getAbsolutePath() + ":" + e.getMessage(),
                            e);
                }
            }
        }
    }

    /**
     * 
     * @param pdfis
     * @return Si el fitxer no és PDF llavors retorna 0.
     */
    public static int getNumberOfSignaturesInPDF(InputStream pdfis) {
        try {
            PdfReader reader;
            try {
                reader = new PdfReader(pdfis);
            } catch (IOException e1) {
                throw new I18NException(e1, "genapp",
                        new I18NArgumentString("Error llegint PDF firmat: " + e1.getMessage()));
            }

            return getNumberOfSignaturesInPDF(reader);
        } catch (Throwable e) {
            log.error("Error desconegut intentant obtenir numero de firmes d'un PDF: " + e.getMessage());
            return 0;
        }

    }

    /**
     * 
     * @param reader
     * @return
     */
    public static int getNumberOfSignaturesInPDF(PdfReader reader) {
        AcroFields af = reader.getAcroFields();
        ArrayList<String> names = af.getSignatureNames();

        if (names == null || names.size() == 0) {
            return 0;
        }

        // Calculam només les firmes sense els Segells de Temps
        int totalNomesFirmes = 0;
        for (int i = names.size() - 1; i >= 0; i--) {

            String name = names.get(i);
            PdfPKCS7 pk = af.verifySignature(name);
            if (!pk.isTsp()) {
                totalNomesFirmes++;
            }

        }

        return totalNomesFirmes;
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
    public static void addCustodiaInfo(PdfReader reader, PdfStamper stamp, StampCustodiaInfo custodiaInfo,
            int posicioTaulaDeFirmes) throws Exception, I18NException {

        int positioCustodiaInfoGeneral = custodiaInfo.posicioCustodiaInfo;

        if (positioCustodiaInfoGeneral == POSICIO_PAGINA_CAP) {
            return;
        }

        int numberOfPages = reader.getNumberOfPages();

        BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, BaseFont.EMBEDDED);

        /*
         * Indica en quines pàgines s'ha de mostrar el missatge i el codi de barres.
         * NULL = no mostrar '*'= totes les pàgines '0'=primera pàgina (taula de firmes)
         * '-1' = darrera pàgina (taula de firmes) '1,3, 4-5' =format Imprimir
         */
        Set<Integer> pagines = processarPagines(custodiaInfo.getPagines(), posicioTaulaDeFirmes, numberOfPages);

        if (pagines.isEmpty()) {
            return;
        }

        float barcode_X = 20;
        float barcode_Y = 20;
        int textAlign = PdfContentByte.ALIGN_LEFT;
        float text_X = 30;
        float text_Y = 150;
        float rotation = 90;

        Image image = (Image) custodiaInfo.getBarcodePlugin().generateTextBarCodeIText(custodiaInfo.barcodeText);

        // PAGINES
        for (Integer pagina : pagines) {
            PdfContentByte over = stamp.getOverContent(pagina);

            Rectangle pageSize = reader.getPageSize(pagina);
            float width = pageSize.getWidth();
            float height = pageSize.getHeight();

            int SIGNATURE_SPLIT_LEN = 0;
            if (height > width) {
                SIGNATURE_SPLIT_LEN = (positioCustodiaInfoGeneral == POSICIO_PAGINA_ESQUERRA
                        || positioCustodiaInfoGeneral == POSICIO_PAGINA_DRETA) ? SIGNATURE_VERTICAL_SPLIT_LEN
                                : SIGNATURE_HORIZONTAL_SPLIT_LEN;
            } else {
                SIGNATURE_SPLIT_LEN = (positioCustodiaInfoGeneral == POSICIO_PAGINA_ESQUERRA
                        || positioCustodiaInfoGeneral == POSICIO_PAGINA_DRETA) ? SIGNATURE_HORIZONTAL_SPLIT_LEN
                                : SIGNATURE_VERTICAL_SPLIT_LEN;
            }

            Vector<String> lines = new Vector<String>();
            int counter = 0;
            String missatge = custodiaInfo.missatgeCustodia;
            while (counter != -1) {
                if (counter + SIGNATURE_SPLIT_LEN < missatge.length()) {
                    int prev = missatge.substring(counter, counter + SIGNATURE_SPLIT_LEN).lastIndexOf(" "); //$NON-NLS-1$
                    String next_string = missatge.substring(counter + SIGNATURE_SPLIT_LEN, missatge.length());
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
                    barcode_X = lineX - image.getPlainHeight() / 2.0f;
                    barcode_Y = lineY;
                    textAlign = PdfContentByte.ALIGN_LEFT;
                    text_X = lineX - ((lines.size() - 1) * SEP_LINE / 2.0f) + (SEP_LINE / 2.0f);
                    text_Y = lineY + image.getPlainWidth() + 15;
                    rotation = 90;
                break;
                case (POSICIO_PAGINA_DRETA):
                    lineX = width - 29;
                    lineY = 20;
                    barcode_X = lineX - image.getPlainHeight() / 2.0f;
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
                    barcode_Y = lineY - image.getPlainHeight() / 2.0f;
                    textAlign = PdfContentByte.ALIGN_LEFT;
                    text_X = lineX + image.getPlainWidth() + 10;
                    text_Y = lineY + ((lines.size() - 1) * SEP_LINE / 2.0f) - (SEP_LINE / 2.0f);
                    rotation = 0;
                break;
                case (POSICIO_PAGINA_ABAIX):
                    lineX = 20;
                    lineY = 27;
                    barcode_X = lineX;
                    barcode_Y = lineY - image.getPlainHeight() / 2.0f;
                    textAlign = PdfContentByte.ALIGN_LEFT;
                    text_X = lineX + image.getPlainWidth() + 10;
                    text_Y = lineY + ((lines.size() - 1) * SEP_LINE / 2.0f) - (SEP_LINE / 2.0f);
                    rotation = 0;
                break;
            }

            // TODO
            /*
             * over.setLineWidth(1.0f); // Make a bit thicker than 1.0 default
             * over.setGrayStroke(0.85f); // 1 = black, 0 = white
             * 
             * over.moveTo(lineX, 0); over.lineTo(lineX, 800);
             * 
             * over.moveTo(0,lineY); over.lineTo(600, lineY); over.stroke();
             */

            image.setAbsolutePosition(barcode_X, barcode_Y);
            if (custodiaInfo.getBarcodePlugin().requireRotation()) {
                image.setRotationDegrees(rotation);
            }
            over.addImage(image);

            counter = 0;

            // Descentramos en función del número de líneas de la firma
            /*
             * text_X = ((positioCustodiaInfo == POSICIO_PAGINA_ESQUERRA ||
             * positioCustodiaInfo == POSICIO_PAGINA_DRETA) ? text_X - (lines.size() / 2.0f)
             * * SEP_LINE : text_X); text_Y = (positioCustodiaInfo == POSICIO_PAGINA_ADALT
             * || positioCustodiaInfo == POSICIO_PAGINA_ABAIX) ? text_Y + (lines.size() /
             * 2.0f) * SEP_LINE : text_Y;
             */

            // Imprimimos las líneas de la firma
            for (counter = 0; counter < lines.size(); counter++) {

                over.beginText();
                over.setFontAndSize(bf, 10);
                float x = ((positioCustodiaInfoGeneral == POSICIO_PAGINA_ESQUERRA
                        || positioCustodiaInfoGeneral == POSICIO_PAGINA_DRETA) ? text_X + counter * SEP_LINE : text_X);
                float y = (positioCustodiaInfoGeneral == POSICIO_PAGINA_ADALT
                        || positioCustodiaInfoGeneral == POSICIO_PAGINA_ABAIX) ? text_Y - counter * SEP_LINE : text_Y;
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
    public static Set<Integer> processarPagines(String pagines, int posicioTaulaDeFirmes, int lastPage)
            throws I18NException {

        Set<Integer> pages = new TreeSet<Integer>();
        if (pagines == null || "buit".equals(pagines) || pagines.trim().length() == 0) {
            return pages;
        }

        // Check Chars allowed
        final String permesos = " *,-0123456789";
        for (int i = 0; i < pagines.length(); i++) {
            if (permesos.indexOf(pagines.charAt(i)) == -1) {
                throw new I18NException("custodiainfo.pagines.errorformat", String.valueOf(pagines.charAt(i)));
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

    public static boolean isPdfA1(final byte[] metadata) {
        if (metadata == null) {
            return false;
        }
        final String rdf = new String(metadata);

        return rdf.replace("\n", "") //$NON-NLS-1$ //$NON-NLS-2$
                .replace("\r", "") //$NON-NLS-1$ //$NON-NLS-2$
                .replace("\t", "") //$NON-NLS-1$ //$NON-NLS-2$
                .replace(" ", "") //$NON-NLS-1$ //$NON-NLS-2$
                .contains("<pdfaid:part>1</pdfaid:part>"); //$NON-NLS-1$
    }

    public static boolean isPdfAx(final byte[] metadata) {

        if (metadata == null) {
            return false;
        }
        final String rdf = new String(metadata);

        return rdf.replace("\n", "") //$NON-NLS-1$ //$NON-NLS-2$
                .replace("\r", "") //$NON-NLS-1$ //$NON-NLS-2$
                .replace("\t", "") //$NON-NLS-1$ //$NON-NLS-2$
                .replace(" ", "") //$NON-NLS-1$ //$NON-NLS-2$
                .contains("pdfaid:part"); //$NON-NLS-1$
    }

}
