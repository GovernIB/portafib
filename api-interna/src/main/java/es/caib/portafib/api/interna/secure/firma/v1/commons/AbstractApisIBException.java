package es.caib.portafib.api.interna.secure.firma.v1.commons;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 
 * @author anadal
 *
 */
@Schema
public abstract class AbstractApisIBException extends Exception {

	private static final long serialVersionUID = 1L;
	protected String description = null;

	/**
	 * 
	 */
	public AbstractApisIBException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 */
	public AbstractApisIBException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public AbstractApisIBException(String message) {
		super(message);
	}

	/**
	 * @param message
	 */
	public AbstractApisIBException(String message, String description) {
		super(message);
		this.description = description;
	}

	/**
	 * @param cause
	 */
	public AbstractApisIBException(Throwable cause) {
		super(cause);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String toString() {
		String msg = super.toString();

		if (this.description != null) {
			msg = msg + "\n" + this.description;
		}

		return msg;

	}

}
