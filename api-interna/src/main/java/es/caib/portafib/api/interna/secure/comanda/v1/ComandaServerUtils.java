package es.caib.portafib.api.interna.secure.comanda.v1;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.temporal.IsoFields;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import javax.activation.MimetypesFileTypeMap;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.InternalServerErrorException;

import org.fundaciobit.pluginsib.utils.rest.RestUtils;

import es.caib.comanda.model.server.monitoring.DiaSetmanaEnum;
import es.caib.comanda.model.server.monitoring.FitxerContingut;
import es.caib.comanda.model.server.monitoring.FitxerInfo;
import es.caib.comanda.model.server.monitoring.Temps;

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

    public static Temps createTempsFromDate(Timestamp data) {

        Temps temps = new Temps();

        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        int mes = cal.get(Calendar.MONTH) + 1;

        temps.data(data.toInstant().atZone(ZoneId.systemDefault()).toOffsetDateTime());
        temps.anualitat(cal.get(Calendar.YEAR));
        temps.trimestre(mes / 3);
        temps.mes(mes);
        temps.setmana(cal.get(Calendar.WEEK_OF_YEAR));

        DiaSetmanaEnum sde = calendarDayOfWeek2DiaSetmanaEnum(cal.get(Calendar.DAY_OF_WEEK));

        temps.diaSetmana(sde);
        temps.dia(cal.get(Calendar.DAY_OF_MONTH));

        return temps;
    }

    protected static DiaSetmanaEnum calendarDayOfWeek2DiaSetmanaEnum(int calendarDayOfWeek) {

        DiaSetmanaEnum sde = null;

        switch (calendarDayOfWeek) {
            case Calendar.SUNDAY:
                sde = DiaSetmanaEnum.DM;
            break;
            case Calendar.MONDAY:
                sde = DiaSetmanaEnum.DL;
            break;
            case Calendar.TUESDAY:
                sde = DiaSetmanaEnum.DM;
            break;
            case Calendar.WEDNESDAY:
                sde = DiaSetmanaEnum.DC;
            break;
            case Calendar.THURSDAY:
                sde = DiaSetmanaEnum.DJ;
            break;
            case Calendar.FRIDAY:
                sde = DiaSetmanaEnum.DS;
            break;
            case Calendar.SATURDAY:
                sde = DiaSetmanaEnum.DG;
            break;
        }
        return sde;
    }

    public static Temps creteTempsFromOffsetDateTime(OffsetDateTime data) {

        Temps temps = new Temps();

        temps.data(data);
        temps.anualitat(data.getYear());
        temps.mes(data.getMonthValue());
        temps.trimestre((temps.getMes() - 1) / 3 + 1);
        temps.setmana(data.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR));
        DiaSetmanaEnum sde = calendarDayOfWeek2DiaSetmanaEnum(data.getDayOfWeek().ordinal() + 1);
        temps.diaSetmana(sde);
        temps.dia(data.getDayOfMonth());

        return temps;
    }
    
    
    
    // ==============================================
    // ================  UTILITATS DE LOGS ================
    // ==============================================
    
    

    public static FitxerContingut getFitxerByNom(String nomFitxer) {
        String logsDir =ComandaServerUtils. getLogsDirectory();

        if (logsDir == null) {
            throw new InternalServerErrorException("No s'ha pogut determinar el directori de logs de JBoss");
        }

        java.nio.file.Path logFilePath = Paths.get(logsDir, nomFitxer);

        if (!logFilePath.toFile().exists()) {
            throw new InternalServerErrorException("El fitxer de log no existeix: " + nomFitxer);
        }

        FitxerInfo info = getInfoOfFile(logFilePath.toFile());

        FitxerContingut fc = new FitxerContingut();
        fc.setNom(info.getNom());
        try {
            fc.setContingut(Files.readAllBytes(logFilePath));
        } catch (IOException e) {
            throw new InternalServerErrorException(
                    "No es pot llegir el fitxer: " + nomFitxer + ". Error: " + e.getMessage());
        }
        fc.setDataCreacio(info.getDataCreacio());
        fc.setDataModificacio(info.getDataModificacio());
        fc.setMida(info.getMida());
        fc.setMimeType(info.getMimeType());

        return fc;
    }
    

    protected static FitxerInfo getInfoOfFile(File fitxer) {
        FitxerInfo info = new FitxerInfo();

        try {

            BasicFileAttributes attrs = Files.readAttributes(fitxer.toPath(), BasicFileAttributes.class);

            FileTime creationTime = attrs.creationTime();

            info.setDataCreacio(RestUtils.convertDateToDateTimeISO8601(new java.sql.Timestamp(creationTime.toMillis())));

        } catch (IOException e) {

        }

        info.setDataModificacio(RestUtils.convertDateToDateTimeISO8601(new Timestamp(fitxer.lastModified())));
        info.setMida(fitxer.length());
        // Obtenir el tipus MIME del fitxer
        try {
            String mimeType = Files.probeContentType(fitxer.toPath());
            if (mimeType == null) {
                // Si no se pudo determinar el tipo MIME, usar una alternativa
                mimeType = new MimetypesFileTypeMap().getContentType(fitxer);
            }
            info.setMimeType(mimeType);
        } catch (IOException e) {
            // En cas d'error, assignar un tipus MIME genèric
            info.setMimeType("application/octet-stream");
        }

        info.setNom(fitxer.getName());
        return info;
    }

    public static  String getLogsDirectory() {
        // Intentar obtenir el directori de logs de JBoss des de propietats del sistema
        String jbossLogDir = System.getProperty("jboss.server.log.dir");

        if (jbossLogDir != null && !jbossLogDir.isEmpty()) {
            return jbossLogDir;
        }

        // Alternativa: usar jboss.server.base.dir + /log
        String jbossBaseDir = System.getProperty("jboss.server.base.dir");
        if (jbossBaseDir != null && !jbossBaseDir.isEmpty()) {
            return jbossBaseDir + File.separator + "log";
        }

        return null;

    }
    
    

    public static  List<String> llegirUltimesLinies(String nomFitxer, Long nLinies) {
        String logsDir = getLogsDirectory();

        if (logsDir == null) {
            throw new InternalServerErrorException("No s'ha pogut determinar el directori de logs de JBoss");
        }

        java.nio.file.Path logFilePath = Paths.get(logsDir, nomFitxer);

        if (!logFilePath.toFile().exists()) {
            throw new InternalServerErrorException("El fitxer de log no existeix: " + nomFitxer);
        }

        List<String> linies = new ArrayList<>();

        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(logFilePath.toFile(), "r");
            long fileLength = raf.length();
            long position = fileLength - 1;
            StringBuilder sb = new StringBuilder();
            int liniesLlegides = 0;

            // Llegir des del final cap enrere
            while (position >= 0 && liniesLlegides < nLinies) {
                raf.seek(position);
                char c = (char) raf.read();

                if (c == '\n' && sb.length() > 0) {
                    linies.add(sb.reverse().toString());
                    sb = new StringBuilder();
                    liniesLlegides++;
                } else if (c != '\n') {
                    sb.append(c);
                }

                position--;
            }

            // Afegir la darrere línia si queda contingut
            if (sb.length() > 0) {
                linies.add(sb.reverse().toString());
            }

            Collections.reverse(linies);
        } catch (IOException e) {
            throw new InternalServerErrorException(
                    "No es pot llegir el fitxer: " + nomFitxer + ". Error: " + e.getMessage());
        } finally {
            if (raf != null) {
                try {
                    raf.close();
                } catch (IOException e) {
                    // Ignorar error al cerrar
                }
            }
        }

        return linies;
    }
    
    
    

    public static  List<FitxerInfo> llistarFitxers() {
        String logsDir = ComandaServerUtils.getLogsDirectory();

        if (logsDir == null) {
            throw new InternalServerErrorException("No s'ha pogut determinar el directori de logs de JBoss");
        }

        java.nio.file.Path logPath = Paths.get(logsDir);

        if (!Files.exists(logPath) || !Files.isDirectory(logPath)) {
            throw new InternalServerErrorException("El directori de logs no existeix: " + logsDir);
        }

        List<FitxerInfo> list = new ArrayList<>();
        File[] fitxers = logPath.toFile().listFiles();
        for (File fitxer : fitxers) {
            FitxerInfo info = getInfoOfFile(fitxer);
            list.add(info);
        }

        return list;
    }

    
    
}
