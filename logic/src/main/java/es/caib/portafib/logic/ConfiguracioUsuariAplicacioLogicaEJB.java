package es.caib.portafib.logic;

import java.util.List;

import es.caib.portafib.ejb.UsuariAplicacioConfiguracioEJB;
import es.caib.portafib.model.entity.PerfilsPerUsuariAplicacio;
import es.caib.portafib.model.entity.UsuariAplicacioConfiguracio;
import es.caib.portafib.model.entity.PerfilDeFirma;
import es.caib.portafib.model.fields.PerfilsPerUsuariAplicacioFields;
import es.caib.portafib.model.fields.PerfilsPerUsuariAplicacioQueryPath;
import es.caib.portafib.model.fields.PerfilDeFirmaFields;
import es.caib.portafib.utils.ConstantsV2;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.Where;
import org.jboss.ejb3.annotation.SecurityDomain;

/**
 * 
 * @author anadal
 *
 */
@Stateless(name = "ConfiguracioUsuariAplicacioLogicaEJB")
@SecurityDomain("seycon")
public class ConfiguracioUsuariAplicacioLogicaEJB extends UsuariAplicacioConfiguracioEJB
    implements ConfiguracioUsuariAplicacioLogicaLocal {

  @EJB(mappedName = es.caib.portafib.ejb.PerfilsPerUsuariAplicacioLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.PerfilsPerUsuariAplicacioLocal perfilsPerUsuariAplicacioEjb;

  @EJB(mappedName = es.caib.portafib.ejb.PerfilDeFirmaLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.PerfilDeFirmaLocal perfilDeFirmaEjb;


  @Override
  @RolesAllowed({ "PFI_ADMIN", "PFI_USER" })
  public UsuariAplicacioConfiguracio getConfiguracioUsuariAplicacio(
      final String usuariAplicacioID, String codiPerfil, final int usFirma) throws I18NException {

    // Check si codiPerfil existeix
    PerfilDeFirma usuariApicacioPerfil;
    {
      List<PerfilDeFirma> list = perfilDeFirmaEjb
          .select(PerfilDeFirmaFields.CODI.equal(codiPerfil));

      if (list == null || list.size() == 0) {
        // XYZ ZZZ TRA Traduir
        throw new I18NException("genapp.comodi", "El codi de perfil " + codiPerfil
            + " no existeix. Consulti amb l'Administrador.");
      }

      usuariApicacioPerfil = list.get(0);
    }

    // Check si usuariAplicacio té assignat aquest codi perfil
    // PerfilsPerUsuariAplicacio perfil;
    {

      Where w = Where.AND(PerfilsPerUsuariAplicacioFields.PERFILDEFIRMAID
          .equal(usuariApicacioPerfil.getUsuariAplicacioPerfilID()),
          PerfilsPerUsuariAplicacioFields.USUARIAPLICACIOID.equal(usuariAplicacioID));

      List<PerfilsPerUsuariAplicacio> perfils = perfilsPerUsuariAplicacioEjb.select(w);

      if (perfils == null || perfils.size() == 0) {
        // XYZ ZZZ TRA Traduir
        throw new I18NException("genapp.comodi", "El codi de perfil " + codiPerfil
            + " no està associat a l'usuari aplicació " + usuariAplicacioID
            + ". Consulti amb l'Administrador.");
      }

      // perfil = perfils.get(0);
    }

    // XYZ ZZZ Falta Fer !!!!
    // Aqui s'ha de processar la condició del Perfil i veure quin valor retorna
    // A partir del valor sencer obtingut retornar UsrAppConfiguracio1ID
    // o UsrAppConfiguracio2ID o UsrAppConfiguracio3ID

    final Long idConf = usuariApicacioPerfil.getConfiguracioDeFirma1ID();

    final UsuariAplicacioConfiguracio config = findByPrimaryKey(idConf);
    
    boolean tePermis = false;
    String nom;
    switch(usFirma) {
      case ConstantsV2.US_FIRMA_CONF_APP_APIFIRMASIMPLESERVIDOR: 
        tePermis=config.isUsEnFirmaApiSimpleServidor();
        nom = "US_FIRMA_CONF_APP_APIFIRMASIMPLESERVIDOR";
        break;
      case ConstantsV2.US_FIRMA_CONF_APP_APIFIRMASIMPLEWEB:
        tePermis=config.isUsEnFirmaApiSimpleServidor();
      nom = "US_FIRMA_CONF_APP_APIFIRMASIMPLEWEB";
       break;
      case ConstantsV2.US_FIRMA_CONF_APP_FIRMAWEB:
        tePermis=config.isUsEnFirmaApiSimpleServidor();
        nom = "US_FIRMA_CONF_APP_FIRMAWEB";
        break;
      case ConstantsV2.US_FIRMA_CONF_APP_FIRMAWS2:
        tePermis=config.isUsEnFirmaApiSimpleServidor();
        nom = "US_FIRMA_CONF_APP_FIRMAWS2";
         break;
      case ConstantsV2.US_FIRMA_CONF_APP_PASSARELAFIRMASERVIDOR:
        tePermis=config.isUsEnFirmaApiSimpleServidor();
        nom = "US_FIRMA_CONF_APP_PASSARELAFIRMASERVIDOR";
         break;
      case ConstantsV2.US_FIRMA_CONF_APP_PASSARELAFIRMAWEB: 
        tePermis=config.isUsEnFirmaApiSimpleServidor(); 
        nom = "US_FIRMA_CONF_APP_PASSARELAFIRMAWEB";
        break;
      default:
        // XYZ ZZZ TRA Traduir
        throw new I18NException("genapp.comodi", "Ús de Firma amb codi  " + usFirma + " desconegut." 
             + "Consulti amb l'Administrador.");
    
    }
    
    
    if (!tePermis) {
      
      // XYZ ZZZ TRA Traduir
      throw new I18NException("genapp.comodi", "El codi de perfil " + codiPerfil
            + " associat a l'usuari aplicació " + usuariAplicacioID 
            + " no té permis per ser usat en firmes de tipus " + nom
            + ". Consulti amb l'Administrador.");
    }
    

    return config;
  }

  /**
   * Els usuaris aplicacio de passarela NOMES poden tenir UN SOL PERFIL
   * 
   * @param usuariAplicacioID
   * @return
   * @throws I18NException
   */
  @Override
  @RolesAllowed({ "PFI_ADMIN", "PFI_USER" })
  public UsuariAplicacioConfiguracio getConfiguracioUsuariAplicacioPerPassarela(
      final String usuariAplicacioID, final boolean esFirmaEnServidor) throws I18NException {

    final Field<Boolean> usFirmaPassarela;
    
    final int usFirma;
    if (esFirmaEnServidor) {
      usFirma = ConstantsV2.US_FIRMA_CONF_APP_PASSARELAFIRMASERVIDOR;
      usFirmaPassarela = new PerfilsPerUsuariAplicacioQueryPath().PERFILDEFIRMA().CONFIGURACIODEFIRMA1().USENFIRMAPASSARELASERVIDOR();
    } else {
      usFirma = ConstantsV2.US_FIRMA_CONF_APP_PASSARELAFIRMAWEB;
      usFirmaPassarela = new PerfilsPerUsuariAplicacioQueryPath().PERFILDEFIRMA().CONFIGURACIODEFIRMA1().USENFIRMAPASSARELAWEB();
    }
    
    // Hauria de retornar 1
    List<String> codisPerfil = perfilsPerUsuariAplicacioEjb.executeQuery(
        new PerfilsPerUsuariAplicacioQueryPath().PERFILDEFIRMA().CODI(),
        Where.AND(
          PerfilsPerUsuariAplicacioFields.USUARIAPLICACIOID.equal(usuariAplicacioID),
          usFirmaPassarela.equal(true)
           ));

    if (codisPerfil == null || codisPerfil.size() != 1) {
      // XYZ ZZZ TRA
      throw new I18NException("genapp.comodi", "L´usuari Aplicacio " + usuariAplicacioID
          + " no te cap perfil de firma o en té més d´un. Els usuaris aplicació que"
          + " es fan servir per passarel·la han de tenir un i solament un perfil assignat.");
    }

    return getConfiguracioUsuariAplicacio(usuariAplicacioID, codisPerfil.get(0), usFirma);

  }

}
