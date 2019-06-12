package es.caib.portafib.back.controller.rest.apifirmaasyncsimple.v2;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleAvailableProfile;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleError;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.exceptions.ServerException;
import org.fundaciobit.genapp.common.query.Where;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import es.caib.portafib.back.controller.rest.RestFirmaUtils;
import es.caib.portafib.model.entity.PerfilDeFirma;

/**
 * 
 * @author anadal(u80067)
 *
 */
public class RestApiFirmaAsyncUtils extends RestFirmaUtils {
  


  public ResponseEntity<FirmaAsyncSimpleError> generateServerError(String msg) {
    return generateServerError(msg, null, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  public ResponseEntity<FirmaAsyncSimpleError> generateServerError(String msg, HttpStatus status) {
    return generateServerError(msg, null, status);
  }

  public ResponseEntity<FirmaAsyncSimpleError> generateServerError(String msg, Throwable th) {
    return generateServerError(msg, th, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  public ResponseEntity<FirmaAsyncSimpleError> generateServerError(String msg, Throwable th,
      HttpStatus status) {
    String sStackTrace = null;
    if (th != null) {
      StringWriter sw = new StringWriter();
      PrintWriter pw = new PrintWriter(sw);
      th.printStackTrace(pw);
      sStackTrace = sw.toString();
    }

    return new ResponseEntity<FirmaAsyncSimpleError>(new FirmaAsyncSimpleError(msg,
        ServerException.class.getName(), sStackTrace), status);
  }
  
  
  
  
  
  public ResponseEntity<?> internalGetAvailableProfiles(HttpServletRequest request,
      String locale) {
    String error = autenticate(request);
    if (error != null) {
      return generateServerError(error, HttpStatus.UNAUTHORIZED);
    }

    try {
      

      
     // FALTA ELEGIR ELS PERFILS QUE TENGUIN API_PORTAFIB_WS_V2
      Where w = null;
      List<PerfilDeFirma> perfils = commonAvailableProfiles(w);

      List<FirmaAsyncSimpleAvailableProfile> list = new ArrayList<FirmaAsyncSimpleAvailableProfile>();

      for (PerfilDeFirma perfil : perfils) {

        String codiPerfil = perfil.getCodi();

        String descripcio = perfil.getDescripcio();

        // Falta llegir-ho de la BBDD
        FirmaAsyncSimpleAvailableProfile ap = new FirmaAsyncSimpleAvailableProfile(codiPerfil,
            perfil.getNom(), descripcio);

        list.add(ap);
      }

      HttpHeaders headers = addAccessControllAllowOrigin();
      ResponseEntity<?> re = new ResponseEntity<List<FirmaAsyncSimpleAvailableProfile>>(list, headers,
          HttpStatus.OK);

      return re;

    } catch (Throwable th) {

      // XYZ ZZZ Traduir
      String msg = "Error desconegut retornant el perfils d'un usuari aplicacio: "
          + th.getMessage();

      log.error(msg, th);

      return generateServerError(msg, th);
    }
  }

  
}
