package es.caib.portafib.back.utils;

import es.caib.portafib.utils.ConstantsV2;
import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Crea un Zip dins un fitxer temporal que després pot ser transferit
 */
public class ZipProducer {

    private final Logger log = Logger.getLogger(getClass());

    final ZipOutputStream zipOutputStream;
    final File zipFile;

    final Map<String, Integer> entryNameOcurrences = new HashMap<String, Integer>();

    // Amb Java 1.6, els Zips no soporten codificacions de caràcters més enllà de Ascii.
    final SafeCharsetEncoder encoder = SafeCharsetEncoder.getInstance(ConstantsV2.US_ASCII);

    boolean closed = false;

    public static ZipProducer getInstance() throws IOException {
        return new ZipProducer(null);
    }

    public static ZipProducer getInstance(File tempDirectory) throws IOException {
        if (!tempDirectory.isDirectory()) {
            throw new IllegalArgumentException("tempDirectory ha de ser un directori");
        }

        return new ZipProducer(tempDirectory);
    }

    private ZipProducer(File tempDirectory) throws IOException {
        zipFile = File.createTempFile("zip", ".zip", tempDirectory);
        zipOutputStream = new ZipOutputStream(new FileOutputStream(zipFile));
    }

    public void addEntry(String entryName, File file) throws IOException {
        failIfClosed();

        String safeEntryName = encoder.encode(entryName);
        String entryNameWithCounter = updateWithOcurrences(safeEntryName);

        FileInputStream fis = new FileInputStream(file);
        try {
            ZipEntry zipEntry = new ZipEntry(entryNameWithCounter);
            zipOutputStream.putNextEntry(zipEntry);
            FileSystemManager.copy(fis, zipOutputStream);
            zipOutputStream.closeEntry();
        } finally {
            // Asseguram que tancam el fitxer que hem obert aquí.
            closeSilent(fis);
        }
    }

    private String updateWithOcurrences(String entryName) {
        int ocurrences = updateEntryNameOcurrences(entryName);
        if (ocurrences > 1) {
            String ocurrencesAppend = "_" + (ocurrences - 1);
            int lastPointIndex = entryName.lastIndexOf('.');
            if (lastPointIndex == -1) {
                entryName = entryName + ocurrencesAppend;
            } else {
                String prefix = entryName.substring(0, lastPointIndex);
                String suffix = entryName.substring(lastPointIndex);
                entryName = prefix + ocurrencesAppend + suffix;
            }
        }

        return entryName;
    }

    private int updateEntryNameOcurrences(String entryName) {
        Integer ocurrences = entryNameOcurrences.get(entryName);
        if (ocurrences == null) {
            entryNameOcurrences.put(entryName, 1);
            return 1;
        } else {
            int newOcurrences = ocurrences + 1;
            entryNameOcurrences.put(entryName, newOcurrences);
            return newOcurrences;
        }
    }

    public void transferTo(OutputStream outputStream) throws IOException {
        failIfClosed();
        close();

        FileInputStream inputStream = new FileInputStream(zipFile);
        try {
            FileSystemManager.copy(inputStream, outputStream);
        } finally {
            // Asseguram que tancam el fitxer que hem obert aquí.
           closeSilent(inputStream);
        }
    }

    private void failIfClosed() throws IllegalStateException {
        if (closed) {
            throw new IllegalStateException("Operació no permesa quan el ZipProducer ja està tancat");
        }
    }

    private void closeSilent(Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException e) {
            log.warn("Error ignorat tancant recurs", e);
        }
    }

    private void close() {
        if (!closed) {
            try {
                zipOutputStream.flush();
            } catch (IOException io) {
                log.error("Error ignorat fent flush del ZipOutputStream", io);
            } finally {
                closeSilent(zipOutputStream);
            }
            closed = true;
        }
    }

    /**
     * Sempre cridar aquest mètode dins el finally d'un try, per assegurar que s'esborra el fitxer temporal.
     */
    public void cleanUp() {
        close();
        if (zipFile.exists()) {
            if (!zipFile.delete()) {
                zipFile.deleteOnExit();
            }
        }
    }

    /**
     * Els fitxers Zip poden ser molt grossos. Miram de garantir que s'esborrin.
     */
    @Override
    protected void finalize() {
        cleanUp();
    }
}
