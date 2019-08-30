package es.caib.portafib.logic.utils;

import com.itextpdf.text.pdf.PdfReader;
import es.caib.portafib.logic.utils.datasource.IPortaFIBDataSource;
import es.caib.portafib.utils.ConstantsV2;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;
import org.fundaciobit.genapp.common.i18n.I18NException;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author anadal(u80067)
 *
 */
public class PdfComparator implements ConstantsV2 {

  protected static Logger log = Logger.getLogger(PdfComparator.class);

  /**
   * 
   * @param adaptat
   * @param signed
   * @param tmpDir
   * @param posTaulaDeFirmes
   * @throws I18NException
   */
  public static void compare(IPortaFIBDataSource adaptat, IPortaFIBDataSource signed,
      File tmpDir, int posTaulaDeFirmes) throws I18NException {

    int esborrar = -1; // -1 significa esborrar les imatges generades

    int start;
    int end;

    switch (posTaulaDeFirmes) {

      case TAULADEFIRMES_SENSETAULA:
        start = 0;
        end = 0;
      break;
      case TAULADEFIRMES_PRIMERAPAGINA:
        start = 1;
        end = 0;
      break;
      case TAULADEFIRMES_DARRERAPAGINA:
        start = 0;
        end = 1;
      break;

      default:
        // XYZ ZZZ TRA
        throw new I18NException("Posició de Taula de Firmes Desconeguda: " + posTaulaDeFirmes);
    }

    String prefix = System.currentTimeMillis() + "_" + System.nanoTime();

    File[] adaptades = null;

    File[] signats = null;
    // 1 Compara les Imatges
    try {

      adaptades = generateImagesOfPDF(prefix + "_orig", adaptat, tmpDir, start, end);

      signats = generateImagesOfPDF(prefix + "_sign", signed, tmpDir, start, end);

      if (adaptades.length != signats.length) {
        // XYZ ZZZ TRA
        throw new I18NException("genapp.comodi", "El numero de pàgines del fitxer adaptat"
            + " i del firmat son diferents: orig[" + adaptades.length + "] i signat["
            + signats.length + "] ");
      }

      for (int i = 0; i < signats.length; i++) {

        try {
          if (!FileUtils.contentEquals(adaptades[i], signats[i])) {
            // XYZ ZZZ TRA
            esborrar = i; // ignorar l'esborrat d'aquesta pàgina
            throw new I18NException("genapp.comodi", "La pàgina " + (start + i + 1)
                + " del document adaptat i del document signat són diferents: "
                + adaptades[i].getAbsolutePath() + " != " + signats[i].getAbsolutePath());
          }
        } catch (IOException e) {
          // XYZ ZZZ TRA
          throw new I18NException("genapp.comodi",
              "No es poden llegir algunes de les imatges de la pàgina " + (start + i + 1)
                  + " del PDF: " + e.getMessage());
        }
      }

    } finally {
      clean(adaptades, esborrar);
      clean(signats, esborrar);
      // System.gc();
      adaptades = null;
      signats = null;
    }

    // 2 XYZ ZZZ Falta revisar Annexos que s'hagin mantingut
    List<AttachedFile> adaptatAdjunts = null;
    List<AttachedFile> firmatsAdjunts = null;
    try {

      InputStream is1 = null;
      InputStream is2 = null;
      try {
        is1 = adaptat.getInputStream();
        PdfReader reader = new PdfReader(is1);
        adaptatAdjunts = PdfUtils.extractAttachments(reader);
        reader.close();

        is2 = signed.getInputStream();
        PdfReader reader2 = new PdfReader(is2);
        firmatsAdjunts = PdfUtils.extractAttachments(reader2);
        reader2.close();
      } catch (Exception e) {
        // XYZ ZZZ TRA
        String msg = "Error desconegut intentant extreure adjunts d'un fitxer PDF: " + e.getMessage();
        log.error(msg, e);
        throw new I18NException("genapp.comodi", msg);
      }

      if (adaptatAdjunts.size() != firmatsAdjunts.size()) {
        // XYZ ZZZ TRA
        throw new I18NException("genapp.comodi",
            "El document original i el signat tenen diferent nombre d´adjunts [Orig: "
                + adaptatAdjunts.size() + "] [Signats: " + firmatsAdjunts.size() + "]");
      }

      // NOTA: Hi pot haver multiples fitxers amb el mateix nom i descripcio
      Map<String, List<AttachedFile>> originalsAdjuntsMap;
      originalsAdjuntsMap = new HashMap<String, List<AttachedFile>>();

      for (AttachedFile attachedFile : adaptatAdjunts) {
        String nom = attachedFile.getName();
        List<AttachedFile> list = originalsAdjuntsMap.get(nom);
        if (list == null) {
          list = new ArrayList<AttachedFile>();
          originalsAdjuntsMap.put(nom, list);
        }
        list.add(attachedFile);
      }

      for (AttachedFile attachedFile : firmatsAdjunts) {
        List<AttachedFile> adjuntsList = originalsAdjuntsMap.get(attachedFile.getName());

        if (adjuntsList == null) {
          // XYZ ZZZ TRA
          throw new I18NException("genapp.comodi",
              "El document signat conté un adjunt amb nom " + attachedFile.getName()
                  + " que no existeix en el document original.");
        }

        boolean trobat = false;
        for (AttachedFile af : adjuntsList) {
          if (compare(attachedFile, af)) {
            adjuntsList.remove(af);
            trobat = true;
            break;
          }
        }

        if (trobat) {
          if (adjuntsList.size() == 0) {
            originalsAdjuntsMap.remove(attachedFile.getName());
          }
        } else {
          // XYZ ZZZ TRA
          throw new I18NException("genapp.comodi",
              "El document adaptat i el signat conté un adjunt amb nom "
                  + attachedFile.getName()
                  + " però no coincideixen en la descripció o en el contingut.");
        }
      }

    } finally {
      // Falta Esborrar fitxers
      clean(adaptatAdjunts);
      clean(firmatsAdjunts);
    }

  }

  protected static boolean compare(AttachedFile af1, AttachedFile af2) {

    if (af1 == null) {
      if (af2 == null) {
        return true;
      } else {
        return false;
      }
    } else {
      if (af2 == null) {
        return false;
      } else {

        if (af1.getDescription().equals(af2.getDescription())) {
          try {
            if (FileUtils.contentEquals(af1.getContent(), af2.getContent())) {
              return true;
            }
          } catch (IOException e) {
            log.error("Error comparat contingut d'adjunts " + e.getMessage(),e);
            return false;
          }
        }
        return false;
      }

    }

  }

  protected static void clean(File[] images, int noesborrar) {
    if (images != null) {
      for (int i = 0; i < images.length; i++) {

        if (i != noesborrar) {
          clean(images[i]);
        }
        images[i] = null;

      }

    }
  }

  protected static void clean(List<AttachedFile> attachedFiles) {
    if (attachedFiles != null && attachedFiles.size() != 0) {

      for (AttachedFile attachedFile : attachedFiles) {
        if (attachedFile != null) {
          clean(attachedFile.getContent());
        }
      }

    }
  }

  protected static void clean(File file) {
    if (file != null) {
      if (!file.delete()) {
        // XYZ ZZZ ZZZ log.warn()
        System.err.println("No s'ha pogut esborrar el fitxer " + file.getAbsolutePath());
        file.deleteOnExit();
      }
    }
  }

  protected static File[] generateImagesOfPDF(String prefix, IPortaFIBDataSource original,
      File tmp, int start, int end) throws I18NException {

    InputStream is = null;
    try {

      is = original.getInputStream();

      final PDDocument document = PDDocument.load(is);

      PDFRenderer pdfRenderer = new PDFRenderer(document);

      int numberOfPages = document.getNumberOfPages() - end;

      File[] images = new File[numberOfPages - start];

      int count = 0;

      final int dpi = 50;
      for (int page = start; page < numberOfPages; ++page) {
        BufferedImage bim = pdfRenderer.renderImageWithDPI(page, dpi, ImageType.RGB);
        images[count] = new File(tmp, prefix + "_" + page + ".png");
        ImageIOUtil.writeImage(bim, images[count].getAbsolutePath(), dpi);
        count++;
      }
      document.close();

      return images;
    } catch (Exception e) {
      throw new I18NException("genapp.comodi", "Error generant imatges ");
    } finally {
      if (is != null) {
        try {
          is.close();
        } catch (Exception e2) {
          log.error("Error tancant stream.", e2);
        }
      }
    }

  }
}
