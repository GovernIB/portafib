package es.caib.portafib.back.controller.dest.proces;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class CarretHolder {

    private static final String CARRET_ATTRIBUTE = "carretEstatsPeticio";

    public static Carret createCarret(HttpServletRequest request, Map<Long, Long> estatsPeticions) {
        HttpSession session = request.getSession();
        Carret carret = new Carret(estatsPeticions);
        session.setAttribute(CARRET_ATTRIBUTE, carret);
        return carret;
    }

    public static Carret getCarret(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Carret carret = (Carret) session.getAttribute(CARRET_ATTRIBUTE);
        if (carret == null) {
            throw new IllegalStateException("Carret no existeix");
        }
        return carret;
    }

    public static void removeCarret(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute(CARRET_ATTRIBUTE);
    }
}
