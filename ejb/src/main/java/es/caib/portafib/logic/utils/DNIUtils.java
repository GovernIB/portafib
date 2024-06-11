package es.caib.portafib.logic.utils;

import org.fundaciobit.pluginsib.core.v3.utils.CertificateUtils;
import org.fundaciobit.pluginsib.core.v3.utils.DNIExtractor;

import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DNIUtils {

    public static String getDNI(X509Certificate certificate) {

        String dniPattern = PropietatGlobalUtil.getDniPattern();
        if (dniPattern == null || dniPattern.trim().isEmpty()) {
            return CertificateUtils.getDNI(certificate);
        }

        String[] patterns = dniPattern.split("[\r\n]+");
        List<String> patternsList = new ArrayList<String>(Arrays.asList(patterns));
        patternsList.add(DNIExtractor.DEFAULT_DNI_PATTERN);

        return CertificateUtils.getDNI(certificate, patternsList);
    }
}
