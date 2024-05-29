
--######################################################################
--#####  Fer funcionals els Revisors de Destinatari #824  
--######################################################################


insert into pfi_propietatglobal (propietatglobalid, clau, descripcio, entitatid, valor)
select pfi_propietatglobal_seq.nextval as propietaglobalid ,'es.caib.portafib.revisordedestinatari.restretornarrevisorsglobals' as clau, 
        'Opcional. Valor per defecte false. Si val true en la consulta al servei rest també retorna els Revisors Globals.' as descripcio,
        entitatid, null as valor
        from pfi_entitat;



--###########################################################################
--#####  Actualitzar PluginsIB-SignatureServer i PluginsIB-SignatureWeb #827  
--###########################################################################


UPDATE pfi_plugin SET classe=REPLACE(classe, 'org.fundaciobit.plugins.', 'org.fundaciobit.pluginsib.')  WHERE tipus=0 OR tipus=3;
UPDATE pfi_plugin SET propertiesadmin=REPLACE(propertiesadmin, 'es.caib.portafib.plugins.', 'es.caib.portafib.pluginsib.')  WHERE tipus=0 OR tipus=3;
UPDATE pfi_plugin SET propertiesentitat=REPLACE(propertiesentitat, 'es.caib.portafib.plugins.', 'es.caib.portafib.pluginsib.')  WHERE tipus=0 OR tipus=3;
