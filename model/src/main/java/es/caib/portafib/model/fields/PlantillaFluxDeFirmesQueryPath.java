
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class PlantillaFluxDeFirmesQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public PlantillaFluxDeFirmesQueryPath() {
  }

  protected PlantillaFluxDeFirmesQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField FLUXDEFIRMESID() {
    return new LongField(getQueryPath(), PlantillaFluxDeFirmesFields.FLUXDEFIRMESID);
  }

  public StringField DESCRIPCIO() {
    return new StringField(getQueryPath(), PlantillaFluxDeFirmesFields.DESCRIPCIO);
  }

  public StringField USUARIENTITATID() {
    return new StringField(getQueryPath(), PlantillaFluxDeFirmesFields.USUARIENTITATID);
  }

  public StringField USUARIAPLICACIOID() {
    return new StringField(getQueryPath(), PlantillaFluxDeFirmesFields.USUARIAPLICACIOID);
  }

  public BooleanField COMPARTIR() {
    return new BooleanField(getQueryPath(), PlantillaFluxDeFirmesFields.COMPARTIR);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (PlantillaFluxDeFirmesFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public PermisGrupPlantillaQueryPath PERMISGRUPPLANTILLAS() {
    return new PermisGrupPlantillaQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PlantillaFluxDeFirmesQueryPath.this.getQueryPath() + "permisGrupPlantillas" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public PermisUsuariPlantillaQueryPath PERMISUSUARIPLANTILLAS() {
    return new PermisUsuariPlantillaQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PlantillaFluxDeFirmesQueryPath.this.getQueryPath() + "permisUsuariPlantillas" + ".";
      }
    });
  }
*/

  public FluxDeFirmesQueryPath FLUXDEFIRMES() {
    return new FluxDeFirmesQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PlantillaFluxDeFirmesQueryPath.this.getQueryPath() + "fluxDeFirmes" + ".";
      }
    });
  }

  public UsuariEntitatQueryPath USUARIENTITAT() {
    return new UsuariEntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PlantillaFluxDeFirmesQueryPath.this.getQueryPath() + "usuariEntitat" + ".";
      }
    });
  }

  public UsuariAplicacioQueryPath USUARIAPLICACIO() {
    return new UsuariAplicacioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PlantillaFluxDeFirmesQueryPath.this.getQueryPath() + "usuariAplicacio" + ".";
      }
    });
  }

}
