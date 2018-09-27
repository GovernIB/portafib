package es.caib.portafib.back.controller.common;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import es.caib.portafib.back.controller.common.rest.RestUtils;
import es.caib.portafib.jpa.TipusDocumentJPA;
import es.caib.portafib.jpa.TraduccioMapJPA;
import es.caib.portafib.logic.EntitatLogicaLocal;
import es.caib.portafib.logic.UsuariAplicacioLogicaLocal;
import es.caib.portafib.logic.utils.I18NLogicUtils;
import es.caib.portafib.model.entity.TipusDocument;
import es.caib.portafib.model.fields.IdiomaFields;
import es.caib.portafib.model.fields.TipusDocumentFields;
import es.caib.portafib.model.fields.UsuariAplicacioFields;
import es.caib.portafib.utils.Configuracio;

import javax.ejb.EJB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * Created 24/08/17 13:33
 *
 * @author anadal
 */
@Controller
@RequestMapping(value = "/public/rest")
public class RestController extends RestUtils {

  @EJB(mappedName = es.caib.portafib.ejb.TipusDocumentLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.TipusDocumentLocal tipusDocumentEjb;

  @EJB(mappedName = "portafib/UsuariAplicacioLogicaEJB/local")
  protected UsuariAplicacioLogicaLocal usuariAplicacioLogicaEjb;

  @EJB(mappedName = "portafib/EntitatLogicaEJB/local")
  protected EntitatLogicaLocal entitatLogicaEjb;

  @EJB(mappedName = es.caib.portafib.ejb.IdiomaLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.IdiomaLocal idiomaEjb;

  /**
   * Obtiene los {@link es.caib.dir3caib.persistence.model.Unidad} por
   * denominacion
   */
  @RequestMapping(value = "/tipusdocument/v1/list", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<?> tipusDocumental(
      @RequestParam(value = "lang", required = false) String lang,
      @RequestParam(value = "appuser", required = false) String appuser) throws Exception {

    try {

      if (lang == null || lang.trim().length() == 0) {
        lang = Configuracio.getDefaultLanguage();
      } else {
        List<String> idiomes = idiomaEjb.executeQuery(IdiomaFields.IDIOMAID,
            IdiomaFields.SUPORTAT.equal(true));

        lang = lang.trim();

        if (!idiomes.contains(lang)) {
          // L´idioma {0} no està suportat. Valors possibles: {1}

          
          final String msg = I18NUtils.tradueix("rest.idiomanosuportat.error", lang, 
              Arrays.toString(idiomes.toArray()));
          return new ResponseEntity<String>(msg, HttpStatus.BAD_REQUEST);
        }
      }

      Where whereTD = null;
      if (appuser != null && appuser.trim().length() != 0) {

        appuser = appuser.trim();

        Long count = usuariAplicacioLogicaEjb.count(UsuariAplicacioFields.USUARIAPLICACIOID
            .equal(appuser));
        if (count == 0) {
          // No existe el Usuario-Aplicación {0}.
          final String msg = I18NUtils.tradueix("rest.usrappnoexisteix.error", appuser);
          return new ResponseEntity<String>(msg, HttpStatus.BAD_REQUEST);
        }

        whereTD = Where.OR(TipusDocumentFields.USUARIAPLICACIOID.equal(appuser),
            TipusDocumentFields.USUARIAPLICACIOID.isNull());

      }

      if (whereTD == null) {
        whereTD = TipusDocumentFields.USUARIAPLICACIOID.isNull();
      }

      List<TipusDocument> list = tipusDocumentEjb.select(whereTD);

      List<TipusDocumentRest> resultat = new ArrayList<TipusDocumentRest>();

      for (TipusDocument td : list) {

        TraduccioMapJPA tramap;
        tramap = ((TipusDocumentJPA) td).getNom().getTraduccio(lang);
        if (tramap == null) {
          tramap = ((TipusDocumentJPA) td).getNom().getTraduccio(
              Configuracio.getDefaultLanguage());
        }

        long id = td.getTipusDocumentID();
        String nom = tramap.getValor();

        // XYZ ZZZ PORTAFIB v2: Falta el pare del document NTI
        Long tipusDocumentNTIID = ((id >= 0) && (id <= 99)) ? null : 99L; // ALTRES

        resultat.add(new TipusDocumentRest(id, nom, tipusDocumentNTIID));
      }

      HttpHeaders headers = addAccessControllAllowOrigin();
      return new ResponseEntity<List<TipusDocumentRest>>(resultat, headers, HttpStatus.OK);

    } catch (I18NException i18ne) {
      Locale locale = new Locale(Configuracio.getDefaultLanguage());
      String msg = I18NLogicUtils.getMessage(i18ne, locale);
      return new ResponseEntity<String>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }


  /**
   * 
   * @author anadal
   *
   */
  public class TipusDocumentRest {

    protected long tipusDocumentID;

    protected String nom;

    protected Long tipusDocumentNTI;

    /**
       * 
       */
    public TipusDocumentRest() {
      super();
    }

    /**
     * 
     * @param tipusDocumentID
     * @param nom
     * @param tipusDocumentNTI
     */
    public TipusDocumentRest(long tipusDocumentID, String nom, Long tipusDocumentNTI) {
      super();
      this.tipusDocumentID = tipusDocumentID;
      this.nom = nom;
      this.tipusDocumentNTI = tipusDocumentNTI;
    }

    public long getTipusDocumentID() {
      return tipusDocumentID;
    }

    public void setTipusDocumentID(long tipusDocumentID) {
      this.tipusDocumentID = tipusDocumentID;
    }

    public String getNom() {
      return nom;
    }

    public void setNom(String nom) {
      this.nom = nom;
    }

    public Long getTipusDocumentNTI() {
      return tipusDocumentNTI;
    }

    public void setTipusDocumentNTI(Long tipusDocumentNTI) {
      this.tipusDocumentNTI = tipusDocumentNTI;
    }

  }

}
