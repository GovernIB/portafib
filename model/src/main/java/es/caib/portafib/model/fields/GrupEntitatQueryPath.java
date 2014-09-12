
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class GrupEntitatQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public GrupEntitatQueryPath() {
  }

  protected GrupEntitatQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField GRUPENTITATID() {
    return new LongField(getQueryPath(), GrupEntitatFields.GRUPENTITATID);
  }

  public StringField NOM() {
    return new StringField(getQueryPath(), GrupEntitatFields.NOM);
  }

  public StringField DESCRIPCIO() {
    return new StringField(getQueryPath(), GrupEntitatFields.DESCRIPCIO);
  }

  public StringField ENTITATID() {
    return new StringField(getQueryPath(), GrupEntitatFields.ENTITATID);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (GrupEntitatFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public GrupEntitatUsuariEntitatQueryPath GRUPENTITATUSUARIENTITATS() {
    return new GrupEntitatUsuariEntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return GrupEntitatQueryPath.this.getQueryPath() + "grupEntitatUsuariEntitats" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public PermisGrupPlantillaQueryPath PERMISGRUPPLANTILLAS() {
    return new PermisGrupPlantillaQueryPath(new QueryPath() {
      public String getQueryPath() {
          return GrupEntitatQueryPath.this.getQueryPath() + "permisGrupPlantillas" + ".";
      }
    });
  }
*/

  public EntitatQueryPath ENTITAT() {
    return new EntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return GrupEntitatQueryPath.this.getQueryPath() + "entitat" + ".";
      }
    });
  }

}
