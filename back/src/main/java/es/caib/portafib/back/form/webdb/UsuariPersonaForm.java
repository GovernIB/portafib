package es.caib.portafib.back.form.webdb;

import java.util.List;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import es.caib.portafib.back.form.PortaFIBBaseForm;
import es.caib.portafib.persistence.UsuariPersonaJPA;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * Created by GenApp. Do not modify.
 */
public class UsuariPersonaForm extends PortaFIBBaseForm {
  
  private UsuariPersonaJPA usuariPersona;
  
  
  private CommonsMultipartFile rubricaID;
  private boolean rubricaIDDelete;
  
  public UsuariPersonaForm() {
  }
  
  public UsuariPersonaForm(UsuariPersonaForm __toClone) {
    super(__toClone);
      this.usuariPersona = __toClone.usuariPersona;
    this.listOfIdiomaForIdiomaID = __toClone.listOfIdiomaForIdiomaID;
  }
  
  public UsuariPersonaForm(UsuariPersonaJPA usuariPersona, boolean nou) {
    super(nou);
    this.usuariPersona = usuariPersona;
  }
  
  public UsuariPersonaJPA getUsuariPersona() {
    return usuariPersona;
  }
  public void setUsuariPersona(UsuariPersonaJPA usuariPersona) {
    this.usuariPersona = usuariPersona;
  }
  
  
  public CommonsMultipartFile getRubricaID() {
    return rubricaID;
  }
  
   public void setRubricaID(CommonsMultipartFile rubricaID) {
    this.rubricaID = rubricaID;
  }
  public boolean isRubricaIDDelete() {
    return rubricaIDDelete;
  }
  
  public void setRubricaIDDelete(boolean rubricaIDDelete) {
    this.rubricaIDDelete = rubricaIDDelete;
   }
  private List<StringKeyValue> listOfIdiomaForIdiomaID;

  public List<StringKeyValue> getListOfIdiomaForIdiomaID() {
    return this.listOfIdiomaForIdiomaID;
  }

  public void setListOfIdiomaForIdiomaID(List<StringKeyValue> listOfIdiomaForIdiomaID) {
    this.listOfIdiomaForIdiomaID = listOfIdiomaForIdiomaID;
  }



  
} // Final de Classe 
