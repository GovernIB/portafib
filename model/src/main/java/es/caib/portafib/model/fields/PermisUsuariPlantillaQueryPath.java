
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class PermisUsuariPlantillaQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public PermisUsuariPlantillaQueryPath() {
  }

  protected PermisUsuariPlantillaQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField PERMISUSUARIPLANTILLAID() {
    return new LongField(getQueryPath(), PermisUsuariPlantillaFields.PERMISUSUARIPLANTILLAID);
  }

  public StringField USUARIENTITATID() {
    return new StringField(getQueryPath(), PermisUsuariPlantillaFields.USUARIENTITATID);
  }

  public LongField PLANTILLAFLUXDEFIRMESID() {
    return new LongField(getQueryPath(), PermisUsuariPlantillaFields.PLANTILLAFLUXDEFIRMESID);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (PermisUsuariPlantillaFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


  public UsuariEntitatQueryPath USUARIENTITAT() {
    return new UsuariEntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PermisUsuariPlantillaQueryPath.this.getQueryPath() + "usuariEntitat" + ".";
      }
    });
  }

  public PlantillaFluxDeFirmesQueryPath PLANTILLAFLUXDEFIRMES() {
    return new PlantillaFluxDeFirmesQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PermisUsuariPlantillaQueryPath.this.getQueryPath() + "plantillaFluxDeFirmes" + ".";
      }
    });
  }

}
