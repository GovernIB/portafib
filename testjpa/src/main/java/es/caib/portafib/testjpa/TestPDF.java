package es.caib.portafib.testjpa;

import com.itextpdf.text.pdf.PdfArray;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfObject;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfString;
import es.caib.portafib.logic.utils.PdfUtils;
import org.fundaciobit.genapp.common.i18n.I18NException;

import java.io.File;
import java.io.FilenameFilter;

public class TestPDF extends PdfUtils {

    public static void main(String[] args) {

        new TestPDF().main();
    }

    public void main() {

        File dir = new File(".");

        File[] files = dir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File file1, String s) {
                return s.startsWith("input") && s.endsWith(".pdf");
            }
        });

        if (files.length == 0) {
            System.out.println("Cap fitxer per processar. Ficau fitxers PDF amb el nom que comenci per 'input' i extensió '.pdf' dins el directori " + dir.getAbsolutePath());
            return;
        }

        for (File file : files) {
            processFile(file);
        }
    }

    protected void processFile(File fitxer) {
        System.out.println("---------------------------------------------------------");
        System.out.println("--- Processant " + fitxer.getName());
        System.out.println("---------------------------------------------------------");

        //List<AttachedFile> oldList = new ArrayList<AttachedFile>();
        //List<AttachedFile> newList = new ArrayList<AttachedFile>();

        String newName = System.currentTimeMillis() + "_" + fitxer.getName();
        File newFile = new File(newName);
        try {
            PdfUtils.add_TableSign_Attachments_CustodyInfo_PDF(fitxer, newFile, null, null, null, null, false);

            PdfReader reader = new PdfReader(fitxer.getAbsolutePath());

            PdfDictionary root = reader.getCatalog();
            PdfDictionary names = root.getAsDict(PdfName.NAMES);

            if (names != null) {
                PdfDictionary embedded = names.getAsDict(PdfName.EMBEDDEDFILES);
                if (embedded != null) {
                    PdfArray filespecs = embedded.getAsArray(PdfName.NAMES);

                    if (filespecs != null) {
                        for (int i = 0; i < filespecs.size(); ) {
                            PdfString name = filespecs.getAsString(i++);
                            PdfDictionary filespecDict = filespecs.getAsDict(i++);
                            log.info("-----------------------------------------------------------");
                            log.info(name.toUnicodeString() + ": " + filespecDict.toString());
                            log.info("-----------------------------------------------------------");

                            for (PdfName key : filespecDict.getKeys()) {
                                PdfObject directObject = filespecDict.getDirectObject(key);
                                log.info(key + ": " + directObject.getClass() + ": " + directObject);
                            }
                            log.info("-----------------------------------------------------------");

                            PdfDictionary efDict = filespecDict.getAsDict(PdfName.EF);
                            for (PdfName key : efDict.getKeys()) {
                                PdfString stringObject = efDict.getAsString(key);
                                PdfObject directObject = efDict.getDirectObject(key);
                                log.info(key + ": " + stringObject);
                                log.info(key + ": " + directObject.getClass() + ": " + directObject);
                            }
                            log.info("-----------------------------------------------------------");
                        }
                    }
                }
            }

        } catch (I18NException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println(newFile.getAbsolutePath());

        /*
        String newName = System.currentTimeMillis() + "_" + fitxer.getName();
        if (!newFile.renameTo(new File(newName)))
            throw new RuntimeException("No s'ha pogut renombrar");

         */
        System.out.println("Guardat a " + newName);
    }
}