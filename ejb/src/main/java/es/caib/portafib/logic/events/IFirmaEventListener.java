package es.caib.portafib.logic.events;


import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.portafib.persistence.PeticioDeFirmaJPA;
import es.caib.portafib.model.entity.EstatDeFirma;

/**
 * 
 * @author anadal
 *
 */
public interface IFirmaEventListener {

  public void requerit_per_validar(PeticioDeFirmaJPA peticioDeFirma, EstatDeFirma estatDeFirma) throws I18NException;
  
  public void descartat_per_validar(PeticioDeFirmaJPA peticioDeFirma, EstatDeFirma estatDeFirma) throws I18NException;
  
  public void requerit_per_firmar(PeticioDeFirmaJPA peticioDeFirma, EstatDeFirma estatDeFirma) throws I18NException;
  
  public void descartat_per_firmar(PeticioDeFirmaJPA peticioDeFirma, EstatDeFirma estatDeFirma) throws I18NException;
  
  public void validat(PeticioDeFirmaJPA peticioDeFirma, EstatDeFirma estatDeFirma) throws I18NException;
  
  public void invalidat(PeticioDeFirmaJPA peticioDeFirma, EstatDeFirma estatDeFirma) throws I18NException;
  
  public void firma_parcial(PeticioDeFirmaJPA peticioDeFirma,EstatDeFirma estatDeFirma) throws I18NException;
  
  public void peticio_firmada(PeticioDeFirmaJPA peticioDeFirma) throws I18NException;
  
  public void peticio_rebutjada(PeticioDeFirmaJPA peticioDeFirma,EstatDeFirma estatDeFirma) throws I18NException;

  public void peticio_en_proces(PeticioDeFirmaJPA peticioDeFirma) throws I18NException;
  
  public void peticio_pausada(PeticioDeFirmaJPA peticioDeFirma) throws I18NException;

}
