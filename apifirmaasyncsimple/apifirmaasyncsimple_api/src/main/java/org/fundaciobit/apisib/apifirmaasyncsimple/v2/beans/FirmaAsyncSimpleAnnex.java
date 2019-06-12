package org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class FirmaAsyncSimpleAnnex {

  protected FirmaAsyncSimpleFile annex;
  protected boolean attach;
  protected boolean sign;

  public FirmaAsyncSimpleAnnex() {
    super();
  }

  public FirmaAsyncSimpleAnnex(FirmaAsyncSimpleFile annex, boolean attach, boolean sign) {
    super();
    this.annex = annex;
    this.attach = attach;
    this.sign = sign;
  }

  public FirmaAsyncSimpleFile getAnnex() {
    return annex;
  }

  public void setAnnex(FirmaAsyncSimpleFile annex) {
    this.annex = annex;
  }

  public boolean isAttach() {
    return attach;
  }

  public void setAttach(boolean attach) {
    this.attach = attach;
  }

  public boolean isSign() {
    return sign;
  }

  public void setSign(boolean sign) {
    this.sign = sign;
  }

}
