package org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author anadal
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class FirmaAsyncSimpleFile {

  protected java.lang.String nom;

  protected java.lang.String mime;

  protected byte[] data;

  /** Constructor Buit */
  public FirmaAsyncSimpleFile() {
  }

  /** Constructor amb tots els camps */
  public FirmaAsyncSimpleFile(java.lang.String nom, java.lang.String mime, byte[] data) {
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
