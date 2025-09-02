package es.caib.portafib.logic.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.cert.X509Certificate;

import org.apache.commons.io.FileUtils;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pluginsib.core.v3.utils.CertificateUtils;

import es.caib.portafib.logic.ValidacioCompletaFirmaLogicaEJB;
import es.caib.portafib.logic.utils.datasource.ByteArrayDataSource;
import es.caib.portafib.logic.utils.datasource.IPortaFIBDataSource;

public class Esborrar {
    public static void main(String[] args) {
        try {
            
            //testGetDni();
            
            lastCertificateTest();
            
            

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    protected static void lastCertificateTest() throws I18NException, IOException {
        File f = new File("./Rossi-PNOEs-signat.pdf");
        
        
        
        IPortaFIBDataSource ds = new ByteArrayDataSource(FileUtils.readFileToByteArray(f));
        
        
        X509Certificate cert = ValidacioCompletaFirmaLogicaEJB.getLastCertificateOfSignedPdf(ds, 1, 1);
        
        System.out.println("\n\n\nNIF ===> ]" + CertificateUtils.getDNI(cert) + "[");
    }

    protected static void testGetDni() throws IOException, Exception, FileNotFoundException {
        File propertiesFile= new File("tmp.properties");
        propertiesFile.createNewFile();
        
        System.setProperty("es.caib.portafib.properties", propertiesFile.getAbsolutePath());
        

        File f = new File(
                "D:\\dades\\dades\\CarpetesPersonals\\ProgramacioPortaFIB3\\"
                + "pluginsib-validatecertificate-3.0\\certificats\\CAIB\\1048720.crt");

        System.out.println(f.exists());

        X509Certificate cert = CertificateUtils.decodeCertificate(new FileInputStream(f));
        
        //System.out.println("\n\n\nNIF ===> ]" + DNIUtils.getDNI(cert) + "[");
        
        System.out.println("\n\n\nNIF ===> ]" + CertificateUtils.getDNI(cert) + "[");
    }
}
