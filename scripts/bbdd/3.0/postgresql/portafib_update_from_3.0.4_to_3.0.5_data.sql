
--######################################################################
--##### 04/04/2025  Revisar enviament de correus als Administradors d'Agents CAIB #1022 
--######################################################################

INSERT INTO pfi_tipusnotificacio(tipusnotificacioid, descripcio, nom, esavis)  VALUES (100, null, 'notificacioavis.incidencies_administrador', null);


--######################################################################
--##### 04/04/2025  Revisar el rendiment de la secció de gestió de peticions de firma #1020
--######################################################################

CREATE INDEX pfi_petifirma_datasolicitud_i ON pfi_peticiodefirma (datasolicitud);
CREATE INDEX pfi_petifirma_datafinal_i ON pfi_peticiodefirma (datafinal);


--######################################################################
--##### 09/04/2025 Cron per anar Rebutjant Peticions Caducades #1008
--######################################################################


insert into pfi_propietatglobal (propietatglobalid, clau, descripcio, entitatid, valor)
   values(nextval('pfi_propietatglobal_seq'), 'es.caib.portafib.rebuigpeticionscaducades.cron', 'Opcional. Cron Expression per definir quan executar-se el procés de Rebuig de Peticion Caducades. Si val null o buit, llavors no s´executa el procés de neteja. Despres de modificar aquest valor, es necessari reiniciar el servidor. Veure cronmaker.com.', null, '0 0/10 1 * * * *');

insert into pfi_propietatglobal (propietatglobalid, clau, descripcio, entitatid, valor)
    values(nextval('pfi_propietatglobal_seq') ,'es.caib.portafib.rebuigpeticionscaducades.dies',
        'Opcional. Indica els dies després d´aquest valor en que el procés de Rebuig de peticions Caducades actuarà. Despres de modificar aquest valor, es necessari reiniciar el servidor. Valor per defecte 4 anys (1460)', null, 1460);

