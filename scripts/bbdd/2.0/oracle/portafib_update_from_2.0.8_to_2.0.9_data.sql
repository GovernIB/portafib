
--######################################################################
--#####  Acursar nom de la petició en llistat de Solicitant #147 
--######################################################################


-- ========================================
-- Propietats Globals
-- ========================================

insert into pfi_propietatglobal
select pfi_portafib_seq.nextval as propietaglobalid ,'es.caib.portafib.maxpeticiotitlelength' as clau, 
        'Opcional. Valor per defecte 50. Indica la longitud màxima del titol de una peticio de firma.' as descripcio,
        entitatid, null as valor
        from pfi_entitat;

