package es.caib.portafib.logic.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import junit.framework.Assert;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.fundaciobit.plugins.barcode.barcode128.BarCode128Plugin;
import org.fundaciobit.plugins.barcode.pdf417.Pdf417Plugin;
import org.fundaciobit.plugins.barcode.qrcode.QrCodePlugin;
import org.fundaciobit.pluginsib.barcode.IBarcodePlugin;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.utils.Utils;
import org.junit.Test;

import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfArray;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import es.caib.portafib.utils.ConstantsV2;
import es.caib.portafib.utils.SignBoxRectangle;

/**
 * 
 * @author anadal
 * 
 */
public class TestPDFUtils implements ConstantsV2 {

  public static final Logger log = Logger.getLogger(TestPDFUtils.class);

  @Test
  public void testPositionCaixesDeFirmes() {
    
    String expected = "-- 1\n"
      +"signaturePositionOnPageLowerLeftX=106\n"
      +"signaturePositionOnPageLowerLeftY=651\n"
      +"signaturePositionOnPageUpperRightX=555\n"
      +"signaturePositionOnPageUpperRightY=713\n"
      +"\n"
      +"\n"
      +"-- 2\n"
      +"signaturePositionOnPageLowerLeftX=106\n"
      +"signaturePositionOnPageLowerLeftY=584\n"
      +"signaturePositionOnPageUpperRightX=555\n"
      +"signaturePositionOnPageUpperRightY=646\n"
      +"\n"
      +"\n"
      +"-- 3\n"
      +"signaturePositionOnPageLowerLeftX=106\n"
      +"signaturePositionOnPageLowerLeftY=517\n"
      +"signaturePositionOnPageUpperRightX=555\n"
      +"signaturePositionOnPageUpperRightY=579\n"
      +"\n"
      +"\n"
      +"-- 4\n"
      +"signaturePositionOnPageLowerLeftX=106\n"
      +"signaturePositionOnPageLowerLeftY=450\n"
      +"signaturePositionOnPageUpperRightX=555\n"
      +"signaturePositionOnPageUpperRightY=512\n"
      +"\n"
      +"\n"
      +"-- 5\n"
      +"signaturePositionOnPageLowerLeftX=106\n"
      +"signaturePositionOnPageLowerLeftY=383\n"
      +"signaturePositionOnPageUpperRightX=555\n"
      +"signaturePositionOnPageUpperRightY=445\n"
      +"\n"
      +"\n"
      +"-- 6\n"
      +"signaturePositionOnPageLowerLeftX=106\n"
      +"signaturePositionOnPageLowerLeftY=316\n"
      +"signaturePositionOnPageUpperRightX=555\n"
      +"signaturePositionOnPageUpperRightY=378\n\n\n";
    
    
    StringBuffer properties = new StringBuffer();
    for (int i = 0; i < 6; i++) {
      
      SignBoxRectangle sbr = SignBoxRectangle.getPositionOfVisibleSignature(i +1);
      
      properties.append("-- " + (i+1) + "\n");
      properties.append("signaturePositionOnPageLowerLeftX=" + (int)sbr.llx + "\n");
      properties.append("signaturePositionOnPageLowerLeftY=" + (int)sbr.lly + "\n");
      properties.append("signaturePositionOnPageUpperRightX=" + (int)sbr.urx + "\n");
      properties.append("signaturePositionOnPageUpperRightY=" + (int)sbr.ury + "\n\n\n");
      
      

      log.info(" info.put(" + (i+1) + ", \"" 
          + (int)sbr.llx + "," + (int)sbr.lly + "," + (int)sbr.urx + "," + (int)sbr.ury 
          + "\");");
      
    }

    org.junit.Assert.assertEquals(expected, properties.toString());

  }
  
  
  
  
  @Test
  public void testProcessarPagines() {
    
    
    String[][] test = new String[][] {
     // PATRO     , // Darrera pagina = -1                           // Cap pagina = 0              // Primera pagina  = 1 
     { "-1, 0, 1, *, 3, 4-5, 8-", "[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]", "[1, 2, 3, 4, 5, 6, 7, 8, 9]", "[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]"},
     { "-1, 0, 1, 4-5,3,  8-",    "[1, 3, 4, 5, 8, 9, 10]",          "[1, 3, 4, 5, 8, 9]",          "[1, 2, 4, 5, 6, 9, 10]" },
     { "-1, 0, 1, 3, ",           "[1, 3, 10]",                      "[1, 3, 9]",                   "[1, 2, 4, 10]"},
     // Llança error
     //{ " ,, -1, 0",               "[1, 10]",                         "[1, 9]",                      "[1, 10]" }
    };
    

    // Cap pagina
    for (int t = 0; t < test.length; t++) {
      
    
    String pagines = test[t][0];
    int[] posicioTaulaDeFirmes = new int[] {
        TAULADEFIRMES_DARRERAPAGINA,
        TAULADEFIRMES_SENSETAULA,
        TAULADEFIRMES_PRIMERAPAGINA
    };
    int lastPage = 10;
    
    for (int i = 0; i < posicioTaulaDeFirmes.length; i++) {
      Set<Integer> set;
      try {
        set = PdfUtils.processarPagines(pagines, posicioTaulaDeFirmes[i],
            posicioTaulaDeFirmes[i] == TAULADEFIRMES_SENSETAULA? (lastPage -1) : lastPage);
        String actual = Arrays.toString(set.toArray());
        //log.info("Actual: " + actual);
        //log.info("Expect: " + test[t][2 + posicioTaulaDeFirmes[i]]);
        
        Assert.assertEquals(test[t][2 + posicioTaulaDeFirmes[i]], actual);
        
      } catch (I18NException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    
    }
    
  }
  
  
//  public static void main1(String[] args) {
//
//    try {
//     
//    File parent = new File("D:\\dades\\dades\\CarpetesPersonals\\Programacio\\PortaFIB\\applet\\web");
//
//    File docOrig = new File(parent, "hola_amb_taula.pdf");
//    File docFir = new File(parent, "hola_amb_taula_firmat.pdf");
//    
//    
//    byte[] pdfData = FileUtils.readFileToByteArray(docFir);
//    
//    byte[] originalData = FileUtils.readFileToByteArray(docOrig);
//    
//    boolean result = PdfUtils.checkDocumentWhenFirstSign(originalData, pdfData, 0);
//    
//    log.info("Orig Len = " + docOrig.length());
//    
//    log.info("Result = " + result);
//    
//
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
//    
//  
//  }
  
  
  @Test
  public void testAttachingFilesToPDFwithAttachs() {
    
    
    try {
      File srcPDF = getFileFromResource("pdf_with_attach.pdf");
      
      final String[] names = new String[] {"annex_1.txt", "annex_2.jpg", "annex_3.bin" } ;
      

      
      List<AttachedFile> files = new ArrayList<AttachedFile>();
      
       
      for (int i = 0; i < names.length; i++) {
        File f = getFileFromResource(names[i]);
        files.add(new AttachedFile(names[i], f));
      }
      
      File dstPDF = new File("testAttachingFilesToPDFwithAttachs_test.pdf");
      
      final boolean acceptTransformPDFA = false;

      PdfUtils.add_TableSign_Attachments_CustodyInfo_PDF(srcPDF, dstPDF, files, 
          null, null, null,acceptTransformPDFA);
      
      Set<String> attachments = extractDocLevelAttachments(dstPDF.getAbsolutePath());
      
      System.out.println(" TOTAL = " + attachments.size());
      
     
    } catch (Throwable e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    
    
  }
  
  
  
  private static Set<String> extractDocLevelAttachments(String filename) throws IOException {
    PdfReader reader = new PdfReader(filename);
    PdfDictionary root = reader.getCatalog();
    PdfDictionary documentnames = root.getAsDict(PdfName.NAMES);
    PdfDictionary embeddedfiles = documentnames.getAsDict(PdfName.EMBEDDEDFILES);
    PdfArray filespecs = embeddedfiles.getAsArray(PdfName.NAMES);
    PdfDictionary filespec;
    PdfDictionary refs;
    Set<String> attachments = new HashSet<String>();
    for (int i = 0; i < filespecs.size(); ) {
      filespecs.getAsString(i++);
      filespec = filespecs.getAsDict(i++);
      refs = filespec.getAsDict(PdfName.EF);
      for (PdfName key : refs.getKeys()) {
 
        attachments.add(filespec.getAsString(key).toString());
        break;
      }
    }
    reader.close();
    return attachments;
}
  
  
  
  
  /*
  public static void addAttachments(
    String srcPdf, String dest, String[] attachments) throws IOException, DocumentException {
    PdfReader reader = new PdfReader(srcPdf);
    PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
    for (int i = 0; i < attachments.length; i++) {
      
      PdfWriter writer = stamper.getWriter();
      File src = new File(attachments[i]);
      
      PdfFileSpecification fs =
          PdfFileSpecification.fileEmbedded(writer, src.getAbsolutePath(), src.getName(), null);
        writer.addFileAttachment(src.getName().substring(0, src.getName().indexOf('.')), fs);
      
    }
    stamper.close();
  }
  */

  
    
  
  
  public static PdfReader getPdfReaderFromResource(String name) throws Exception  {
    
    if (!name.startsWith("/")) {
      name = '/' + name; 
    }
    InputStream is = TestPDFUtils.class.getResourceAsStream(name);
    
    return new PdfReader(is);

  }
  
  
  public static File getFileFromResource(String name) throws Exception {

    if (!name.startsWith("/")) {
      name = '/' + name;
    }
    InputStream is = TestPDFUtils.class.getResourceAsStream(name);
    File parent = new File(System.getProperty("java.io.tmpdir"), String.valueOf(System.nanoTime()));
    parent.mkdirs();
    parent.deleteOnExit();
    
    File f = new File(parent, name);
    f.deleteOnExit();

    FileOutputStream fos = new FileOutputStream(f);
    IOUtils.copy(is, fos);

    fos.flush();
    fos.close();

    is.close();

    return f;

  }
    
  
  
  
  
  
  public static void main4(String[] args) {
    
    File f = new File("C:\\Documents and Settings\\anadal\\Escritorio\\DEMO\\hola.pdf");
    
    log.info("TIPUS MIME = " + Utils.getMimeType(f.getAbsolutePath()));
    
  }
  
  
  
//  public static void main5(String[] args) {
//
//    try {
//
//      File parent = new File("C:\\Users\\anadal.IBITNET\\Desktop\\FIRMATS_NO_PORTAFIB");
//      
//      File original = new File(parent, "Fichero 3 MB - firmado.pdf");
//      File firmat = new File(parent, "ELIMINAR_Fichero3mb_firmado_.pdf");
//
//      byte[] originalData = FileUtils.readFileToByteArray(original);
//
//      byte[] firmatData = FileUtils.readFileToByteArray(firmat);
//
//      
//
//    } catch (Throwable e) {
//      e.printStackTrace();
//    }
//
//  }
  
  
  
  public static void mainTestHorizontal(String[] args) {
    
    try {
      //new TestPDFUtils().testAttachingFilesToPDFwithAttachs(); 
      new TestPDFUtils().testHorizontal();
    } catch (Throwable e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  

  public static void main0(String[] args) {

    File parent = new File("C:\\Documents and Settings\\anadal\\Escritorio\\PortaSIB\\proves");

    File srcPDF = new File(parent, "holat.pdf");

    String dsfFileName = "hola_custodia.pdf";

    IBarcodePlugin[] barcodes = new IBarcodePlugin[] { new Pdf417Plugin(), new QrCodePlugin(),
        new BarCode128Plugin() };

    for (int i = 0; i < barcodes.length; i++) {

      try {

        File dstPDF = new File(barcodes[i].getName() + dsfFileName);

        PdfReader reader = new PdfReader(new FileInputStream(srcPDF));
        FileOutputStream output = new FileOutputStream(dstPDF);

        PdfStamper stamper = new PdfStamper(reader, output);

        
        StampCustodiaInfo custodiaInfo = getCustodyInfo(barcodes[i]);
        
        int posicioTaulaDeFirmes = ConstantsV2.TAULADEFIRMES_DARRERAPAGINA;
        

        // IBarcodePlugin barcode = new PDF417IText();
        PdfUtils.addCustodiaInfo(reader, stamper, custodiaInfo, posicioTaulaDeFirmes);

        stamper.close();

        log.info(" --- FINAL ----");

      } catch (Exception e) {
        e.printStackTrace();
      } catch (I18NException e) {
        log.info(" Error = " + I18NLogicUtils.getMessage(e, new Locale("ca")));
        e.printStackTrace();
      }
    }

  }




  public static StampCustodiaInfo getCustodyInfo(IBarcodePlugin barcode) {
    StampCustodiaInfo custodiaInfo;
    String url = "http://vd.caib.es/1399274075742"; //PINBAL00000000000000001179161128677562589045";

    //int position =  POSICIO_PAGINA_ABAIX;
    //int position = POSICIO_PAGINA_ADALT;
    //int position = POSICIO_PAGINA_DRETA; 
    int position = POSICIO_PAGINA_ESQUERRA;

    
    // SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
    // "dd/MM/yyyy HH:mm:ss");

    String missatge = " HOLA CARCOLA Data Validació: "  + url 
       + " DAta " + new Date().toString() 
       
       //+ " XXXXX  XXXXXXXXXXXXXX "
       
       + " Plugin: " + barcode.getClass().getName() 
       //+ " Special= {4}"

       //+ " XXXXXXXXXXXX XXXXXXXXXXXXX XXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXX XXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXX XXXXXXXXXXXXXX"
       ;
    
    String barcodeText = url;
    
    String pagines = "*";
    
    custodiaInfo = new StampCustodiaInfo(position, missatge,
        barcode, barcodeText, pagines);
    return custodiaInfo;
  }

  public static void main(String[] args) {
    //new TestPDFUtils().generaPDFPerProvesApplet();
    
    main3(args);
    
  }
    

  //@Test
  public void generaPDFPerProvesApplet() {

    //File parent = new File("C:\\Documents and Settings\\anadal\\Escritorio\\PortaSIB\\proves");
    //File srcPDF = new File(parent, "hola.pdf");
    try {
      log.info(" Constants.MAX FIRMES = " + ConstantsV2.MAX_FIRMES_PER_TAULA);
      
    File srcPDF = getFileFromResource("test.pdf");
    
    File dstPDF = new File("..\\applet\\web\\hola_amb_taula.pdf");

    log.info("TIPUS MIME = " + Utils.getMimeType("hola.pdf"));

    int numFirmes = 18;
    numFirmes = (numFirmes > MAX_FIRMES_PER_TAULA) ? MAX_FIRMES_PER_TAULA
        : numFirmes;
    log.info("Numero firmes a imprimir = " + numFirmes);
    int posicio = (int) TAULADEFIRMES_PRIMERAPAGINA;
    String signantLabel = "Signant";
    String resumLabel = "Resum de Firmes";
    String descLabel = "Descripció";
    String desc = "Ammmmmmmm Bmmmmmmmm "
        + "Cmmmmmmmm Dmmmmmmmm Emmmmmmmm Fmmmmmmmm Gmmmmmmmm "
        + "Hmmmmmmmm Immmmmmmm Jmmmmmmmm Kmmmmmmmm Lmmmmmmmm " + "Mmmmmmmmm Nmmmmmmmm...";
    log.info(desc.length());

    String titolLabel = "Títol";
    String titol = "Ammmmmmmmm Bmmmmmmmmm Cmmmmmmmmm Dmmmmmm";
    log.info(titol.length());
    // File logoFile = new File("logotaulafirmesfundaciobit.jpg");
    File logoFile = getFileFromResource("logotaulafirmescaib.jpg");
    // File logoFile = new File("logotaulafirmesfundaciobit2.jpg");
    
    byte[] logoSegell;    
    try {
      logoSegell = FileUtils.readFileToByteArray(logoFile);
    } catch (IOException e) {
      String msg = "Error desconegut llegint logo-segell del recurs logotaulafirmescaib.jpg: "
        + e.getMessage();
      log.error(msg, e);
      throw new I18NException("error.unknown",  msg);
    }
    
    
      List<AttachedFile> attachments = new ArrayList<AttachedFile>(); 
      attachments.add( new AttachedFile( "hola.txt", getFileFromResource("annex_1.txt")));

      log.info(" INICI XX " + dstPDF.getAbsolutePath());
      // File logoFile = null;
      
      
      final StampTaulaDeFirmes stamp = new StampTaulaDeFirmes(numFirmes, posicio, signantLabel,
          resumLabel, descLabel, desc, titolLabel, titol, logoSegell); 
      
      final boolean acceptTransformPDFA = false;
      
      
      PdfUtils.add_TableSign_Attachments_CustodyInfo_PDF(srcPDF, dstPDF, attachments,
           null, stamp,  null , acceptTransformPDFA);

    } catch (Throwable e) {
      e.printStackTrace();
    }

  }

  public static void main3(String[] args) {

    try {

      File plantillaPdf = new File(
          "D:\\dades\\dades\\CarpetesPersonals\\Programacio\\PortaFIB\\scripts\\templates\\formulariDelegacio.pdf");

      // TODO Check Plantilla

      File dstPDF = new File("holaxx.pdf");
      if (dstPDF.exists()) {
        dstPDF.delete();
      }

      PdfReader reader = new PdfReader(new FileInputStream(plantillaPdf));
      PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dstPDF), '\0', false);
      AcroFields fields = stamper.getAcroFields();

      fields.setField("DESTINATARI.NOM", "nomDest");

      fields.setField("DESTINATARI.NIF", "destinatari.getNif()");

      fields.setField("DELEGAT.NOM", "Utils.getOnlyNom(delegat.getUsuariPersona())");

      fields.setField("DELEGAT.NIF", "delegat.getNif()");

      fields.setField("MOTIU", "delegacio.getMotiu()");

      fields.setField("DOCUMENTS", "documents");

      fields.setField("DEST", "nomDest");

      fields.setField("DATA", new SimpleDateFormat().format(new Date()));

      stamper.setFormFlattening(true);

      stamper.close();

    } catch (Exception e) {
      // TODO: handle exception
    }
  }

  
  @Test
  public void testHorizontal() throws Exception, I18NException  {

    List<File> files = new ArrayList<File>();

    files.add(getFileFromResource("aa_horitzontal.pdf"));
    files.add(getFileFromResource("ab_horitzontalvertical.pdf"));

    List<AttachedFile> attachments = new ArrayList<AttachedFile>();

    log.info("TIPUS MIME = " + Utils.getMimeType("hola.pdf"));

    StampTaulaDeFirmes taulaDeFirmesInfo = null; // getTaulaDeFirmesInfo();
    
    //IBarcodePlugin barcode = new BarCode128Plugin(); // new QrCodePlugin(), new BarCode128Plugin() 
    
    StampCustodiaInfo custodiaInfo = null; //getCustodyInfo(barcode);
    
   
    final boolean acceptTransformPDFA = false;

    for (File file : files) {

      File dstPDF = new File("testHorizontal_" + file.getName());

      PdfUtils.add_TableSign_Attachments_CustodyInfo_PDF(file, dstPDF, attachments, null,
          taulaDeFirmesInfo , custodiaInfo, acceptTransformPDFA);
    }

  }




  public static StampTaulaDeFirmes getTaulaDeFirmesInfo() throws Exception {
    StampTaulaDeFirmes stamp;
    int numFirmes = 18;
    numFirmes = (numFirmes > MAX_FIRMES_PER_TAULA) ? MAX_FIRMES_PER_TAULA
        : numFirmes;
    log.info("Numero firmes a imprimir = " + numFirmes);
    int posicio = (int) TAULADEFIRMES_PRIMERAPAGINA;
    String signantLabel = "Signant";
    String resumLabel = "Resum de Firmes";
    String descLabel = "Descripció";
    String desc = "Ammmmmmmm Bmmmmmmmm "
        + "Cmmmmmmmm Dmmmmmmmm Emmmmmmmm Fmmmmmmmm Gmmmmmmmm "
        + "Hmmmmmmmm Immmmmmmm Jmmmmmmmm Kmmmmmmmm Lmmmmmmmm " + "Mmmmmmmmm Nmmmmmmmm...";

    String titolLabel = "Títol";
    String titol = "Ammmmmmmmm Bmmmmmmmmm Cmmmmmmmmm Dmmmmmm";
    log.info(titol.length());
    // File logoFile = new File("logotaulafirmesfundaciobit.jpg");
    File logoFile = getFileFromResource("logotaulafirmescaib.jpg");
    
    byte[] logoSegell;    
    try {
      logoSegell = FileUtils.readFileToByteArray(logoFile);
    } catch (IOException e) {
      String msg = "Error desconegut llegint logo-segell del recurs logotaulafirmescaib.jpg: "
        + e.getMessage();
      log.error(msg, e);
      throw e;
    }
    
      stamp = new StampTaulaDeFirmes(numFirmes, posicio, signantLabel, resumLabel, descLabel,
        desc, titolLabel, titol, logoSegell);
    return stamp;
  }
  
}
