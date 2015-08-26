package es.caib.portafib.back.form.soli;

import java.util.ArrayList;
import java.util.List;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * @author anadal
 *
 */
public class PeticioFirmaMassivaForm {

  private Long peticioDeFirmaID;

  private List<MultipartFile> files = new ArrayList<MultipartFile>();

  private String titolPeticio;

  private String descripcio;

  private String motiu;

  private List<StringKeyValue> peticionsDeFirmesBase;

  public Long getPeticioDeFirmaID() {
    return peticioDeFirmaID;
  }

  public void setPeticioDeFirmaID(Long peticioDeFirmaID) {
    this.peticioDeFirmaID = peticioDeFirmaID;
  }

  public List<MultipartFile> getFiles() {
    return files;
  }

  public void setFiles(List<MultipartFile> files) {
    this.files = files;
  }

  public List<StringKeyValue> getPeticionsDeFirmesBase() {
    return peticionsDeFirmesBase;
  }

  public void setPeticionsDeFirmesBase(List<StringKeyValue> peticionsDeFirmesBase) {
    this.peticionsDeFirmesBase = peticionsDeFirmesBase;
  }

  public String getTitolPeticio() {
    return titolPeticio;
  }

  public void setTitolPeticio(String titolPeticio) {
    this.titolPeticio = titolPeticio;
  }

  public String getDescripcio() {
    return descripcio;
  }

  public void setDescripcio(String descripcio) {
    this.descripcio = descripcio;
  }

  public String getMotiu() {
    return motiu;
  }

  public void setMotiu(String motiu) {
    this.motiu = motiu;
  }

}
