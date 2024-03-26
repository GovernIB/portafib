package es.caib.portafib.logic.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.SelectMultipleStringKeyValue;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.utils.Utils;

import es.caib.portafib.persistence.FirmaJPA;
import es.caib.portafib.persistence.PeticioDeFirmaJPA;
import es.caib.portafib.persistence.PortaFIBJPADaoManagers;
import es.caib.portafib.persistence.UsuariPersonaJPA;
import es.caib.portafib.model.IPortaFIBDaoManagers;
import es.caib.portafib.model.PortaFIBDaoManager;
import es.caib.portafib.model.dao.IBlocDeFirmesManager;
import es.caib.portafib.model.dao.IEstatDeFirmaManager;
import es.caib.portafib.model.dao.IFirmaManager;
import es.caib.portafib.model.dao.IPeticioDeFirmaManager;
import es.caib.portafib.model.dao.IPropietatGlobalManager;
import es.caib.portafib.model.dao.IUsuariEntitatManager;
import es.caib.portafib.model.entity.PeticioDeFirma;
import es.caib.portafib.model.entity.PropietatGlobal;
import es.caib.portafib.model.fields.BlocDeFirmesFields;
import es.caib.portafib.model.fields.EstatDeFirmaFields;
import es.caib.portafib.model.fields.EstatDeFirmaQueryPath;
import es.caib.portafib.model.fields.FirmaFields;
import es.caib.portafib.model.fields.FirmaQueryPath;
import es.caib.portafib.model.fields.PeticioDeFirmaFields;
import es.caib.portafib.model.fields.PeticioDeFirmaQueryPath;
import es.caib.portafib.model.fields.PropietatGlobalFields;
import es.caib.portafib.model.fields.UsuariEntitatFields;
import es.caib.portafib.model.fields.UsuariEntitatQueryPath;
import es.caib.portafib.utils.ConstantsV2;



/**
 * Hello world!
 * 
 */
public class TestJPA {
  
  public static final Logger log = Logger.getLogger(TestJPA.class);

  public static void main(String[] args) {
    new TestJPA().main();
  }

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
      prop.put("javax.persistence.jdbc.url", "jdbc:postgresql://localhost:5432/portafib20");
      prop.put("javax.persistence.jdbc.user", "portafib");
      prop.put("javax.persistence.jdbc.password", "portafib");
      

      prop.put("hibernate.connection.driver_class", "org.postgresql.Driver");
      // prop.put("javax.persistence.jdbc.url","jdbc:postgresql://192.168.35.151:5432/portafib");
      prop.put("hibernate.connection.url", "jdbc:postgresql://localhost:5432/portafib20");
      prop.put("hibernate.connection.username", "portafib");
      prop.put("hibernate.connection.password", "portafib");
      
      prop.put("hibernate.show_sql", "false");


      
      
      

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
      
      // ------------  A PARTIR D'AQUI PROVA EL TEU CODI -----------------------
      
      
      
      enviarMailPeticionsPendentsDeFirmar();
      
      

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
     
     log.info(" ESBORRAT !!!!");
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



  public void enviarMailPeticionsPendentsDeFirmar() throws Exception, I18NException {
    System.out.println("Hola caracola");

    IPortaFIBDaoManagers pdm = PortaFIBDaoManager.getDaoManagers();
    
    IPeticioDeFirmaManager peticioDeFirmaEjb = pdm.getPeticioDeFirmaManager();
    IBlocDeFirmesManager blocDeFirmesEjb = pdm.getBlocDeFirmesManager();
    IFirmaManager firmaEjb = pdm.getFirmaManager();
    IEstatDeFirmaManager estatDeFirmaEjb = pdm.getEstatDeFirmaManager();
    IUsuariEntitatManager usuariEntitatEjb = pdm.getUsuariEntitatManager();
    IPropietatGlobalManager propietatGlobalManagerEjb = pdm.getPropietatGlobalManager();

    // Cercar les entitats que tenguin activat l'enviament de correus
    Where wpg = Where.AND(PropietatGlobalFields.ENTITATID.isNotNull(),
        PropietatGlobalFields.CLAU
            .equal(PropietatGlobalUtil.PROPERTY_BYENTITY_AVISOS_FIRMES_PENDENTS_DIESABANS),
        PropietatGlobalFields.VALOR.isNotNull());

    List<PropietatGlobal> entitatsID = propietatGlobalManagerEjb.select(wpg);

    if (entitatsID == null || entitatsID.size() == 0) {
      System.out.println("No he trobat cap entitat");
      return;
    }

    System.out.println();
    System.out.print("Entitats a processar = ");
    for (PropietatGlobal pg : entitatsID) {
      System.out.println(pg.getEntitatID() + ", ");
    }
    System.out.println();


    // Per no sobrecarregar ho farem entitat per entitat
    for (PropietatGlobal pg : entitatsID) {

      int margeDeDies;

      margeDeDies = 60;

      /* TODO Descomentar
      try {
        margeDeDies = Integer.parseInt(pg.getValor());

        if (margeDeDies <= 0) {
          throw new NumberFormatException("El marge de dies es 0 o negatiu");
        }

      } catch (NumberFormatException e) {
        // TODO Missatge
        // log.error(,e)
        e.printStackTrace();
        continue;
      }
      */

      String entitatID = pg.getEntitatID();

      Where where = Where.AND(
          PeticioDeFirmaFields.TIPUSESTATPETICIODEFIRMAID.equal(ConstantsV2.TIPUSESTATPETICIODEFIRMA_ENPROCES), 
          new PeticioDeFirmaQueryPath().USUARIAPLICACIO().ENTITATID().equal(entitatID));
      List<PeticioDeFirma> peticions = peticioDeFirmaEjb.select(where);

      // Cercar nom entitats per petició

      Set<String> usuarisAppIDs = new HashSet<String>();
      for (PeticioDeFirma peticioDeFirma : peticions) {
        usuarisAppIDs.add(peticioDeFirma.getSolicitantUsuariAplicacioID());
      }

      //SelectMultipleStringKeyValue skv = new SelectMultipleStringKeyValue(
      //    UsuariAplicacioFields.USUARIAPLICACIOID.select,
      //    new UsuariAplicacioQueryPath().ENTITATID().select);

      //Where whereEntitats = UsuariAplicacioFields.USUARIAPLICACIOID.in(usuarisAppIDs);

      //List<StringKeyValue> listSKV = usuariAplicacioEjb.executeQuery(skv, whereEntitats);

      //Map<String, String> map = Utils.listToMap(listSKV);

      // TODO Moure a static 
      SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");

      System.out.println();
      System.out.println();
      System.out.println();
      System.out.println();
      System.out.println();

      long now = System.currentTimeMillis();

      List<PeticioDeFirma> avisarA = new ArrayList<PeticioDeFirma>();

      for (PeticioDeFirma peticioDeFirma : peticions) {

        long dif;

        Timestamp inici = peticioDeFirma.getDataSolicitud();
        Timestamp fi = peticioDeFirma.getDataCaducitat();

        boolean enviarCorreu;
        Long firmes;
        Long firmesRealitzades;
        Date dataSeguentAvis;

        if (fi.getTime() < now) {
          dif = -1;
          enviarCorreu = true;
          firmes = null;
          firmesRealitzades = null;
          dataSeguentAvis = null;
        } else {

          long diff = fi.getTime() - inici.getTime();

          // Firmes a realitzar
          dif = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
          Where w = BlocDeFirmesFields.FLUXDEFIRMESID
              .equal(peticioDeFirma.getFluxDeFirmesID());
          firmes = blocDeFirmesEjb.sumInteger(BlocDeFirmesFields.MINIMDEFIRMES, w);
                  //executeQueryOne(new SelectSum(BlocDeFirmesFields.MINIMDEFIRMES), w);

          // Firmes realitzades
          Where wf = Where.AND(
              new FirmaQueryPath().BLOCDEFIRMES().FLUXDEFIRMESID()
                  .equal(peticioDeFirma.getFluxDeFirmesID()),
              FirmaFields.TIPUSESTATDEFIRMAFINALID
                  .equal(ConstantsV2.TIPUSESTATDEFIRMAFINAL_FIRMAT));
          firmesRealitzades = firmaEjb.count(wf);
          System.out.println(" ---------------------- ");
          System.out.println("firmesRealitzades = " + firmesRealitzades);

          double part = (1.0 * dif / firmes);
          System.out.println("part = " + part);

          double margeDeDiesAjustat = (1.0 * margeDeDies / firmes);
          System.out.println("margeDeDiesAjustat (" + margeDeDies + "/" + firmes + " = "
              + margeDeDiesAjustat);

          double diesSeguentAvis = (((firmesRealitzades + 1) * part) - margeDeDiesAjustat);
          System.out.println("diesSeguentAvis = " + diesSeguentAvis);

          dataSeguentAvis = new Date(inici.getTime()
              + (int) (diesSeguentAvis * 24 * 60 * 60 * 1000));

          enviarCorreu = (dataSeguentAvis.getTime() < now);

        }

        if (enviarCorreu) {
          avisarA.add(peticioDeFirma);
        }

        System.out.println(peticioDeFirma.getTitol() + "\t " + sdf.format(inici) + "\t "
            + sdf.format(fi) + "\t " + dif + "\t "
            + ((firmes == null) ? "-" : String.valueOf(firmes)) + "\t "
            + ((firmesRealitzades == null) ? "-" : String.valueOf(firmesRealitzades)) + "\t "
            + ((dataSeguentAvis == null) ? "-" : sdf.format(dataSeguentAvis)) + "\t "
            + enviarCorreu + "\t[" + entitatID + "]");
      }

      // Cercar les firmes actives de les peticions a les que hem d'avisar
      Set<String> recuperarMailsDe = new HashSet<String>();

      Map<PeticioDeFirma, Set<String>> avisosUsuariPerPeticio = new HashMap<PeticioDeFirma, Set<String>>();

      for (PeticioDeFirma pf : avisarA) {

        Where wf = Where.AND(new EstatDeFirmaQueryPath().FIRMA().BLOCDEFIRMES()
            .FLUXDEFIRMESID().equal(pf.getFluxDeFirmesID()),
            EstatDeFirmaFields.TIPUSESTATDEFIRMAINICIALID
                .equal(ConstantsV2.TIPUSESTATDEFIRMAINICIAL_ASSIGNAT_PER_FIRMAR),
            EstatDeFirmaFields.TIPUSESTATDEFIRMAFINALID.isNull());

        List<String> avisarusuaris = estatDeFirmaEjb.executeQuery(
            EstatDeFirmaFields.USUARIENTITATID, wf);

        avisosUsuariPerPeticio.put(pf, new HashSet<String>(avisarusuaris));

        recuperarMailsDe.addAll(avisarusuaris);

        System.err.println("Per la peticio " + pf.getTitol() + " s'ha d'avisar a "
            + Arrays.toString(avisarusuaris.toArray()));

        // List<Long> firmesId =
        // estatDeFirmaEjb.executeQuery(EstatDeFirmaFields.FIRMAID, wf);
        // System.out.println(" Firmes = " +
        // Arrays.toString(firmesId.toArray()));
        System.out.println();

      }

      System.err.println("TOTS = " + Arrays.toString(recuperarMailsDe.toArray()));

      // *** usuariEntitatEjb cercar correu del usuaris persona amb id de
      // usuarientitat dins recuperarMailsDe

      // Mail UsuariEntitat
      Map<String, String> mailsByUsuariEntitat = new HashMap<String, String>();
      {
        SelectMultipleStringKeyValue smskv = new SelectMultipleStringKeyValue(
            UsuariEntitatFields.USUARIENTITATID.select, UsuariEntitatFields.EMAIL.select);

        Where w1 = Where.AND(UsuariEntitatFields.USUARIENTITATID.in(recuperarMailsDe),
            UsuariEntitatFields.EMAIL.isNotNull());

        List<StringKeyValue> mailUE = usuariEntitatEjb.executeQuery(smskv, w1);

        mailsByUsuariEntitat.putAll(Utils.listToMap(mailUE));

      }
      // mail usuari Persona
      {
        SelectMultipleStringKeyValue smskv = new SelectMultipleStringKeyValue(
            UsuariEntitatFields.USUARIENTITATID.select, new UsuariEntitatQueryPath()
                .USUARIPERSONA().EMAIL().select);

        Where w1 = Where.AND(UsuariEntitatFields.USUARIENTITATID.in(recuperarMailsDe),
            UsuariEntitatFields.EMAIL.isNull());

        List<StringKeyValue> mailUE = usuariEntitatEjb.executeQuery(smskv, w1);

        mailsByUsuariEntitat.putAll(Utils.listToMap(mailUE));
      }

      System.out.println("mailsByUsuariEntitat.size()::" + mailsByUsuariEntitat.size());
      System.out.println("recuperarMailsDe::" + recuperarMailsDe.size());
      System.out.println();

      for (String ue : mailsByUsuariEntitat.keySet()) {
        System.out.println(ue + "\t=> " + mailsByUsuariEntitat.get(ue));
      }

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
    
    
    
    List<FirmaJPA> firmes =   (List<FirmaJPA>)query.getResultList();
    
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
      
      log.info("POSICIO = " + peticio.getPosicioTaulaFirmesID());

      
    }
  }




/*
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
    // Esborrar i llistar
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
  */
}
