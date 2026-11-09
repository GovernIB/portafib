package es.caib.portafib.back.controller.adapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.query.selectcolumn.Select2Columns;
import org.fundaciobit.genapp.common.query.selectcolumn.Select2Values;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalButtonStyle;
import org.fundaciobit.genapp.common.web.form.AdditionalField;
import org.fundaciobit.genapp.common.web.html.IconUtils;
import org.fundaciobit.genapp.common.web.menuoptions.MenuOption;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.portafib.back.controller.webdb.UsuariAplicacioConfiguracioController;
import es.caib.portafib.back.form.webdb.UsuariAplicacioConfiguracioFilterForm;
import es.caib.portafib.back.form.webdb.UsuariAplicacioConfiguracioForm;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.back.utils.Tab;
import es.caib.portafib.logic.UsuariAplicacioConfiguracioLogicaLocal;
import es.caib.portafib.model.entity.UsuariAplicacioConfiguracio;
import es.caib.portafib.model.fields.PerfilDeFirmaFields;
import es.caib.portafib.model.fields.PerfilsPerUsuariAplicacioFields;
import es.caib.portafib.model.fields.UsuariAplicacioConfiguracioFields;
import es.caib.portafib.model.fields.UsuariAplicacioFields;

/**
 * Afegir opcions de menú per enumerar les aplicacions amb APIs obsoletes #1176
 * @author anadal (u80067)
 * 9 nov 2026 11:37:28
 */

@Controller
@RequestMapping(value = ConfiguracioDeFirmaAmbApiObsoletaAdappController.CONTEXT_WEB)
@SessionAttributes(types = { UsuariAplicacioConfiguracioForm.class, UsuariAplicacioConfiguracioFilterForm.class })
@MenuOption(group = Tab.MENU_ADAPP, labelCode = "configuraciodefirma.apiobsoleta.plural", order = 32)
public class ConfiguracioDeFirmaAmbApiObsoletaAdappController extends UsuariAplicacioConfiguracioController {

    public static final String CONTEXT_WEB = "/adapp/configdefirmaapiobsoleta";

    public static final int COLUMN_APLICACIONS = 1;

    public static final int COLUMN_PERFILS = 2;

    @EJB(mappedName = UsuariAplicacioConfiguracioLogicaLocal.JNDI_NAME)
    private UsuariAplicacioConfiguracioLogicaLocal usuariAplicacioConfiguracioLogicaEjb;

    @EJB(mappedName = es.caib.portafib.ejb.PerfilsPerUsuariAplicacioService.JNDI_NAME)
    protected es.caib.portafib.ejb.PerfilsPerUsuariAplicacioService perfilsPerUsuariAplicacioEjb;

    @Override
    public String getTileForm() {
        return "usuariAplicacioConfiguracioFormAdapp";
    }

    @Override
    public String getTileList() {
        return "usuariAplicacioConfiguracioListAdapp";
    }

    @Override
    public String getEntityNameCode() {
        return "usuariAplicacioConfiguracio.usuariAplicacioConfiguracio";
    }

    public String getEntityNameCodePlural() {
        return "configuraciodefirma.apiobsoleta.plural";
    }

    /**
     * Només volen les Configuracions que tenguin API Indra i WS Api Activa
     */
    @Override
    public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {

        Where whereUs = Where.OR(UsuariAplicacioConfiguracioFields.USENFIRMAWS1.equal(Boolean.TRUE),
                UsuariAplicacioConfiguracioFields.USENFIRMAPASSARELAWEB.equal(Boolean.TRUE),
                UsuariAplicacioConfiguracioFields.USENFIRMAPASSARELASERVIDOR.equal(Boolean.TRUE));

        return Where.AND(ENTITATID.equal(LoginInfo.getInstance().getEntitatID()), ESDEPETICIO.equal(false), whereUs);

    }

    @Override
    public UsuariAplicacioConfiguracioFilterForm getUsuariAplicacioConfiguracioFilterForm(Integer pagina,
            ModelAndView mav, HttpServletRequest request) throws I18NException {
        UsuariAplicacioConfiguracioFilterForm usuariAplicacioConfiguracioFilterForm;
        usuariAplicacioConfiguracioFilterForm = super.getUsuariAplicacioConfiguracioFilterForm(pagina, mav, request);

        if (usuariAplicacioConfiguracioFilterForm.isNou()) {
            Set<Field<?>> hiddenFields = new HashSet<Field<?>>(
                    Arrays.asList(UsuariAplicacioConfiguracioFields.ALL_USUARIAPLICACIOCONFIGURACIO_FIELDS));
            hiddenFields.remove(NOM);
            hiddenFields.remove(USUARIAPLICACIOCONFIGID);
            //hiddenFields.remove(UsuariAplicacioConfiguracioFields.POLITICATAULAFIRMES);
            // hiddenFields.remove(UsuariAplicacioConfiguracioFields.POLITICACUSTODIA);
            //hiddenFields.remove(UsuariAplicacioConfiguracioFields.PLUGINFIRMASERVIDORID);
            hiddenFields.remove(UsuariAplicacioConfiguracioFields.USENFIRMAPASSARELASERVIDOR);
            hiddenFields.remove(UsuariAplicacioConfiguracioFields.USENFIRMAPASSARELAWEB);
            hiddenFields.remove(UsuariAplicacioConfiguracioFields.USENFIRMAWS1);

            usuariAplicacioConfiguracioFilterForm.setHiddenFields(hiddenFields);

            usuariAplicacioConfiguracioFilterForm.setDeleteSelectedButtonVisible(false);

            usuariAplicacioConfiguracioFilterForm.setVisibleMultipleSelection(false);

            usuariAplicacioConfiguracioFilterForm.setAddButtonVisible(false);
            usuariAplicacioConfiguracioFilterForm.setEditButtonVisible(false);
            usuariAplicacioConfiguracioFilterForm.setDeleteButtonVisible(false);

            usuariAplicacioConfiguracioFilterForm.addAdditionalButtonForEachItem(
                    new AdditionalButton(IconUtils.ICON_EDIT /* "fas fa-info-circle icon-white" */,
                            "configuraciodefirma.apiobsoleta.plural=Configuracions que usen APIs Obsoletes\r\n"
                                    + "configuraciodefirma.apiobsoleta",
                            ConfiguracioDeFirmaAdappController.CONTEXT_WEB + "/{0}/edit", AdditionalButtonStyle.INFO));

            // NOVA COLUMNA APLICACIONS
            {
                AdditionalField<Long, String> adfieldRN = new AdditionalField<Long, String>();
                adfieldRN.setCodeName(UsuariAplicacioFields._TABLE_TRANSLATION + ".plural");
                adfieldRN.setPosition(COLUMN_APLICACIONS);
                adfieldRN.setEscapeXml(false);
                // Els valors s'ompliran al mètode postList()
                adfieldRN.setValueMap(new HashMap<Long, String>());

                usuariAplicacioConfiguracioFilterForm.addAdditionalField(adfieldRN);
            }

            // NOVA COLUMNA PERFILS
            {
                AdditionalField<Long, String> adfieldRN = new AdditionalField<Long, String>();
                adfieldRN.setCodeName(PerfilDeFirmaFields._TABLE_TRANSLATION + ".plural");
                adfieldRN.setPosition(COLUMN_PERFILS);
                adfieldRN.setEscapeXml(false);
                // Els valors s'ompliran al mètode postList()
                adfieldRN.setValueMap(new HashMap<Long, String>());

                usuariAplicacioConfiguracioFilterForm.addAdditionalField(adfieldRN);
            }

        }

        return usuariAplicacioConfiguracioFilterForm;
    }

    @EJB(mappedName = es.caib.portafib.ejb.PerfilDeFirmaService.JNDI_NAME)
    protected es.caib.portafib.ejb.PerfilDeFirmaService perfilDeFirmaEjb;

    @Override
    public void postList(HttpServletRequest request, ModelAndView mav, UsuariAplicacioConfiguracioFilterForm filterForm,
            List<UsuariAplicacioConfiguracio> list) throws I18NException {

        Map<Long, String> aplicacionsMap = (Map<Long, String>) filterForm.getAdditionalField(COLUMN_APLICACIONS)
                .getValueMap();
        aplicacionsMap.clear();

        Map<Long, String> perfilsMap = (Map<Long, String>) filterForm.getAdditionalField(COLUMN_PERFILS).getValueMap();
        perfilsMap.clear();

        for (UsuariAplicacioConfiguracio usuariAplicacioConfiguracio : list) {

            Long configuracioId = usuariAplicacioConfiguracio.getUsuariAplicacioConfigID();

            // Perfils de les configuracions

            Where wPerfils = Where.OR(PerfilDeFirmaFields.CONFIGURACIODEFIRMA1ID.equal(configuracioId),
                    PerfilDeFirmaFields.CONFIGURACIODEFIRMA2ID.equal(configuracioId),
                    PerfilDeFirmaFields.CONFIGURACIODEFIRMA3ID.equal(configuracioId),
                    PerfilDeFirmaFields.CONFIGURACIODEFIRMA4ID.equal(configuracioId),
                    PerfilDeFirmaFields.CONFIGURACIODEFIRMA5ID.equal(configuracioId));

            Select2Columns<Long, String> selectPerfil = new Select2Columns<Long, String>(
                    PerfilDeFirmaFields.USUARIAPLICACIOPERFILID.select, PerfilDeFirmaFields.NOM.select);

            List<Select2Values<Long, String>> perfils = perfilDeFirmaEjb.executeQuery(selectPerfil, wPerfils);

            StringBuilder perfilHtml = new StringBuilder();
            perfilHtml.append("<ol>");
            List<Long> perfilIDs = new ArrayList<Long>();

            for (Select2Values<Long, String> perfil : perfils) {

                perfilIDs.add(perfil.getValue1());

                perfilHtml.append("<li><a href=\"" + request.getContextPath() + PerfilDeFirmaAdappController.CONTEXT_WEB + "/" + perfil.getValue1()
                        + "/edit\">" + perfil.getValue2() + "</a></li>");
            }
            perfilHtml.append("</ol>");

            List<String> aplicacions = perfilsPerUsuariAplicacioEjb.executeQuery(
                    PerfilsPerUsuariAplicacioFields.USUARIAPLICACIOID,
                    PerfilsPerUsuariAplicacioFields.PERFILDEFIRMAID.in(perfilIDs));


            StringBuilder aplicacionsHtml = new StringBuilder();
            aplicacionsHtml.append("<ol>");

            for (String app : aplicacions) {

                aplicacionsHtml.append("<li><a href=\"" + request.getContextPath() + GestioUsuariAplicacioAdappController.CONTEXTWEB + "/" + app
                        + "/edit\">" + app + "</a></li>");
            }
            aplicacionsHtml.append("</ol>");

            perfilsMap.put(configuracioId, perfilHtml.toString());
            aplicacionsMap.put(configuracioId, aplicacionsHtml.toString());

        }

    }

    @Override
    public boolean isActiveFormNew() {
        return false;
    }

}
