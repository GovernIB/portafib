package es.caib.portafib.logic.utils;

import com.itextpdf.text.pdf.PdfReader;

import de.redsix.pdfcompare.AbstractCompareResultWithSwap;
import de.redsix.pdfcompare.ImageWithDimension;
import de.redsix.pdfcompare.PageDiffCalculator;
import es.caib.portafib.logic.utils.datasource.IPortaFIBDataSource;
import es.caib.portafib.utils.ConstantsV2;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.jboss.logging.Logger;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
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
     * @author anadal
     * 23 oct 2025 11:33:42
     */
    public static class CompareResultImplDisk extends AbstractCompareResultWithSwap {
        //CompareResultWithMemoryOverflow   CompareResultWithPageOverflow
        /* AbstractCompareResultWithSwap */

        protected final int maxDiffPages;

        protected final boolean debug;

        private boolean stop = false;

        protected int count = 0;

        public CompareResultImplDisk(int maxDiffPages, boolean debug) {
            super();
            this.maxDiffPages = maxDiffPages;
            this.debug = debug;
        }

        @Override
        public synchronized void addPage(final PageDiffCalculator diffCalculator, final int pageIndex,
                final ImageWithDimension expectedImage, final ImageWithDimension actualImage,
                final ImageWithDimension diffImage) {

            if (!stop) {
                super.addPage(diffCalculator, pageIndex, expectedImage, actualImage, diffImage);
            }

        }

        @Override
        protected boolean needToSwap() {

            /*
            count++;
            
            if (count % 10 == 0) {
                System.out.println(" - " + count + " - ");
            }
            return super.needToSwap();
            */

            diffImages.clear();

            count++;

            if (debug) {

                if (count % 5 == 0) {
                    log.info(" - " + count + " - ");
                }
            }

            if (getDifferences().size() > maxDiffPages) {
                if (debug) {
                    String msg = "Ja hi ha massa diferències. Indicam que hem d'aturar";
                    log.info(msg);

                }
                this.stop = true;
                //this.noPagesFound();
                //throw new CompareException("Massa diferències");
                return true;
            }

            if (count % 5 == 0) {
                System.gc();
            }
            return true;

        }

    }

    /**
     * 
     * @param adaptat
     * @param signed
     * @param tmpDir
     * @param posTaulaDeFirmes
     * @throws I18NException
     */
    public static void compare(IPortaFIBDataSource adaptat, IPortaFIBDataSource signed, File tmpDir,
            int posTaulaDeFirmes, boolean validateChangesInAttachedFiles, boolean debug) throws I18NException {

        int maxDiffPages;
        if (posTaulaDeFirmes == TAULADEFIRMES_SENSETAULA) {
            maxDiffPages = 0;
        } else if (posTaulaDeFirmes == TAULADEFIRMES_PRIMERAPAGINA || posTaulaDeFirmes == TAULADEFIRMES_DARRERAPAGINA) {
            maxDiffPages = 1;
        } else {
            // XYZ ZZZ TRA
            throw new I18NException("genapp.comodi", "Posició de Taula de Firmes Desconeguda: " + posTaulaDeFirmes);
        }

        {

            if (debug) {
                printMemory("(A) Abans de PdfComparator: ");
            }

            de.redsix.pdfcompare.PdfComparator<CompareResultImplDisk> pc;

            CompareResultImplDisk comp = new CompareResultImplDisk(maxDiffPages, debug);
            pc = new de.redsix.pdfcompare.PdfComparator<>(adaptat.getInputStream(), signed.getInputStream(), comp);

            try {
                comp = pc.compare();
            } catch (Exception e) {
                throw new I18NException("genapp.comodi",
                        "Error intentant comparar el document adaptat i del document signat: " + e.getMessage());
            }

            Collection<Integer> pages = comp.getPagesWithDifferences();
            // Sempre hi haurà una pàgina diferent que serà la primera o la darrera,
            // En el cas de que n'hi hagi més d'una, significa que hi ha més diferències
            if (pages.size() > maxDiffPages) {
                throw new I18NException("genapp.comodi",
                        "Hi ha diferències entre el document adaptat i del document signat: "
                                + comp.getDifferencesJson());
            }

            if (debug) {
                printMemory("(A) Despres de PdfComparator: ");
            }

        }

        if (debug) {
            log.info("validateChangesInAttachedFiles = " + validateChangesInAttachedFiles);
        }

        if (validateChangesInAttachedFiles) {
            // XYZ ZZZ Falta revisar Annexos que s'hagin mantingut
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
                        throw new I18NException("genapp.comodi", "El document signat conté un adjunt amb nom "
                                + attachedFile.getName() + " que no existeix en el document original.");
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
                                "El document adaptat i el signat conté un adjunt amb nom " + attachedFile.getName()
                                        + " però no coincideixen en la descripció o en el contingut.");
                    }
                }

            } finally {
                // Falta Esborrar fitxers
                clean(adaptatAdjunts);
                clean(firmatsAdjunts);
            }
        }

    }

    protected static void printMemory(String titol) {
        Runtime runtime = Runtime.getRuntime();
        log.info(titol + "Memòria  " + runtime.freeMemory() / (1024 * 1024) + "/"
                + runtime.totalMemory() / (1024 * 1024));

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
                        log.error("Error comparat contingut d'adjunts " + e.getMessage(), e);
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
                log.warn("No s'ha pogut esborrar el fitxer " + file.getAbsolutePath());
                file.deleteOnExit();
            }
        }
    }

    protected static File[] generateImagesOfPDF(String prefix, IPortaFIBDataSource original, File tmp, int start,
            int end) throws I18NException {

        InputStream is = null;
        try {

            is = original.getInputStream();

            final PDDocument document = Loader.loadPDF(IOUtils.toByteArray(is));

            PDFRenderer pdfRenderer = new PDFRenderer(document);

            int numberOfPages = document.getNumberOfPages() - end;

            File[] images = new File[numberOfPages - start];

            int count = 0;

            final int dpi = 50;
            long s;
            for (int page = start; page < numberOfPages; ++page) {

                s = System.currentTimeMillis();
                BufferedImage bim = pdfRenderer.renderImageWithDPI(page, dpi, ImageType.RGB);
                //BufferedImage bim = pdfRenderer.renderImage(page);

                log.info(" RENDER IMAGE[" + page + "] " + (System.currentTimeMillis() - s) + " ms");

                images[count] = new File(tmp, prefix + "_" + page + ".png");
                s = System.currentTimeMillis();

                ImageIOUtil.writeImage(bim, images[count].getAbsolutePath(), dpi);
                log.info(" WRITE IMAGE[" + page + "]" + (System.currentTimeMillis() - s) + " ms");
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
