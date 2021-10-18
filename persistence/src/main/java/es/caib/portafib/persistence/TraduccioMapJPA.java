
package es.caib.portafib.persistence;
import es.caib.portafib.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;


@SuppressWarnings("deprecation")
@javax.persistence.Embeddable
@Table(name = "pfi_traducciomap" )
@javax.xml.bind.annotation.XmlRootElement
public class TraduccioMapJPA implements TraduccioMap {



private static final long serialVersionUID = 1632585305L;

  /**  */
    @Column(name="valor",length = 4000)
    java.lang.String valor;



  /** Constructor Buit */
  public TraduccioMapJPA() {
  }

  public TraduccioMapJPA(String _valor_) {
    this.valor = _valor_;
  }

	public java.lang.String getValor() {
		return(valor);
	};
	public void setValor(java.lang.String _valor_) {
		this.valor = _valor_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof TraduccioMap) {
      TraduccioMap __instance = (TraduccioMap)__obj;
      __result = true;
      __result = super.equals(__instance);
    } else {
      __result = false;
    }
    return __result;
  }



}
