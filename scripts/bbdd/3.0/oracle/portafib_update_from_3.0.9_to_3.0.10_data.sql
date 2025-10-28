

--######################################################################
--##### 14/08/2025 Substituir el sistema actual CSP (Content Security Policy X-Frame-Options) #1075
--######################################################################

insert into pfi_propietatglobal (propietatglobalid, clau, descripcio, entitatid, valor)
   values(pfi_propietatglobal_seq.nextval, 'es.caib.portafib.bitacolacleanercronexpression', 'Opcional. Expressió cron que indica cada quan s´ha d´executar el netejador de Bitacoles antigues. Per defecte s´executa cada dia a les 5:00 (0 0 5 1/1 * ? *). Exemples: - L´executa cada dos minuts: 0 0/2 * 1/1 * ? *  - L´executa cada dia a les 6:00: 0 0 6 1/1 * ? Veure www.cronmaker.com per altres valors. Requereix una execució del cron o reiniciar el servidor per actualitzar el valor.', null, '0 0 5 1/1 * ? *');


insert into pfi_propietatglobal (propietatglobalid, clau, descripcio, entitatid, valor)
   values(pfi_propietatglobal_seq.nextval, 'es.caib.portafib.bitacolacleanerdaysold', 'Opcional. Valor a utilitzat per esborrar les Bitacoles amb més de X dies d´antiguitat.
 Per defecte val null el que indica que no es netejarà cap bitàcola', null, '');

