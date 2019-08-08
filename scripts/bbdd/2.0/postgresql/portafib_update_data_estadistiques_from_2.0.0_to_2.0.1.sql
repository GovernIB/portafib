
-- Peticions firmades
INSERT INTO pfi_estadistica(estadisticaid, tipus, data, entitatid, valor, parametres, usuariaplicacioid, usuarientitatid)
select nextval('pfi_portafib_seq'), 3, p.datafinal, 'fundaciobit', max(f.numfirmadocument),
 CONCAT('usuariEntitatID=', p.usuarientitatid, chr(10), 'entitatID=', 'fundaciobit', chr(10), 'tipusFirmaID=', p.tipusfirmaid, chr(10), 'peticioDeFirmaID=', p.peticiodefirmaid, chr(10),'tipusDocumentID=', p.tipusdocumentid),
 p.usuariaplicacioid, p.usuarientitatid
 from pfi_peticiodefirma p, pfi_blocdefirmes b, pfi_firma f
 where p.fluxdefirmesid = b.fluxdefirmesid
   and b.blocdefirmesid = f.blocdefirmaid
   and p.tipusestatpeticiodefirmaid = 4
 group by p.peticiodefirmaid;
 
