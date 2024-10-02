package es.caib.portafib.api.interna.secure.apisimple.v1.apisib;

public class ApisIBKeyValue {


	  protected String key;

	  protected String value;

	  /**
	 * 
	 */
	  public ApisIBKeyValue() {
	    super();
	  }

	  /**
	   * @param key
	   * @param value
	   */
	  public ApisIBKeyValue(String key, String value) {
	    super();
	    this.key = key;
	    this.value = value;
	  }

	  public String getKey() {
	    return key;
	  }

	  public void setKey(String key) {
	    this.key = key;
	  }

	  public String getValue() {
	    return value;
	  }

	  public void setValue(String value) {
	    this.value = value;
	  }
}
