package es.caib.portafib.back.controller.aden;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.time.LocalDate;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.POST;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.SelectDistinct;
import org.fundaciobit.genapp.common.query.Where;
import org.jboss.logging.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.logic.UsuariAplicacioLogicaLocal;
import es.caib.portafib.model.fields.EstadisticaFields;
import es.caib.portafib.utils.ConstantsV2;

/**
 * Controlador per a mostrar les estadístiques gràfiques d'Aden.
 * @author anadal (u80067)
 * 5 may 2026 13:19:07
 */
public abstract class AbstractEstadisticaGraficaAdenController implements ConstantsV2 {

    protected Logger log = Logger.getLogger(this.getClass());

    protected static final String[] MESOS = new String[] { "Gener", "Febrer", "Març", "Abril", "Maig", "Juny", "Juliol",
            "Agost", "Setembre", "Octubre", "Novembre", "Desembre" };

    // TODO Moure a constantsV2 i posar més grups i més tipus de petició, etc.
    protected static final Map<Integer, String> GRUP_LABELS = Map.of(

            // ----------------
            ESTADISTICA_GRUP_APIFIRMASIMPLE_SERVIDOR_UPGRADE, "API de firma simple en servidor",
            // ----------------
            ESTADISTICA_GRUP_APIFIRMASIMPLE_SINCRONA, "API de firma simple web síncrona",
            // ----------------
            ESTADISTICA_GRUP_APIFIRMASIMPLE_ASINCRONA, "API de firma simple web asíncrona",
            //  ----------------
            ESTADISTICA_GRUP_APISWAGGER_SERVER_UPGRADE, "API Swagger de firma en servidor i upgrade",
            //  ----------------
            ESTADISTICA_GRUP_APISWAGGER_SYNCV1, "API Swagger de firma web síncrona v1",
            //  ----------------
            ESTADISTICA_GRUP_APISWAGGER_ASYNCV1, "API Swagger de firma web asíncrona v1",
            //  ----------------
            ESTADISTICA_GRUP_APISWAGGER_VALIDATEV1, "API Swagger de validació de firma v1",
            //  ----------------
            ESTADISTICA_GRUP_PORTAFIB_VALIDATE, "Ús intern de Validació de Firmes de PortaFIB"

    );

    public static final int CATEGORIA_FIRMA_EN_SERVIDOR_UPGRADE = 3;

    public static final int CATEGORIA_VALIDACIO_FIRMA = 4;

    public static final int CATEGORIA_FIRMA_SINCRONA = 5;

    public static final int CATEGORIA_FIRMA_ASINCRONA = 6;

    protected static final Map<Integer, List<Integer>> GRUPS_PER_CATEGORIES = Map.of(

            // --------- CATEGORIA_FIRMA_EN_SERVIDOR_UPGRADE
            CATEGORIA_FIRMA_EN_SERVIDOR_UPGRADE,
            List.of(ESTADISTICA_GRUP_APIFIRMASIMPLE_SERVIDOR_UPGRADE, ESTADISTICA_GRUP_APISWAGGER_SERVER_UPGRADE),
            // --------- CATEGORIA_VALIDACIO_FIRMA
            CATEGORIA_VALIDACIO_FIRMA,
            List.of(ESTADISTICA_GRUP_APISWAGGER_VALIDATEV1, ESTADISTICA_GRUP_PORTAFIB_VALIDATE),
            // --------- CATEGORIA_FIRMA_SINCRONA
            CATEGORIA_FIRMA_SINCRONA,
            List.of(ESTADISTICA_GRUP_APIFIRMASIMPLE_SINCRONA, ESTADISTICA_GRUP_APISWAGGER_SYNCV1),
            // --------- CATEGORIA_FIRMA_ASINCRONA
            CATEGORIA_FIRMA_ASINCRONA,
            List.of(ESTADISTICA_GRUP_APIFIRMASIMPLE_ASINCRONA, ESTADISTICA_GRUP_APISWAGGER_ASYNCV1)

    );

    @EJB(mappedName = UsuariAplicacioLogicaLocal.JNDI_NAME)
    protected UsuariAplicacioLogicaLocal usuariAplicacioEjb;

    @EJB(mappedName = es.caib.portafib.ejb.EstadisticaService.JNDI_NAME)
    protected es.caib.portafib.ejb.EstadisticaService estadisticaEjb;

    public abstract int getCategoria();

    public abstract String getCategoriaTitol();

    public abstract boolean isSupportedCancelStatus();

    /**
     * Retorna la llista de tipus d'estadístiques que són de tipus CREATE per a aquesta categoria.
     * Su retorn null significa que no suporta estadistiques de tipus CREATE.
     * @return
     */
    public List<Integer> getCreateEstadistiques() {
        return null;
    }

    /**
     * Retorna la llista de tipus d'estadístiques que són de tipus FIRMES per a aquesta categoria.
     * Su retorn null significa que no suporta estadistiques de tipus FIRMES.
     * @return
     */
    public List<Integer> getFirmesEstadistiques() {
        return null;
    }

    public enum EstadistiquesRang {
        ANY(1), MES(2), DIA(3);

        private final int code;

        EstadistiquesRang(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        public static EstadistiquesRang fromCode(Integer code) {
            if (code == null) {
                return DIA;
            }
            for (EstadistiquesRang value : values()) {
                if (value.code == code.intValue()) {
                    return value;
                }
            }
            return DIA;
        }
    }

    @RequestMapping(value = "/")
    @GET
    @POST
    public ModelAndView index(@RequestParam(value = "grup", required = false)
    Integer grupCode2, @RequestParam(value = "rang", required = false)
    Integer rangCode, @RequestParam(value = "date", required = false)
    String dateParam, @RequestParam(value = "aplicacio", required = false)
    String aplicacio) throws Exception {

        ModelAndView mav = new ModelAndView("estadisticagraficaAden");

        String entitatID = LoginInfo.getInstance().getEntitatID();

        if (rangCode == null) {
            rangCode = EstadistiquesRang.MES.getCode();
        }

        EstadistiquesRang estadisticaRang = EstadistiquesRang.fromCode(rangCode);
        LocalDate selectedDate = (dateParam == null || dateParam.isBlank()) ? LocalDate.now()
                : LocalDate.parse(dateParam);
        int any = selectedDate.getYear();
        int mes = selectedDate.getMonthValue();
        int dia = selectedDate.getDayOfMonth();

        final List<Integer> createEstadistiques = getCreateEstadistiques();
        final boolean isSuportedCreate = createEstadistiques != null && !createEstadistiques.isEmpty();

        final List<Integer> firmesEstadistiques = getFirmesEstadistiques();
        final boolean isSupportedFirmes = firmesEstadistiques != null && !firmesEstadistiques.isEmpty();

        if (isSuportedCreate) {
            mav.addObject("titleCreate", getCreateLabel());
        }

        mav.addObject("titleOK", getOkLabel());
        if (isSupportedCancelStatus()) {
            mav.addObject("titleCancel", getCancelLabel());
        }
        mav.addObject("titleError", getErrorLabel());
        if (isSupportedFirmes) {
            mav.addObject("titleFirmes", "Firmes realitzades");
        }

        final List<Integer> grupsDisponibles = GRUPS_PER_CATEGORIES.get(getCategoria());

        Map<Integer, String> labelsGrups = new HashMap<>();

        for (Integer grup : grupsDisponibles) {
            String label = GRUP_LABELS.get(grup);
            if (label != null) {
                labelsGrups.put(grup, label);
            }
        }

        mav.addObject("grups", labelsGrups);

        final List<Integer> grupsSeleccionats;

        final String grupLabel;

        if (grupCode2 == null) {
            // XYS ZZZ TRA
            // TODO
            grupLabel = "Tots els grups";

            grupsSeleccionats = grupsDisponibles;

        } else {

            grupLabel = GRUP_LABELS.get(grupCode2);
            if (grupLabel == null) {
                throw new IllegalArgumentException("Grup d'Estadistiques no suportat: " + grupCode2);
            }

            grupsSeleccionats = List.of(grupCode2);
        }

        mav.addObject("grupLabel", grupLabel);
        mav.addObject("selectedGrup", grupCode2);
        mav.addObject("selectedRange", estadisticaRang.getCode());
        mav.addObject("selectedDate", selectedDate.toString());

        final List<Integer> tipusCreateByGrup = new ArrayList<Integer>();

        final List<Integer> tipusFirmesByGrup = new ArrayList<Integer>();

        final List<Integer> tipusOkByGrup = new ArrayList<Integer>();

        final List<Integer> tipusCancelByGrup = new ArrayList<Integer>();

        final List<Integer> tipusErrorByGrup = new ArrayList<Integer>();

        List<Integer> totesLesEstadistiques = new ArrayList<Integer>();

        for (Integer grup : grupsSeleccionats) {

            {

                log.info("Grup: " + grup);

                List<Integer> estadistiquesPerGrup = ConstantsV2.ESTADISTIQUES_BY_GRUP.get(grup);

                totesLesEstadistiques.addAll(estadistiquesPerGrup);

                for (int estadistica : estadistiquesPerGrup) {

                    if (isSupportedFirmes && firmesEstadistiques.contains(estadistica)) {
                        // Si és una estadística de tipus CREATE, la considerem com a OK
                        tipusFirmesByGrup.add(estadistica);

                    } else if (isSuportedCreate && createEstadistiques.contains(estadistica)) {
                        // Si és una estadística de tipus CREATE, la considerem com a OK
                        tipusCreateByGrup.add(estadistica);

                    } else if (estadistica > 0) {

                        if (estadistica > 100) {
                            // CANCEL
                            tipusCancelByGrup.add(estadistica);
                        } else {
                            // OK
                            tipusOkByGrup.add(estadistica);
                        }

                    } else {
                        tipusErrorByGrup.add(estadistica);
                    }

                }
            }

        }

        // XYZ DEBUG
        log.info("TipusOK by grup: " + tipusOkByGrup.stream().map(String::valueOf) // Converteix cada Integer a String
                .collect(Collectors.joining(", ")));
        log.info("TipusError by grup: " + tipusErrorByGrup.stream().map(String::valueOf) // Converteix cada Integer a String
                .collect(Collectors.joining(", ")));
        log.info("TipusCancel by grup: " + tipusCancelByGrup.stream().map(String::valueOf) // Converteix cada Integer a String
                .collect(Collectors.joining(", ")));

        final String[] labelsX;
        final String[] valuesCreate;
        final String[] valuesFirmes;
        final String[] valuesOK;
        final String[] valuesCancel;
        final String[] valuesError;

        String titolApp;
        Where wAplicacio;
        if (aplicacio != null && !aplicacio.isBlank()) {
            wAplicacio = EstadisticaFields.USUARIAPLICACIOID.equal(aplicacio);
            titolApp = " (Aplicació: " + aplicacio + ")";
        } else {
            wAplicacio = EstadisticaFields.USUARIAPLICACIOID.isNotNull();
            titolApp = " (Totes les aplicacions)";
        }
        wAplicacio = Where.AND(wAplicacio, EstadisticaFields.USUARIENTITATID.isNull());

        Timestamp rangInicial;
        Timestamp rangFinal = null;

        switch (estadisticaRang) {
            case ANY: {
                mav.addObject("rang", " per l´any " + any + titolApp);

                labelsX = MESOS;
                valuesCreate = new String[labelsX.length];
                valuesFirmes = new String[labelsX.length];
                valuesOK = new String[labelsX.length];
                valuesCancel = new String[labelsX.length];
                valuesError = new String[labelsX.length];
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, any);

                rangInicial = null;

                for (int i = 0; i < 12; i++) {

                    calendar.set(Calendar.MONTH, i);

                    calendar.set(Calendar.DAY_OF_MONTH, 1);

                    calendar.set(Calendar.HOUR_OF_DAY, 0);
                    calendar.set(Calendar.MINUTE, 0);
                    calendar.set(Calendar.SECOND, 0);

                    if (rangInicial == null) {
                        rangInicial = new Timestamp(calendar.getTimeInMillis());
                    }

                    Timestamp from = new Timestamp(calendar.getTimeInMillis());

                    // Posar el darrer dia del mes actual. Per exemple per febre són 28 dies, per abril són 30 dies, etc.

                    calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));

                    calendar.set(Calendar.HOUR_OF_DAY, 23);
                    calendar.set(Calendar.MINUTE, 59);
                    calendar.set(Calendar.SECOND, 59);

                    rangFinal = new Timestamp(calendar.getTimeInMillis());

                    Timestamp to = new Timestamp(calendar.getTimeInMillis());

                    Where wData = EstadisticaFields.DATA.between(from, to);

                    if (isSuportedCreate) {
                        valuesCreate[i] = "" + estadisticaEjb.sumDecimal(EstadisticaFields.VALOR,
                                Where.AND(wData, EstadisticaFields.TIPUS.in(tipusCreateByGrup), wAplicacio));
                    }

                    if (isSupportedFirmes) {
                        valuesFirmes[i] = "" + estadisticaEjb.sumDecimal(EstadisticaFields.VALOR,
                                Where.AND(wData, EstadisticaFields.TIPUS.in(tipusFirmesByGrup), wAplicacio));
                    }

                    valuesOK[i] = "" + estadisticaEjb.sumDecimal(EstadisticaFields.VALOR,
                            Where.AND(wData, EstadisticaFields.TIPUS.in(tipusOkByGrup), wAplicacio));

                    if (isSupportedCancelStatus()) {
                        valuesCancel[i] = "" + estadisticaEjb.sumDecimal(EstadisticaFields.VALOR,
                                Where.AND(wData, EstadisticaFields.TIPUS.in(tipusCancelByGrup), wAplicacio));
                    }

                    valuesError[i] = "" + estadisticaEjb.sumDecimal(EstadisticaFields.VALOR,
                            Where.AND(wData, EstadisticaFields.TIPUS.in(tipusErrorByGrup), wAplicacio));
                }
            }
            break;
            case MES: {

                mav.addObject("rang", " pel mes de " + MESOS[mes - 1] + " de " + any + titolApp);

                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, any);
                calendar.set(Calendar.DAY_OF_MONTH, 1);
                calendar.set(Calendar.MONTH, mes - 1);

                rangInicial = new Timestamp(calendar.getTimeInMillis());
                rangFinal = null;

                int darrerDiaDeMes = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

                labelsX = new String[darrerDiaDeMes];
                valuesCreate = new String[labelsX.length];
                valuesFirmes = new String[labelsX.length];
                valuesOK = new String[labelsX.length];
                valuesCancel = new String[labelsX.length];
                valuesError = new String[labelsX.length];

                for (int i = 0; i < darrerDiaDeMes; i++) {

                    labelsX[i] = "" + (i + 1);

                    calendar.set(Calendar.DAY_OF_MONTH, i + 1);

                    calendar.set(Calendar.HOUR_OF_DAY, 0);
                    calendar.set(Calendar.MINUTE, 0);
                    calendar.set(Calendar.SECOND, 0);

                    rangFinal = new Timestamp(calendar.getTimeInMillis());

                    Timestamp from = new Timestamp(calendar.getTimeInMillis());

                    // Posar el darrer dia del mes actual. Per exemple per febre són 28 dies, per abril són 30 dies, etc.

                    calendar.set(Calendar.HOUR_OF_DAY, 23);
                    calendar.set(Calendar.MINUTE, 59);
                    calendar.set(Calendar.SECOND, 59);

                    Timestamp to = new Timestamp(calendar.getTimeInMillis());

                    Where wData = EstadisticaFields.DATA.between(from, to);
                    Where wTipus;

                    if (isSuportedCreate) {
                        wTipus = EstadisticaFields.TIPUS.in(tipusCreateByGrup);
                        Double sumCreate = estadisticaEjb.sumDecimal(EstadisticaFields.VALOR,
                                Where.AND(wData, wTipus, wAplicacio));
                        valuesCreate[i] = sumCreate == null ? "0" : ("" + sumCreate.intValue());
                    }

                    if (isSupportedFirmes) {
                        wTipus = EstadisticaFields.TIPUS.in(tipusFirmesByGrup);
                        Double sumFirmes = estadisticaEjb.sumDecimal(EstadisticaFields.VALOR,
                                Where.AND(wData, wTipus, wAplicacio));
                        valuesFirmes[i] = sumFirmes == null ? "0" : ("" + sumFirmes.intValue());
                    }

                    wTipus = EstadisticaFields.TIPUS.in(tipusOkByGrup);
                    Double sumOK = estadisticaEjb.sumDecimal(EstadisticaFields.VALOR,
                            Where.AND(wData, wTipus, wAplicacio));
                    valuesOK[i] = sumOK == null ? "0" : ("" + sumOK.intValue());

                    if (isSupportedCancelStatus()) {
                        wTipus = EstadisticaFields.TIPUS.in(tipusCancelByGrup);
                        Double sumCancel = estadisticaEjb.sumDecimal(EstadisticaFields.VALOR,
                                Where.AND(wData, wTipus, wAplicacio));
                        valuesCancel[i] = sumCancel == null ? "0" : ("" + sumCancel.intValue());
                    }

                    wTipus = EstadisticaFields.TIPUS.in(tipusErrorByGrup);
                    Double sumError = estadisticaEjb.sumDecimal(EstadisticaFields.VALOR,
                            Where.AND(wData, wTipus, wAplicacio));
                    valuesError[i] = sumError == null ? "0" : ("" + sumError.intValue());
                }
            }

            break;

            case DIA: {
                labelsX = new String[] { "00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00",
                        "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00",
                        "18:00", "19:00", "20:00", "21:00", "22:00", "23:00" };

                valuesCreate = new String[labelsX.length];
                valuesOK = new String[labelsX.length];
                valuesFirmes = new String[labelsX.length];
                valuesCancel = new String[labelsX.length];
                valuesError = new String[labelsX.length];

                mav.addObject("rang", " pel dia " + dia + " de " + MESOS[mes - 1] + " de " + any + titolApp);

                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, any);
                calendar.set(Calendar.DAY_OF_MONTH, dia);
                calendar.set(Calendar.MONTH, mes - 1);

                rangInicial = null;

                for (int i = 0; i < 24; i++) {

                    calendar.set(Calendar.HOUR_OF_DAY, i);
                    calendar.set(Calendar.MINUTE, 0);
                    calendar.set(Calendar.SECOND, 0);

                    if (rangInicial == null) {
                        rangInicial = new Timestamp(calendar.getTimeInMillis());
                    }

                    Timestamp from = new Timestamp(calendar.getTimeInMillis());

                    // Posar final de l'hora anterior

                    calendar.set(Calendar.HOUR_OF_DAY, i);
                    calendar.set(Calendar.MINUTE, 59);
                    calendar.set(Calendar.SECOND, 59);

                    rangFinal = new Timestamp(calendar.getTimeInMillis());

                    Timestamp to = new Timestamp(calendar.getTimeInMillis());

                    Where wData = EstadisticaFields.DATA.between(from, to);
                    Where wTipus;

                    if (isSuportedCreate) {

                        wTipus = EstadisticaFields.TIPUS.in(tipusCreateByGrup);
                        Double sumCreate = estadisticaEjb.sumDecimal(EstadisticaFields.VALOR,
                                Where.AND(wData, wTipus, wAplicacio));
                        valuesCreate[i] = sumCreate == null ? "0" : ("" + sumCreate.intValue());
                    }

                    if (isSupportedFirmes) {

                        wTipus = EstadisticaFields.TIPUS.in(tipusFirmesByGrup);
                        Double sumFirmes = estadisticaEjb.sumDecimal(EstadisticaFields.VALOR,
                                Where.AND(wData, wTipus, wAplicacio));
                        valuesFirmes[i] = sumFirmes == null ? "0" : ("" + sumFirmes.intValue());
                    }

                    wTipus = EstadisticaFields.TIPUS.in(tipusOkByGrup);
                    Double sumOK = estadisticaEjb.sumDecimal(EstadisticaFields.VALOR,
                            Where.AND(wData, wTipus, wAplicacio));
                    valuesOK[i] = sumOK == null ? "0" : ("" + sumOK.intValue());

                    wTipus = EstadisticaFields.TIPUS.in(tipusCancelByGrup);
                    Double sumCancel = estadisticaEjb.sumDecimal(EstadisticaFields.VALOR,
                            Where.AND(wData, wTipus, wAplicacio));
                    valuesCancel[i] = sumCancel == null ? "0" : ("" + sumCancel.intValue());

                    wTipus = EstadisticaFields.TIPUS.in(tipusErrorByGrup);
                    Double sumError = estadisticaEjb.sumDecimal(EstadisticaFields.VALOR,
                            Where.AND(wData, wTipus, wAplicacio));
                    valuesError[i] = sumError == null ? "0" : ("" + sumError.intValue());
                }
            }

            break;

            default:
                throw new IllegalArgumentException("Rang no suportat: " + estadisticaRang);
        }

        mav.addObject("categoria", getCategoriaTitol());
        mav.addObject("labels", labelsX);
        if (isSuportedCreate) {
            mav.addObject("valuesCreate", valuesCreate);
        }
        if (isSupportedFirmes) {
            mav.addObject("valuesFirmes", valuesFirmes);
        }

        mav.addObject("valuesOK", valuesOK);
        if (isSupportedCancelStatus()) {
            mav.addObject("valuesCancel", valuesCancel);
        }
        mav.addObject("valuesError", valuesError);
        mav.addObject("aplicacions", getUsuarisAplicacio(entitatID, rangInicial, rangFinal, totesLesEstadistiques));

        return mav;
    }

    public String getCreateLabel() {
        return "Creades";
    }

    public String getErrorLabel() {
        return "Errònies";
    }

    public String getCancelLabel() {
        return "Cancel·lades";
    }

    public String getOkLabel() {
        return "Correctes";
    }

    /**
     * 
     * @param entitatID
     * @param rangInicial
     * @param rangFinal
     * @param totesLesEstadistiques
     * @return
     * @throws I18NException
     */
    protected List<String> getUsuarisAplicacio(String entitatID, Timestamp rangInicial, Timestamp rangFinal,
            List<Integer> totesLesEstadistiques) throws I18NException {

        // Imprimier al log les variables d'entrada
        // XYZ DEBUG
        log.info("getUsuarisAplicacio - entitatID: " + entitatID);
        log.info("getUsuarisAplicacio - rangInicial: " + rangInicial);
        log.info("getUsuarisAplicacio - rangFinal: " + rangFinal);
        log.info("getUsuarisAplicacio - totesLesEstadistiques: " + Arrays.toString(totesLesEstadistiques.toArray()));

        List<String> usuaris = estadisticaEjb.executeQuery(
                new SelectDistinct<String>(EstadisticaFields.USUARIAPLICACIOID),
                Where.AND(EstadisticaFields.ENTITATID.equal(entitatID),
                        EstadisticaFields.DATA.between(rangInicial, rangFinal),
                        EstadisticaFields.TIPUS.in(totesLesEstadistiques)));
        return usuaris;
    }

}