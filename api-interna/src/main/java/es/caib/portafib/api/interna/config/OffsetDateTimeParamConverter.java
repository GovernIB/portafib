package es.caib.portafib.api.interna.config;


import java.time.OffsetDateTime;
import java.time.format.DateTimeParseException;
import javax.ws.rs.ext.ParamConverter;

public class OffsetDateTimeParamConverter implements ParamConverter<OffsetDateTime> {

    @Override
    public OffsetDateTime fromString(String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        try {
            return OffsetDateTime.parse(value);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid OffsetDateTime format: " + value, e);
        }
    }

    @Override
    public String toString(OffsetDateTime value) {
        return value != null ? value.toString() : null;
    }
}
