package es.caib.portafib.back.utils;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Crea un Zip dins un fitxer temporal que després pot ser transferit
 */
public class ZipProducer {

    private final Logger log = Logger.getLogger(getClass());

    final ZipArchiveOutputStream zipOutputStream;
    final File zipFile;

    final Map<String, Integer> entryNameOcurrences = new HashMap<String, Integer>();

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
        zipOutputStream = new ZipArchiveOutputStream(zipFile);
    }

    public void addEntry(String entryName, File file) throws IOException {
        failIfClosed();
        String entryNameWithCounter = updateWithOcurrences(entryName);

        FileInputStream fis = new FileInputStream(file);
        try {
            ArchiveEntry entry = zipOutputStream.createArchiveEntry(file, entryNameWithCounter);
            zipOutputStream.putArchiveEntry(entry);
            IOUtils.copy(fis, zipOutputStream);
            zipOutputStream.closeArchiveEntry();
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
            IOUtils.copy(inputStream, outputStream);
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
                zipOutputStream.finish();
            } catch (IOException io) {
                log.error("Error ignorat fent finish del ZipArchiveOutputStream", io);
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
