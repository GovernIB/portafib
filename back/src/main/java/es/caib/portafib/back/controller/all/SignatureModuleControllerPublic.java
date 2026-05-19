package es.caib.portafib.back.controller.all;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import es.caib.portafib.back.controller.AbstractSignatureModuleController;

/**
 * Serveix per a que la passarela de firma pugui realitzar la seva feina sense 
 * problemes de seguretat
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = AbstractSignatureModuleController.PUBLIC_CONTEXTWEB)
public class SignatureModuleControllerPublic extends AbstractSignatureModuleController {

    @Override
    public boolean isPublicContext() {
        return true;
    }

}
