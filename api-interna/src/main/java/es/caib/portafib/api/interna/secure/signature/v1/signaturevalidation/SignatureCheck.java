package es.caib.portafib.api.interna.secure.signature.v1.signaturevalidation;

/**
 * 
 * @author anadal
 *
 */
public class SignatureCheck {

    protected String name;

    protected String type;

    /**
     * 
     */
    public SignatureCheck() {
        super();
    }

    /**
     * @param name
     * @param type
     */
    public SignatureCheck(String name, String type) {
        super();
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
