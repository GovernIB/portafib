package es.caib.portafib.logic.signatures;

import es.caib.portafib.logic.utils.datasource.IPortaFIBDataSource;
import org.fundaciobit.genapp.common.i18n.I18NException;

import java.util.List;

public interface SignatureExtractor {

    List<Signature> extract(IPortaFIBDataSource source) throws I18NException;
}
