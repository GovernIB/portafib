package es.caib.portafib.logic.utils;

import java.io.File;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.portafib.logic.utils.datasource.FileDataSource;
import es.caib.portafib.utils.ConstantsV2;

/**
 * 
 * @author anadal(u80067)
 *
 */
public class ComparePdfTester implements ConstantsV2 {

  public static void main(String[] args) {

    File tmpDir = new File("tmpfiles");
    tmpDir.mkdirs();

    String[][] testFiles = new String[][] {
        { "Edictes-PDF-A-1a amb annexos.pdf", "Edictes-PDF-A-1a amb annexos-signed.pdf", ""
         + TAULADEFIRMES_SENSETAULA },
        { "Test_5_Adobe_PDF_simple.pdf", "Test_5-BIS_RESULT_OK.pdf" , "" +
         TAULADEFIRMES_SENSETAULA },
        { "A4 Portrait PDF-A-1a.pdf", "Test_5-BIS_RESULT_OK.pdf" , "" +
         TAULADEFIRMES_SENSETAULA },
        { "A4 Portrait PDF-A-1a.pdf", "A4 Portrait PDF-A-1a-signed.pdf" , "" +
         TAULADEFIRMES_SENSETAULA },
         
        { "Test_3_Qoopa_Sense_revisio_1a_firma.pdf", "Test_3_RESULT_OK.pdf" , "" +
         TAULADEFIRMES_SENSETAULA },
       /* { "Error_Dictionary_OutOfBounds_P.O.16.19_ADAP_TAULA_FIRST_PAGE.pdf",
         "Error_Dictionary_OutOfBounds_P.O.16.19_SIGNED.pdf" , "" +
         TAULADEFIRMES_PRIMERAPAGINA },*/

         { "RV1_nov-18_FIRMADO.pdf", "RV1_nov-18_FIRMADO_AMB_PORTAFIB_2_firmes.pdf", "" +
         TAULADEFIRMES_SENSETAULA },
        
         { "E18-0142.Acta.de.replanteo.signed.pdf",
         "E18-0142.Acta.de.replanteo.signed_FIRMAT_AMB _PORTAFIB.pdf", "" +
         TAULADEFIRMES_SENSETAULA },
        
         { "TD110_QG_151023.pdf" , "TD110_QG_151023_firmado.pdf" , "" +
         TAULADEFIRMES_PRIMERAPAGINA },

        { "TONI_REUS_ORIGINAL.pdf", "TONI_REUS_3_FIRMES.pdf", "" + TAULADEFIRMES_SENSETAULA },
/*
         { "Fitxer_5Mb_Adapt_Amb_ATTACHED.pdf", "Fitxer_5Mb_Adapt_Amb_ATTACHED_SIGNED.pdf",
         "" + TAULADEFIRMES_SENSETAULA },*/

        { "Fitxer_5Mb_Adapt_Amb_ATTACHED.pdf",
            "Fitxer_5Mb_Adapt_Amb_ATTACHED_FALTA_1_ADJUNT_SIGNED.pdf",
            "" + TAULADEFIRMES_SENSETAULA },
            
        { "Fitxer_5Mb_Adapt_Amb_ATTACHED.pdf",
              "Fitxer_5Mb_Adapt_Amb_ATTACHED_DIFERENTS_ADJUNTS_SIGNED.pdf",
              "" + TAULADEFIRMES_SENSETAULA }

    };

    final File testDir = new File("testfiles");
    
    long totaltime = 0;
    long totalsizes = 0;
    long totalpages = 0;
    

    for (int i = 0; i < testFiles.length; i++) {
      long start = System.currentTimeMillis();
      File adap = new File(testDir, testFiles[i][0]);
      File sign = new File(testDir, testFiles[i][1]);

      String error = null;
      try {
        PdfComparator.compare(new FileDataSource(adap), new FileDataSource(sign), tmpDir, Integer.parseInt(testFiles[i][2]));
      } catch (I18NException e) {

        error = e.getTraduccio().getArgs()[0].getValue();
      }

      long end = System.currentTimeMillis();           
      
      if (error == null) {
        long time = (end - start);
        System.out.println(" Test[" + i + "]:{OK}\t" + adap.length() + "\t" + sign.length()
            + "\t" + time + " ms");
        
        // Estadistiques 
        
        try {
          PDDocument doc = PDDocument.load(sign);
          totalpages = totalpages + doc.getNumberOfPages();
          totalsizes = totalsizes + sign.length();
          totaltime = totaltime + time;
          
          doc.close();

        } catch (Exception e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }

      } else {
        System.err.println(" Test[" + i + "]:{ERR}\t" + adap.length() + "\t" + sign.length()
            + "\t" + (end - start) + " ms => " + error);
      }

    }

    System.err.flush();

    System.out.flush();
    
    System.out.println("---------- ESTADISTIQUES -------------");
    
    System.out.println(" Temps per MEGA = " + (totalsizes/(1024.0))/(totaltime/1000) + " KB/s");
    System.out.println(" Temps per PAGINES = " + (totalpages)/(totaltime/1000) + " Pag/s");
    

    System.err.println("Final!");
  }
}
