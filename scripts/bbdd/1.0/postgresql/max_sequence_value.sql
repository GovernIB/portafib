select max(mx)
from
(
select max(algorismedefirmaid) as mx from pfi_algorismedefirma
union
select max(annexfirmatid) as mx from pfi_annexfirmat
union
select max(annexid) as mx from pfi_annex
union
select max(bitacolaid) as mx from pfi_bitacola
union
select max(blocdefirmesid) as mx from pfi_blocdefirmes
union
select max(colaboraciodelegacioid) as mx from pfi_colaboraciodelegacio
union
select max(custodiainfoid) as mx from pfi_custodiainfo
union
select max(estatdefirmaid) as mx from pfi_estatdefirma
union
select max(firmaid) as mx from pfi_firma
union
select max(fitxerid) as mx from pfi_fitxer
union
select max(fluxdefirmesid) as mx from pfi_fluxdefirmes
union
select max(grupentitatid) as mx from pfi_grupentitat
union
select max(grupentitatusuarientitatid) as mx from pfi_grupentitatusuarientitat
union
select max(metadadaid) as mx from pfi_metadada
union
select max(permisgrupplantillaid) as mx from pfi_permisgrupplantilla
union
select max(permisusuariplantillaid) as mx from pfi_permisusuariplantilla
union
select max(peticiodefirmaid) as mx from pfi_peticiodefirma
union
select max(posiciopaginaid) as mx from pfi_posiciopagina
union
select max(posiciotaulafirmesid) as mx from pfi_posiciotaulafirmes
union
select max(prioritatid) as mx from pfi_prioritat
union
select max(tipusdocumentid) as mx from pfi_tipusdocument
union
select max(tipusestatdefirmafinalid) as mx from pfi_tipusestatdefirmafinal
union
select max(tipusestatdefirmainicialid) as mx from pfi_tipusestatdefirmainicial
union
select max(tipusestatpeticiodefirmaid) as mx from pfi_tipusestatpeticiodefirma
union
select max(tipusfirmaid) as mx from pfi_tipusfirma
union
select max(tipusmetadadaid) as mx from pfi_tipusmetadada
union
select max(tipusnotificacioid) as mx from pfi_tipusnotificacio
union
select max(traduccioid) as mx from pfi_traduccio
) as maxall