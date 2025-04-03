package es.caib.portafib.back.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.genapp.common.web.menuoptions.MenuOption;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import es.caib.portafib.back.utils.Tab;
import es.caib.portafib.commons.utils.Configuracio;
/**
 * 
 * @author anadal
 * 2 abr 2025 14:11:10
 */
@MenuOption(
        labelCode = "propietat.reload",
        order = 1040,
        group = Tab.MENU_ADMIN,
        baseLink = "/admin/reloadproperties",
        relativeLink = "")
@Controller
public class ReloadFilePropertiesAdminController {

    /*
    @RequestMapping(value = "/admin/systemproperties")
    public ModelAndView systemproperties(HttpSession session, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Properties prop = Configuracio.getPortaFIBSystemProperties();

        List<KeyValueItem> keyValuelist = new ArrayList<KeyValueItem>();

        for (Object key : prop.keySet()) {
            keyValuelist.add(new KeyValueItem((String) key, "***************"));
        }

        Collections.sort(keyValuelist);

        ModelAndView mav = new ModelAndView("keyvalueAdmin");
        mav.addObject("title", "Item list of demogenapp.system.properties file");
        mav.addObject("subtitle", "");
        mav.addObject("keyValueList", keyValuelist);
        return mav;
    }
    */

    @RequestMapping(value = "/admin/reloadproperties")
    public String reloadproperties(HttpSession session, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Configuracio.reloadProperties();
        
        HtmlUtils.saveMessageSuccess(request, I18NUtils.tradueix("propietat.reload.ok"));

        return "redirect:/admin/";
    }

    public class KeyValueItem implements Comparable<KeyValueItem> {
        private String key;
        private String value;
        private String pre;
        private String post;

        public KeyValueItem(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public KeyValueItem(String key, String value, String pre, String post) {
            this.key = key;
            this.value = value;
            this.pre = pre;
            this.post = post;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getPre() {
            return pre;
        }

        public void setPre(String pre) {
            this.pre = pre;
        }

        public String getPost() {
            return post;
        }

        public void setPost(String post) {
            this.post = post;
        }

        @Override
        public int compareTo(KeyValueItem o2) {
            return this.getKey().compareTo(o2.getKey());
        }
    }
}
