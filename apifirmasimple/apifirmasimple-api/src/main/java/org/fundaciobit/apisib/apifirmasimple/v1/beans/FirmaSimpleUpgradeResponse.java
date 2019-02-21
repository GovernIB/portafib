package org.fundaciobit.apisib.apifirmasimple.v1.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Resultat d'una actualització de firma
 * 
 * @author anadal
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class FirmaSimpleUpgradeResponse {

  protected FirmaSimpleFile upgradedFile;

  protected FirmaSimpleUpgradeFileInfo upgradedFileInfo;

  /**
   * 
   */
  public FirmaSimpleUpgradeResponse() {
    super();
  }

  public FirmaSimpleUpgradeResponse(FirmaSimpleFile upgradedFile,
      FirmaSimpleUpgradeFileInfo upgradedFileInfo) {
    super();
    this.upgradedFile = upgradedFile;
    this.upgradedFileInfo = upgradedFileInfo;
  }


  public FirmaSimpleFile getUpgradedFile() {
    return upgradedFile;
  }

  public void setUpgradedFile(FirmaSimpleFile upgradedFile) {
    this.upgradedFile = upgradedFile;
  }

  public FirmaSimpleUpgradeFileInfo getUpgradedFileInfo() {
    return upgradedFileInfo;
  }

  public void setUpgradedFileInfo(FirmaSimpleUpgradeFileInfo upgradedFileInfo) {
    this.upgradedFileInfo = upgradedFileInfo;
  }

}
