package es.caib.portafib.back.controller.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Serveix per a que la passarela de firma pugui realitzar la seva feina sense 
 * problemes de seguretat
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = SignatureModuleController.PUBLIC_CONTEXTWEB)
public class SignatureModuleControllerPublic extends SignatureModuleController {

}
