package es.caib.portafib.logic.passarela;

import javax.activation.FileDataSource;
import java.io.File;

/**
 * Un {@link FileDataSource} temporal, que quan es processat per el GC borra el fitxer amb el que s'ha construit.
 */
public class TemporaryFileDataSource extends FileDataSource {

    public TemporaryFileDataSource(File file) {
        super(file);
    }

    @Override
    protected void finalize() {
        File file = getFile();
        if (file != null && file.exists() && !file.delete()) {
             file.deleteOnExit();
        }
    }
}
