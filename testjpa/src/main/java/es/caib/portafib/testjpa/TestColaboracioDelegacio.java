package es.caib.portafib.testjpa;

import es.caib.portafib.jpa.PortaFIBJPADaoManagers;
import es.caib.portafib.model.PortaFIBDaoManager;
import es.caib.portafib.model.dao.IEstatDeFirmaManager;
import es.caib.portafib.model.entity.EstatDeFirma;
import es.caib.portafib.model.fields.EstatDeFirmaFields;
import es.caib.portafib.utils.ConstantsV2;
import org.fundaciobit.genapp.common.i18n.I18NCommonUtils;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.GroupByValueItem;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.SelectGroupByAndCountForField;
import org.fundaciobit.genapp.common.query.Where;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * @author areus
 */
public class TestColaboracioDelegacio {

    public static void main(String[] args) {
        I18NCommonUtils.BUNDLES = new String[] {"missatges"};
        new TestColaboracioDelegacio().main();
    }

    private void main() {
        try {
            System.out.println("Test col·laboració delegació");


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

            final Long[] ESTATS_INICIALS_COLA = new Long[] {
                    ConstantsV2.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_VALIDAR,
                    ConstantsV2.TIPUSESTATDEFIRMAINICIAL_REVISANT_PER_VALIDAR };

            List<EstatDeFirma> estatsDeFirma = estatDeFirmaManager.select(EstatDeFirmaFields.USUARIENTITATID.equal(usuariEntitatID));
            long startTime;

            infoColaboradorsDelegats(usuariEntitatID, estatsDeFirma, ESTATS_INICIALS_COLA);
            infoColaboradorsDelegats2(usuariEntitatID, estatsDeFirma, ESTATS_INICIALS_COLA, em);

            infoColaboradorsDelegats(usuariEntitatID, estatsDeFirma, ESTATS_INICIALS_COLA);
            infoColaboradorsDelegats2(usuariEntitatID, estatsDeFirma, ESTATS_INICIALS_COLA, em);

            for (int i = 0; i < 10; i++) {

                startTime = System.nanoTime();
                Map<Long, int[]> infoColaboradorsDelegats = infoColaboradorsDelegats(usuariEntitatID, estatsDeFirma, ESTATS_INICIALS_COLA);
                System.out.println("v1 Time: " + TimeUnit.NANOSECONDS.toMicros(System.nanoTime() - startTime) + "mc");

                /*
                for (Map.Entry<Long, int[]> entry : infoColaboradorsDelegats.entrySet()) {
                    System.out.println(entry.getKey() + ": " + Arrays.toString(entry.getValue()));
                }*/

                startTime = System.nanoTime();
                Map<Long, int[]> infoColaboradorsDelegats2 = infoColaboradorsDelegats2(usuariEntitatID, estatsDeFirma, ESTATS_INICIALS_COLA, em);
                System.out.println("v2 Time: " + TimeUnit.NANOSECONDS.toMicros(System.nanoTime() - startTime) + "mc");

                /*
                for (Map.Entry<Long, int[]> entry : infoColaboradorsDelegats2.entrySet()) {
                    System.out.println(entry.getKey() + ": " + Arrays.toString(entry.getValue()));
                }*/
            }


        } catch (I18NException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private Map<Long, int[]> infoColaboradorsDelegats2(String usuariEntitatID, List<EstatDeFirma> estatsDeFirma,
                                                      Long[] estatsInicials, EntityManager em) {
        final int LENGTH = ConstantsV2.TIPUSESTATDEFIRMAFINAL.length + 2;
        final Map<Long, Long> firma2estat = new HashMap<Long, Long>(estatsDeFirma.size());
        final Map<Long, int[]> infoColaboradorsDelegats = new HashMap<Long, int[]>(estatsDeFirma.size());
        for (EstatDeFirma estatDeFirma: estatsDeFirma) {
            firma2estat.put(estatDeFirma.getFirmaID(), estatDeFirma.getEstatDeFirmaID());
            infoColaboradorsDelegats.put(estatDeFirma.getEstatDeFirmaID(), new int[LENGTH]);
        }

        Query query = em.createQuery(
                "select count(e), e.firmaID, e.tipusEstatDeFirmaFinalID " +
                "from EstatDeFirmaJPA e " +
                "where e.firmaID in (:idsFirma) " +
                "  and e.usuariEntitatID <> :usuariEntitat " +
                "  and e.tipusEstatDeFirmaInicialID in (:estatsInicials) " +
                "group by e.firmaID, e.tipusEstatDeFirmaFinalID ");
                //"order by e.firmaID, e.tipusEstatDeFirmaFinalID ");
        query.setParameter("idsFirma", firma2estat.keySet());
        query.setParameter("usuariEntitat", usuariEntitatID);
        query.setParameter("estatsInicials", Arrays.asList(estatsInicials));

        List<Object[]> resultList = (List<Object[]>) query.getResultList();
        for (Object[] result: resultList) {
            final Long count = (Long) result[0];
            final Long idFirma = (Long) result[1];
            final int index = (result[2] == null ? 1 : ((Long)result[2]).intValue() + 2);

            final int[] counters = infoColaboradorsDelegats.get(firma2estat.get(idFirma));
            counters[index] = count.intValue();
            counters[0] += count.intValue();
        }

        return infoColaboradorsDelegats;
    }

    protected Map<Long, int[]> infoColaboradorsDelegats(String usuariEntitatID, List<EstatDeFirma> estatDeFirma,
                                                        Long[] estatsInicials) throws I18NException, Exception {
        Map<Long, int[]> infoValidacionsByEstat = new HashMap<Long, int[]>();
        for (EstatDeFirma estat : estatDeFirma) {
            Where w = Where.AND(EstatDeFirmaFields.USUARIENTITATID.notEqual(usuariEntitatID),
                    EstatDeFirmaFields.FIRMAID.equal(estat.getFirmaID()),
                    EstatDeFirmaFields.TIPUSESTATDEFIRMAINICIALID.in(estatsInicials));

            IEstatDeFirmaManager estatDeFirmaManager = PortaFIBDaoManager.getDaoManagers().getEstatDeFirmaManager();
            List<GroupByValueItem> list = estatDeFirmaManager.executeQuery(
                    new SelectGroupByAndCountForField(EstatDeFirmaFields.TIPUSESTATDEFIRMAFINALID), w,
                    (OrderBy[]) null);

            int[] valors = new int[ConstantsV2.TIPUSESTATDEFIRMAFINAL.length + 2]; // +2 = pendent
            // i total
            int total = 0;
            for (GroupByValueItem item : list) {
                int count = item.getCount().intValue();
                String valStr = item.getValue();
                // Pendent
                if (valStr == null) {
                    valors[1] = count;
                    total = total + count;
                    continue;
                }
                int index = Long.valueOf(valStr).intValue();
                try {
                    valors[index + 2] = count;
                    total = total + count;
                } catch (IndexOutOfBoundsException iobe) {
                    System.out.println("Valor desconegut " + valStr + " o index fora de rang " + index);
                }
            }
            valors[0] = total;
            infoValidacionsByEstat.put(estat.getEstatDeFirmaID(), valors);
        }
        return infoValidacionsByEstat;
    }
}
