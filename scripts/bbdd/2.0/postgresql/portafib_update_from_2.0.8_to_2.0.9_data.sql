
--######################################################################
--#####  Acursar nom de la petició en llistat de Solicitant #147 
--######################################################################


-- ========================================
-- Propietats Globals
-- ========================================

insert into pfi_propietatglobal (propietatglobalid, clau, descripcio, entitatid, valor)
select nextval('pfi_portafib_seq') as propietaglobalid ,'es.caib.portafib.maxpeticiotitlelength' as clau, 
        'Opcional. Valor per defecte 0. Indica la longitud màxima del titol de una peticio de firma si està fixada.' as descripcio,
        entitatid, null as valor
        from pfi_entitat;

