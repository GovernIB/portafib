package es.caib.portafib.persistence.test;

import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;

import es.caib.portafib.persistence.PortaFIBJPADaoManagers;
import es.caib.portafib.model.PortaFIBDaoManager;

/*
 * IMPORTANT - NO MODIFICAR - DERIVA AQUESTA CLASSE SI VOLS FER UN TEST 
 * IMPORTANT - DO NOT MODIFY - EXTENDS THIS CLASS IF YOU WANT DO A TEST
 *
 */

/**
 * 
 * @author anadal
 *
 */
public class TestPersistenceJPA {

    public static final Logger log = Logger.getLogger(TestPersistenceJPA.class);

    public static final void main(String[] args) {
        try {
            log.info(">>>>>>>>>>>>  Hello World!");

            // USING GENAPP
            // ============

            EntityManager em = initDB();

            EntityTransaction tx = em.getTransaction();

            tx.begin();

            // CADA TEST HA d'ANAR DINS D'UNA TRANSACCIO  !!!!!!!

            /*   EXEMPLE DE CRIDADA DIRECTE
              
             
            String hsql = "SELECT " + PluginFields.NOMID.fullName
             + " FROM PluginJPA plugin, 
             + " ORDER BY " + PluginFields.NOMID.fullName + " DESC";
            
            javax.persistence.Query qry = em.createQuery(hsql);
            
            List<Object> list = qry.getResultList();
            for (Object object : list) {
                System.out.println("Object[] => " + object);
            }
            
            */

            /*
             EXEMPLE DE LLISTAT 
             
            IPluginManager pluginMan = PortaFIBDaoManager.getDaoManagers().getPluginManager();
            
            
            SelectTraduccio st = new SelectTraduccio(PluginFields.NOMID, "es");
            
            List<String> noms = pluginMan.executeQuery(st, null);
            
            for (String nom : noms) {
                System.out.println("NOM[" + nom + "]");
            }
            
            */

            /*  CONSULTA IDIOMES DISPONIBLES
             * IIdiomaManager idioma = PortaFIBDaoManager.getDaoManagers().getIdiomaManager();
             * 
             * List<Idioma> llist = idioma.select(new OrderBy(IdiomaFields.IDIOMAID,
             * OrderType.DESC));
             * 
             * for (Idioma idioma2 : llist) { System.out.println("Idoma = " +
             * idioma2.getIdiomaID() + " => " + idioma2.getNom()); }
             * 
             * System.out.println("===");
             * 
             * llist = idioma.select(IdiomaFields.NOM.like("%Cat%"));
             * 
             * for (Idioma idioma2 : llist) { System.out.println("Idoma222 = " +
             * idioma2.getIdiomaID() + " => " + idioma2.getNom()); }
             * 
             */

            tx.commit();
            log.info("<<<<<<<<<<<  Good Bye!");

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 
     * @return
     */
    public static EntityManager initDB() {
        Properties prop = new Properties();

        prop.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        prop.put("javax.persistence.jdbc.driver", "org.postgresql.Driver");
        prop.put("javax.persistence.jdbc.url", "jdbc:postgresql://localhost:5432/portafib");
        prop.put("javax.persistence.jdbc.user", "portafib");
        prop.put("javax.persistence.jdbc.password", "portafib");

        prop.put("hibernate.connection.driver_class", "org.postgresql.Driver");
        prop.put("hibernate.connection.url", "jdbc:postgresql://localhost:5432/portafib");
        prop.put("hibernate.connection.username", "portafib");
        prop.put("hibernate.connection.password", "portafib");

        prop.put("hibernate.show_sql", "true");

        EntityManagerFactory emf;

        // Veure persistence.xml
        emf = Persistence.createEntityManagerFactory("portafibPULocal", prop);

        EntityManager em = emf.createEntityManager();

        em.setFlushMode(FlushModeType.AUTO);
        
        PortaFIBDaoManager.setDaoManagers(new PortaFIBJPADaoManagers(em)); 
        
        return em;
    }

}
