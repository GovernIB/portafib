package es.caib.portafib.api.interna.secure.comanda.v1;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import javax.ws.rs.BadRequestException;

/**
 * Utilitats comunes pel servidor de Comanda
 * @author anadal
 * 6 feb 2026 9:21:33
 */
public class ComandaServerUtils {

    /** Metode que donat un timestamp retorna un array on l'element 0 és un Timestamp amb hora 00:00:00.000 
     * i l'element 1 és un Tmestamp amb hora 23:59:59.999 
     */
    public static Timestamp[] getStartAndEndOfDay(Timestamp timestamp) throws BadRequestException {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            String dateStr = dateFormat.format(timestamp);
            Timestamp startOfDay = new Timestamp(dateFormat.parse(dateStr).getTime());
            Timestamp endOfDay = new Timestamp(startOfDay.getTime() + 24 * 60 * 60 * 1000 - 1);
            return new Timestamp[] { startOfDay, endOfDay };
        } catch (ParseException e) {
            throw new BadRequestException("Error al parsear la fecha: " + timestamp, e);
        }
    }

    public static Timestamp stringWithFormatddMMyy2Timestamp(String data) {
        Timestamp timestamp;
        // Convertir String en format dd-MM-yyyy a Timestamp

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            java.util.Date parsedDate = dateFormat.parse(data);
            timestamp = new Timestamp(parsedDate.getTime());

            // Aquí puedes usar el timestamp para obtener las estadísticas correspondientes
        } catch (Exception e) {
            throw new BadRequestException("Formato de fecha inválido. Se esperaba dd-MM-yyyy. Rebuda data : " + data,
                    e);
        }
        return timestamp;
    }

    public static OffsetDateTime createTempsFromDate(Timestamp data) {

        return data.toInstant().atZone(ZoneId.systemDefault()).toOffsetDateTime();

    }

}
