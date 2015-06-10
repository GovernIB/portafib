package es.caib.portafib.logic.utils;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.junit.Test;

import es.caib.portafib.jpa.FirmaJPA;
import es.caib.portafib.jpa.PeticioDeFirmaJPA;
import es.caib.portafib.jpa.PortaFIBJPADaoManagers;
import es.caib.portafib.jpa.TipusFirmaJPA;
import es.caib.portafib.jpa.UsuariPersonaJPA;
import es.caib.portafib.model.PortaFIBDaoManager;
import es.caib.portafib.model.dao.ITipusFirmaManager;

import es.caib.portafib.model.entity.TipusFirma;



/**
 * Hello world!
 * 
 */
public class TestJPA {
  
  public static final Logger log = Logger.getLogger(TestJPA.class);

  public static void main(String[] args) {
    
    new TestJPA().main();
  }
  
 
  
  @Test
  public void main() {
    try {
      log.info(">>>>>>>>>>>>  Hello World!");
      
      // USING GENAPP
      // ============
      
      Properties prop = new Properties();

//    
//    prop.put("hibernate.dialect","org.hibernate.dialect.MySQL5InnoDBDialect");
//    prop.put("javax.persistence.jdbc.driver","com.mysql.jdbc.Driver");
//    prop.put("javax.persistence.jdbc.url", "jdbc:mysql://192.168.35.151:3306/portasib");
//    prop.put("javax.persistence.jdbc.user","portasib");
//    prop.put("javax.persistence.jdbc.password","portasib");
//    

      prop.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
      prop.put("javax.persistence.jdbc.driver", "org.postgresql.Driver");
      // prop.put("javax.persistence.jdbc.url","jdbc:postgresql://192.168.35.151:5432/portafib");
      prop.put("javax.persistence.jdbc.url", "jdbc:postgresql://localhost:5432/portafib");
      prop.put("javax.persistence.jdbc.user", "portafib");
      prop.put("javax.persistence.jdbc.password", "portafib");
      

      prop.put("hibernate.connection.driver_class", "org.postgresql.Driver");
      // prop.put("javax.persistence.jdbc.url","jdbc:postgresql://192.168.35.151:5432/portafib");
      prop.put("hibernate.connection.url", "jdbc:postgresql://localhost:5432/portafib");
      prop.put("hibernate.connection.username", "portafib");
      prop.put("hibernate.connection.password", "portafib");
      
      prop.put("hibernate.show_sql", "true");


      
      
      

      EntityManagerFactory emf;

      // Veure persistence.xml
      emf = Persistence.createEntityManagerFactory("portafibDBStandalone", prop);
      
      
      
      EntityManager em = emf.createEntityManager();  
      
      em.setFlushMode(FlushModeType.AUTO);
      
      EntityTransaction tx = em.getTransaction();

      tx.begin();
      
      boolean f = false;
      if (f) {
        throw new I18NException("werrrtt");
      }
      
      PortaFIBDaoManager.setDaoManagers(new PortaFIBJPADaoManagers(em)); // firmesDaoManagers

      // ITipusFirmaManager tipusFirmaMan = PortaFIBDaoManager.getDaoManagers().getTipusFirmaManager();
      //testTipusFirma(em, tipusFirmaMan);
      
  
      
      /*
      try {
      ITraduccioManager traduccio = PortaFIBDaoManager.getDaoManagers().getTraduccioManager();
      
      
      em.getTransaction().begin();
      
      String[] idioma = new String[] { "ca" , "es" };
      String[] valor = new String[] { "Valor en ca" , "Valor en es"};
      final long grup = 1234;
      
          for (int i = 0; i < idioma.length; i++) {
            TraduccioJPA tra = new TraduccioJPA();
            tra.setIdiomaID(idioma[i]);
            tra.setValor(valor[i]);
            tra.setTraduccioGrupID(grup);
           
            tra = (TraduccioJPA)traduccio.create(tra);
            
            
            log.info(" ==========CREATED WITH ID = " + tra.getTraduccioID() + " ============== ");

          }
      
          em.getTransaction().commit();
      
      
      log.info(" ---------------- LLISTAT ----------------- ");
      
      List<Traduccio> list = traduccio.select();
      for (Traduccio tra : list) {
        log.info(" TRA[" + tra.getTraduccioID() + "] = (" + tra.getIdiomaID() + ") " + tra.getValor() );
      }
      
       } catch (I18NException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
*/
      
      
      /*   ------------- EDU --------------
      
      ITraduccioManager traman = PortaFIBDaoManager.getDaoManagers().getTraduccioManager();
      

      
      
      TraduccioJPA trad = new TraduccioJPA(); //987654);
      
      trad.setTraduccio("es", "NOU ES");
      trad.setTraduccio("ca", "NOU CA");
      
      
      trad = (TraduccioJPA)traman.create(trad);
      
      //trad = em.merge(trad);
      
      log.info("ID: " + trad.getTraduccioID());     
      */
      
      
//      IProva2Manager prova2 = PortaFIBDaoManager.getDaoManagers().getProva2Manager();
//      
//      Prova2JPA prova = new Prova2JPA();
//      prova.setNom2traduccioID(1234);
//      prova.setDescripcio2("_descripcio2_");
//      
//      prova  = (Prova2JPA)prova2.create(prova);
//      
//      
//      log.info("PROVA ID = " + prova.getProva2ID());
      
      
      
      //Thread.sleep(2000);
      
      
      
      /*
      ITraduccioManager traman = PortaFIBDaoManager.getDaoManagers().getTraduccioManager();
      
      TraduccioJPA trad = (TraduccioJPA)traman.findByPrimaryKey(1234); //987654);
      
      log.info("ID = " + trad.getTraduccioID());
      
      
      
      {
        Map<String,TraduccioMapJPA > map = trad.getTraduccions();
        
        if (map.isEmpty()) {
          log.info(" MAP BUIT !!!! ");
        }
        
        for (String key : map.keySet()) {
          
          TraduccioMapJPA tra = map.get(key);
          
          log.info(key + " OK --> " + tra.getValor());
          
         }
        
        TraduccioMapJPA nova = new TraduccioMapJPA();
        nova.setValor("IN INGLISH");
        
        map.put("en", nova);
        
        traman.update(trad);
        
       }
      */
      /*
      trad = (TraduccioJPA)traman.findByPrimaryKey(1234);
      Map<String,TraduccioMapJPA > map = trad.getTraduccions();
      
      if (map.isEmpty()) {
        log.info(" MAP BUIT !!!! ");
      }
      
      for (String key : map.keySet()) {
        
        TraduccioMapJPA tra = map.get(key);
        
        log.info(key + " OK --> " + tra.getValor());
        
       }
      */
     
      
      /*
      IProva2Manager prova2Man = PortaFIBDaoManager.getDaoManagers().getProva2Manager();
      
         
     
     TraduccioJPA tra = new TraduccioJPA();
     tra.addTraduccio("ca", new TraduccioMapJPA("TEST CA"));
     tra.addTraduccio("es", new TraduccioMapJPA("TEST ES"));
     
     //traman.create(tra);
     //log.info(" TRA CREAT = " + tra.getTraduccioID());
     
     Prova2JPA p2 = new Prova2JPA();
     p2.setNom2traduccioID(tra.getTraduccioID());
     p2.setNom2traduccio(tra);
     
     p2.setDescripcio2("DESCR TEST");
     
     p2 = (Prova2JPA)prova2Man.create(p2);
     
     log.info(" ID P2 CREAT = " + p2.getProva2ID());
     
     log.info(" ID P2.TRA CREAT = " + p2.getNom2traduccioID());
    
     tx.commit();
     
     System.in.read();
     
     tx.begin();
     
     tra.addTraduccio("ca", new TraduccioMapJPA("++++ TEST CA +++++"));
     
     prova2Man.update(p2);
     
     tx.commit();
     log.info("Actualitzat !!!");
     
     
     
     System.in.read();
     
     System.in.read();
     
     tx.begin();
     
     log.info(" BORRAT !!!!");
     prova2Man.delete(p2);
     */
     
      
      /*
     Prova2JPA p2 = (Prova2JPA)prova2Man.findByPrimaryKey(6789); //987654);
      
     log.info("ID = " + p2.getProva2ID());
     
     log.info("TRAID = " + p2.getNom2traduccioID());
     
     log.info("DESC = " + p2.getDescripcio2());
     
     {
     Map<String,TraduccioMapJPA > map = p2.getNom2traduccioTraduccions();
     
     if (map.isEmpty()) {
       log.info(" MAP BUIT !!!! ");
     }
     
     for (String key : map.keySet()) {
       
       TraduccioMapJPA tra = map.get(key);
       
       log.info(key + " OK --> " + tra.getValor());
       
    }
     
     TraduccioMapJPA nova = new TraduccioMapJPA();    
     nova.setValor("IN INGLISH");
     
     map.put("en", nova);
     map.put("ca", new TraduccioMapJPA(" NOUUUUUUUUUUUU value"));
     
     prova2Man.update(p2); 
     
     
     
     }

    */
     
     

     /** OK */
     /*
     {
     Map<String,TraduccioMapJPA > map = p2.getNom2traduccioTraduccions();
     
     for (String key : map.keySet()) {
       
       TraduccioMapJPA tra = map.get(key);
       
       log.info(key + " OK --> " + tra.getValor());
       
    }
     }
     
     
     Map<String,TraduccioMapJPA > map = p2.getNom2traduccioTraduccions();
     
     TraduccioMapJPA nova = new TraduccioMapJPA();     
     nova.setIdiomaID("en");
     nova.setValor("IN INGLISH");
     
     
     Map<String,TraduccioMapJPA > map2 = new HashMap<String, TraduccioMapJPA>(map);
     map2.put(nova.getIdiomaID(), nova);
     
     //p2.getNom2traduccio().setTraduccions(map2);
     p2.getNom2traduccio().getTraduccions().put(nova.getIdiomaID(), nova);
     
     prova2.update(p2);
     
     */
    /* */
      
      //testOneByOneInverse(em);
      
      
      
      //testOneByOneDirect(em);
      
      
      //testUsuariPersonaUsuariEntitat(em);
      
      
      tx.commit();
      log.info("<<<<<<<<<<<  Good Bye!");
      
      

    } catch (I18NException e) {
      e.printStackTrace();
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

  
  
  
  public void testUsuariPersonaUsuariEntitat(EntityManager em) {
    Query query = em.createQuery("select up from UsuariPersonaJPA up where up.usuariEntitats.entitatID='caib'");

    List<UsuariPersonaJPA> firmes =   query.getResultList();
    
    for (UsuariPersonaJPA usuariPersonaJPA : firmes) {
      log.info(usuariPersonaJPA.getNom() + " " + usuariPersonaJPA.getLlinatges());
    }

  }

  
  @SuppressWarnings("unused")
  private void testOneByOneInverse(EntityManager em) {
    Query query = em.createQuery("select firma from FirmaJPA firma " // join fetch firma.blocDeFirmes b join fetch b.fluxDeFirmes f   " 
        + " where ( (  firma.blocDeFirmes.fluxDeFirmes.peticioDeFirma.peticioDeFirmaID = 58900 ) AND ( firma.fitxerFirmatID IS NOT NULL ) )");
    
    // ( firma.blocDeFirmes.fluxDeFirmes.fluxDeFirmesID = 58750 )
    // ( firma.blocDeFirmes.fluxDeFirmesID = 58750 )
    // ( firma.blocDeFirmes.blocDeFirmesID = 58800 )
    // ( firma.blocDeFirmaID = 58800 )
    // ( firma.firmaID = 58850 )
    // ( firma.blocDeFirmes.fluxDeFirmes.peticioDeFirma.peticioDeFirmaID = 58900 )
    
    
    List<FirmaJPA> firmes =   query.getResultList();
    
    int size = firmes.size();
      
    log.info("List Size = " + size);
    
    if (size != 0) {
      FirmaJPA firma = firmes.get(0);
      
      log.info(firma.getBlocDeFirmes());
      
      log.info(firma.getBlocDeFirmes().getFluxDeFirmes());
      
    }
  }
  
  
  @SuppressWarnings("unused")
  private void testOneByOneDirect(EntityManager em) {
    Query query = em.createQuery("select peticiodefirma from PeticioDeFirmaJPA peticiodefirma"
        + " join fetch peticiodefirma.fluxDeFirmes f  join fetch f.blocDeFirmess b  join fetch b.firmas fi "
       
        // "   "  
        + " where ( "        
        + " fi.firmaID = 58850"
        + "  )");
    
    
    // OK peticiodefirma.fluxDeFirmes.fluxDeFirmesID in (select bf.fluxDeFirmesID from BlocDeFirmesJPA bf where bf.blocDeFirmesID = 58800)
    
    // + "(  peticiodefirma.fluxDeFirmes.fluxDeFirmesID in (select bf.fluxDeFirmesID from BlocDeFirmesJPA bf where bf.blocDeFirmesID in (select fi.blocDeFirmaID from FirmaJPA fi where fi.firmaID = 58850 )) )"
    
    // peticiodefirma.fluxDeFirmes.blocDeFirmess in (select bf from BlocDeFirmesJPA bf where bf.blocDeFirmesID = 58800
    
    // peticiodefirma.fluxDeFirmes.blocDeFirmess.firmas.firmaID = 58850 
    
    // peticiodefirma.fluxDeFirmes.fluxDeFirmesID = 58750
    
    // ( firma.blocDeFirmes.fluxDeFirmes.peticioDeFirma.peticioDeFirmaID = 58900 )
    
    
    List<PeticioDeFirmaJPA> peticions =   query.getResultList();
    
    int size = peticions.size();
      
    log.info("List Size = " + size);
    
    if (size != 0) {
      PeticioDeFirmaJPA peticio = peticions.get(0);

      log.info("PETICIO ID = " + peticio.getPeticioDeFirmaID());
      
      log.info("FLUX = " + peticio.getFluxDeFirmes());
      
      log.info("FITXER = " + peticio.getFitxerAFirmar());
      
      log.info("POSICIO = " + peticio.getPosicioTaulaFirmes());

      
    }
  }





  @SuppressWarnings("unused")
  private void testTipusFirma(EntityManager em, ITipusFirmaManager tipusFirmaMan)
      throws I18NException, IOException {
    // Llistar
    log.info(" ========= LLISTAR");
    listTipus(tipusFirmaMan);
    // Afegir i llistar
    TipusFirmaJPA nova = new TipusFirmaJPA(4, "Prova" , false);
    em.persist(nova);
    System.in.read();
    //tipusFirmaMan.create(nova);
    log.info(" ========= LLISTAR + NOU");
    listTipus(tipusFirmaMan);
    // Borrar i llistar
    tipusFirmaMan.delete(nova);
    log.info(" ========= LLISTAR - NOU");
    listTipus(tipusFirmaMan);
  }




  private void listTipus(ITipusFirmaManager tipusFirmaMan) throws I18NException {
    List<TipusFirma> list2 = tipusFirmaMan.select();
    int counter = 0;
    for (TipusFirma tipus : list2) {
      counter++;
      log.info(counter + ".- " + tipus.getNom() + "(ID = " + tipus.getTipusFirmaID() + "): "
         + ( tipus.isSuportada()?"SI":"NO") + " SUPORTADA)");
    }
  }
}
