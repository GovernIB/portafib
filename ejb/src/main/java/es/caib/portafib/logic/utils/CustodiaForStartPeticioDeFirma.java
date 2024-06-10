package es.caib.portafib.logic.utils;

import org.fundaciobit.pluginsib.documentcustody.api.IDocumentCustodyPlugin;

import es.caib.portafib.model.entity.CustodiaInfo;

/**
 * 
 * @author anadal(u80067)
 *
 */
public class CustodiaForStartPeticioDeFirma {

    public final String custodyID;

    public final CustodiaInfo custodiaInfo;

    public final IDocumentCustodyPlugin plugin;

    public CustodiaForStartPeticioDeFirma(String custodyID, CustodiaInfo custodiaInfo, IDocumentCustodyPlugin plugin) {
        super();
        this.custodyID = custodyID;
        this.custodiaInfo = custodiaInfo;
        this.plugin = plugin;
    }

}
