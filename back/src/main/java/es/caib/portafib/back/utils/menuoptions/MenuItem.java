package es.caib.portafib.back.utils.menuoptions;

/**
 * 
 * @author anadal(u80067)
 *
 */
public class MenuItem {

    public final int order;

    public final String label;

    public final String url;

    public final String urlbase;

    public MenuItem(String label, String url) {
        this(label, url, url, 0);
    }

    public MenuItem(String label, String url, int order) {
        this(label, url, url, order);
    }

    public MenuItem(String label, String url, String urlbase) {
        this(label, url, urlbase, 0);
    }

    public MenuItem(String label, String url, String urlbase, int order) {
        super();
        this.label = label;
        this.url = url;
        this.urlbase = urlbase;
        this.order = order;
    }

    public String getLabel() {
        return label;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlbase() {
        return urlbase;
    }

    public int getOrder() {
        return order;
    }

    public static MenuItem retallaDarrerPath(String label, String url) {
        int i = url.lastIndexOf('/');

        return new MenuItem(label, url, url.substring(0, i));

    }

}
