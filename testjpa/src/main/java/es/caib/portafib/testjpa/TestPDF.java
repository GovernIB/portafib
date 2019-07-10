package es.caib.portafib.testjpa;

import es.caib.portafib.logic.utils.AttachedFile;
import es.caib.portafib.logic.utils.PdfUtils;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

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

        List<AttachedFile> oldList = new ArrayList<AttachedFile>();
        List<AttachedFile> newList = new ArrayList<AttachedFile>();
        File newFile;
        try {
            newFile = PdfUtils.forceCleanPdfInternal(newList, fitxer, oldList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println(newFile.getAbsolutePath());

        String newName = System.currentTimeMillis() + "_" + fitxer.getName();
        if (!newFile.renameTo(new File(newName)))
            throw new RuntimeException("No s'ha pogut renombrar");
        System.out.println("Guardat a " + newName);
    }
}