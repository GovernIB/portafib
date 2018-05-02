package es.caib.portafib.logic.events;

import java.util.ArrayList;
import java.util.List;

import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.portafib.jpa.PeticioDeFirmaJPA;
import es.caib.portafib.model.entity.EstatDeFirma;
import es.caib.portafib.utils.ConstantsV2;
/**
 * Classe emprada per guardar els events a llançar, i realitzar-los 
 * al final de cert process de forma conjunta.
 * 
 * @author anadal
 */
public class FirmaEventList implements IFirmaEventListener, ConstantsV2 {


  
  private List<FirmaEvent> list = new ArrayList<FirmaEvent>();
  

  public void requerit_per_validar(PeticioDeFirmaJPA peticioDeFirma, EstatDeFirma estatDeFirma) throws I18NException {
    list.add(new FirmaEvent(NOTIFICACIOAVIS_REQUERIT_PER_VALIDAR, FirmaEvent.PRIORITAT_NORMAL, peticioDeFirma, estatDeFirma));
  }


  public void descartat_per_validar(PeticioDeFirmaJPA peticioDeFirma, EstatDeFirma estatDeFirma)throws I18NException {
    list.add(new FirmaEvent(NOTIFICACIOAVIS_DESCARTAT_PER_VALIDAR, FirmaEvent.PRIORITAT_BAIXA, peticioDeFirma, estatDeFirma));
  }


  public void requerit_per_firmar(PeticioDeFirmaJPA peticioDeFirma, EstatDeFirma estatDeFirma) throws I18NException {    
    list.add(new FirmaEvent(NOTIFICACIOAVIS_REQUERIT_PER_FIRMAR, FirmaEvent.PRIORITAT_NORMAL, peticioDeFirma, estatDeFirma));    
  }


  public void descartat_per_firmar(PeticioDeFirmaJPA peticioDeFirma,EstatDeFirma estatDeFirma) throws I18NException{
    list.add(new FirmaEvent(NOTIFICACIOAVIS_DESCARTAT_PER_FIRMAR, FirmaEvent.PRIORITAT_BAIXA, peticioDeFirma, estatDeFirma));    
  }


  public void validat(PeticioDeFirmaJPA peticioDeFirma,EstatDeFirma estatDeFirma) throws I18NException {
    list.add(new FirmaEvent(NOTIFICACIOAVIS_VALIDAT, FirmaEvent.PRIORITAT_BAIXA, peticioDeFirma, estatDeFirma));
  }


  public void invalidat(PeticioDeFirmaJPA peticioDeFirma,EstatDeFirma estatDeFirma) throws I18NException{
    list.add(new FirmaEvent(NOTIFICACIOAVIS_INVALIDAT, FirmaEvent.PRIORITAT_BAIXA, peticioDeFirma, estatDeFirma));
  }


  public void firma_parcial(PeticioDeFirmaJPA peticioDeFirma, EstatDeFirma estatDeFirma)throws I18NException {
    list.add(new FirmaEvent(NOTIFICACIOAVIS_FIRMA_PARCIAL, FirmaEvent.PRIORITAT_ALTA, peticioDeFirma, estatDeFirma));
  }


  public void peticio_firmada(PeticioDeFirmaJPA peticioDeFirma) throws I18NException {
    list.add(new FirmaEvent(NOTIFICACIOAVIS_PETICIO_FIRMADA, FirmaEvent.PRIORITAT_NORMAL, peticioDeFirma));
  }


  public void peticio_rebutjada(PeticioDeFirmaJPA peticioDeFirma, EstatDeFirma estatDeFirma)throws I18NException {
    list.add(new FirmaEvent(NOTIFICACIOAVIS_PETICIO_REBUTJADA, FirmaEvent.PRIORITAT_NORMAL, peticioDeFirma, estatDeFirma));
  }

  public void peticio_en_proces(PeticioDeFirmaJPA peticioDeFirma) throws I18NException {
    list.add(new FirmaEvent(NOTIFICACIOAVIS_PETICIO_EN_PROCES, FirmaEvent.PRIORITAT_ALTA, peticioDeFirma)); 
  }
  

  @Override
  public void peticio_pausada(PeticioDeFirmaJPA peticioDeFirma) throws I18NException {
    list.add(new FirmaEvent(NOTIFICACIOAVIS_PETICIO_PAUSADA, FirmaEvent.PRIORITAT_ALTA, peticioDeFirma));
  }

  public List<FirmaEvent> getList() {
    return list;
  }


  public void setList(List<FirmaEvent> list) {
    this.list = list;
  }


  public void add(FirmaEvent event) {
    this.list.add(event);
  }



  
}
