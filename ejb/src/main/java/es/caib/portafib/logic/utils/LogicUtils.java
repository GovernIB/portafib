package es.caib.portafib.logic.utils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.portafib.commons.utils.Configuracio;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.Where;

/**
 * 
 * @author anadal(u80067)
 * @author areus
 *
 */
public class LogicUtils {

    protected static Logger log = Logger.getLogger(LogicUtils.class);

    public static boolean checkExpectedNif(String nifPersonaFirmant, String expectedNif, String nifEmpresaFirmant,
            String expectedCif) throws I18NException {
        if (nifPersonaFirmant == null) {
            /* S'ha firmat amb un certificat que no té associat cap nif, però es requeria el nif {0} */
            final String codeError = "error.no_nif_en_certificat";
            if (Configuracio.isDesenvolupament()) {
                // Només mostram l'error pel LOG
                // TODO S'ha de crear un idioma per defecte dins configuracio
                log.error(I18NLogicUtils.tradueix(new Locale("ca"), codeError), new Exception());
            } else {
                throw new I18NException(codeError);
            }
        } else {

            if (expectedNif.trim().equalsIgnoreCase(nifPersonaFirmant.trim())) {

                // OK, Només si el NIF de la persona coincideix amb el NIF esperat
                // i no s'ha de comprovar el CIF, per tant no es llença cap error
                if (nifEmpresaFirmant == null && expectedCif == null) {
                    return true;
                } else {
                    return false;
                }

            } else {

                // En cas que el nif esperat no coincideixi amb el nif de la persona firmant, comprovam si el nif de
                // l'empresa coincideix amb el nif esperat. 

                // Comprovam a veure si el NIF esperat és el de l'empresa
                if (nifEmpresaFirmant != null && expectedNif.trim().equalsIgnoreCase(nifEmpresaFirmant.trim())) {

                    // OK, el NIF de l'empresa coincideix amb el NIF esperat, per tant no es llença cap error
                    return true;

                } else {

                    log.error("checkExpectedNif::nifPersonaFirmant: " + nifPersonaFirmant);
                    log.error("checkExpectedNif::expectedNif: " + expectedNif);
                    log.error("checkExpectedNif::nifEmpresaFirmant: " + nifEmpresaFirmant);

                    // =S´ha firmat amb un certificat on el nif associat és {0}, però es requeria el nif  {1}
                    final String codeError = "error.firmat_amb_nif_incorrecte";
                    throw new I18NException(codeError, nifPersonaFirmant, expectedNif);
                }
            }
        }
        return false;
    }

    public static void checkExpectedCif(String cifFirmant, String expectedCif, String nifPersonaFirmant)
            throws I18NException {

        if (expectedCif == null) {
            // Només hem de comprovar que el CIF del certificat sigui null, sinó es llença un error ja que en aquest cas 
            // s'esperava un certificat personal i s'ha utitlitzat un certificat de persona JURIDICA
            if (cifFirmant != null) {
                log.error("checkExpectedCif::cifFirmant: " + cifFirmant);
                log.error("checkExpectedCif::expectedCif: " + expectedCif);
                log.error("checkExpectedCif::nifPersonaFirmant: " + nifPersonaFirmant);

                // Per a la firma, s´esperava l´ús d´un certificat personal associat al NIF {0} però 
                // el certificat usat per firmar està associat l´empresa amb NIF {1}
                final String codeError = "error.no_cif_expected_but_cif_in_certificate";
                throw new I18NException(codeError, nifPersonaFirmant, cifFirmant);
            }
        } else {

            if (cifFirmant == null) {
                final String codeError = "error.no_cif_en_certificat";
                if (Configuracio.isDesenvolupament()) {
                    // Només mostram l'error pel LOG
                    // TODO S'ha de crear un idioma per defecte dins configuracio
                    log.error(I18NLogicUtils.tradueix(new Locale("ca"), codeError), new Exception());
                } else {
                    throw new I18NException(codeError);
                }
            } else {

                if (!expectedCif.trim().equalsIgnoreCase(cifFirmant)) {
                    // =S´ha firmat amb un certificat on el cif associat és {0}, però es requeria el cif
                    // {1}
                    final String codeError = "error.firmat_amb_cif_incorrecte";
                    if (Configuracio.isDesenvolupament()) {
                        // Només mostram l'error pel LOG
                        // TODO S'ha de crear un idioma per defecte dins configuracio
                        log.error(I18NLogicUtils.tradueix(new Locale("ca"), codeError, cifFirmant, expectedCif),
                                new Exception());
                    } else {
                        throw new I18NException(codeError, cifFirmant, expectedCif);
                    }
                }
            }
        }
    }

    public static File sobreescriureFitxerChecked(File src, Long fitxerID) throws IOException {

        if (!src.exists()) {
            String msg = "El fitxer origen [" + src.getAbsolutePath() + "] no existeix";
            log.error(msg);
            throw new IOException(msg);
        }

        // Movem el fitxer
        final long srcLength = src.length();
        File dest = FileSystemManager.sobreescriureFitxer(src, fitxerID);
        if (!dest.exists()) {
            String msg = "El fitxer resultant [" + dest.getAbsolutePath() + "] no existeix. (Fitxer origen "
                    + src.getAbsolutePath() + ")";
            log.error(msg);
            throw new IOException(msg);
        }

        if (dest.length() != srcLength) {
            String msg = "La mida del fitxer destí després de fer el rename [" + dest.getAbsolutePath()
                    + "] és diferent a la del fitxer original";
            log.error(msg);
            throw new IOException(msg);
        }

        if (src.exists()) {
            log.warn("El fitxer origen [" + src.getAbsolutePath() + "] encara existeix");
            if (!src.delete()) {
                log.warn("El fitxer origen [" + src.getAbsolutePath() + "] no s'ha pogut esborrar");
                src.deleteOnExit();
            }
        }

        return dest;
    }

    /**
     * Workaround per https://github.com/GovernIB/genapp/issues/39
     * Crea una Where de tipus IN dividida en blocs de màxim 1000.
     * @param field camp a emprar
     * @param values llista de valors a comprovar dins l'IN
     * @return un where que el camp field està dins values.
     */
    public static <C> Where getSafeWhereIn(Field<C> field, List<C> values) {
        final int MAX_IN_SIZE = 1000;
        Where whereIn;
        if (values.size() < MAX_IN_SIZE) {
            whereIn = field.in(values);
        } else {
            int iterations = ((values.size() - 1) / MAX_IN_SIZE) + 1;
            Where[] wheresIn = new Where[iterations];
            for (int i = 0; i < iterations; i++) {
                int firstIndex = i * MAX_IN_SIZE;
                int lastIndex = Math.min(firstIndex + MAX_IN_SIZE, values.size());
                wheresIn[i] = field.in(values.subList(firstIndex, lastIndex));
            }
            whereIn = Where.OR(wheresIn);
        }
        return whereIn;
    }
}
