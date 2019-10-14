package es.caib.portafib.logic.jaxb;

import javax.activation.DataHandler;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Adapter per evitar que el contingut dels fitxers es passi dins la serialització.
 * Atès que aquesta classe s'aplicarà sempre, per defecte no fa res. Només en el cas que s'instancii amb el valor
 * a true i es registri dins el marshaller amb el setAdapter actuarà.
 */
public class DataHandlerAdapter extends XmlAdapter<DataHandler, DataHandler> {

   private static final DataHandler EMPTY_DATAHANDLER = new DataHandler(new byte[0], "application/octet-stream");

   private boolean audit;

   public DataHandlerAdapter(boolean audit) {
      this.audit = audit;
   }

   public DataHandlerAdapter() {
      this(false);
   }

   @Override
   public DataHandler unmarshal(DataHandler dataHandler) {
      return dataHandler;
   }

   @Override
   public DataHandler marshal(DataHandler dataHandler) {
      if (audit) {
         return EMPTY_DATAHANDLER;
      }
      return dataHandler;
   }
}
