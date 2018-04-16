

El fitxer portafib_create_schema.sql es genera des de PgAdmin III emprant 
una exportació simple de només l'esquema emprant "comandes d'Inserció":

Despres l'editam amb notepad++ i executam el següent reemplaç emprant "expressions regurals"

ORIGEN:  -- TOC entry .+ \(class .+ OID .+\)\n
REEMPLAÇ: {BUIT}
