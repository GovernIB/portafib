package es.caib.portafib.logic;

import es.caib.portafib.ejb.BlocDeFirmesLocal;
import es.caib.portafib.ejb.FirmaLocal;
import es.caib.portafib.ejb.EstatDeFirmaEJB;
import es.caib.portafib.ejb.PeticioDeFirmaLocal;
import es.caib.portafib.jpa.EstatDeFirmaJPA;
import es.caib.portafib.model.entity.BlocDeFirmes;
import es.caib.portafib.model.entity.Firma;
import es.caib.portafib.model.entity.EstatDeFirma;
import es.caib.portafib.model.entity.PeticioDeFirma;
import es.caib.portafib.model.fields.BlocDeFirmesFields;
import es.caib.portafib.model.fields.EstatDeFirmaFields;
import es.caib.portafib.model.fields.FirmaFields;
import es.caib.portafib.model.fields.PeticioDeFirmaFields;
import es.caib.portafib.utils.Constants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.SubQuery;
import org.fundaciobit.genapp.common.query.Where;
import org.jboss.ejb3.annotation.SecurityDomain;

/**
 * 
 * @author anadal
 * 
 */
@Stateless(name = "EstatDeFirmaLogicaEJB")
@SecurityDomain("seycon")
public class EstatDeFirmaLogicaEJB extends EstatDeFirmaEJB
  implements EstatDeFirmaLogicaLocal {

  @EJB(mappedName = "portafib/FirmaEJB/local")
  private FirmaLocal firmaEjb;

  @EJB(mappedName = "portafib/BlocDeFirmesEJB/local")
  private BlocDeFirmesLocal blocDeFirmesEjb;

  @EJB(mappedName = "portafib/PeticioDeFirmaEJB/local")
  protected PeticioDeFirmaLocal peticioDeFirmaEjb;

  @Override
  public EstatDeFirmaJPA createFull(EstatDeFirmaJPA estatDeFirma) throws I18NException {
    return (EstatDeFirmaJPA)this.create(estatDeFirma);
  }
  
  
  @Override
  public EstatDeFirmaJPA findByPrimaryKeyUnauthorized(Long id) {
    return this.findByPrimaryKey(id);
  }
  

  @Override
  public List<EstatDeFirma> getEstatDeFirmaByUsuariEntitat(String usu_ent_actual, String rol,
      Long[] estatsDeFirma) throws I18NException {
    List<EstatDeFirma> estatsDeFirmaList;
    Where w1 = EstatDeFirmaFields.USUARIENTITATID.equal(usu_ent_actual);
    
    Where w2;
    if (Constants.ROLE_DEST.equals(rol)) {
      // DESTINATARI
      w2 = EstatDeFirmaFields.COLABORACIODELEGACIOID.isNull();
    } else {
      // DELEGAT o SOLICITANT
      w2 = EstatDeFirmaFields.COLABORACIODELEGACIOID.isNotNull();
    }
    
    Where w3;
    if (estatsDeFirma == null || estatsDeFirma.length == 0) {
      w3 = null;
    } else {
      if (estatsDeFirma.length == 1) {
        w3 = EstatDeFirmaFields.TIPUSESTATDEFIRMAINICIALID.equal(estatsDeFirma[0]);
      } else {
        w3 = EstatDeFirmaFields.TIPUSESTATDEFIRMAINICIALID.in(estatsDeFirma);
      }
    }
    Where w4 = EstatDeFirmaFields.TIPUSESTATDEFIRMAFINALID.isNull();

    estatsDeFirmaList = this.select(Where.AND(w1, w2, w3, w4));

    return estatsDeFirmaList;
  }

  /**
   * 
   * @param estatDeFirmaList
   * @return
   * @throws Exception
   */
  @Override
  public Map<Long, PeticioDeFirma> getPeticioDeFirmaFromEstatDeFirmaID(
      List<EstatDeFirma> estatDeFirmaList) throws I18NException {

    if (estatDeFirmaList == null) {
      return null;
    }

    Map<Long, PeticioDeFirma> map = new HashMap<Long, PeticioDeFirma>();

    for (EstatDeFirma estatDeFirma : estatDeFirmaList) {

      SubQuery<Firma, Long> subqueryFirma;
      subqueryFirma = firmaEjb.getSubQuery(FirmaFields.BLOCDEFIRMAID,
          FirmaFields.FIRMAID.equal(estatDeFirma.getFirmaID()));

      SubQuery<BlocDeFirmes, Long> subqueryBloc;
      subqueryBloc = blocDeFirmesEjb.getSubQuery(BlocDeFirmesFields.FLUXDEFIRMESID,
          BlocDeFirmesFields.BLOCDEFIRMESID.in(subqueryFirma));

      /*
       * SubQuery<FluxDeFirmes,Long> subqueryFlux ; subqueryFlux =
       * fluxDeFirmesEjb.getSubQuery(FluxDeFirmesFields.FLUXDEFIRMESID,
       * FluxDeFirmesFields.FLUXDEFIRMESID.in(subqueryBloc),(OrderBy[]) null);
       */
      // SubQuery<PeticioDeFirma,Long> subqueryPeticio ;
      // subqueryPeticio =
      // peticioDeFirmaEjb.getSubQuery(PeticioDeFirmaFields.PETICIODEFIRMAID,
      // PeticioDeFirmaFields.FLUXDEFIRMESID.in(subqueryFlux),(OrderBy[]) null);

      Where w = PeticioDeFirmaFields.FLUXDEFIRMESID.in(subqueryBloc);

      //log.info("SQL: " + w.toSQL());

      List<PeticioDeFirma> list = peticioDeFirmaEjb.select(w);

      if (list != null && list.size() != 0) {
        map.put(estatDeFirma.getEstatDeFirmaID(), list.get(0));
      }

    }

    return map;

  }
  
  
  @Override
  public List<EstatDeFirma> getAllEstatDeFirmaActiuOfFlux(Long fluxDeFirmesID)
     throws I18NException {
    
    
    SubQuery<BlocDeFirmes, Long> subqueryBloc;
    subqueryBloc = blocDeFirmesEjb.getSubQuery(BlocDeFirmesFields.BLOCDEFIRMESID,
        Where.AND(
            BlocDeFirmesFields.FLUXDEFIRMESID.equal(fluxDeFirmesID),
            BlocDeFirmesFields.DATAFINALITZACIO.isNull()
            )
        );

    SubQuery<Firma, Long> subqueryFirma;
    subqueryFirma = firmaEjb.getSubQuery(FirmaFields.FIRMAID,
        Where.AND(
            FirmaFields.BLOCDEFIRMAID.in(subqueryBloc),
            FirmaFields.FITXERFIRMATID.isNull()
            ));


    Where wEdF = Where.AND(
        EstatDeFirmaFields.DATAFI.isNull(),
        EstatDeFirmaFields.FIRMAID.in(subqueryFirma)
        );
    
    return select(wEdF);

  }
  
}
