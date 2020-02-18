package org.fundaciobit.apisib.apifirmasimple.example.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.fundaciobit.apisib.apifirmasimple.example.web.form.ConfiguracioForm;
import org.fundaciobit.apisib.apifirmasimple.example.web.utils.ApiFirmaEnServidorCache;
import org.fundaciobit.apisib.apifirmasimple.example.web.utils.ApiFirmaWebCache;
import org.fundaciobit.apisib.apifirmasimple.example.web.utils.HtmlUtils;
import org.fundaciobit.apisib.apifirmasimple.v1.ApiFirmaEnServidorSimple;
import org.fundaciobit.apisib.apifirmasimple.v1.ApiFirmaWebSimple;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.RedirectView;

/**
 * 
 * @author anadal
 *
 */
public abstract class AbstractConfiguracioController implements WebMvcConfigurer {

    /** Logger for this class and subclasses */
    protected final Logger log = Logger.getLogger(getClass());

    // @Au
    // XYZ ZZZtowired
    // protected ConfiguracioValidator configuracioValidator;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/configuracio")
    public ModelAndView configuracioGet(HttpServletRequest request) {

        ConfiguracioForm form = new ConfiguracioForm();

        ModelAndView mav = new ModelAndView("configuracio");

        if (isWeb()) {

            ApiFirmaWebSimple api = ApiFirmaWebCache.getApiFirmaWebSimple(request);
            if (api == null) {
                form.setEndPoint("http://localhost:8080/portafib/common/rest/apifirmawebsimple/v1/");
                form.setUsername("app_username");
                form.setPassword("app_password");
            }
        } else {

            ApiFirmaEnServidorSimple api = ApiFirmaEnServidorCache.getApiFirmaEnServidorSimple(request);
            if (api == null) {
                form.setEndPoint("http://localhost:8080/portafib/common/rest/apifirmaserversimple/v1/");
                form.setUsername("app_username");
                form.setPassword("app_password");
            }
        }

        mav.addObject(form);

        return mav;

    }

    @PostMapping("/configuracio")
    public ModelAndView configuracioPost(HttpServletRequest request, HttpServletResponse response,
            @Valid ConfiguracioForm form, BindingResult bindingResult) throws Exception {

        ModelAndView mav = new ModelAndView("configuracio");
        mav.addObject(form);

        if (bindingResult.hasErrors()) {
            log.warn("Validador té errors !!!!");
            return mav;
        }

        Object obj;

        try {
            if (isWeb()) {
                obj = ApiFirmaWebCache.setApiFirmaWebSimple(request, form.getEndPoint(), form.getUsername(),
                        form.getPassword());
            } else {
                obj = ApiFirmaEnServidorCache.setApiFirmaEnServidorSimple(request, form.getEndPoint(),
                        form.getUsername(), form.getPassword());
            }
        } catch (Exception e) {
            String msg = "Configuració incorrecte: " + e.getMessage();
            log.error(msg, e);

            HtmlUtils.saveMessageError(request, msg);

            return mav;
        }

        if (obj == null) {

            return mav;
        } else {

            // TODO XYZ ZZZ Traduir
            HtmlUtils.saveMessageSuccess(request, "Configuració guardada correctament ");

            return new ModelAndView(new RedirectView("/", true));
        }

    }

    @ModelAttribute("isConfigWeb")
    public final boolean isConfigWeb() {
        return isWeb();
    }

    public abstract boolean isWeb();

    @ModelAttribute("configuracioCodeLabel")
    public String getConfiguracioCodeLabel() {
        return isWeb() ? "configuracio.web" : "configuracio.servidor";
    }
}
