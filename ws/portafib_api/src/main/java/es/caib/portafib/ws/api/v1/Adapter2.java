
package es.caib.portafib.ws.api.v1;

import java.sql.Time;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class Adapter2
    extends XmlAdapter<String, Time>
{


    public Time unmarshal(String value) {
        return (org.fundaciobit.genapp.common.ws.WsTimeAdapter.parseTime(value));
    }

    public String marshal(Time value) {
        return (org.fundaciobit.genapp.common.ws.WsTimeAdapter.printTime(value));
    }

}
