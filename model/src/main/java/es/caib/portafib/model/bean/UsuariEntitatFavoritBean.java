
package es.caib.portafib.model.bean;

import es.caib.portafib.model.entity.UsuariEntitatFavorit;


public class UsuariEntitatFavoritBean implements UsuariEntitatFavorit {



	long ID;// PK
	java.lang.String origenID;
	java.lang.String favoritID;


  /** Constructor Buit */
  public UsuariEntitatFavoritBean() {
  }

  /** Constructor amb tots els camps  */
  public UsuariEntitatFavoritBean(long ID , java.lang.String origenID , java.lang.String favoritID) {
    this.ID=ID;
    this.origenID=origenID;
    this.favoritID=favoritID;
}
  /** Constructor sense valors autoincrementals */
  public UsuariEntitatFavoritBean(java.lang.String origenID , java.lang.String favoritID) {
    this.origenID=origenID;
    this.favoritID=favoritID;
}
  public UsuariEntitatFavoritBean(UsuariEntitatFavorit __bean) {
    this.setID(__bean.getID());
    this.setOrigenID(__bean.getOrigenID());
    this.setFavoritID(__bean.getFavoritID());
	}

	public long getID() {
		return(ID);
	};
	public void setID(long _ID_) {
		this.ID = _ID_;
	};

	public java.lang.String getOrigenID() {
		return(origenID);
	};
	public void setOrigenID(java.lang.String _origenID_) {
		this.origenID = _origenID_;
	};

	public java.lang.String getFavoritID() {
		return(favoritID);
	};
	public void setFavoritID(java.lang.String _favoritID_) {
		this.favoritID = _favoritID_;
	};



  // ======================================

  public static UsuariEntitatFavoritBean toBean(UsuariEntitatFavorit __bean) {
    if (__bean == null) { return null;}
    UsuariEntitatFavoritBean __tmp = new UsuariEntitatFavoritBean();
    __tmp.setID(__bean.getID());
    __tmp.setOrigenID(__bean.getOrigenID());
    __tmp.setFavoritID(__bean.getFavoritID());
		return __tmp;
	}



}
