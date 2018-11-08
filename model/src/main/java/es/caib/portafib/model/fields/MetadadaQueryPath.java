
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class MetadadaQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public MetadadaQueryPath() {
  }

  protected MetadadaQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField METADADAID() {
    return new LongField(getQueryPath(), MetadadaFields.METADADAID);
  }

  public StringField NOM() {
    return new StringField(getQueryPath(), MetadadaFields.NOM);
  }

  public StringField VALOR() {
    return new StringField(getQueryPath(), MetadadaFields.VALOR);
  }

  public StringField DESCRIPCIO() {
    return new StringField(getQueryPath(), MetadadaFields.DESCRIPCIO);
  }

  public LongField PETICIODEFIRMAID() {
    return new LongField(getQueryPath(), MetadadaFields.PETICIODEFIRMAID);
  }

  public IntegerField TIPUSMETADADAID() {
    return new IntegerField(getQueryPath(), MetadadaFields.TIPUSMETADADAID);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (MetadadaFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


  public PeticioDeFirmaQueryPath PETICIODEFIRMA() {
    return new PeticioDeFirmaQueryPath(new QueryPath() {
      public String getQueryPath() {
          return MetadadaQueryPath.this.getQueryPath() + "peticioDeFirma" + ".";
      }
    });
  }

}
