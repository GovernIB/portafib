
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class PermisGrupPlantillaQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public PermisGrupPlantillaQueryPath() {
  }

  protected PermisGrupPlantillaQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField PERMISGRUPPLANTILLAID() {
    return new LongField(getQueryPath(), PermisGrupPlantillaFields.PERMISGRUPPLANTILLAID);
  }

  public LongField GRUPENTITATID() {
    return new LongField(getQueryPath(), PermisGrupPlantillaFields.GRUPENTITATID);
  }

  public LongField PLANTILLAFLUXDEFIRMESID() {
    return new LongField(getQueryPath(), PermisGrupPlantillaFields.PLANTILLAFLUXDEFIRMESID);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (PermisGrupPlantillaFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


  public GrupEntitatQueryPath GRUPENTITAT() {
    return new GrupEntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PermisGrupPlantillaQueryPath.this.getQueryPath() + "grupEntitat" + ".";
      }
    });
  }

  public PlantillaFluxDeFirmesQueryPath PLANTILLAFLUXDEFIRMES() {
    return new PlantillaFluxDeFirmesQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PermisGrupPlantillaQueryPath.this.getQueryPath() + "plantillaFluxDeFirmes" + ".";
      }
    });
  }

}
