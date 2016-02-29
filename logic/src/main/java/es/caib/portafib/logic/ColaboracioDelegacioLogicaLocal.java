package es.caib.portafib.logic;

import java.io.File;
import java.util.Set;

import es.caib.portafib.ejb.ColaboracioDelegacioLocal;
import es.caib.portafib.jpa.ColaboracioDelegacioJPA;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;

/**
 * 
 * @author dboerner
 * @author anadal
 *
 */
@Local
public interface ColaboracioDelegacioLogicaLocal extends ColaboracioDelegacioLocal {

  public ColaboracioDelegacioJPA findByPrimaryKeyFull(Long _ID_);
 
	public ColaboracioDelegacioJPA createFull(ColaboracioDelegacioJPA instance) throws I18NException;

	public Set<Long> deleteFull(ColaboracioDelegacioJPA instance) throws I18NException;
	
	public ColaboracioDelegacioJPA updateFull(ColaboracioDelegacioJPA instance) throws I18NException;
	
	public void assignarAutoritzacioADelegacio(Long delegacioID, File firmat, String nom)
    throws Exception, I18NException;
}