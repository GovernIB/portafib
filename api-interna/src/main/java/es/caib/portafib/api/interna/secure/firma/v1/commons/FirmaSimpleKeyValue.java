package es.caib.portafib.api.interna.secure.firma.v1.commons;

import es.caib.portafib.api.interna.secure.firma.v1.commons.apisib.ApisIBKeyValue;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Ofereix informació addicional sobre el firmant o la firma")
public class FirmaSimpleKeyValue extends ApisIBKeyValue {

	public FirmaSimpleKeyValue() {
		super();
	}

	public FirmaSimpleKeyValue(String key, String value) {
		super(key, value);
	}

}
