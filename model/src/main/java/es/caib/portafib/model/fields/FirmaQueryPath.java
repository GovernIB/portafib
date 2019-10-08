
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

  public StringField MOTIU() {
    return new StringField(getQueryPath(), FirmaFields.MOTIU);
  }

  public IntegerField MINIMDEREVISORS() {
    return new IntegerField(getQueryPath(), FirmaFields.MINIMDEREVISORS);
  }

  public BooleanField CHECKADMINISTRATIONIDOFSIGNER() {
    return new BooleanField(getQueryPath(), FirmaFields.CHECKADMINISTRATIONIDOFSIGNER);
  }

  public BooleanField CHECKDOCUMENTMODIFICATIONS() {
    return new BooleanField(getQueryPath(), FirmaFields.CHECKDOCUMENTMODIFICATIONS);
  }

  public BooleanField CHECKVALIDATIONSIGNATURE() {
    return new BooleanField(getQueryPath(), FirmaFields.CHECKVALIDATIONSIGNATURE);
  }

  public StringField PERFILDEFIRMA() {
    return new StringField(getQueryPath(), FirmaFields.PERFILDEFIRMA);
  }

  public StringField USUARIEXTERNNOM() {
    return new StringField(getQueryPath(), FirmaFields.USUARIEXTERNNOM);
  }

  public StringField USUARIEXTERNLLINATGES() {
    return new StringField(getQueryPath(), FirmaFields.USUARIEXTERNLLINATGES);
  }

  public StringField USUARIEXTERNEMAIL() {
    return new StringField(getQueryPath(), FirmaFields.USUARIEXTERNEMAIL);
  }

  public StringField USUARIEXTERNIDIOMA() {
    return new StringField(getQueryPath(), FirmaFields.USUARIEXTERNIDIOMA);
  }

  public StringField USUARIEXTERNTOKEN() {
    return new StringField(getQueryPath(), FirmaFields.USUARIEXTERNTOKEN);
  }

  public IntegerField USUARIEXTERNNIVELLSEGURETAT() {
    return new IntegerField(getQueryPath(), FirmaFields.USUARIEXTERNNIVELLSEGURETAT);
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

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public RevisorDeFirmaQueryPath REVISORDEFIRMAS() {
    return new RevisorDeFirmaQueryPath(new QueryPath() {
      public String getQueryPath() {
          return FirmaQueryPath.this.getQueryPath() + "revisorDeFirmas" + ".";
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

}
