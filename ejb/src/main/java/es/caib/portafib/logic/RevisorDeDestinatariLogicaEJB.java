package es.caib.portafib.logic;

import java.util.ArrayList;
import java.util.List;

import es.caib.portafib.ejb.RevisorDeDestinatariEJB;
import es.caib.portafib.ejb.RoleUsuariEntitatService;
import es.caib.portafib.model.bean.UsuariPersonaBean;
import es.caib.portafib.model.entity.RevisorDeFirma;
import es.caib.portafib.model.fields.FirmaFields;
import es.caib.portafib.model.fields.FirmaQueryPath;
import es.caib.portafib.model.fields.FluxDeFirmesQueryPath;
import es.caib.portafib.model.fields.PeticioDeFirmaQueryPath;
import es.caib.portafib.model.fields.RevisorDeDestinatariFields;
import es.caib.portafib.model.fields.RevisorDeDestinatariQueryPath;
import es.caib.portafib.model.fields.RevisorDeFirmaFields;
import es.caib.portafib.model.fields.RoleUsuariEntitatFields;
import es.caib.portafib.model.fields.UsuariPersonaQueryPath;
import es.caib.portafib.utils.ConstantsV2;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NTranslation;
import org.fundaciobit.genapp.common.query.SelectMultipleStringKeyValue;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.query.selectcolumn.Select5Columns;
import org.fundaciobit.genapp.common.query.selectcolumn.Select5Values;

/**
 * 
 * @author anadal(u80067)
 *
 */
@Stateless(name = "RevisorDeDestinatariLogicaEJB")
public class RevisorDeDestinatariLogicaEJB extends RevisorDeDestinatariEJB
        implements RevisorDeDestinatariLogicaService {

    @EJB(mappedName = RoleUsuariEntitatService.JNDI_NAME)
    protected RoleUsuariEntitatService roleUsuariEntitatEjb;

    @EJB(mappedName = es.caib.portafib.ejb.RevisorDeFirmaService.JNDI_NAME)
    protected es.caib.portafib.ejb.RevisorDeFirmaService revisorDeFirmaEjb;

    @EJB(mappedName = FirmaLogicaLocal.JNDI_NAME)
    protected FirmaLogicaLocal firmaLogicaEjb;

    @Override
    public List<UsuariPersonaBean> getRevisorsDeDestinatariUsingUsuariEntitatID(String destinatariUsuariEntitatID)
            throws I18NException {
        return getRevisorsDeDestinatariUsingUsuariEntitatID(destinatariUsuariEntitatID, null);
    }

    @Override
    public List<UsuariPersonaBean> getRevisorsDeDestinatariUsingUsuariEntitatID(String destinatariUsuariEntitatID,
            String filterByUsername) throws I18NException {

        RevisorDeDestinatariQueryPath rdqp = new RevisorDeDestinatariQueryPath();
        UsuariPersonaQueryPath upqp = rdqp.REVISOR().USUARIPERSONA();

        Select5Columns<String, String, String, String, String> sc;
        sc = new Select5Columns<String, String, String, String, String>(upqp.USUARIPERSONAID().select,
                upqp.NIF().select, upqp.NOM().select, upqp.LLINATGES().select, rdqp.REVISOR().USUARIENTITATID().select);

        Where w1 = rdqp.DESTINATARIID().equal(destinatariUsuariEntitatID);

        Where w;
        if (filterByUsername == null) {
            w = w1;
        } else {
            Where w2 = Where.OR( 
                    upqp.USUARIPERSONAID().like("%" + filterByUsername + "%"),
                    upqp.NIF().like("%" + filterByUsername + "%"),
                    upqp.NOM().like("%" + filterByUsername + "%"),
                    upqp.LLINATGES().like("%" + filterByUsername + "%")
                    );
            w = Where.AND(w1, w2);
        }

        List<Select5Values<String, String, String, String, String>> result = this.executeQuery(sc, w);

        List<UsuariPersonaBean> persones = new ArrayList<UsuariPersonaBean>();
        for (Select5Values<String, String, String, String, String> sv : result) {
            UsuariPersonaBean bui = new UsuariPersonaBean();
            bui.setUsuariPersonaID(sv.getValue1());
            bui.setNif(sv.getValue2());
            bui.setNom(sv.getValue3());
            bui.setLlinatges(sv.getValue4());
            bui.setContrasenya(sv.getValue5());
            persones.add(bui);
        }
        return persones;
    }

    // Comprovar que l'usuari es revisor: té el rol global o es revisor d'un destinatari.
    @Override
    public boolean usuariEntitatIdEsRevisor(String usuariEntitatID) throws I18NException {

        if (usuariEntitatID == null || usuariEntitatID.trim().length() == 0) {
            return false;
        }

        // Te el role de REVISOR ???
        Long count = roleUsuariEntitatEjb.count(Where.AND(RoleUsuariEntitatFields.ROLEID.equal(ConstantsV2.ROLE_REVI),
                RoleUsuariEntitatFields.USUARIENTITATID.equal(usuariEntitatID)));

        if (count != null && count != 0) {
            return true;
        }

        //  Es Revisor d'algun DESTINATARI ???

        count = this.count(RevisorDeDestinatariFields.REVISORID.equal(usuariEntitatID));
        if (count != null && count != 0) {
            return true;
        }

        return false;

    }
    
    
    

    /**
     * Comprova si es pot esborrar el revisor. 
     * @param usuariEntitatID
     * @return Retorna null si es pot esborrar, sino un I18NTranslation amb el missatge d'error.
     * @throws I18NException
     */
    @Override
    public I18NTranslation pucEsborrarRevisor(String usuariEntitatID) throws I18NException {
        I18NTranslation i18n = null;
        {
        
        // #169 Ho feim a SACO => Si apareix a alguna Peticio de Firma
        // com a revisor llavors no es pot esborrar

        List<RevisorDeFirma> revisions;
        revisions = revisorDeFirmaEjb
                .select(RevisorDeFirmaFields.USUARIENTITATID.equal(usuariEntitatID));

        if (revisions == null || revisions.size() == 0) {
            i18n = null;
        } else {

            List<Long> firmesID = new ArrayList<Long>();
            for (RevisorDeFirma revisorDeFirma : revisions) {
                firmesID.add(revisorDeFirma.getFirmaID());
            }

            // Revisors de Firma en Peticions de Firma
            {
                FirmaQueryPath fqp = new FirmaQueryPath();

                PeticioDeFirmaQueryPath pfqp = fqp.BLOCDEFIRMES().FLUXDEFIRMES().PETICIODEFIRMA();

                SelectMultipleStringKeyValue smskv = new SelectMultipleStringKeyValue(pfqp.PETICIODEFIRMAID().select,
                        pfqp.TITOL().select);

                List<StringKeyValue> skvList = firmaLogicaEjb.executeQuery(smskv, FirmaFields.FIRMAID.in(firmesID));

                if (skvList != null && skvList.size() != 0) {
                    StringBuffer str = new StringBuffer();
                    for (StringKeyValue skv : skvList) {
                        str.append(skv.getValue()).append(" (").append(skv.getKey()).append("), ");
                    }

                    // "El revisor amb usuari-entitat {0} no es pot esborrar ja que esta donat
                    // d´alta com a revisor en les següent peticions de firma: {1}
                    i18n = new I18NTranslation("revisor.error.apareixenpeticions",
                            usuariEntitatID, str.toString());
                }
            }

            // Revisors de Firma en Plantilles de Flux de Firma
            {
                FirmaQueryPath fqp = new FirmaQueryPath();

                FluxDeFirmesQueryPath pfqp = fqp.BLOCDEFIRMES().FLUXDEFIRMES();

                SelectMultipleStringKeyValue smskv = new SelectMultipleStringKeyValue(pfqp.FLUXDEFIRMESID().select,
                        pfqp.NOM().select);

                List<StringKeyValue> skvList = firmaLogicaEjb.executeQuery(smskv, FirmaFields.FIRMAID.in(firmesID));

                if (skvList != null && skvList.size() != 0) {
                    StringBuffer str = new StringBuffer();
                    for (StringKeyValue skv : skvList) {
                        str.append(skv.getValue()).append(" (").append(skv.getKey()).append("), ");
                    }

                    // "El revisor amb usuari-entitat {0} no es pot esborrar ja que esta donat
                    // d´alta com a revisor en les següent peticions de firma: {1}
                    i18n = new I18NTranslation("revisor.error.apareixenflux",
                            usuariEntitatID, str.toString());
                }
            }

        }
        
        
        
        }
        return i18n;
    }


}
