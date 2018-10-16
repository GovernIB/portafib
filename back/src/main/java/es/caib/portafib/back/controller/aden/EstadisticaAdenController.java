package es.caib.portafib.back.controller.aden;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NCommonDateFormat;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.portafib.back.controller.webdb.EstadisticaController;
import es.caib.portafib.back.form.webdb.EstadisticaFilterForm;
import es.caib.portafib.back.form.webdb.EstadisticaForm;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.jpa.EstadisticaJPA;
import es.caib.portafib.model.entity.Estadistica;
import es.caib.portafib.model.fields.UsuariAplicacioFields;

/**
 *
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/aden/estadistica")
@SessionAttributes(types = { EstadisticaForm.class, EstadisticaFilterForm.class })
public class EstadisticaAdenController extends EstadisticaController {

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
    return ENTITATID.equal(LoginInfo.getInstance().getEntitatID());
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

    EstadisticaFilterForm filterForm = super.getEstadisticaFilterForm(pagina, mav, request);

    if (filterForm.isNou()) {

      filterForm.setVisibleExportList(true);

      filterForm.addHiddenField(SUBTIPUS);
      filterForm.addHiddenField(PARAMETRES);
      filterForm.addHiddenField(ENTITATID);
      filterForm.addHiddenField(ESTADISTICAID);

      filterForm.setVisibleMultipleSelection(false);

      filterForm.setAddButtonVisible(false);

      filterForm.setDeleteButtonVisible(false);

      filterForm.setEditButtonVisible(false);
      
      filterForm.setItemsPerPage(5);

      filterForm.addAdditionalButton(new AdditionalButton("icon-signal", "estadistica.grafica",
          "javascript:submitForm('" + request.getContextPath() + getContextWeb() + "/grafic', true)", "btn-info"));
      
      
      filterForm.addAdditionalButtonForEachItem(new AdditionalButton("icon-info-sign", "genapp.viewtitle",
           getContextWeb() + "/view/{0}", "btn-info"));

    }

    return filterForm;
  }

  @RequestMapping(value = "/grafic", method = RequestMethod.POST)
  public void grafic(HttpServletRequest request, HttpServletResponse response,
      EstadisticaFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());

    List<Estadistica> list = llistat(mav, request, filterForm);

    Map<Date, Double> valorsPerData = agruparPerDia(list);

//    XYDataset line_chart_dataset = new XYDataset();
//    
//    XYDataset
//
//    for (Map.Entry<Date, Double> entry : valorsPerData.entrySet()) {
//      line_chart_dataset.addValue(entry.getValue(), "items", entry.getKey());
//    }

    //JFreeChart chart = ChartFactory.createTimeSeriesChart("", "Data", "Items",
    //    line_chart_dataset, PlotOrientation.VERTICAL, false, false, false);
    
    
    TimeSeries series = new TimeSeries("Random Data");
    
    for (Map.Entry<Date, Double> entry : valorsPerData.entrySet()) {
      //line_chart_dataset.addValue(entry.getValue(), "items", entry.getKey());
      series.add(new org.jfree.data.time.Day(entry.getKey()), entry.getValue());
    }
    
    
    String groupBy = filterForm.getGroupBy();
    String titol;
    if (groupBy == null) {
      titol = "Estadistiques";
    } else {
      
      // GROUP BY
      String value = filterForm.getGroupValue();
      
      if (TIPUS.javaName.equals(groupBy)) {
        
        if ("0".equals(value)) {
          titol = I18NUtils.tradueix("estadistica.peticiofirma.inici");
        } else if( "1".equals(value)) {
          titol = I18NUtils.tradueix("estadistica.peticiofirma.final");
        } else {
          titol = "Valor desconegut(" + value +") per l'agrupació " + TIPUS.javaName;
        }
        
      } else if (USUARIAPLICACIOID.javaName.equals(groupBy)) {
        
        titol = I18NUtils.tradueix(UsuariAplicacioFields._TABLE_MODEL + "." + UsuariAplicacioFields._TABLE_MODEL)
            + ": " + value;
        
      } else {
        log.info("\n\n  GROUPBY => " + filterForm.getGroupBy() + "\n\n");
        log.info("\n\n  GROUPBY VALUE => " + filterForm.getGroupValue() + "\n\n");
        titol = "Valor de filter desconegut ["+ groupBy+"]";
        log.warn(titol, new Exception());
      }
      
      // FILTER
      String filterUsrApp = filterForm.getUsuariAplicacioID();
      if (filterUsrApp != null) {
        titol = titol + " / " + I18NUtils.tradueix(UsuariAplicacioFields._TABLE_MODEL + "." + UsuariAplicacioFields._TABLE_MODEL)
            + ": " + filterUsrApp;
      }
    }
    
    
    
    

    TimeSeriesCollection data = new TimeSeriesCollection(series);
    JFreeChart chart = ChartFactory.createScatterPlot(titol, "Data", "Items", data,
                                                      PlotOrientation.VERTICAL, 
                                                      false, false, false);
    
    
    //final String dateFormat = "yyyy/MM/dd";
    
    
    I18NCommonDateFormat df = new I18NCommonDateFormat(I18NUtils.getLocale());
    
    XYPlot plot = (XYPlot) chart.getPlot();
    DateAxis axis = new DateAxis();
    axis.setDateFormatOverride(df.getSimpleDateFormat(I18NUtils.getLocale())); //.toPattern()new SimpleDateFormat(dateFormat));
    plot.setDomainAxis(axis);
    
    //DateAxis axis = (DateAxis) plot.getDomainAxis();
    //axis.setDateFormatOverride(new SimpleDateFormat(dateFormat));

    response.setContentType("image/jpeg");
    response.setHeader("Content-Disposition", "attachment; filename=\"grafic.jpeg\"");

    ChartUtilities.writeChartAsJPEG(response.getOutputStream(), chart, 700, 400);

  }

  @Override
  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
      HttpServletRequest request, HttpServletResponse response,
      EstadisticaFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());

    List<Estadistica> list = llistat(mav, request, filterForm);

    Field<?>[] allFields = { DATA, VALOR }; // ALL_ESTADISTICA_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    // __mapping.put(TIPUS, filterForm.getMapOfValuesForTipus());
    // __mapping.put(ENTITATID, filterForm.getMapOfEntitatForEntitatID());

    Map<Date, Double> valorsPerData = agruparPerDia(list);

    List<Estadistica> listExport;
    listExport = new ArrayList<Estadistica>();
    for (Date onlyDate : valorsPerData.keySet()) {
      Estadistica e = new EstadisticaJPA();
      e.setData(new Timestamp(onlyDate.getTime()));
      e.setValor(valorsPerData.get(onlyDate));
      listExport.add(e);
    }

    exportData(request, response, dataExporterID, filterForm, listExport, allFields,
        __mapping, PRIMARYKEY_FIELDS);
  }

  private Map<Date, Double> agruparPerDia(List<Estadistica> list) {

    Map<Date, Double> valorsPerData = new HashMap<Date, Double>();

    for (Estadistica estadistica : list) {

      Double valor = estadistica.getValor();

      Calendar cal = Calendar.getInstance(); // locale-specific
      cal.setTime(new Date(estadistica.getData().getTime()));
      cal.set(Calendar.HOUR_OF_DAY, 0);
      cal.set(Calendar.MINUTE, 0);
      cal.set(Calendar.SECOND, 0);
      cal.set(Calendar.MILLISECOND, 0);

      Date onlyDate = cal.getTime();

      Double sum = valorsPerData.get(onlyDate);

      if (sum == null) {
        valorsPerData.put(onlyDate, valor);
      } else {
        valorsPerData.put(onlyDate, sum + valor);
      }

    }

    return valorsPerData;
  }

  @Override
  public List<StringKeyValue> getReferenceListForTipus(HttpServletRequest request,
      ModelAndView mav, Where where) throws I18NException {
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
    __tmp.add(new StringKeyValue("0", I18NUtils.tradueix("estadistica.peticiofirma.inici")));
    __tmp.add(new StringKeyValue("1", I18NUtils.tradueix("estadistica.peticiofirma.final")));
    return __tmp;
  }

}
