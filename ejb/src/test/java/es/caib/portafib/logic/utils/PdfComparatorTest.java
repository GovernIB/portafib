package es.caib.portafib.logic.utils;

import java.io.File;

import org.jboss.logging.Logger;
import es.caib.portafib.logic.utils.datasource.FileDataSource;
import es.caib.portafib.logic.utils.datasource.IPortaFIBDataSource;
import es.caib.portafib.utils.ConstantsV2;

/**
 * 
 * @author anadal
 * 9 oct 2024 9:32:32
 */
public class PdfComparatorTest {
    protected static final Logger log = Logger.getLogger(PdfComparatorTest.class);

    public static void main(String[] args) {

        log.info("PdfComaparatorTest");

        try {
            File o = new File("prova_massiu (22).pdf");
            File s = new File("prova_massiu (22)-signat.pdf"); // "testHorizontal_aa_horitzontal.pdf");

            IPortaFIBDataSource adaptat = new FileDataSource(o);
            IPortaFIBDataSource signed = new FileDataSource(s);

            File tmpDir = new File("d:\\tmp\\images");
            boolean validateChangesInAttachedFiles = true;
            final int posTaulaDeFirmes = ConstantsV2.TAULADEFIRMES_SENSETAULA;

            long start = System.currentTimeMillis();
            PdfComparator.compare(adaptat, signed, tmpDir, posTaulaDeFirmes, validateChangesInAttachedFiles);

            log.info(" TOTAL: " + (System.currentTimeMillis() - start) + " ms");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
