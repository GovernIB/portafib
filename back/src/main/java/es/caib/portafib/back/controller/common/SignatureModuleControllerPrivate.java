package es.caib.portafib.back.controller.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import es.caib.portafib.back.controller.AbstractSignatureModuleController;

/**
 * 
 * @author anadal (u80067)
 * 4 may 2026 8:44:38
 */
@Controller
@RequestMapping(value = AbstractSignatureModuleController.PRIVATE_CONTEXTWEB)
public class SignatureModuleControllerPrivate extends AbstractSignatureModuleController {

    @Override
    public boolean isPublicContext() {
        return false;
    }

}