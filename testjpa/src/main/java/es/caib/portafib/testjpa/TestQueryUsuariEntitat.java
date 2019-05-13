package es.caib.portafib.testjpa;

import java.util.List;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Where;
import org.hibernate.Hibernate;

import es.caib.portafib.jpa.PortaFIBJPADaoManagers;
import es.caib.portafib.jpa.UsuariEntitatJPA;
import es.caib.portafib.model.PortaFIBDaoManager;
import es.caib.portafib.model.dao.IUsuariEntitatManager;
import es.caib.portafib.model.dao.IUsuariPersonaManager;
import es.caib.portafib.model.entity.UsuariEntitat;
import es.caib.portafib.model.entity.UsuariPersona;
import es.caib.portafib.model.fields.UsuariEntitatFields;
import es.caib.portafib.model.fields.UsuariEntitatQueryPath;
import es.caib.portafib.model.fields.UsuariPersonaFields;

/**
 * 
 * @author anadal(u80067)
 *
 */
public class TestQueryUsuariEntitat {

  public static void main(String[] args) {

    new TestQueryUsuariEntitat().main();
  }

  public void main() {
    try {
      System.out.println("Hello World!");



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
      //prop.put("hibernate.show_sql", "true");
      prop.put("javax.persistence.jdbc.driver", "org.postgresql.Driver");
      prop.put("javax.persistence.jdbc.url", "jdbc:postgresql://localhost:5432/portafib2");
      prop.put("javax.persistence.jdbc.user", "portafib");
      prop.put("javax.persistence.jdbc.password", "portafib");
      

      prop.put("hibernate.connection.driver_class", "org.postgresql.Driver");
      prop.put("hibernate.connection.url", "jdbc:postgresql://localhost:5432/portafib2");
      prop.put("hibernate.connection.username", "portafib");
      prop.put("hibernate.connection.password", "portafib");
      

      EntityManagerFactory emf;

      // Veure persistence.xml
      emf = Persistence.createEntityManagerFactory("portafibDBStandalone", prop);
      
      
      EntityManager em = emf.createEntityManager();      
      PortaFIBDaoManager.setDaoManagers(new PortaFIBJPADaoManagers(em)); // firmesDaoManagers
     
      
      
      
      
      // Llistar Tipus document
//      ITipusDocumentManager  tipusFirmaMan = PortaFIBDaoManager.getDaoManagers().getTipusDocumentManager();
//      System.out.println(" ========= LLISTAR");
//      List<TipusDocument> list2 = tipusFirmaMan.select();
//      int counter = 0;
//      for (TipusDocument tipus : list2) {
//        counter++;
//        System.out.println(counter + ".- " + tipus.getNomID() + "(ID = " + tipus.getTipusDocumentBaseID() + "): "
//           );
//      }
  
      
   
    {
      
      System.out.println(" ========= LLISTAR PERSONES ");
      IUsuariPersonaManager  personaMan = PortaFIBDaoManager.getDaoManagers().getUsuariPersonaManager();
      
    Where w1 = UsuariPersonaFields.EMAIL.like("a%");
    Where w2 =UsuariPersonaFields.IDIOMAID.equal("ca");
    
   
    
    List<UsuariPersona> list2 = personaMan.select(Where.AND(w1,w2), new OrderBy(UsuariPersonaFields.USUARIPERSONAID));
    int counter = 0;
    for (UsuariPersona tipus : list2) {
      counter++;
      System.out.println(counter + ".- [" + tipus.getUsuariPersonaID() + "] " + tipus.getNom() + "" + tipus.getLlinatges() + " (" + tipus.getEmail() + "):  " + tipus.getIdiomaID());
    }
    }
      
      
  {
        
        System.out.println(" ========= LLISTAR USUARI ENTITATS ");
        IUsuariEntitatManager  personaMan = PortaFIBDaoManager.getDaoManagers().getUsuariEntitatManager();
        
     // Where w1 = UsuariPersonaFields.EMAIL.like("anadal%");
     // Where w2 =UsuariPersonaFields.IDIOMAID.equal("ca");
        Where w1 = new UsuariEntitatQueryPath().USUARIPERSONA().EMAIL().like("a%");
        Where w2 = new UsuariEntitatQueryPath().USUARIPERSONA().IDIOMAID().equal("ca");

      
      List<UsuariEntitat> list2 = personaMan.select(Where.AND(w1,w2), new OrderBy(UsuariEntitatFields.USUARIPERSONAID));
      int counter = 0;
      for (UsuariEntitat tipus : list2) {
        counter++;
        
        Hibernate.initialize(((UsuariEntitatJPA)tipus).getUsuariPersona());
        
        System.out.println(counter + ".- [" + tipus.getUsuariEntitatID() + "]" + tipus.getUsuariPersonaID() + " " + ((UsuariEntitatJPA)tipus).getUsuariPersona().getNom() + " E: " + tipus.getEntitatID());
      }
    }
      
      
      System.out.println("Good Bye!");

    } catch (I18NException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

}
