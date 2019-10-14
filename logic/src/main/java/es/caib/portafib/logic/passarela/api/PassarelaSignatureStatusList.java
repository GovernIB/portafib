package es.caib.portafib.logic.passarela.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe d'utilitat per serialitzar amb JAXB una llista de
 * {@link es.caib.portafib.logic.passarela.api.PassarelaSignatureStatus}
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PassarelaSignatureStatusList {

   public PassarelaSignatureStatusList() {
   }

   private List<PassarelaSignatureStatus> passarelaSignatureStatus = new ArrayList<PassarelaSignatureStatus>();

   public List<PassarelaSignatureStatus> getPassarelaSignatureStatus() {
      return passarelaSignatureStatus;
   }

   public void setPassarelaSignatureStatus(List<PassarelaSignatureStatus> passarelaSignatureStatus) {
      this.passarelaSignatureStatus = passarelaSignatureStatus;
   }
}
