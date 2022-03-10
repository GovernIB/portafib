package es.caib.portafib.back.controller.aden;

import es.caib.portafib.back.controller.webdb.EstadisticaController;
import es.caib.portafib.back.form.aden.EstadisticaAdenFilterForm;
import es.caib.portafib.back.form.webdb.EstadisticaFilterForm;
import es.caib.portafib.back.form.webdb.EstadisticaForm;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.back.utils.PortaFIBDataExporter;
import es.caib.portafib.model.entity.Estadistica;
import es.caib.portafib.model.fields.EstadisticaFields;
import es.caib.portafib.utils.Configuracio;
import es.caib.portafib.utils.ConstantsV2;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.exportdata.DataExporterManager;
import org.fundaciobit.genapp.common.web.exportdata.IDataExporter;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.pluginsib.exportdata.ExportData;
import org.fundaciobit.pluginsib.exportdata.ExportFile;
import org.fundaciobit.pluginsib.exportdata.ExportItem;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 *
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/aden/estadistica")
@SessionAttributes(types = { EstadisticaForm.class, EstadisticaAdenFilterForm.class })
public class EstadisticaAdenController extends EstadisticaController {

  public static String SESSION_ESTADISTIQUES_PER = "SESSION_ESTADISTIQUES_PER";

  @Override
  public String getTileList() {
    return "estadisticaListAden";
  }

  @Override
  public String getSessionAttributeFilterForm() {
    return "EstadisticaAden_FilterForm";
  }

  @Override
  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    Where w = ENTITATID.equal(LoginInfo.getInstance().getEntitatID());
    if (isEstadistiquesPerUsrApp(request)) {
      return w;
    } else {
      return Where.AND(w, USUARIENTITATID.isNotNull());
    }
  }

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
    return true;
  }

  @Override
  public EstadisticaFilterForm getEstadisticaFilterForm(Integer pagina, ModelAndView mav,
      HttpServletRequest request) throws I18NException {

    EstadisticaFilterForm estadisticaFilterForm = super.getEstadisticaFilterForm(pagina, mav, request);
    EstadisticaAdenFilterForm filterForm;
    if (estadisticaFilterForm instanceof EstadisticaAdenFilterForm) {
      filterForm = (EstadisticaAdenFilterForm) estadisticaFilterForm;
    } else {
      filterForm = new EstadisticaAdenFilterForm(estadisticaFilterForm);
    }

    if (filterForm.isNou()) {

      filterForm.setVisibleExportList(true);

      filterForm.addHiddenField(PARAMETRES);
      filterForm.addHiddenField(ENTITATID);
      filterForm.addHiddenField(ESTADISTICAID);

      filterForm.setVisibleMultipleSelection(false);

      filterForm.setAddButtonVisible(false);

      filterForm.setDeleteButtonVisible(false);

      filterForm.setEditButtonVisible(false);

      filterForm.setItemsPerPage(-1);
      //filterForm.setAllItemsPerPage(null);

      filterForm.setAplicacio(true);

       { // FIXAR EL FILTRE DE DATES DEL PRINCIPI AL FINAL DEL MES ANTERIOR
          Calendar calendar = Calendar.getInstance(I18NUtils.getLocale());

          //Fixam que siguin les 00:00:00 000
          calendar.set(Calendar.HOUR_OF_DAY, 0);
          calendar.set(Calendar.MINUTE, 0);
          calendar.set(Calendar.SECOND, 0);
          calendar.set(Calendar.MILLISECOND, 0);

          // Anam un més enrera.
          calendar.add(Calendar.MONTH, -1);
          // Fixam el primer dia del mes
          calendar.set(Calendar.DAY_OF_MONTH, 1);

          // Això serà la data inicial
          filterForm.setDataDesde(new Timestamp(calendar.getTimeInMillis()));

          // Fixam el mes següent
          calendar.add(Calendar.MONTH, 1);
          // I llevam un milisegon perquè siguin les 23:59:59 999 del darrer dia del mes anterior
          calendar.add(Calendar.MILLISECOND, -1);

          filterForm.setDataFins(new Timestamp(calendar.getTimeInMillis()));
       }

      setEstadistiquesPerUsrApp(request, true);

      /*
       * filterForm.addAdditionalButton(new AdditionalButton("icon-signal",
       * "estadistica.grafica", "javascript:submitForm('" + request.getContextPath() +
       * getContextWeb() + "/grafic', true)", "btn-info"));
       */

      filterForm.addAdditionalButtonForEachItem(new AdditionalButton("icon-info-sign",
          "genapp.viewtitle", getContextWeb() + "/view/{0}", "btn-info"));

      filterForm.setGroupBy(EstadisticaFields.TIPUS.javaName);
      filterForm.setGroupValue(String.valueOf(ConstantsV2.ESTADISTICA_TIPUS_PETICIO_FINAL));

    }

    String groupBy = filterForm.getGroupBy();
    if (groupBy == null) {
      filterForm.setVisibleExportList(false);
      HtmlUtils.saveMessageWarning(request, I18NUtils.tradueix("estadistiques.info"));
    } else {
      filterForm.setVisibleExportList(true);
    }
    
    
    String dataPlugins = Configuracio.getExportDataPlugins();
    if (dataPlugins == null || dataPlugins.trim().isEmpty()) {
      HtmlUtils.saveMessageError(request, I18NUtils.tradueix("estadistiques.nopluginsdisponibles"));
    } else {
      if (DataExporterManager.getAllDataExporters().size() == 0) {
        HtmlUtils.saveMessageError(request, I18NUtils.tradueix("estadistiques.nopluginsdisponibles"));
      }
    }

    filterForm.getAdditionalButtons().clear();
    if (isEstadistiquesPerUsrApp(request)) {
      filterForm.setTitleCode("estadistiques.per.aplicacio");
      filterForm.addAdditionalButton(new AdditionalButton("icon-user",
          "estadistiques.per.persona", getContextWeb() + "/canvipersonaaplicacio/false",
          "btn-info"));

      filterForm.addHiddenField(USUARIENTITATID);
      filterForm.getHiddenFields().remove(USUARIAPLICACIOID);

    } else {
      filterForm.setTitleCode("estadistiques.per.persona");

      filterForm.addAdditionalButton(new AdditionalButton("icon-hdd",
          "estadistiques.per.aplicacio", getContextWeb() + "/canvipersonaaplicacio/true",
          "btn-info"));

      filterForm.addHiddenField(USUARIAPLICACIOID);
      filterForm.getHiddenFields().remove(USUARIENTITATID);
    }
    filterForm.addAdditionalButton(new AdditionalButton("icon-minus",
        "estadistiques.simples", getContextWeb() + "/search",
           "btn-info"));

     List<IDataExporter> allDataExporters = DataExporterManager.getAllDataExporters();
     mav.addObject("dataExporters", allDataExporters);
    if (filterForm.getExporter() == null && !allDataExporters.isEmpty()) {
       filterForm.setExporter(allDataExporters.get(0).getID());
    }

    return filterForm;
  }

  @RequestMapping(value = "/canvipersonaaplicacio/{isAplicacio}", method = RequestMethod.GET)
  public String canvipersonaaplicacio(HttpServletRequest request,
      HttpServletResponse response, @PathVariable("isAplicacio") java.lang.Boolean isAplicacio)
      throws Exception, I18NException {

    setEstadistiquesPerUsrApp(request, isAplicacio);
    return "redirect:" + getContextWeb() + "/list/";
  }

  protected boolean isEstadistiquesPerUsrApp(HttpServletRequest request) {
    Boolean value = (Boolean) request.getSession().getAttribute(SESSION_ESTADISTIQUES_PER);

    if (value == null) {
      return true;
    }

    return value.booleanValue();

  }

  protected void setEstadistiquesPerUsrApp(HttpServletRequest request, boolean value) {
    request.getSession().setAttribute(SESSION_ESTADISTIQUES_PER, Boolean.valueOf(value));
  }

  /*
   * @RequestMapping(value = "/grafic", method = RequestMethod.POST) public void
   * grafic(HttpServletRequest request, HttpServletResponse response, EstadisticaFilterForm
   * filterForm) throws Exception, I18NException {
   * 
   * ModelAndView mav = new ModelAndView(getTileList());
   * 
   * List<Estadistica> list = llistat(mav, request, filterForm);
   * 
   * Map<Date, Double> valorsPerData = agruparPerDia(list);
   * 
   * // XYDataset line_chart_dataset = new XYDataset(); // // XYDataset // // for
   * (Map.Entry<Date, Double> entry : valorsPerData.entrySet()) { //
   * line_chart_dataset.addValue(entry.getValue(), "items", entry.getKey()); // }
   * 
   * //JFreeChart chart = ChartFactory.createTimeSeriesChart("", "Data", "Items", //
   * line_chart_dataset, PlotOrientation.VERTICAL, false, false, false);
   * 
   * 
   * TimeSeries series = new TimeSeries("Random Data");
   * 
   * for (Map.Entry<Date, Double> entry : valorsPerData.entrySet()) {
   * //line_chart_dataset.addValue(entry.getValue(), "items", entry.getKey()); series.add(new
   * org.jfree.data.time.Day(entry.getKey()), entry.getValue()); }
   * 
   * 
   * String groupBy = filterForm.getGroupBy(); String titol; if (groupBy == null) { titol =
   * "Estadistiques"; } else {
   * 
   * // GROUP BY String value = filterForm.getGroupValue();
   * 
   * if (TIPUS.javaName.equals(groupBy)) {
   * 
   * if ("0".equals(value)) { titol = I18NUtils.tradueix("estadistica.peticiofirma.inici"); }
   * else if( "1".equals(value)) { titol =
   * I18NUtils.tradueix("estadistica.peticiofirma.final"); } else { titol = "Valor desconegut("
   * + value +") per l'agrupació " + TIPUS.javaName; }
   * 
   * } else if (USUARIAPLICACIOID.javaName.equals(groupBy)) {
   * 
   * titol = I18NUtils.tradueix(UsuariAplicacioFields._TABLE_MODEL + "." +
   * UsuariAplicacioFields._TABLE_MODEL) + ": " + value;
   * 
   * } else { log.info("\n\n  GROUPBY => " + filterForm.getGroupBy() + "\n\n");
   * log.info("\n\n  GROUPBY VALUE => " + filterForm.getGroupValue() + "\n\n"); titol =
   * "Valor de filter desconegut ["+ groupBy+"]"; log.warn(titol, new Exception()); }
   * 
   * // FILTER String filterUsrApp = filterForm.getUsuariAplicacioID(); if (filterUsrApp !=
   * null) { titol = titol + " / " + I18NUtils.tradueix(UsuariAplicacioFields._TABLE_MODEL +
   * "." + UsuariAplicacioFields._TABLE_MODEL) + ": " + filterUsrApp; } }
   * 
   * 
   * 
   * 
   * 
   * TimeSeriesCollection data = new TimeSeriesCollection(series); JFreeChart chart =
   * ChartFactory.createScatterPlot(titol, "Data", "Items", data, PlotOrientation.VERTICAL,
   * false, false, false);
   * 
   * 
   * //final String dateFormat = "yyyy/MM/dd";
   * 
   * 
   * I18NCommonDateFormat df = new I18NCommonDateFormat(I18NUtils.getLocale());
   * 
   * XYPlot plot = (XYPlot) chart.getPlot(); DateAxis axis = new DateAxis();
   * axis.setDateFormatOverride(df.getSimpleDateFormat(I18NUtils.getLocale()));
   * //.toPattern()new SimpleDateFormat(dateFormat)); plot.setDomainAxis(axis);
   * 
   * //DateAxis axis = (DateAxis) plot.getDomainAxis(); //axis.setDateFormatOverride(new
   * SimpleDateFormat(dateFormat));
   * 
   * response.setContentType("image/jpeg"); response.setHeader("Content-Disposition",
   * "attachment; filename=\"grafic.jpeg\"");
   * 
   * ChartUtilities.writeChartAsJPEG(response.getOutputStream(), chart, 700, 400);
   * 
   * }
   */

  @RequestMapping(value = "/search", method = RequestMethod.GET)
  public ModelAndView search(HttpServletRequest request, HttpServletResponse response) throws I18NException {
    ModelAndView mav = new ModelAndView("estadisticaSearchAden");
    EstadisticaFilterForm filterForm = getEstadisticaFilterForm(1, mav, request);

    filterForm.getAdditionalButtons().clear();
    filterForm.addAdditionalButton(new AdditionalButton("icon-plus",
           "estadistiques.complexes", getContextWeb() + "/list",
           "btn-info"));
    llistat(mav, request, filterForm);
    return mav;
  }

  @RequestMapping(value = "/search", method = RequestMethod.POST)
  public String search(HttpServletRequest request, HttpServletResponse response,
                       @ModelAttribute EstadisticaFilterForm filterForm) throws I18NException {
    setEstadistiquesPerUsrApp(request, ((EstadisticaAdenFilterForm)filterForm).getAplicacio());
    return "redirect:" + getContextWeb() + "/search";
  }

  /*
  @RequestMapping(value = "/search", method = RequestMethod.POST)
  public ModelAndView search(HttpServletRequest request, HttpServletResponse response, @ModelAttribute EstadisticaFilterForm filterForm) throws I18NException {
    ModelAndView mav = new ModelAndView("estadisticaSearchAden");
    llistat(mav, request, getEstadisticaFilterForm(1, mav, request));
    return mav;
  }*/

  @Override
  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
      HttpServletRequest request, HttpServletResponse response,
      EstadisticaFilterForm filterForm) throws Exception, I18NException {

    setEstadistiquesPerUsrApp(request, ((EstadisticaAdenFilterForm)filterForm).getAplicacio());

    int itemsInicials = filterForm.getItemsPerPage();
    
    filterForm.setItemsPerPage(-1);

    ModelAndView mav = new ModelAndView(getTileList());

    List<Estadistica> estadistiques = llistat(mav, request, filterForm);
    
    filterForm.setItemsPerPage(itemsInicials);

    // CHECK que existeix
    // int tipus = Integer.parseInt(filterForm.getGroupValue());

    boolean isUsrApp = isEstadistiquesPerUsrApp(request);

    Set<String> usuarisSet = new TreeSet<String>();

    Map<String, Map<String, Double>> agrupades = agruparPerMes(estadistiques, usuarisSet,
        isUsrApp);

    List<String> usuaris = new ArrayList<String>(usuarisSet);

    Set<String> dates = agrupades.keySet();

    PortaFIBDataExporter dataExporter = (PortaFIBDataExporter) DataExporterManager
        .getByID(dataExporterID);

    String[] titles = new String[dates.size() + 1]; // 1 columna per afegir noms de usuaris

    ExportItem[][] tableItems = new ExportItem[usuaris.size()][titles.length];

    if (usuaris.size() > 0) {

      for (int u = 0; u < usuaris.size(); u++) {
        String usr = usuaris.get(u);
        tableItems[u][0] = new ExportItem(usr, usr);
      }

      int titlePos = 1;
      for (String mes : dates) {
        titles[titlePos] = mes;

        Map<String, Double> valorsPerUsuari = agrupades.get(mes);

        for (int u = 0; u < usuaris.size(); u++) {
          String usr = usuaris.get(u);

          Double valor = valorsPerUsuari.get(usr);

          if (valor == null) {
            valor = 0.0;
          }

          tableItems[u][titlePos] = new ExportItem(String.valueOf(valor), valor);
        }

        titlePos++;
      }

    } else {
      titles[0] = I18NUtils.tradueix("estadistiques.buid");
    }
    ExportData data = new ExportData(titles, tableItems);

    ExportFile exportFile = dataExporter.getExportDataPlugin().getExportFile(data);

    response.setContentType(exportFile.getContentType());
    response.setHeader("Content-Disposition", "inline; filename=\"" + exportFile.getFilename()
        + "\"");
    response.setContentLength((int) exportFile.getData().length);

    OutputStream output = response.getOutputStream();

    output.write(exportFile.getData());

    output.flush();

    output.close();

  }

  // Map<MES-ANY, Map<USUARI, Double>>
  private Map<String, Map<String, Double>> agruparPerMes(List<Estadistica> list,
      Set<String> usuaris, boolean isUsrApp) {

    Map<String, Map<String, Double>> valorsPerData = new TreeMap<String, Map<String, Double>>();

    String usuari;
    for (Estadistica estadistica : list) {

      if (isUsrApp) {
        usuari = estadistica.getUsuariAplicacioID();
      } else {
        if (estadistica.getUsuariEntitatID() == null) {
          continue;
        } else {
          usuari = estadistica.getUsuariEntitatID();
        }
      }
      usuaris.add(usuari);

      Calendar cal = Calendar.getInstance();
      cal.setTime(new Date(estadistica.getData().getTime()));
      int year = cal.get(Calendar.YEAR);
      int month = cal.get(Calendar.MONTH) + 1;

      String onlyDate = year + "-" + String.format("%02d", month);

      Map<String, Double> estPerMes = valorsPerData.get(onlyDate);

      if (estPerMes == null) {
        estPerMes = new HashMap<String, Double>();
        valorsPerData.put(onlyDate, estPerMes);
      }

      Double sum = estPerMes.get(usuari);

      if (sum == null) {
        estPerMes.put(usuari, estadistica.getValor());
      } else {
        estPerMes.put(usuari, sum + estadistica.getValor());
      }

    }

    return valorsPerData;
  }

  @Override
  public List<StringKeyValue> getReferenceListForTipus(HttpServletRequest request,
      ModelAndView mav, Where where) throws I18NException {

    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();

    final int[] tipus = { ConstantsV2.ESTADISTICA_TIPUS_PETICIO_INICI,
        ConstantsV2.ESTADISTICA_TIPUS_PETICIO_FINAL,
        ConstantsV2.ESTADISTICA_TIPUS_PETICIO_REBUTJADA,
        ConstantsV2.ESTADISTICA_TIPUS_PETICIO_FIRMES};

    for (int i = 0; i < tipus.length; i++) {
      __tmp.add(new StringKeyValue(String.valueOf(tipus[i]), I18NUtils
          .tradueix("estadistica.peticiofirma." + tipus[i])));
    }

    return __tmp;
  }

}
