
package es.caib.portafib.model.bean;

import es.caib.portafib.model.entity.Pseudonim;


public class PseudonimBean implements Pseudonim {



	long pseudonimid;// PK
	java.lang.String pseudonim;
	java.lang.String nif;


  /** Constructor Buit */
  public PseudonimBean() {
  }

  /** Constructor amb tots els camps  */
  public PseudonimBean(long pseudonimid , java.lang.String pseudonim , java.lang.String nif) {
    this.pseudonimid=pseudonimid;
    this.pseudonim=pseudonim;
    this.nif=nif;
}
  /** Constructor sense valors autoincrementals */
  public PseudonimBean(java.lang.String pseudonim , java.lang.String nif) {
    this.pseudonim=pseudonim;
    this.nif=nif;
}
  public PseudonimBean(Pseudonim __bean) {
    this.setPseudonimid(__bean.getPseudonimid());
    this.setPseudonim(__bean.getPseudonim());
    this.setNif(__bean.getNif());
	}

	public long getPseudonimid() {
		return(pseudonimid);
	};
	public void setPseudonimid(long _pseudonimid_) {
		this.pseudonimid = _pseudonimid_;
	};

	public java.lang.String getPseudonim() {
		return(pseudonim);
	};
	public void setPseudonim(java.lang.String _pseudonim_) {
		this.pseudonim = _pseudonim_;
	};

	public java.lang.String getNif() {
		return(nif);
	};
	public void setNif(java.lang.String _nif_) {
		this.nif = _nif_;
	};



  // ======================================

  public static PseudonimBean toBean(Pseudonim __bean) {
    if (__bean == null) { return null;}
    PseudonimBean __tmp = new PseudonimBean();
    __tmp.setPseudonimid(__bean.getPseudonimid());
    __tmp.setPseudonim(__bean.getPseudonim());
    __tmp.setNif(__bean.getNif());
		return __tmp;
	}



}
