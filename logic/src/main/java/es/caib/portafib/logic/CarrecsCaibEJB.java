package es.caib.portafib.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import es.caib.portafib.jpa.UsuariEntitatJPA;
import es.caib.portafib.logic.utils.EmailUtil;
import es.caib.portafib.logic.utils.I18NLogicUtils;
import es.caib.portafib.model.entity.Firma;
import es.caib.portafib.model.entity.UsuariEntitat;
import es.caib.portafib.model.fields.BlocDeFirmesQueryPath;
import es.caib.portafib.model.fields.EstatDeFirmaFields;
import es.caib.portafib.model.fields.FirmaFields;
import es.caib.portafib.model.fields.FirmaQueryPath;
import es.caib.portafib.utils.Configuracio;
import es.caib.portafib.utils.Constants;

import javax.annotation.security.RunAs;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.IntegerField;
import org.fundaciobit.genapp.common.query.Where;
import org.jboss.ejb3.annotation.SecurityDomain;

/**
 * 
 * @author anadal
 *
 */
@Stateless(name = "CarrecsCaibEJB")
@SecurityDomain("seycon")
@RunAs("PFI_USER")
public class CarrecsCaibEJB implements CarrecsCaibLocal {

  protected final Logger log = Logger.getLogger(getClass());

  public static final Locale locale = new Locale("ca");

  @EJB(mappedName = UsuariEntitatLogicaLocal.JNDI_NAME)
  private UsuariEntitatLogicaLocal usuariEntitatLogicaEjb;

  @EJB(mappedName = "portafib/UsuariPersonaEJB/local")
  protected es.caib.portafib.ejb.UsuariPersonaLocal usuariPersonaEjb;

  @EJB(mappedName = es.caib.portafib.ejb.UsuariAplicacioLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.UsuariAplicacioLocal usuariAplicacioEjb;

  @EJB(mappedName = "portafib/FirmaLogicaEJB/local")
  private FirmaLogicaLocal firmaLogicaEjb;

  @EJB(mappedName = "portafib/EstatDeFirmaLogicaEJB/local")
  private EstatDeFirmaLogicaLocal estatDeFirmaLogicaEjb;

  @Override
  public UsuariEntitat processarCarrecsCaib(String tipus, String codusu, String nomrol,
      String valordomini, String agentsql, String nom) {
    
    String entitatID = System.getProperty("entitatprocessarcarrecs", "caib");

    try {
      log.info(" + tipus =" + tipus);
      log.info(" + codusu = " + codusu);
      log.info(" + nomrol = " + nomrol);
      log.info(" + valordomini = " + valordomini);
      log.info(" + agentsql = " + agentsql);
      log.info(" + nom = " + nom);

      if (tipus == null) {
        log.error("Tipus és NULL !!!!");
        return null;
      }

      final String usuariEntitatID = entitatID + "_carrec_" + nomrol + "-" + valordomini + "-"
          + agentsql;
      
      // Cercar usuari-entitat
      UsuariEntitat ue = usuariEntitatLogicaEjb.findByPrimaryKey(usuariEntitatID);

      // ------------------------------
      // I N S E R T
      // ------------------------------
      if ("insert".equals(tipus.trim())) {
        
        if (ue != null) {
          // llavors ho substituim per un update
          tipus = "update";          
        } else {
          // No existeix i ho cream
         
          
          ue = new UsuariEntitatJPA();
       
          ue.setActiu(false);
          ue.setCarrec(((nom == null) || (nom.trim().length() == 0)) ? "PENDENT posar nom" : nom);
          ue.setEntitatID(entitatID);
          ue.setPotCustodiar(false);
          ue.setUsuariEntitatID(usuariEntitatID);
       
          ue.setUsuariPersonaID(codusu);
          
          usuariEntitatLogicaEjb.create(ue);
         
          enviarCorreuAdmistradors("S'ha creat el càrrec " + usuariEntitatID 
              + " dins l'entitat " + entitatID 
              + ". Accedeixi a la Gestió de càrrecs per revisar el nom i activar-ho", entitatID);
          
          return ue;
        }

      }

      // ------------------------------------------------------
      // U P D A T E & D I S A B L E & D E L E T E
      // ------------------------------------------------------
      // UPDATE: canvi de la persona que representa aquest càrrec
      // DISABLE: desactivar el càrrec
      final boolean isUpdate = "update".equals(tipus.trim());
      final boolean isDisable = "disable".equals(tipus.trim());
      final boolean isDelete =  "delete".equals(tipus.trim());
      if (isUpdate || isDisable || isDelete) {

        if (ue == null) {
          
          // Enviar a correu a ADEN
          String msg = "S'esta intentant executar una operació '" + tipus + "' sobre carrec amb ID="
                + usuariEntitatID + "(Entitat " + entitatID
              + ") però aquest no existeix.";
          enviarCorreuAdmistradors(msg, entitatID);
          log.error(msg, new Exception(msg));
          return null;
        }
        

        // (1) No ha d'apareixer en firmes de peticions de firmes actives o
        // pausades
        // on la firma està en un bloc que s'esta processant
        final BlocDeFirmesQueryPath BLOC = new FirmaQueryPath().BLOCDEFIRMES();
        final IntegerField TIPUSESTATPETICIODEFIRMAID = BLOC.FLUXDEFIRMES().PETICIODEFIRMA()
            .TIPUSESTATPETICIODEFIRMAID();

        List<Firma> firmes = firmaLogicaEjb.select(Where.AND(
            FirmaFields.DESTINATARIID.equal(usuariEntitatID),
            FirmaFields.TIPUSESTATDEFIRMAFINALID.isNull(),
            BLOC.DATAFINALITZACIO().isNull(),
            TIPUSESTATPETICIODEFIRMAID.in(new Integer[] {
                Constants.TIPUSESTATPETICIODEFIRMA_ENPROCES,
                Constants.TIPUSESTATPETICIODEFIRMA_PAUSAT })));

        List<Firma> firmesEnBlocActiu = new ArrayList<Firma>();
        // final FirmaQueryPath firmaQueryPath = new
        // EstatDeFirmaQueryPath().FIRMA();
        for (Firma firma : firmes) {

          Long count = estatDeFirmaLogicaEjb.count(EstatDeFirmaFields.FIRMAID.equal(firma
              .getFirmaID()));
          if (count != 0) {
            firmesEnBlocActiu.add(firma);
          }
        }

        if (firmesEnBlocActiu.size() == 0) {
          if (isUpdate) {
            // ACTUALITZAR USERNAME
            ue.setUsuariPersonaID(codusu);
          } else if (isDisable) {
            // DESACTIVAR
            // NOTA: Es possible desactivar un carrec d'un bloc verge ja que
            // l'execució de la darrera firma del bloc anterior al bloc verge
            // fallarà
            // indicant que en el següent bloc hi ha un càrrec desactivat.
            ue.setActiu(false);
          } else {
            ue.setActiu(false);
            ue.setCarrec("PENDENT DE BORRAR - " + ue.getCarrec());            
          }
          usuariEntitatLogicaEjb.update(ue);
          if (isUpdate) {
            if (!ue.isActiu()) {
              String msg = "S'ha rebut una petició per actualitzar el càrrec amb ID "
                + usuariEntitatID + " per assignarli l'usuari " + ue.getUsuariPersonaID() 
                + ". S'ha actualitzat l'usuari però voliem informar que aquest càrrec està desactivat.";
              enviarCorreuAdmistradors(msg, entitatID);
            }
          } else {
            if (isDelete) {
              String msg = "S'ha rebut una petició per borrar el càrrec amb ID " + usuariEntitatID 
                + ". S'ha pogut desactivar però queda pendent borrar-ho de PortaFIB. ";
              enviarCorreuAdmistradors(msg, entitatID);
            }
          }
          return ue;
        } else {
          // Existeix una firma en marxa dins un bloc actiu que correspon a un
          // càrrec que fa que no poguem canviar l'usuari o desactivar o borrar
          StringBuffer ids = new StringBuffer();
          for(Firma fir : firmesEnBlocActiu) {
            if (ids.length() != 0) {
              ids.append(", ");
            }
            ids.append(fir.getFirmaID() + "(Bloc: " + fir.getBlocDeFirmaID() + ")");
          }

          String msg;
          if (isUpdate) {
            msg = "No es pot actualitzar el carrec " + usuariEntitatID + " del usuari "
                + ue.getUsuariPersonaID() + " pel nou usuari " + codusu
                + " ja que existeix una o varies firmes actives del càrrec anterior "
                + "(Firma/es "  + ids.toString() + ").\n"
                + "Manualment ha d'assignar l'usuari " + codusu + " al càrrec" + usuariEntitatID + ".";
          } else {
            String accio =  (isDisable ? "desactivar" : "eliminar");
            msg = "\nNo es pot " +accio + " el carrec "
                + usuariEntitatID + " ja que existeix una o varies firmes actives pendents de signar "
                + "(Firma/es  " + ids.toString()  + ")\n"
                + "Manualment ha d'aplicar " + accio + " al càrrec" + usuariEntitatID + ".";
          }

          enviarCorreuAdmistradors(msg, entitatID);
          log.error(msg, new Exception(msg));

          return null;
        }

      }

      // Enviar correu a admin
      String msg = "No existeix el tipus ]" + tipus
          + "[ pel seu processament dins processarCarrecsCaib()";
      enviarCorreuAdmistradors(msg, entitatID);
      log.error(msg, new Exception(msg));

    } catch (I18NException e) {
      String msg = I18NLogicUtils.getMessage(e, locale);
      log.error("Error processant CarrecCaib: " + msg, e);
      
      // Enviar correu a admin
      try {
        enviarCorreuAdmistradors(msg, entitatID);
      } catch (Throwable th) {
        log.error(th.getMessage(), th);
      }

    } catch (Exception e) {

      String msg = e.getMessage();
      log.error("Error processant CarrecCaib: " + msg, e);
      
      // Enviar correu a admin
      try {
        enviarCorreuAdmistradors(msg, entitatID);
      } catch (Throwable th) {
        log.error(th.getMessage(), th);
      }

    }

    return null;
  }

  
  protected void enviarCorreuAdmistradors(String message, String entitatID)
     throws Exception, I18NException {
    
    List<String> recipientsList;
    recipientsList = usuariEntitatLogicaEjb.getEmailsOfAdministradorsEntitatByEntitat(entitatID);
    
    String[] recipients = recipientsList.toArray(new String[recipientsList.size()]);
    if (log.isDebugEnabled()) {
      log.info("Enviat correu a " +  Arrays.toString(recipients)+ " amb el missatge: " + message);
    }
    
    final boolean isHtml = false;
    EmailUtil.postMail("PORTAFIB: Actualització de Càrrecs requereix la seva actuació", message, isHtml,
        Configuracio.getAppEmail(), recipients);
    
  }
  


}
