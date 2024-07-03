package es.caib.portafib.back.controller.aden;

import es.caib.portafib.back.controller.FileDownloadController;
import es.caib.portafib.back.controller.webdb.FitxerController;
import es.caib.portafib.back.form.webdb.FitxerFilterForm;
import es.caib.portafib.back.form.webdb.FitxerForm;
import es.caib.portafib.ejb.BlocDeFirmesService;
import es.caib.portafib.ejb.FirmaService;
import es.caib.portafib.logic.PeticioDeFirmaLogicaLocal;
import es.caib.portafib.model.entity.Firma;
import es.caib.portafib.model.entity.Fitxer;
import es.caib.portafib.model.entity.PeticioDeFirma;
import es.caib.portafib.model.fields.BlocDeFirmesFields;
import es.caib.portafib.model.fields.FirmaQueryPath;
import es.caib.portafib.model.fields.FitxerFields;
import es.caib.portafib.utils.ConstantsV2;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.ITableManager;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.OrderType;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalButtonStyle;
import org.fundaciobit.genapp.common.web.form.AdditionalField;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller per gestionar un fitxers d'una petició de firma
 *
 * @author areus
 */
@Controller
@RequestMapping(value = FitxersDePeticioAdenController.CONTEXT_WEB)
@SessionAttributes(types = { FitxerForm.class, FitxerFilterForm.class })
public class FitxersDePeticioAdenController extends FitxerController {

    public static final String CONTEXT_WEB = "/aden/fitxerspeticio";

    public static final String SESSION_BACK_URL = "SESSION_BACK_URL";

    public static final int COLUMN_NOUFITXERID = -3;
    public static final int COLUMN_ORIGEN = -2;
    public static final int COLUMN_DESTINATARI = -1;
    public static final int COLUMN_RUTA = 1;

    public static final String ORIGEN_PETICIO = "fitxersPeticio.origen.peticio";
    public static final String ORIGEN_DETACHED = "fitxersPeticio.origen.detached";
    public static final String ORIGEN_ADAPTAT = "fitxersPeticio.origen.adaptat";

    public static final String ORIGEN_FIRMA_FINAL = "fitxersPeticio.origen.firmaFinal";
    public static final String ORIGEN_FIRMA_NOFINAL = "fitxersPeticio.origen.firmaNoFinal";
    public static final String ORIGEN_FIRMA_DESCARTADA = "fitxersPeticio.origen.firmaDescartada";
    public static final String ORIGEN_FIRMA_PENDENT = "fitxersPeticio.origen.firmaPendent";

    public static final String SESSION_PETICIOID = "fitxersDePeticio_peticioID";

    @EJB(mappedName = BlocDeFirmesService.JNDI_NAME)
    protected BlocDeFirmesService blocDeFirmesEjb;

    @EJB(mappedName = PeticioDeFirmaLogicaLocal.JNDI_NAME)
    protected PeticioDeFirmaLogicaLocal peticioDeFirmaLogicaEjb;

    @EJB(mappedName = FirmaService.JNDI_NAME)
    protected FirmaService firmaEjb;

    @Override
    public boolean isActiveFormNew() {
        return false;
    }

    @Override
    public boolean isActiveFormEdit() {
        return false;
    }

    @Override
    public boolean isActiveDelete() {
        return false;
    }

    @Override
    public boolean isActiveFormView() {
        return false;
    }

    @Override
    public String getTileList() {
        return "fitxersDePeticioListAden";
    }

    @Override
    public String getSessionAttributeFilterForm() {
        return "fitxersDePeticioAden_FilterForm";
    }

    @Override
    public FitxerFilterForm getFitxerFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
            throws I18NException {

        FitxerFilterForm fitxerFilterForm = super.getFitxerFilterForm(pagina, mav, request);

        if (fitxerFilterForm.isNou()) {

            fitxerFilterForm.setVisibleMultipleSelection(false);
            fitxerFilterForm.setVisibleFilterBy(false);
            fitxerFilterForm.setVisibleGroupBy(false);
            fitxerFilterForm.setAddButtonVisible(false);
            fitxerFilterForm.setDeleteSelectedButtonVisible(false);
            fitxerFilterForm.setDeleteButtonVisible(false);
            fitxerFilterForm.setEditButtonVisible(false);

            // All items
            fitxerFilterForm.setItemsPerPage(-1);
            fitxerFilterForm.setAttachedAdditionalJspCode(true);

            fitxerFilterForm.addHiddenField(FitxerFields.FITXERID);
            fitxerFilterForm.addHiddenField(FitxerFields.TAMANY);
            fitxerFilterForm.addHiddenField(FitxerFields.MIME);

            {
                AdditionalField<Long, String> fitxeridfield = new AdditionalField<Long, String>();
                fitxeridfield.setCodeName("fitxer.fitxerID");
                fitxeridfield.setPosition(COLUMN_NOUFITXERID);
                fitxeridfield.setValueMap(new HashMap<Long, String>());
                fitxerFilterForm.addAdditionalField(fitxeridfield);
            }

            {
                AdditionalField<Long, String> origenField = new AdditionalField<Long, String>();
                origenField.setEscapeXml(false);
                origenField.setCodeName("fitxersPeticio.origen");
                origenField.setPosition(COLUMN_ORIGEN);
                origenField.setValueMap(new HashMap<Long, String>());
                fitxerFilterForm.addAdditionalField(origenField);
            }
            {
                AdditionalField<Long, String> destinatariField = new AdditionalField<Long, String>();
                destinatariField.setCodeName("fitxersPeticio.origen.destinatariFirma");
                destinatariField.setPosition(COLUMN_DESTINATARI);
                destinatariField.setValueMap(new HashMap<Long, String>());
                fitxerFilterForm.addAdditionalField(destinatariField);
            }
            {
                AdditionalField<Long, String> rutaField = new AdditionalField<Long, String>();
                rutaField.setCodeName("fitxersPeticio.ruta");
                rutaField.setPosition(COLUMN_RUTA);
                rutaField.setValueMap(new HashMap<Long, String>());
                fitxerFilterForm.addAdditionalField(rutaField);
            }

            fitxerFilterForm.addAdditionalButton(new AdditionalButton("fas fa-long-arrow-alt-left icon-white", "tornar",
                    CONTEXT_WEB + "/tornar", AdditionalButtonStyle.PRIMARY));

        }

        Long peticioID = (Long) request.getSession().getAttribute(SESSION_PETICIOID);
        fitxerFilterForm.setTitleCode("fitxerspeticio.fitxerspeticio");
        fitxerFilterForm.setTitleParam(String.valueOf(peticioID));

        return fitxerFilterForm;
    }

    @Override
    public void postList(HttpServletRequest request, ModelAndView mav, FitxerFilterForm filterForm, List<Fitxer> list)
            throws I18NException {
        super.postList(request, mav, filterForm, list);

        Map<Long, String> mapFitxerId;
        mapFitxerId = (Map<Long, String>) filterForm.getAdditionalField(COLUMN_NOUFITXERID).getValueMap();
        mapFitxerId.clear();

        Map<Long, String> mapOrigen;
        mapOrigen = (Map<Long, String>) filterForm.getAdditionalField(COLUMN_ORIGEN).getValueMap();
        mapOrigen.clear();

        Map<Long, String> mapDestinatari;
        mapDestinatari = (Map<Long, String>) filterForm.getAdditionalField(COLUMN_DESTINATARI).getValueMap();
        mapDestinatari.clear();

        Map<Long, String> mapRuta;
        mapRuta = (Map<Long, String>) filterForm.getAdditionalField(COLUMN_RUTA).getValueMap();
        mapRuta.clear();

        filterForm.getAdditionalButtonsByPK().clear();

        for (Fitxer fitxer : list) {
            if (fitxer.getFitxerID() > 0) {
                mapFitxerId.put(fitxer.getFitxerID(), String.valueOf(fitxer.getFitxerID()));

                mapRuta.put(fitxer.getFitxerID(), FileSystemManager.getFile(fitxer.getFitxerID()).getAbsolutePath());

                filterForm.addAdditionalButtonByPK(fitxer.getFitxerID(),
                        new AdditionalButton("fas fa-download icon-white", "descarregar",
                                FileDownloadController.fileUrl(fitxer), AdditionalButtonStyle.SUCCESS));

            } else {
                mapFitxerId.put(fitxer.getFitxerID(), I18NUtils.tradueix("fitxersPeticio.fitxerID.buid"));
            }

            mapOrigen.put(fitxer.getFitxerID(), I18NUtils.tradueix(((FitxerPeticioFirma) fitxer).getOrigen()));
            mapDestinatari.put(fitxer.getFitxerID(), ((FitxerPeticioFirma) fitxer).getDestinatari());
        }

    }

    @Override
    public List<Fitxer> executeSelect(ITableManager<Fitxer, Long> ejb, Where where2, OrderBy[] orderBy2,
            Integer itemsPerPage, int inici) throws I18NException {
        //return itemsPerPage == null ? ejb.select(where, orderBy) : ejb.select(where, inici, itemsPerPage, orderBy);

        List<Fitxer> llistaFitxers = new ArrayList<Fitxer>();

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        Long peticioFirmaID = (Long) request.getSession().getAttribute(SESSION_PETICIOID);
        PeticioDeFirma peticio = peticioDeFirmaLogicaEjb.findByPrimaryKey(peticioFirmaID);

        // FITXERS DIRECTES DE LA PETICIÓ
        int negativeID = 0;

        llistaFitxers.add(peticio.getFitxerAFirmarID() == null
                ? new FitxerPeticioFirma(--negativeID, ORIGEN_PETICIO,
                        I18NUtils.tradueix("fitxersPeticio.origen.peticio.buid"), null)
                : new FitxerPeticioFirma(peticio.getFitxerAFirmar(), ORIGEN_PETICIO, "", null));

        if (peticio.getFitxerAdaptatID() != null) {
            llistaFitxers.add(new FitxerPeticioFirma(peticio.getFitxerAdaptat(), ORIGEN_ADAPTAT, "", null));
        } else {
            if (peticio.getTipusEstatPeticioDeFirmaID() == ConstantsV2.TIPUSESTATPETICIODEFIRMA_NOINICIAT) {
                llistaFitxers.add(new FitxerPeticioFirma(--negativeID, ORIGEN_ADAPTAT,
                        I18NUtils.tradueix("fitxersPeticio.origen.adaptat.nogenerat"), null));
            } else {
                llistaFitxers.add(new FitxerPeticioFirma(--negativeID, ORIGEN_ADAPTAT,
                        I18NUtils.tradueix("fitxersPeticio.origen.adaptat.descartat"), null));
            }
        }

        llistaFitxers.add(peticio.getFirmaOriginalDetachedID() == null
                ? new FitxerPeticioFirma(--negativeID, ORIGEN_DETACHED,
                        I18NUtils.tradueix("fitxersPeticio.origen.detached.buid"), null)
                : new FitxerPeticioFirma(peticio.getFirmaOriginalDetached(), ORIGEN_DETACHED, "", null));

        // LLISTAT DE FIRMES

        Where where = new FirmaQueryPath().BLOCDEFIRMES().FLUXDEFIRMES().PETICIODEFIRMA().PETICIODEFIRMAID()
                .equal(peticioFirmaID);
        OrderBy orderBy = new OrderBy(BlocDeFirmesFields.ORDRE, OrderType.ASC);

        List<Firma> llistaFirmes = firmaEjb.select(where, orderBy);

        int maxFirma = 0;
        for (Firma firma : llistaFirmes) {
            if (firma.getNumFirmaDocument() != null && firma.getNumFirmaDocument() > maxFirma) {
                maxFirma = firma.getNumFirmaDocument();
            }
        }

        for (Firma firma : llistaFirmes) {

            //BlocDeFirmesJPA blocDeFirmes = (BlocDeFirmesJPA)blocDeFirmesEjb.findByPrimaryKey(firma.getBlocDeFirmaID());

            if (firma.getNumFirmaDocument() == null) { // NO S'HA FIRMAT
                if (firma.getTipusEstatDeFirmaFinalID() == null) { // PERQUÉ ENCARA ESTÀ PENDENT
                    llistaFitxers.add(
                            new FitxerPeticioFirma(--negativeID, ORIGEN_FIRMA_PENDENT, "", firma.getDestinatariID()));
                } else if (firma.getTipusEstatDeFirmaFinalID() == ConstantsV2.TIPUSESTATDEFIRMAFINAL_DESCARTAT) { // PERQUÈ S'HA DESCARTAT
                    llistaFitxers.add(new FitxerPeticioFirma(--negativeID, ORIGEN_FIRMA_DESCARTADA,
                            I18NUtils.tradueix("fitxersPeticio.origen.firmaDescartada.buid"),
                            firma.getDestinatariID()));
                } // ELSE? NO HAURIA DE PASSAR
            } else { // S'HA FIRMAT
                if (firma.getFitxerFirmat() == null) { // I EL FITXER ES BUID
                    if (firma.getNumFirmaDocument() < maxFirma) { // PERQUE LA FIRMA NO ERA FINAL
                        llistaFitxers.add(new FitxerPeticioFirma(--negativeID, ORIGEN_FIRMA_NOFINAL,
                                I18NUtils.tradueix("fitxersPeticio.origen.firmaNoFinal.buid"),
                                firma.getDestinatariID()));
                    } else { // LA FIRMA ES FINAL I ES BUIDA, AIXÔ NO HAURIA DE PASSAR
                        log.warn("LA FIRMA [" + firma.getFirmaID()
                                + "] ÉS LA FIRMA FINAL DE LA PETICIÓ, PERÒ EL FITXER ÉS BUID");
                        llistaFitxers.add(new FitxerPeticioFirma(--negativeID, ORIGEN_FIRMA_FINAL,
                                I18NUtils.tradueix("fitxersPeticio.origen.firmaFinal.buid"), firma.getDestinatariID()));
                    }
                } else {
                    if (firma.getNumFirmaDocument() < maxFirma || peticio
                            .getTipusEstatPeticioDeFirmaID() == ConstantsV2.TIPUSESTATPETICIODEFIRMA_ENPROCES) { // S'HA FIRMAT I EL FITXER NO ES BUID
                        llistaFitxers.add(new FitxerPeticioFirma(firma.getFitxerFirmat(), ORIGEN_FIRMA_NOFINAL, "",
                                firma.getDestinatariID()));
                    } else {
                        llistaFitxers.add(new FitxerPeticioFirma(firma.getFitxerFirmat(), ORIGEN_FIRMA_FINAL, "",
                                firma.getDestinatariID()));
                    }
                }
            }

        }

        return llistaFitxers;
    }

    @Override
    @RequestMapping(value = "/list/{pagina}", method = RequestMethod.GET)
    public ModelAndView llistatPaginat(HttpServletRequest request, HttpServletResponse response,
            @PathVariable Integer pagina) throws I18NException {
        Long peticioID = (Long) request.getSession().getAttribute(SESSION_PETICIOID);
        if (peticioID == null) {
            HtmlUtils.saveMessageError(request, I18NUtils.tradueix("fitxerspeticio.nopeticio"));
            return new ModelAndView(new RedirectView("/canviarPipella/" + ConstantsV2.ROLE_ADEN, true));
        } else {
            return super.llistatPaginat(request, response, pagina);
        }
    }

    @RequestMapping(value = "/peticio/{peticioID}", method = RequestMethod.GET)
    public ModelAndView seleccioPeticio(HttpServletRequest request, HttpServletResponse response,
            @PathVariable Long peticioID) {
        request.getSession().setAttribute(SESSION_PETICIOID, peticioID);

        return new ModelAndView(new RedirectView(CONTEXT_WEB + "/list", true));
    }

    @RequestMapping(value = "/tornar", method = RequestMethod.GET)
    public ModelAndView tornar(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute(SESSION_PETICIOID);

        String url = (String) request.getSession().getAttribute(SESSION_BACK_URL);

        request.getSession().removeAttribute(SESSION_BACK_URL);

        return new ModelAndView(new RedirectView(url, true));
    }

}
