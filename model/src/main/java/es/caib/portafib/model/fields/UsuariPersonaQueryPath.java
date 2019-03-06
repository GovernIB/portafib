
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class UsuariPersonaQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public UsuariPersonaQueryPath() {
  }

  protected UsuariPersonaQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public StringField USUARIPERSONAID() {
    return new StringField(getQueryPath(), UsuariPersonaFields.USUARIPERSONAID);
  }

  public StringField NOM() {
    return new StringField(getQueryPath(), UsuariPersonaFields.NOM);
  }

  public StringField LLINATGES() {
    return new StringField(getQueryPath(), UsuariPersonaFields.LLINATGES);
  }

  public StringField EMAIL() {
    return new StringField(getQueryPath(), UsuariPersonaFields.EMAIL);
  }

  public StringField NIF() {
    return new StringField(getQueryPath(), UsuariPersonaFields.NIF);
  }

  public StringField IDIOMAID() {
    return new StringField(getQueryPath(), UsuariPersonaFields.IDIOMAID);
  }

  public LongField RUBRICAID() {
    return new LongField(getQueryPath(), UsuariPersonaFields.RUBRICAID);
  }

  public BooleanField USUARIINTERN() {
    return new BooleanField(getQueryPath(), UsuariPersonaFields.USUARIINTERN);
  }

  public StringField CONTRASENYA() {
    return new StringField(getQueryPath(), UsuariPersonaFields.CONTRASENYA);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (UsuariPersonaFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public UsuariEntitatQueryPath USUARIENTITATS() {
    return new UsuariEntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return UsuariPersonaQueryPath.this.getQueryPath() + "usuariEntitats" + ".";
      }
    });
  }
*/

  public IdiomaQueryPath IDIOMA() {
    return new IdiomaQueryPath(new QueryPath() {
      public String getQueryPath() {
          return UsuariPersonaQueryPath.this.getQueryPath() + "idioma" + ".";
      }
    });
  }

  public FitxerQueryPath RUBRICA() {
    return new FitxerQueryPath(new QueryPath() {
      public String getQueryPath() {
          return UsuariPersonaQueryPath.this.getQueryPath() + "rubrica" + ".";
      }
    });
  }

}
