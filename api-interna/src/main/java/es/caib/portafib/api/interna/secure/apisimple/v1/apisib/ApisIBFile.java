package es.caib.portafib.api.interna.secure.apisimple.v1.apisib;


import io.swagger.v3.oas.annotations.media.Schema;

public class ApisIBFile {

	  protected java.lang.String nom;

	  protected java.lang.String mime;
	  
	  @Schema(description = "Contingut del fitxer. En llistats aquest camp vendrà buit.",
	            required = false,
	            type = "string",
	            format="byte")
	  protected byte[] data;

	  /** Constructor Buit */
	  public ApisIBFile() {
	  }

	  /** Copy constructor */
	  public ApisIBFile(ApisIBFile apisibfile) {
	    this.nom = apisibfile.nom;
	    this.mime = apisibfile.mime;
	    this.data = apisibfile.data;
	  }
	  
	  
	  /** Constructor amb tots els camps */
	  public ApisIBFile(java.lang.String nom, java.lang.String mime, byte[] data) {
	    this.nom = nom;
	    this.mime = mime;
	    this.data = data;
	  }

	  public java.lang.String getNom() {
	    return (nom);
	  };

	  public void setNom(java.lang.String _nom_) {
	    this.nom = _nom_;
	  };

	  public java.lang.String getMime() {
	    return (mime);
	  };

	  public void setMime(java.lang.String _mime_) {
	    this.mime = _mime_;
	  };

	  public byte[] getData() {
	    return data;
	  }

	  public void setData(byte[] data) {
	    this.data = data;
	  }

	}

