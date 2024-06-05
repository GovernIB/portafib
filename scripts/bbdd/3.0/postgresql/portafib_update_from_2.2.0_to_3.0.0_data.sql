
--######################################################################
--#####  Fer funcionals els Revisors de Destinatari #824  
--######################################################################


insert into pfi_propietatglobal (propietatglobalid, clau, descripcio, entitatid, valor)
select nextval('pfi_propietatglobal_seq') as propietaglobalid ,'es.caib.portafib.revisordedestinatari.restretornarrevisorsglobals' as clau, 
        'Opcional. Valor per defecte false. Si val true en la consulta al servei rest de RevisorDeDestinatari també retorna els Revisors Globals.' as descripcio,
        entitatid, null as valor
        from pfi_entitat;



--###########################################################################
--#####  Actualitzar PluginsIB-SignatureServer i PluginsIB-SignatureWeb #827  
--###########################################################################

UPDATE pfi_plugin SET classe=REPLACE(classe, 'org.fundaciobit.plugins.', 'org.fundaciobit.pluginsib.')  WHERE tipus=0 OR tipus=3;
UPDATE pfi_plugin SET propertiesadmin=REPLACE(propertiesadmin, 'es.caib.portafib.plugins.', 'es.caib.portafib.pluginsib.')  WHERE tipus=0 OR tipus=3;
UPDATE pfi_plugin SET propertiesentitat=REPLACE(propertiesentitat, 'es.caib.portafib.plugins.', 'es.caib.portafib.pluginsib.')  WHERE tipus=0 OR tipus=3;



--###########################################################################
--## Actualitzar Plugin de Validació de Certificats i De Firmes a versió 3.0.0-SNAPSHOT #828   
--###########################################################################

UPDATE pfi_plugin SET classe=REPLACE(classe, 'org.fundaciobit.plugins.', 'org.fundaciobit.pluginsib.')  WHERE tipus=4;
UPDATE pfi_plugin SET propertiesadmin=REPLACE(propertiesadmin, 'es.caib.portafib.plugins.', 'es.caib.portafib.pluginsib.')  WHERE tipus=4;
UPDATE pfi_plugin SET propertiesentitat=REPLACE(propertiesentitat, 'es.caib.portafib.plugins.', 'es.caib.portafib.pluginsib.')  WHERE tipus=4;


--###########################################################################
--##  Actualitzar versió de pluginsib-timestamp a versió 3.0.0 #829    
--###########################################################################

UPDATE pfi_plugin SET classe=REPLACE(classe, 'org.fundaciobit.plugins.', 'org.fundaciobit.pluginsib.')  WHERE tipus=1;
UPDATE pfi_plugin SET propertiesadmin=REPLACE(propertiesadmin, 'es.caib.portafib.plugins.', 'es.caib.portafib.pluginsib.')  WHERE tipus=1;
UPDATE pfi_plugin SET propertiesentitat=REPLACE(propertiesentitat, 'es.caib.portafib.plugins.', 'es.caib.portafib.pluginsib.')  WHERE tipus=1;