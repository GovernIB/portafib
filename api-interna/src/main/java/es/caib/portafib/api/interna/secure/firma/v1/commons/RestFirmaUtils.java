package es.caib.portafib.api.interna.secure.firma.v1.commons;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.pluginsib.utils.rest.RestException;
import org.fundaciobit.pluginsib.utils.rest.RestUtils;

import es.caib.portafib.persistence.EntitatJPA;
import es.caib.portafib.persistence.UsuariAplicacioJPA;
import es.caib.portafib.api.interna.secure.firma.v1.utils.AvailableProfile;
import es.caib.portafib.api.interna.secure.firma.v1.utils.AvailableProfilesRest;
import es.caib.portafib.commons.utils.Configuracio;
import es.caib.portafib.logic.CustodiaInfoLogicaLocal;
import es.caib.portafib.logic.UsuariAplicacioLogicaLocal;
import es.caib.portafib.logic.generator.IdGeneratorFactory;
import es.caib.portafib.logic.utils.EjbManager;
import es.caib.portafib.logic.utils.I18NLogicUtils;
import es.caib.portafib.logic.utils.SignatureUtils;
import es.caib.portafib.model.entity.PerfilDeFirma;
import es.caib.portafib.model.entity.UsuariAplicacioConfiguracio;
import es.caib.portafib.model.fields.PerfilDeFirmaFields;
import es.caib.portafib.model.fields.PerfilsPerUsuariAplicacioFields;
import es.caib.portafib.utils.ConstantsPortaFIB;

/**
 * 
 * @author anadal(u80067)
 *
 */
public class RestFirmaUtils extends RestUtils {

	@EJB(mappedName = es.caib.portafib.ejb.PerfilsPerUsuariAplicacioService.JNDI_NAME)
	protected es.caib.portafib.ejb.PerfilsPerUsuariAplicacioService perfilsPerUsuariAplicacioEjb;

	@EJB(mappedName = es.caib.portafib.ejb.PerfilDeFirmaService.JNDI_NAME)
	protected es.caib.portafib.ejb.PerfilDeFirmaService perfilDeFirmaEjb;

	@EJB(mappedName = CustodiaInfoLogicaLocal.JNDI_NAME)
	protected CustodiaInfoLogicaLocal custodiaInfoLogicaEjb;

	@EJB(mappedName = es.caib.portafib.ejb.TipusDocumentService.JNDI_NAME)
	protected es.caib.portafib.ejb.TipusDocumentService tipusDocumentEjb;


    protected final Logger log = Logger.getLogger(getClass());


    protected String  checkUsuariAplicacio(HttpServletRequest request) {

        String username = request.getUserPrincipal().getName();

        try {

            log.debug(" XYZ ZZZ autenticate::  LOGIN OK OK  OK  OK  OK OK ");

            UsuariAplicacioLogicaLocal usuariAplicacioEjb;
            try {
                usuariAplicacioEjb = EjbManager.getUsuariAplicacioLogicaEJB();
            } catch (Throwable e) {
                // TODO traduccio
                final String msg = "No puc accedir al gestor d´obtenció de informació de usuari-aplicacio " + username
                        + ": " + e.getMessage();
                log.error(" XYZ ZZZ autenticate:: " + msg, e);
                throw new RestException(msg, e);
            }

            UsuariAplicacioJPA usuariAplicacio = usuariAplicacioEjb.findByPrimaryKeyFull(username);
            if (usuariAplicacio == null) {
                final String msg = "L'usuari " + username + " no s'ha donat d'alta en el PortaFIB";
                log.error(msg);
                throw new RestException(msg);
            }

            EntitatJPA entitat = usuariAplicacio.getEntitat();
            // Check deshabilitada
            if (!entitat.isActiva()) {
                final String msg = "L'entitat " + entitat.getNom() + " a la que està associat l'usuari-aplicacio "
                        + username + " esta deshabilitada.";
                log.error(msg);
                throw new RestException(msg);
            }
            return username;

        } catch (RestException re) {
            throw re;
        } catch (Throwable e) {

            String m;
            if (e instanceof I18NException) {
                I18NException i18n = (I18NException) e;
                m = I18NLogicUtils.getMessage(i18n, new Locale(Configuracio.getDefaultLanguage()));
            } else {
                m = e.getMessage();
            }

            final String msg = "Error desconegut intentant obtenir informació de l´usuari aplicació " + username + ": "
                    + m;
            log.error(msg, e);
            throw new RestException(msg, e);
        }

    }

    

    /*
    public void internalGetAvailableProfiles(HttpServletRequest request, String locale) {
        checkUsuariAplicacio(request);
        
        try {

            // FALTA ELEGIR ELS PERFILS QUE TENGUIN API_PORTAFIB_WS_V2
            Where w = null;
            List<PerfilDeFirma> perfils = commonAvailableProfiles(w, request.getUserPrincipal().getName());

            List<ApisIBAvailableProfile> list = new ArrayList<ApisIBAvailableProfile>();

            for (PerfilDeFirma perfil : perfils) {

                String codiPerfil = perfil.getCodi();

                String descripcio = perfil.getDescripcio();

                // Falta llegir-ho de la BBDD
                ApisIBAvailableProfile ap = new ApisIBAvailableProfile(codiPerfil, perfil.getNom(), descripcio,
                        null);

                list.add(ap);
            }


        } catch (Throwable th) {

            // XYZ ZZZ Traduir
            String msg = "Error desconegut retornant el perfils d'un usuari aplicacio: " + th.getMessage();

            log.error(msg, th);

            throw new RestException(msg, th);
        }
    }
    */
    
    
    protected AvailableProfilesRest internalGetAvailableProfiles(HttpServletRequest request, String language, String userApp) {
        /*
        String error = autenticateUsrApp(request);
        if (error != null) {
            throw new RestException(error, Status.UNAUTHORIZED);
        }
        */

        try {

            // FALTA ELEGIR ELS PERFILS QUE TENGUIN API_PORTAFIB_WS_V2
            Where w = null;
            //String userApp = getUserApp(request);
            List<PerfilDeFirma> perfils = commonAvailableProfiles(w, userApp);

            List<AvailableProfile> data= new ArrayList<AvailableProfile>();

            for (PerfilDeFirma perfil : perfils) {

                String codiPerfil = perfil.getCodi();

                String descripcio = perfil.getDescripcio();

                // Falta llegir-ho de la BBDD
                AvailableProfile ap = new AvailableProfile(codiPerfil, perfil.getNom(), descripcio, null);

                data.add(ap);
            }

            // XYZ Resposta antiga amb ResponseEntity.
            // HttpHeaders headers = addAccessControllAllowOrigin();
            // ResponseEntity<?> re = new
            // ResponseEntity<AvailableProfilesRest>(availableProfiles, headers,
            // HttpStatus.OK);
            
            AvailableProfilesRest availableProfiles = new AvailableProfilesRest(data,language );
            
            

            return availableProfiles;

        } catch (Throwable th) {

            // XYZ ZZZ Traduir
            String msg = "Error desconegut retornant el perfils d'un usuari aplicacio: " + th.getMessage();

            log.error(msg, th);

            throw new RestException(msg, th);
        }
    }
    
    /**
     * 
     * @param w
     * @return
     * @throws I18NException
     */
    protected List<PerfilDeFirma> commonAvailableProfiles(Where w, String usuariAplicacioID) throws I18NException {

        log.info(" XYZ ZZZ Usuari-APP = " + usuariAplicacioID);

        List<Long> perfilIDList = perfilsPerUsuariAplicacioEjb.executeQuery(
                PerfilsPerUsuariAplicacioFields.PERFILDEFIRMAID,
                PerfilsPerUsuariAplicacioFields.USUARIAPLICACIOID.equal(usuariAplicacioID));

        List<PerfilDeFirma> perfils = perfilDeFirmaEjb
                .select(PerfilDeFirmaFields.USUARIAPLICACIOPERFILID.in(perfilIDList));

        return perfils;
    }
    
    

    protected String internalGetTransacction() {
        String transactionID = IdGeneratorFactory.getGenerator().generate();
        if (log.isDebugEnabled()) {
            log.debug("Creada transacció amb ID = [" + transactionID + "]");
        }
        return transactionID;
    }


	/**
	 * 
	 * @param usuariAplicacioID
	 * @param config
	 * @param entitatJPA
	 * @return
	 * @throws I18NException
	 */
	protected boolean getUseTimestampOfConfig(final String usuariAplicacioID, final UsuariAplicacioConfiguracio config,
			EntitatJPA entitatJPA) throws I18NException {
		final boolean useTimeStamp;
		{
			int politicaSegellatDeTemps = config.getPoliticaSegellatDeTemps();

			if (politicaSegellatDeTemps == ConstantsPortaFIB.POLITICA_DE_SEGELLAT_DE_TEMPS_DEFINIT_EN_ENTITAT) {
				politicaSegellatDeTemps = entitatJPA.getPoliticaSegellatDeTemps();
			}

			switch (politicaSegellatDeTemps) {
			case ConstantsPortaFIB.POLITICA_DE_SEGELLAT_DE_TEMPS_NOUSAR:
				useTimeStamp = false;
				break;

			case ConstantsPortaFIB.POLITICA_DE_SEGELLAT_DE_TEMPS_US_OBLIGATORI:
				useTimeStamp = true;
				break;

			case ConstantsPortaFIB.POLITICA_DE_SEGELLAT_DE_TEMPS_USUARI_ELEGEIX_PER_DEFECTE_SI:
				useTimeStamp = true;
				break;
			case ConstantsPortaFIB.POLITICA_DE_SEGELLAT_DE_TEMPS_USUARI_ELEGEIX_PER_DEFECTE_NO:
				useTimeStamp = false;
				break;

			default:
				// XYZ ZZZ Traduir
				throw new I18NException("genapp.comodi", "Politica de segellat de temps desconeguda ("
						+ politicaSegellatDeTemps + ") en usuari aplicació " + usuariAplicacioID);
			}
		}
		return useTimeStamp;
	}

	protected String getAlgorismeDeFirmaOfConfig(final UsuariAplicacioConfiguracio config, EntitatJPA entitatJPA)
			throws I18NException {
		int signAlgorithmID = getAlgorismeDeFirmaIDOfConfig(config, entitatJPA);

		// ALGORISME DE FIRMA
		String signAlgorithm = SignatureUtils.convertSignAlgorithmID(signAlgorithmID);
		log.info(" XYZ ZZZ REST: SIGN_ALGO [signAlgorithm] = " + signAlgorithm);
		return signAlgorithm;
	}

	protected int getAlgorismeDeFirmaIDOfConfig(final UsuariAplicacioConfiguracio config, EntitatJPA entitatJPA) {
		Integer signAlgorithmID = config.getAlgorismeDeFirmaID();
		if (signAlgorithmID == null) {
			// Si val null cercar-ho a les DADES DE l'ENTITAT
			signAlgorithmID = entitatJPA.getAlgorismeDeFirmaID();
		}

		log.info(" XYZ ZZZ REST: SIGN_ALGO [signAlgorithmID] = " + signAlgorithmID);
		return signAlgorithmID;
	}

}
