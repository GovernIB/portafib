package org.fundaciobit.pluginsib.signature.firmasimple.apifirmasimple.v1.beans;

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
public class FirmaSimpleFile {

  java.lang.String nom;
  java.lang.String mime;
  byte[] data;

  /** Constructor Buit */
  public FirmaSimpleFile() {
  }

  /** Constructor amb tots els camps */
  public FirmaSimpleFile(java.lang.String nom, java.lang.String mime, byte[] data) {
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
