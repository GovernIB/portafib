
package es.caib.portafib.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class FirmaQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public FirmaQueryPath() {
  }

  protected FirmaQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField FIRMAID() {
    return new LongField(getQueryPath(), FirmaFields.FIRMAID);
  }

  public StringField DESTINATARIID() {
    return new StringField(getQueryPath(), FirmaFields.DESTINATARIID);
  }

  public LongField BLOCDEFIRMAID() {
    return new LongField(getQueryPath(), FirmaFields.BLOCDEFIRMAID);
  }

  public BooleanField OBLIGATORI() {
    return new BooleanField(getQueryPath(), FirmaFields.OBLIGATORI);
  }

  public LongField FITXERFIRMATID() {
    return new LongField(getQueryPath(), FirmaFields.FITXERFIRMATID);
  }

  public IntegerField NUMFIRMADOCUMENT() {
    return new IntegerField(getQueryPath(), FirmaFields.NUMFIRMADOCUMENT);
  }

  public IntegerField CAIXAPAGINA() {
    return new IntegerField(getQueryPath(), FirmaFields.CAIXAPAGINA);
  }

  public IntegerField CAIXAX() {
    return new IntegerField(getQueryPath(), FirmaFields.CAIXAX);
  }

  public IntegerField CAIXAY() {
    return new IntegerField(getQueryPath(), FirmaFields.CAIXAY);
  }

  public IntegerField CAIXAAMPLE() {
    return new IntegerField(getQueryPath(), FirmaFields.CAIXAAMPLE);
  }

  public IntegerField CAIXAALT() {
    return new IntegerField(getQueryPath(), FirmaFields.CAIXAALT);
  }

  public BigIntegerField NUMEROSERIECERTIFICAT() {
    return new BigIntegerField(getQueryPath(), FirmaFields.NUMEROSERIECERTIFICAT);
  }

  public StringField EMISSORCERTIFICAT() {
    return new StringField(getQueryPath(), FirmaFields.EMISSORCERTIFICAT);
  }

  public StringField NOMCERTIFICAT() {
    return new StringField(getQueryPath(), FirmaFields.NOMCERTIFICAT);
  }

  public LongField TIPUSESTATDEFIRMAFINALID() {
    return new LongField(getQueryPath(), FirmaFields.TIPUSESTATDEFIRMAFINALID);
  }

  public BooleanField MOSTRARRUBRICA() {
    return new BooleanField(getQueryPath(), FirmaFields.MOSTRARRUBRICA);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (FirmaFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public AnnexFirmatQueryPath ANNEXFIRMATS() {
    return new AnnexFirmatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return FirmaQueryPath.this.getQueryPath() + "annexFirmats" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public EstatDeFirmaQueryPath ESTATDEFIRMAS() {
    return new EstatDeFirmaQueryPath(new QueryPath() {
      public String getQueryPath() {
          return FirmaQueryPath.this.getQueryPath() + "estatDeFirmas" + ".";
      }
    });
  }
*/

  public UsuariEntitatQueryPath USUARIENTITAT() {
    return new UsuariEntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return FirmaQueryPath.this.getQueryPath() + "usuariEntitat" + ".";
      }
    });
  }

  public BlocDeFirmesQueryPath BLOCDEFIRMES() {
    return new BlocDeFirmesQueryPath(new QueryPath() {
      public String getQueryPath() {
          return FirmaQueryPath.this.getQueryPath() + "blocDeFirmes" + ".";
      }
    });
  }

  public FitxerQueryPath FITXERFIRMAT() {
    return new FitxerQueryPath(new QueryPath() {
      public String getQueryPath() {
          return FirmaQueryPath.this.getQueryPath() + "fitxerFirmat" + ".";
      }
    });
  }

  public TipusEstatDeFirmaFinalQueryPath TIPUSESTATDEFIRMAFINAL() {
    return new TipusEstatDeFirmaFinalQueryPath(new QueryPath() {
      public String getQueryPath() {
          return FirmaQueryPath.this.getQueryPath() + "tipusEstatDeFirmaFinal" + ".";
      }
    });
  }

}
