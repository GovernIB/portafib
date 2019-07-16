package es.caib.portafib.testjpa;

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
            //newFile = PdfUtils.forceCleanPdfInternal(newList, fitxer, oldList);

            PdfUtils.add_TableSign_Attachments_CustodyInfo_PDF(fitxer, newFile, null, null, null, null, true, false);
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