package es.caib.portafib.logic;

import java.util.Set;

import es.caib.portafib.ejb.FirmaLocal;
import es.caib.portafib.jpa.FirmaJPA;
import es.caib.portafib.model.entity.Firma;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;

/**
 * 
 * @author anadal
 * 
 */
@Local
public interface FirmaLogicaLocal extends FirmaLocal {

  String JNDI_NAME = "java:app/portafib-logic/FirmaLogicaEJB";

  public Set<Long> deleteFull(long firmaID) throws I18NException;

  public Set<Long> deleteFull(FirmaJPA firma) throws I18NException;

  public FirmaJPA createFull(FirmaJPA firma) throws I18NException;

  public FirmaJPA getFirmaByToken(String token) throws I18NException;
  
  public FirmaJPA findByPrimaryKeyUnauthorized(Long _ID_);
  
  public Firma updateUnauthorized(Firma instance) throws I18NException;
  
  public String getUniqueTokenForFirma() throws I18NException;
  

}
