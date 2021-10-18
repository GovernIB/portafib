package es.caib.portafib.testjpa;

import es.caib.portafib.persistence.PeticioDeFirmaJPA;
import es.caib.portafib.persistence.PortaFIBJPADaoManagers;
import es.caib.portafib.model.IPortaFIBDaoManagers;
import es.caib.portafib.model.PortaFIBDaoManager;
import es.caib.portafib.model.dao.IEstatDeFirmaManager;
import es.caib.portafib.model.entity.BlocDeFirmes;
import es.caib.portafib.model.entity.EstatDeFirma;
import es.caib.portafib.model.entity.Firma;
import es.caib.portafib.model.entity.PeticioDeFirma;
import es.caib.portafib.model.fields.BlocDeFirmesFields;
import es.caib.portafib.model.fields.EstatDeFirmaFields;
import es.caib.portafib.model.fields.EstatDeFirmaQueryPath;
import es.caib.portafib.model.fields.FirmaFields;
import es.caib.portafib.model.fields.FirmaQueryPath;
import es.caib.portafib.model.fields.PeticioDeFirmaFields;
import es.caib.portafib.model.fields.PeticioDeFirmaQueryPath;
import es.caib.portafib.utils.ConstantsV2;
import org.fundaciobit.genapp.common.i18n.I18NCommonUtils;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.GroupByValueItem;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.SelectGroupByAndCountForField;
import org.fundaciobit.genapp.common.query.SubQuery;
import org.fundaciobit.genapp.common.query.Where;
import org.hibernate.Hibernate;

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
public class TestPeticionsByEstats {

    public static void main(String[] args) {
        I18NCommonUtils.BUNDLES = new String[]{"missatges"};
        new TestPeticionsByEstats().main();
    }

    private void main() {
        try {
            System.out.println("Test peticions by estats");


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

            prop.put("hibernate.show_sql", "false");
            prop.put("hibernate.format_sql", "false");

            EntityManagerFactory emf;

            // Veure persistence.xml
            emf = Persistence.createEntityManagerFactory("portafibDBStandalone", prop);

            EntityManager em = emf.createEntityManager();
            PortaFIBDaoManager.setDaoManagers(new PortaFIBJPADaoManagers(em));

            IEstatDeFirmaManager estatDeFirmaManager = PortaFIBDaoManager.getDaoManagers().getEstatDeFirmaManager();

            String usuariEntitatID = "fundaciobit_pruebas";

            List<EstatDeFirma> estatsDeFirma = estatDeFirmaManager.select(EstatDeFirmaFields.USUARIENTITATID.equal(usuariEntitatID));

            long startTime;

            for (int i = 0; i < 10; i++) {

                startTime = System.nanoTime();
                Map<Long, PeticioDeFirma> peticionsFromEstat = getPeticioDeFirmaFromEstatDeFirmaID(estatsDeFirma);
                System.out.println("v1 Time: " + TimeUnit.NANOSECONDS.toMicros(System.nanoTime() - startTime) + "mc");

                /*
                for (Map.Entry<Long, PeticioDeFirma> entry: peticionsFromEstat.entrySet()) {
                    System.out.println(entry.getKey() + ": " + entry.getValue().getPeticioDeFirmaID());
                }*/

                startTime = System.nanoTime();
                Map<Long, PeticioDeFirma> peticionsFromEstat2 = getPeticioDeFirmaFromEstatDeFirmaID2(estatsDeFirma, em);
                System.out.println("v2 Time: " + TimeUnit.NANOSECONDS.toMicros(System.nanoTime() - startTime) + "mc");

                /*
                for (Map.Entry<Long, PeticioDeFirma> entry: peticionsFromEstat2.entrySet()) {
                    System.out.println(entry.getKey() + ": " + entry.getValue().getPeticioDeFirmaID());
                }*/
            }


        } catch (I18NException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Map<Long, PeticioDeFirma> getPeticioDeFirmaFromEstatDeFirmaID(
            List<EstatDeFirma> estatDeFirmaList) throws I18NException, Exception {

        Map<Long, PeticioDeFirma> map = new HashMap<Long, PeticioDeFirma>();

        for (EstatDeFirma estatDeFirma : estatDeFirmaList) {

            long firmaID = estatDeFirma.getFirmaID();
            PeticioDeFirmaJPA pf = getPeticioDeFirmaFromFirmaID(firmaID);

            if (pf != null) {
                Hibernate.initialize(pf.getTipusDocument());
                map.put(estatDeFirma.getEstatDeFirmaID(), pf);
            }
        }

        return map;
    }

    public Map<Long, PeticioDeFirma> getPeticioDeFirmaFromEstatDeFirmaID2(
            List<EstatDeFirma> estatsDeFirma, EntityManager __em) throws I18NException, Exception {

        // Optimitzat per #447
        List<Long> idsEstats = new ArrayList<Long>(estatsDeFirma.size());
        for (EstatDeFirma estat : estatsDeFirma) {
            idsEstats.add(estat.getEstatDeFirmaID());
        }

        /*
        selecciona parella estatDeFirma, PeticioDeFirmaJPA
        no he trobat alternativa de fer una sola select aquesta consulta amb genapp
        es pot fer un select múltiple on mesclar valors i entitats?
        */
        Query query = __em.createQuery(
                "select e.estatDeFirmaID, p " +
                        "from PeticioDeFirmaJPA p " +
                        "join p.fluxDeFirmes fl " +
                        "join fl.blocDeFirmess b " +
                        "join b.firmas f " +
                        "join f.estatDeFirmas e " +
                        "join fetch p.tipusDocument " +
                        "left join fetch p.fitxerAFirmar " +
                        "left join fetch p.fitxerAdaptat " +
                        "left join fetch p.firmaOriginalDetached " +
                        "left join fetch p.logoSegell " +
                        "where e.estatDeFirmaID IN (:idsEstats)");
        query.setParameter("idsEstats", idsEstats);

        List<Object[]> list = (List<Object[]>) query.getResultList();

        Map<Long, PeticioDeFirma> map = new HashMap<Long, PeticioDeFirma>(list.size());
        for (Object[] result : list) {
            map.put(((Long) result[0]), ((PeticioDeFirmaJPA) result[1]));
        }
        return map;
    }

    public PeticioDeFirmaJPA getPeticioDeFirmaFromFirmaID(long firmaID)
            throws I18NException, Exception {
        SubQuery<Firma, Long> subqueryFirma;
        subqueryFirma = PortaFIBDaoManager.getDaoManagers().getFirmaManager().getSubQuery(FirmaFields.BLOCDEFIRMAID,
                FirmaFields.FIRMAID.equal(firmaID));

        SubQuery<BlocDeFirmes, Long> subqueryBloc;
        subqueryBloc = PortaFIBDaoManager.getDaoManagers().getBlocDeFirmesManager().getSubQuery(BlocDeFirmesFields.FLUXDEFIRMESID,
                BlocDeFirmesFields.BLOCDEFIRMESID.in(subqueryFirma));

        Where w = PeticioDeFirmaFields.FLUXDEFIRMESID.in(subqueryBloc);

        List<PeticioDeFirma> list = PortaFIBDaoManager.getDaoManagers().getPeticioDeFirmaManager().select(w);

        if (list.size() > 0) {
            return (PeticioDeFirmaJPA) list.get(0);
        } else {
            return null;
        }
    }
}
