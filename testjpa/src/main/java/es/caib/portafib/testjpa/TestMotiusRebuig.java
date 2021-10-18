package es.caib.portafib.testjpa;

import es.caib.portafib.persistence.PortaFIBJPADaoManagers;
import es.caib.portafib.model.PortaFIBDaoManager;
import es.caib.portafib.model.dao.IEstatDeFirmaManager;
import es.caib.portafib.model.entity.EstatDeFirma;
import es.caib.portafib.model.fields.EstatDeFirmaFields;
import es.caib.portafib.utils.ConstantsV2;
import org.fundaciobit.genapp.common.i18n.I18NCommonUtils;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * @author areus
 */
public class TestMotiusRebuig {

    public static void main(String[] args) {
        I18NCommonUtils.BUNDLES = new String[] {"missatges"};
        new TestMotiusRebuig().main();
    }

    private void main() {
        try {
            System.out.println("Test motius rebuig");

            Properties prop = new Properties();
            prop.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
            prop.put("javax.persistence.jdbc.driver", "org.postgresql.Driver");
            prop.put("javax.persistence.jdbc.url", "jdbc:postgresql://localhost:5432/portafib2");
            prop.put("javax.persistence.jdbc.user", "portafib");
            prop.put("javax.persistence.jdbc.password", "portafib");

            prop.put("hibernate.connection.driver_class", "org.postgresql.Driver");
            prop.put("hibernate.connection.url", "jdbc:postgresql://localhost:5432/portafib2");
            prop.put("hibernate.connection.username", "portafib");
            prop.put("hibernate.connection.password", "portafib");

            //prop.put("hibernate.show_sql", "true");
            //prop.put("hibernate.format_sql", "true");

            EntityManagerFactory emf;

            // Veure persistence.xml
            emf = Persistence.createEntityManagerFactory("portafibDBStandalone", prop);

            EntityManager em = emf.createEntityManager();
            PortaFIBDaoManager.setDaoManagers(new PortaFIBJPADaoManagers(em));

            IEstatDeFirmaManager estatDeFirmaManager = PortaFIBDaoManager.getDaoManagers().getEstatDeFirmaManager();

            String usuariEntitatID = "fundaciobit_pruebas";

            List<EstatDeFirma> estatsDeFirma = estatDeFirmaManager.select(EstatDeFirmaFields.USUARIENTITATID.equal(usuariEntitatID));
            long startTime;


            getRebuigDescriptionByEstat(estatsDeFirma);
            getRebuigDescriptionByEstat2(estatsDeFirma);
            getRebuigDescriptionByEstat(estatsDeFirma);
            getRebuigDescriptionByEstat2(estatsDeFirma);

            for (int i = 0; i < 10; i++) {

                startTime = System.nanoTime();
                Map<Long, String> rebuigDescriptionByEstat = getRebuigDescriptionByEstat(estatsDeFirma);
                System.out.println("v1 Time: " + TimeUnit.NANOSECONDS.toMicros(System.nanoTime() - startTime) + "mc");

                /*
                for (Map.Entry<Long, String> entry : rebuigDescriptionByEstat.entrySet()) {
                    System.out.println(entry.getKey() + ": " + entry.getValue());
                }*/

                startTime = System.nanoTime();
                Map<Long, String> rebuigDescriptionByEstat2 = getRebuigDescriptionByEstat2(estatsDeFirma);
                System.out.println("v2 Time: " + TimeUnit.NANOSECONDS.toMicros(System.nanoTime() - startTime) + "mc");

                /*
                for (Map.Entry<Long, String> entry : rebuigDescriptionByEstat2.entrySet()) {
                    System.out.println(entry.getKey() + ": " + entry.getValue());
                }*/
            }


        } catch (I18NException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Map<Long, String> getRebuigDescriptionByEstat2(List<EstatDeFirma> estatsDeFirma)
            throws I18NException, Exception {

        final Map<Long, Long> firma2estat = new HashMap<Long, Long>();
        final Map<Long, StringBuilder> motiuBuilderByEntitat = new HashMap<Long, StringBuilder>();
        for (EstatDeFirma estatDeFirma: estatsDeFirma) {
            if (estatDeFirma.getTipusEstatDeFirmaFinalID() == null) {
                firma2estat.put(estatDeFirma.getFirmaID(), estatDeFirma.getEstatDeFirmaID());
                motiuBuilderByEntitat.put(estatDeFirma.getEstatDeFirmaID(), new StringBuilder());
            }
        }

        // Cercarem tots els estats de firma associats a la mateixa firma
        // i que estiguin invalidats
        // (1) Invalidats
        Where w1 = EstatDeFirmaFields.TIPUSESTATDEFIRMAFINALID.equal(ConstantsV2.TIPUSESTATDEFIRMAFINAL_INVALIDAT);
        // (2) Amb la mateix firma
        Where w2 = EstatDeFirmaFields.FIRMAID.in(firma2estat.keySet());

        IEstatDeFirmaManager estatDeFirmaManager = PortaFIBDaoManager.getDaoManagers().getEstatDeFirmaManager();
        List<EstatDeFirma> estatsRebuig = estatDeFirmaManager.select(Where.AND(w1, w2));

        for (EstatDeFirma estatRebuig: estatsRebuig) {
            final StringBuilder motius = motiuBuilderByEntitat.get(firma2estat.get(estatRebuig.getFirmaID()));
            if (motius.length() > 0) {
                motius.append(' ');
            }
            motius.append(estatRebuig.getDescripcio());
        }

        final Map<Long, String> rebuigDescriptionByEstat = new HashMap<Long, String>(motiuBuilderByEntitat.size());
        for (Map.Entry<Long, StringBuilder> entry: motiuBuilderByEntitat.entrySet()) {
            rebuigDescriptionByEstat.put(entry.getKey(), entry.getValue().toString());
        }

        return rebuigDescriptionByEstat;
    }

    public Map<Long, String> getRebuigDescriptionByEstat(List<EstatDeFirma> estatDeFirmaList)
            throws I18NException, Exception {

        IEstatDeFirmaManager estatDeFirmaManager = PortaFIBDaoManager.getDaoManagers().getEstatDeFirmaManager();

        Map<Long, String> rebuigDescriptionByEstat = new HashMap<Long, String>();

        for (EstatDeFirma estatDeFirma : estatDeFirmaList) {

            if (estatDeFirma.getTipusEstatDeFirmaFinalID() == null) {
                // Cercarem tots els estats de firma associats a la mateixa firma
                // i que estiguin invalidats
                // (1) Invalidats
                Where w1 = EstatDeFirmaFields.TIPUSESTATDEFIRMAFINALID.equal(ConstantsV2.TIPUSESTATDEFIRMAFINAL_INVALIDAT);
                // (2) Amb la mateix firma
                Where w2 = EstatDeFirmaFields.FIRMAID.equal(estatDeFirma.getFirmaID());
                List<String> motiuList = estatDeFirmaManager.executeQuery(EstatDeFirmaFields.DESCRIPCIO, Where.AND(w1, w2));

                StringBuilder motius = new StringBuilder();
                for (String m : motiuList) {
                    if (motius.length() != 0) {
                        motius.append(' ');
                    }
                    motius.append(m);
                }
                rebuigDescriptionByEstat.put(estatDeFirma.getEstatDeFirmaID(), motius.toString());
            }
        }

        return rebuigDescriptionByEstat;
    }
}
