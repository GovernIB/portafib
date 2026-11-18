package es.caib.portafib.back.controller.admin;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.ITableManager;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.menuoptions.MenuOption;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.portafib.back.controller.webdb.FitxerController;
import es.caib.portafib.back.form.webdb.FitxerFilterForm;
import es.caib.portafib.back.form.webdb.FitxerForm;
import es.caib.portafib.back.utils.Tab;
import es.caib.portafib.model.bean.FitxerBean;
import es.caib.portafib.model.entity.Fitxer;
import es.caib.portafib.persistence.FitxerJPA;

/**
 *  * Llistat de fitxers orfes al disc dur. Es a dir, els fitxers que existeixen físicament però no tenen una entrada a la BBDD.
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/admin/fitxersorfesdiscdur")
@SessionAttributes(types = { FitxerForm.class, FitxerFilterForm.class })
@MenuOption(group = Tab.MENU_ADMIN, labelCode = "fitxers.orfes.discdur", order = 153, addSeparatorBefore = true)
public class FitxersOrfesDiscDurAdminController extends FitxerController {


    @Override
    public String getTileList() {
        return "fitxersOrfesList";
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
        return true;
    }

    @Override
    public boolean isActiveFormView() {
        return false;
    }

    @Override
    public FitxerJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long fitxerID) throws I18NException {

        FitxerJPA fitxer = new FitxerJPA();
        fitxer.setFitxerID(fitxerID);
        fitxer.setNom(fitxerID.toString());

        return fitxer;
    }

    @Override
    public void delete(HttpServletRequest request, Fitxer fitxer) throws I18NException {
        FileSystemManager.eliminarArxiu(fitxer.getFitxerID());
    }

    @Override
    @RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
    public String deleteSelected(HttpServletRequest request, HttpServletResponse response,
            @ModelAttribute
            FitxerFilterForm filterForm) throws Exception {

        if (!isActiveDelete()) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }

        String[] seleccionats = filterForm.getSelectedItems();
        String redirect = null;
        if (seleccionats != null && seleccionats.length != 0) {
            for (int i = 0; i < seleccionats.length; i++) {
                FileSystemManager.eliminarArxiu(stringToPK(seleccionats[i]));
            }
        }
        if (redirect == null) {
            redirect = getRedirectWhenDelete(request, null, null);
        }

        return redirect;
    }

    @Override
    public FitxerFilterForm getFitxerFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
            throws I18NException {
        FitxerFilterForm fitxerFilterForm;
        fitxerFilterForm = (FitxerFilterForm) super.getFitxerFilterForm(pagina, mav, request);

        if (fitxerFilterForm.isNou()) {
            fitxerFilterForm.setTitleCode("fitxers.orfes.discdur");
            fitxerFilterForm.setSubTitleCode(
                    "=Són fitxers que existeixen físicament al disc dur però no tenen una entrada a la BBDD.");
            fitxerFilterForm.setAddButtonVisible(false);
            fitxerFilterForm.setItemsPerPage(null);
            fitxerFilterForm.setAllItemsPerPage(null);
            fitxerFilterForm.setEditButtonVisible(false);

        }

        return fitxerFilterForm;
    }

    @Override
    public List<Fitxer> executeSelect(ITableManager<Fitxer, Long> ejb, Where where, final OrderBy[] orderBy,
            final Integer itemsPerPage, final int inici) throws I18NException {

        // Fitxers que hi ha en disc dur
        String[] fitxersFisics = FileSystemManager.getAllFileNames();

        // Fitxers que hi ha en BBDD
        List<Long> fitxersBBDDList = fitxerEjb.executeQuery(FITXERID, new OrderBy(FITXERID));

        Set<Long> fitxersBBDD = new HashSet<>(fitxersBBDDList);

        // Fitxers que existeixen fisicament però no en BBDD
        List<Fitxer> fitxersOrfes = new ArrayList<>();
        for (String fdd : fitxersFisics) {
            Long fitxerID = Long.parseLong(fdd);
            if (!fitxersBBDD.contains(fitxerID)) {
                Fitxer fitxer = new FitxerBean();
                fitxer.setNom(fdd);
                fitxer.setFitxerID(fitxerID);
                fitxersOrfes.add(fitxer);
            }
        }

        // Ordenar per fitxerID ascendent
        fitxersOrfes.sort((f1, f2) -> Long.compare(f1.getFitxerID(), f2.getFitxerID()));

        return fitxersOrfes;

    }

}
