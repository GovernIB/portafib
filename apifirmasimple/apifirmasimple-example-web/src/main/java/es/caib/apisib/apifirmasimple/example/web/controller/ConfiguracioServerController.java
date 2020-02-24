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
@RequestMapping(value = "/server")
@SessionAttributes(types = { es.caib.apisib.apifirmasimple.example.web.form.FirmaForm.class })
public class ConfiguracioServerController extends AbstractConfiguracioController {

    @Override
    public boolean isWeb() {
        return false;
    }

}
