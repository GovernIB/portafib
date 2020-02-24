package es.caib.apisib.apifirmasimple.example.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = FirmaEnServidorController.CONTEXTWEB)
@SessionAttributes(types = { es.caib.apisib.apifirmasimple.example.web.form.FirmaForm.class })
public class FirmaEnServidorController extends AbstractFirmaController {

    public static final String CONTEXTWEB = "/firmaenservidor";
    
    @Override
    public boolean isWeb() {
        return false;
    }
    
    @Override    
    public String getContextWeb() {
        return   CONTEXTWEB;
    }

}
