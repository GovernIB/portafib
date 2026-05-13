package es.caib.portafib.back.controller.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.fundaciobit.genapp.common.web.menuoptions.MenuOption;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import es.caib.portafib.back.utils.Tab;
import es.caib.portafib.logic.EstadisticaLogicaLocal;

/**
 * AdminController
 * @author anadal (u80067)
 * 12 may 2026 8:26:09
 */
@MenuOption(
        labelCode = "=Size of database tables",
        order = 156,
        group = Tab.MENU_ADMIN,
        baseLink = "/admin/sizeofdatabasetables",
        relativeLink = "")
@Controller
public class AdminController {

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

    @EJB(mappedName = EstadisticaLogicaLocal.JNDI_NAME)
    EstadisticaLogicaLocal estadisticaLogicaEjb;

    @RequestMapping(value = "/admin/sizeofdatabasetables")
    public ModelAndView tablesize(HttpSession session, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Map<String, Long> sizes = estadisticaLogicaEjb.getTableSizes();

        List<KeyValueItem> keyValuelist = new ArrayList<KeyValueItem>();

        for (Map.Entry<String, Long> entry : sizes.entrySet()) {
            keyValuelist.add(new KeyValueItem((String) entry.getKey(), entry.getValue() + " bytes",
                    "<i class=\"fas fa-database\"></i>", humanReadableByteCount(entry.getValue())));
        }

        ModelAndView mav = new ModelAndView("keyvalueAdmin");
        mav.addObject("title", "Size of database tables");
        mav.addObject("subtitle", "");
        mav.addObject("keyValueList", keyValuelist);
        return mav;
    }

    public static String humanReadableByteCount(long bytes) {
        int unit = 1024;
        if (bytes < unit) {
            return bytes + " B";
        }
        int exp = (int) (Math.log(bytes) / Math.log(unit));
        String prefix = "KMGTPE".charAt(exp - 1) + "B"; // KB, MB, GB, TB, PB, EB
        return String.format("%.2f %s", bytes / Math.pow(unit, exp), prefix);
    }

}
