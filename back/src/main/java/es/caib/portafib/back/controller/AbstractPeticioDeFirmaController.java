package es.caib.portafib.back.controller;

import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.pluginsib.utils.signature.SignatureCommonUtils;
import org.fundaciobit.pluginsib.utils.signature.SignatureConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import es.caib.portafib.back.controller.admin.GestioEntitatAdminController;
import es.caib.portafib.back.controller.webdb.PeticioDeFirmaController;
import es.caib.portafib.back.form.webdb.PeticioDeFirmaForm;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.back.utils.Utils;
import es.caib.portafib.back.validator.PeticioDeFirmaAmbFitxerAFirmarWebValidator;
import es.caib.portafib.persistence.PeticioDeFirmaJPA;
import es.caib.portafib.logic.PeticioDeFirmaLogicaLocal;
import es.caib.portafib.model.entity.PeticioDeFirma;
import es.caib.portafib.model.fields.PeticioDeFirmaFields;
import es.caib.portafib.utils.ConstantsV2;

/**
 * Afegeix un Validador per no deixar passar FitxersAFirma NULLs
 * 
 * @author anadal
 *
 */
public abstract class AbstractPeticioDeFirmaController extends PeticioDeFirmaController {

    @EJB(mappedName = PeticioDeFirmaLogicaLocal.JNDI_NAME)
    protected PeticioDeFirmaLogicaLocal peticioDeFirmaLogicaEjb;

    @Autowired
    private PeticioDeFirmaAmbFitxerAFirmarWebValidator peticioDeFirmaAmbFitxerAFirmarWebValidator;

    @PostConstruct
    public void initValidador() {
        setWebValidator(this.peticioDeFirmaAmbFitxerAFirmarWebValidator);
    }

    @Override
    public void delete(HttpServletRequest request, PeticioDeFirma peticioDeFirma) throws I18NException {

        Set<Long> fitxers;
        fitxers = peticioDeFirmaLogicaEjb.deleteFullUsingUsuariEntitat(peticioDeFirma.getPeticioDeFirmaID(),
                LoginInfo.getInstance().getUsuariPersona().getUsuariPersonaID());

        borrarFitxers(fitxers);
    }

    @Override
    public PeticioDeFirmaJPA update(HttpServletRequest request, PeticioDeFirmaJPA peticioDeFirma)
            throws I18NException, I18NValidationException {
        return peticioDeFirmaLogicaEjb.updateFull(peticioDeFirma,
                LoginInfo.getInstance().getUsuariPersona().getUsuariPersonaID());
    }

    // #166
    @Override
    public List<StringKeyValue> getReferenceListForPosicioTaulaFirmesID(HttpServletRequest request, ModelAndView mav,
            Where where) throws I18NException {
        return GestioEntitatAdminController.staticGetReferenceListForPosicioTaulaFirmes();
    }

    // #199
    @Override
    public List<StringKeyValue> getReferenceListForTipusFirmaID(HttpServletRequest request, ModelAndView mav,
            Where where) throws I18NException {
        List<StringKeyValue> __tmp = staticGetReferenceListForTipusFirmaID();
        return __tmp;
    }

    // #199
    public static List<StringKeyValue> staticGetReferenceListForTipusFirmaID() {

        /*
        List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
        
        __tmp.add(new StringKeyValue(String.valueOf(ConstantsV2.TIPUSFIRMA_PADES), "PAdES"));
        __tmp.add(new StringKeyValue(String.valueOf(ConstantsV2.TIPUSFIRMA_XADES), "XAdES"));
        __tmp.add(new StringKeyValue(String.valueOf(ConstantsV2.TIPUSFIRMA_CADES), "CAdES"));
        __tmp.add(new StringKeyValue(String.valueOf(ConstantsV2.TIPUSFIRMA_SMIME), "SMIME"));
        return __tmp;
        */

        List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();

        for (int tipus : ConstantsV2.TIPUSFIRMA_SUPPORTED) {
            __tmp.add(new StringKeyValue(String.valueOf(tipus), I18NUtils.tradueix("tipusfirma." + tipus)));
        }
        return __tmp;

    }

    //#199
    @Override
    public List<StringKeyValue> getReferenceListForAlgorismeDeFirmaID(HttpServletRequest request, ModelAndView mav,
            Where where) throws I18NException {
        return staticGetReferenceListForAlgorismeDeFirmaID();
    }

    public static List<StringKeyValue> staticGetReferenceListForAlgorismeDeFirmaID() {
        List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();

        __tmp.add(new StringKeyValue(String.valueOf(ConstantsV2.SIGN_ALGORITHM_SHA1WITHRSA), "SHA-1"));
        __tmp.add(new StringKeyValue(String.valueOf(ConstantsV2.SIGN_ALGORITHM_SHA256WITHRSA), "SHA-256"));
        __tmp.add(new StringKeyValue(String.valueOf(ConstantsV2.SIGN_ALGORITHM_SHA384WITHRSA), "SHA-384"));
        __tmp.add(new StringKeyValue(String.valueOf(ConstantsV2.SIGN_ALGORITHM_SHA512WITHRSA), "SHA-512"));
        return __tmp;
    }

    @Override
    public List<StringKeyValue> getReferenceListForModeDeFirma(HttpServletRequest request, ModelAndView mav,
            Where where) throws I18NException {
        return getReferenceListForModeDeFirma();
    }

    public static List<StringKeyValue> getReferenceListForModeDeFirma() {

        final int[] modes = new int[] { 
                
                /** El document inclou la Firma */
                SignatureConstants.SIGN_MODE_ATTACHED_ENVELOPED, // = 0
                
                /** El fitxer resultant serà la firma que incloura les dades originals */
                SignatureConstants.SIGN_MODE_ATTACHED_ENVELOPING, // = 3;

                /** El fitxer de firma no inclourà les dades: per separat trobarem un fitxer de firma i el fitxer original */
                SignatureConstants.SIGN_MODE_DETACHED, // = 1;

                /** Firma especial XAdES en que la firma i les dades estan al mateix nivell dins de l'XML: ni la firma inclou les dades ni les dades inclouen la firma */
                SignatureConstants.SIGN_MODE_INTERNALLY_DETACHED, // = 4;

                /** Firma especial XAdES també anomenada XAdES-Manifest
                * 
                * https://www.linkedin.com/pulse/art%C3%ADculo-t%C3%A9cnico-firmas-electr%C3%B3nicas-xades-de-con-tom%C3%A1s-garc%C3%ADa-mer%C3%A1s/
                * https://administracionelectronica.gob.es/ctt/resources/Soluciones/323/Descargas/Sistema%20de%20referenciacion%20de%20documentos%20en%20las%20AAPP-v11.docx?idIniciativa=323&idElemento=16433
                * https://www.w3.org/TR/2000/WD-xmldsig-core-20000510/#sec-o-Manifest
                */
                SignatureConstants.SIGN_MODE_EXTERNALLY_DETACHED // = 5;
        };

        List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();

        for (int i = 0; i < modes.length; i++) {
            __tmp.add(new StringKeyValue(String.valueOf(modes[i]), SignatureCommonUtils.signModeToString(modes[i])));
        }

        return __tmp;

    }

    //#199
    @Override
    public List<StringKeyValue> getReferenceListForPrioritatID(HttpServletRequest request, ModelAndView mav,
            Where where) throws I18NException {
        List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();

        for (int i = 0; i < 10; i++) {
            __tmp.add(new StringKeyValue(String.valueOf(i), i + " " + I18NUtils.tradueix("prioritat." + i)));
        }

        return __tmp;
    }

    //#199
    @Override
    public List<StringKeyValue> getReferenceListForTipusEstatPeticioDeFirmaID(HttpServletRequest request,
            ModelAndView mav, Where where) throws I18NException {
        List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();

        for (int estat : ConstantsV2.TIPUSESTATPETICIODEFIRMA) {
            __tmp.add(
                    new StringKeyValue(String.valueOf(estat), I18NUtils.tradueix("tipusestatpeticiodefirma." + estat)));
        }
        return __tmp;
    }

    // Nous camps de Peticio de Firma #281
    @Override
    public List<StringKeyValue> getReferenceListForOrigenPeticioDeFirma(HttpServletRequest request, ModelAndView mav,
            Where where) throws I18NException {
        List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
        for (int i = 0; i < 4; i++) {
            __tmp.add(new StringKeyValue(String.valueOf(i), I18NUtils.tradueix("origenpeticiodefirma." + i)));
        }
        return __tmp;
    }

    @Override
    public PeticioDeFirmaForm getPeticioDeFirmaForm(PeticioDeFirmaJPA _jpa2, boolean __isView,
            HttpServletRequest request, ModelAndView mav) throws I18NException {

        PeticioDeFirmaForm peticioDeFirmaForm = super.getPeticioDeFirmaForm(_jpa2, __isView, request, mav);

        if (__isView) {
            // Ocultar Camps NULL CampsNull
            Utils.hiddenEmptyFields(peticioDeFirmaForm, peticioDeFirmaForm.getPeticioDeFirma(),
                    PeticioDeFirmaFields.ALL_PETICIODEFIRMA_FIELDS);
        }

        return peticioDeFirmaForm;
    }

}
