package org.fundaciobit.apisib.apifirmasimple.example.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/web")
@SessionAttributes(types = { org.fundaciobit.apisib.apifirmasimple.example.web.form.FirmaForm.class })
public class ConfiguracioWebController extends AbstractConfiguracioController {

    @Override
    public boolean isWeb() {
        return true;
    }

}
