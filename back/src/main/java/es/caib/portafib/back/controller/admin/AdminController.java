package es.caib.portafib.back.controller.admin;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.genapp.common.web.menuoptions.MenuItem;
import org.fundaciobit.genapp.common.web.menuoptions.MenuOption;
import org.fundaciobit.genapp.common.web.menuoptions.MenuOptionManager;
import org.fundaciobit.genapp.common.web.tiles.Tile;
import org.fundaciobit.genapp.common.web.tiles.TileAttribute;
import org.fundaciobit.genapp.common.web.tiles.TileType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.back.utils.Tab;
import es.caib.portafib.commons.utils.Constants;
import es.caib.portafib.logic.EstadisticaLogicaLocal;
import es.caib.portafib.logic.utils.PropietatGlobalUtil;

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
@MenuOption(
        labelCode = "=WebPages list",
        order = 1050,
        group = Tab.MENU_ADMIN,
        baseLink = "/admin/webpages",
        relativeLink = "")
@Tile(
        name = AdminController.KEYVALUE_ADMIN_TILE,
        extendsTile = Tab.MENU_ADMIN,
        contentJsp = "/WEB-INF/jsp/common/keyvalue.jsp",
        attributes = { @TileAttribute(name = "titol", value = "admin.admin") },
        type = TileType.ANOTHER)
@Tile(
        name = AdminController.KEYVALUE_PUBLIC_TILE,
        extendsTile = Tab.MENU_INICI,
        contentJsp = "/WEB-INF/jsp/common/keyvalue.jsp",
        attributes = { @TileAttribute(name = "titol", value = "public.public") },
        type = TileType.ANOTHER)
@Controller
public class AdminController {
    
    public static final String KEYVALUE_ADMIN_TILE = "keyvalueAdmin";
    
    public static final String KEYVALUE_PUBLIC_TILE = "keyvaluePublic";

    public static class KeyValueItem implements Comparable<KeyValueItem> {
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

    @RequestMapping(value = "/admin/webpages")
    public ModelAndView webPages(HttpSession session, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String[] tabs = { Tab.MENU_INICI, Tab.MENU_SOLI, Tab.MENU_DEST, Tab.MENU_DELE, Tab.MENU_COLA, Tab.MENU_REVI,
                Tab.MENU_ADEN, Tab.MENU_ADAPP, Tab.MENU_ADMIN };
        
        
        
        String title = "Llistat d'Opcions de Menú";
        String subtitle =
                "Per descarregar les URL a les pàgines web en format pla, feis clic aquí:"
                        + " <b><a target=\"_blank\"  href=\"" + request.getContextPath()
                        + "/admin/webpagesplain?includenew=true\" >[AMB NEW]</a></b>"
                        + " &nbsp;&nbsp;<b><a target=\"_blank\"  href=\"" + request.getContextPath()
                        + "/admin/webpagesplain?includenew=false\" >[SENSE NEW]</a></b>";
        String tile = KEYVALUE_ADMIN_TILE;
        
        return webPages(session, request, response, tabs, title, subtitle, tile);
        
    }
    
    
    @RequestMapping(value =  "/public/mapaweb")
    public ModelAndView mapWeb(HttpSession session, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        /*
        String[] tabs = { Tab.MENU_INICI, Tab.MENU_SOLI, Tab.MENU_DEST, Tab.MENU_DELE, Tab.MENU_COLA, Tab.MENU_REVI,
                Tab.MENU_ADEN, Tab.MENU_ADAPP, Tab.MENU_ADMIN };
        */
        
        
        
        LoginInfo loginInfo = LoginInfo.getInstance();
        Set<GrantedAuthority> roles= loginInfo.getRoles();
        
        List<String> tabsList = new ArrayList<>();
        
        /*
         *     public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_USUARI_TIPUS_I = "ROLE_USUARI_TIPUS_I";

    // ROLE Any (tothom)
    public static final String ROLE_ANY = "ROLE_ANY";
    // Destinatari
    public static final String ROLE_DEST = "ROLE_DEST";
    // Delegat
    public static final String ROLE_DELE = "ROLE_DELE";
    // Col·laborador
    public static final String ROLE_COLA = "ROLE_COLA";
    // Administrador d''Entitat
    public static final String ROLE_ADEN = "ROLE_ADEN";
    // Administrador d'Aplicacions
    public static final String ROLE_ADAPP = "ROLE_ADAPP";
    // Sol·licitant
    public static final String ROLE_SOLI = "ROLE_SOLI";
    // Revisor
    public static final String ROLE_REVI = "ROLE_REVI";
         */
        
     
        tabsList.add(Tab.MENU_INICI);
        
        
        
     // Mapeig de roles a tabs
        for (GrantedAuthority role : roles) {
            String roleName = role.getAuthority();
            switch (roleName) {
                case Constants.ROLE_ADMIN:
                    tabsList.add(Tab.MENU_ADMIN);
                    break;                
                case Constants.ROLE_ANY:
                    // Any usuari autenticat ve l'inici
                    tabsList.add(Tab.MENU_INICI);
                    break;
                case Constants.ROLE_USUARI_TIPUS_I:
                case Constants.ROLE_DEST:
                    tabsList.add(Tab.MENU_DEST);
                    break;
                case Constants.ROLE_DELE:
                    tabsList.add(Tab.MENU_DELE);
                    break;
                case Constants.ROLE_COLA:
                    tabsList.add(Tab.MENU_COLA);
                    break;
                case Constants.ROLE_ADEN:
                    tabsList.add(Tab.MENU_ADEN);
                    break;
                case Constants.ROLE_ADAPP:
                    tabsList.add(Tab.MENU_ADAPP);
                    break;
                case Constants.ROLE_SOLI:
                    tabsList.add(Tab.MENU_SOLI);
                    break;
                case Constants.ROLE_REVI:
                    tabsList.add(Tab.MENU_REVI);
                    break;
                default:
                    // Rol no reconegut, ignorar
                    break;
            }
        }
        
        // Eliminar duplicats i convertir a array
        String[] tabs = tabsList.stream().distinct().toArray(String[]::new);
        

        
        String title = I18NUtils.tradueix("label.mapaweb");
        String subtitle = "";
        String tile = KEYVALUE_PUBLIC_TILE;
        
        return webPages(session, request, response, tabs, title, subtitle, tile);
        
    }
    
    
    

    public static ModelAndView webPages(HttpSession session, HttpServletRequest request, HttpServletResponse response,
            String[] tabs, String title, String subtitle, String tile)
                throws Exception {
        List<KeyValueItem> keyValuelist = new ArrayList<KeyValueItem>();
        final String appUrl = PropietatGlobalUtil.getAppUrl();

        for (String tab : tabs) {
            List<MenuItem> items = MenuOptionManager.getMenuItems(tab);
            for (MenuItem item : items) {

                if (item == null) {
                    continue;
                }

                String label = item.getLabel();

                if (label.startsWith("=")) {
                    label = label.substring(1);
                } else {
                    label = I18NUtils.tradueix(label);
                }

                KeyValueItem keyValueItem = new KeyValueItem(label, appUrl + item.getUrl());

                keyValueItem.setPre(tab);

                keyValuelist.add(keyValueItem);

                // System.out.println("Tab: " + tab + ", Label: " + item.getLabel() + ", Link: " + item.getLink());
            }
        }

        ModelAndView mav = new ModelAndView(tile);
        mav.addObject("title", title);
        mav.addObject("subtitle", subtitle);

        mav.addObject("keyValueList", keyValuelist);
        return mav;
    }

    @RequestMapping(value = "/admin/webpagesplain")
    public void webPagesPlain(HttpSession session, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String includeNewStr = request.getParameter("includenew");

        boolean includeNew = "true".equalsIgnoreCase(includeNewStr);
        
        final String appUrl = PropietatGlobalUtil.getAppUrl();

        String[] tabs = { Tab.MENU_INICI, Tab.MENU_SOLI, Tab.MENU_DEST, Tab.MENU_DELE, Tab.MENU_COLA, Tab.MENU_REVI,
                Tab.MENU_ADEN, Tab.MENU_ADAPP, Tab.MENU_ADMIN };

        // Plain text response
        response.setContentType("text/plain");

        PrintWriter writer = response.getWriter();

        for (String tab : tabs) {

            Map<Class<?>, List<MenuItem>> fullInfo = MenuOptionManager.getMenuItemsByClass(tab);

            fullInfo.forEach((classe, items) -> {
                //writer.println("Class: " + classe.getName());
                for (MenuItem item : items) {
                    if (item == null) {
                        continue;
                    }
                    
                    writer.println(appUrl + item.getUrl());
                }

                // Si classe  té mètode isActiveFormNew i retorna true, afegir una URL amb /new
                if (includeNew) {

                    try {
                        if (classe.getMethod("isActiveFormNew") != null) {
                            Object obj = classe.getDeclaredConstructor().newInstance();
                            Boolean isActiveFormNew = (Boolean) classe.getMethod("isActiveFormNew").invoke(obj);

                            if (Boolean.TRUE.equals(isActiveFormNew)) {

                                // Cercar si classe té un mètode "String getContextWeb()"

                                if (classe.getMethod("getContextWeb") != null) {

                                    String contextWeb = (String) classe.getMethod("getContextWeb").invoke(obj);

                                    writer.println(appUrl + contextWeb + "/new");
                                }

                            }

                        }
                    } catch (NoSuchMethodException e) {
                        // No action needed, just skip
                    } catch (Throwable e) {
                        // No action needed, just skip
                        e.printStackTrace();
                    }
                }

            });

        }

        writer.println(appUrl + "/public/index.html");
        writer.println(appUrl + "/common/principal.html");

        writer.flush();
        writer.close();
    }

}
