package es.caib.portafib.logic.jaxb;

import es.caib.portafib.logic.events.FirmaEvent;
import es.caib.portafib.model.bean.FitxerBean;
import es.caib.portafib.model.bean.PeticioDeFirmaBean;
import org.apache.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import java.io.StringWriter;

/**
 * Classe d'utilitat per a serialitzar/deserialitzar objectes amb cadenes de XML.
 * @author areus
 */
public class JAXBUtil {

   private static final Logger log = Logger.getLogger(JAXBUtil.class);

   /**
    * La classe és thread-safe i costa construir-la, per tant es reaprofita la instància.
    */
   private static final JAXBContext jaxbContext;

   static {
      try {
         // S'han de passar totes les classes que voldrem serialitzar.
         jaxbContext = JAXBContext.newInstance(PeticioDeFirmaBean.class, FirmaEvent.class);
      } catch (JAXBException e) {
         // L'error en la incialització serà un error de sistema.
         throw new RuntimeException(e);
      }
   }

   /**
    * A partir d'un objecte el retorna serialitzat en XML
    * @param objecte Ha de ser un objecte d'un dels tipus registrats a JAXBContext.
    * @return String amb la representació en XML de l'objecte, o null si l'objecte és null o no és un dels tipus vàlids.
    */
   public static String marshal(Object objecte) {
      try {
         StringWriter writer = new StringWriter();
         Marshaller marshaller = jaxbContext.createMarshaller();
         marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
         marshaller.marshal(getJAXBElement(objecte), writer);
         return writer.toString();
      } catch (JAXBException e) {
         log.error("Error serialitzant a xml", e);
         return null;
      }
   }

   /* Poc elegant, però atès que no tenim control sobre les classes del model, l'única manera d'assignar-lis
    un root element, i fer les adaptaciosn que calguin és així.
    */
   private static JAXBElement<?> getJAXBElement(Object objecte) {
      if (objecte instanceof FirmaEvent) {
         return new JAXBElement<FirmaEvent>(
               new QName("firmaEvent"), FirmaEvent.class, (FirmaEvent) objecte);
      }
      if (objecte instanceof PeticioDeFirmaBean) {
         PeticioDeFirmaBean peticioDeFirmaBean = (PeticioDeFirmaBean) objecte;
         cleanFitxerBean(peticioDeFirmaBean.getFitxerAFirmar());
         cleanFitxerBean(peticioDeFirmaBean.getFitxerAdaptat());
         cleanFitxerBean(peticioDeFirmaBean.getFirmaOriginalDetached());
         cleanFitxerBean(peticioDeFirmaBean.getLogoSegell());
         return new JAXBElement<PeticioDeFirmaBean>(
               new QName("peticioDeFirmaBean"), PeticioDeFirmaBean.class, peticioDeFirmaBean);
      }
      return null;
   }

   private static void cleanFitxerBean(FitxerBean fitxerBean) {
      if (fitxerBean != null) {
         fitxerBean.setData(null);
      }
   }
}
