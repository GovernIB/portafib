package es.caib.portafib.back.controller.dest;

import es.caib.portafib.back.controller.dest.proces.Carret;
import es.caib.portafib.back.controller.dest.proces.CarretHolder;
import es.caib.portafib.back.controller.dest.proces.ProcessarEstatsCheckoutController;
import es.caib.portafib.back.form.webdb.EstatDeFirmaFilterForm;
import es.caib.portafib.back.utils.Utils;
import es.caib.portafib.utils.ConstantsV2;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Controller per gestionar un EstatDeFirma Pendent
 * 
 * @author anadal
 */
@Controller
@RequestMapping(value = ConstantsV2.CONTEXT_DEST_ESTATFIRMA_PENDENT)
@SessionAttributes(types = { EstatDeFirmaFilterForm.class })
public class EstatFirmaPendentDestController extends EstatFirmaAbstractDestController {

  @Override
  public int getFilterType() {
    return FILTRAR_PER_PENDENT;
  }

  // Métodes per accions amb el carret

  @RequestMapping(value = "/processar/inici", method = RequestMethod.POST)
  public ModelAndView processarInici(HttpServletRequest request, HttpServletResponse response,
                                     @ModelAttribute EstatDeFirmaFilterForm filterForm) throws I18NException {
    // seleccionats conté els estatIDs
    String[] seleccionatsStr = filterForm.getSelectedItems();
    if (seleccionatsStr == null || seleccionatsStr.length == 0) {
      HtmlUtils.saveMessageWarning(request, I18NUtils.tradueix("carret.processar.cap"));
      return new ModelAndView(new RedirectView(getContextWeb() + "/list", true));
    }

    List<Long> idsEstat = parseLongArray(seleccionatsStr);
    Map<Long, Long> estatPeticio = getEstatsPeticioMap(idsEstat);

    Carret carret = CarretHolder.createCarret(request, estatPeticio);
    return getModelAndViewCarret(carret);
  }

  @RequestMapping(value = "/processar/afegirFirmar", method = RequestMethod.GET)
  public ModelAndView processarAfegirFirmar(HttpServletRequest request, HttpServletResponse response) {
    Carret carret = CarretHolder.getCarret(request);
    carret.addEstatFirmar();
    return getNextModelAndViewCarret(carret);
  }

  @RequestMapping(value = "/processar/ignorar", method = RequestMethod.GET)
  public ModelAndView processarIgnorar(HttpServletRequest request, HttpServletResponse response) {
    Carret carret = CarretHolder.getCarret(request);
    return getNextModelAndViewCarret(carret);
  }

  @RequestMapping(value = "/processar/afegirRebutjar", method = RequestMethod.GET)
  public ModelAndView processarAfegirRebutjar(HttpServletRequest request, HttpServletResponse response,
                                              @RequestParam String motiu) {
    Carret carret = CarretHolder.getCarret(request);

    if (motiu == null || motiu.isEmpty()) {
      HtmlUtils.saveMessageError(request, I18NUtils.tradueix("carret.processar.motiuRequerit"));
      return getModelAndViewCarret(carret);
    }

    carret.addEstatRebuig(motiu);
    return getNextModelAndViewCarret(carret);
  }

  @RequestMapping(value = "/processar/cancelar", method = RequestMethod.GET)
  public ModelAndView processarCancelar(HttpServletRequest request, HttpServletResponse response) {
    CarretHolder.removeCarret(request);
    HtmlUtils.saveMessageWarning(request, I18NUtils.tradueix("carret.processar.cancelat"));
    return new ModelAndView(new RedirectView(getContextWeb() + "/list", true));
  }

  @RequestMapping(value = "/processar/executar", method = RequestMethod.POST)
  public ModelAndView processarExecutar(HttpServletRequest request, HttpServletResponse response,
                                        @RequestParam("url_user") String baseUrlFull)
          throws I18NException {
    String baseUrl = Utils.getUrlBaseFromFullUrl(request, baseUrlFull);

    Carret carret = CarretHolder.getCarret(request);

    // ja el podem llevar de la sessió
    CarretHolder.removeCarret(request);

    // Feim el rebuig primer
    Map<Long, String> estatsRebuig = carret.getEstatsRebuig();
    for (Long estatId : estatsRebuig.keySet()) {
      String motiu = estatsRebuig.get(estatId);
      Long peticioId = carret.getEstatsPeticions().get(estatId);
      rebutjarInternal(request, response, estatId, peticioId, motiu);
    }

    Set<Long> estatsFirmar = carret.getEstatsFirmar();
    // No hi res que signar, ja hem acabat
    if (estatsFirmar.isEmpty()) {
      HtmlUtils.saveMessageWarning(request, I18NUtils.tradueix("carret.processar.finalitzat"));
      return new ModelAndView(new RedirectView(getContextWeb() + "/list", true));
    }

    // Agafam el map de estat/peticio a firmar
    Map<Long, Long> estatsPeticioFirmar = new HashMap<Long, Long>();
    for (Long estat : estatsFirmar) {
      estatsPeticioFirmar.put(estat, carret.getEstatsPeticions().get(estat));
    }

    return firmarSeleccionatsInternal(request, response, estatsPeticioFirmar, baseUrl);
  }

  // Mètodes d'utilitat pel process del carret

  private ModelAndView getNextModelAndViewCarret(Carret carret) {
    if (carret.hasNext()) {
      carret.next();
      String url = getContextWeb() + "/fullView/" + carret.getEstat() + "/" + carret.getPeticio();
      return new ModelAndView(new RedirectView(url, true));
    }

    return new ModelAndView(new RedirectView(ProcessarEstatsCheckoutController.CONTEXT_WEB + "/list", true));
  }

  private ModelAndView getModelAndViewCarret(Carret carret) {
    String url = getContextWeb() + "/fullView/" + carret.getEstat() + "/" + carret.getPeticio();
    RedirectView view = new RedirectView(url, true);
    return new ModelAndView(view);
  }
}
