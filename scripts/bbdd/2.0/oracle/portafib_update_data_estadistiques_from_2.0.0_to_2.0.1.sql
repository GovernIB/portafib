-- Peticions firmades
INSERT INTO pfi_estadistica(estadisticaid, tipus, data, entitatid, valor, parametres, usuariaplicacioid, usuarientitatid)
select pfi_portafib_seq.nextval, 3, datafinal, entitatid, valor, parametres, usuariaplicacioid, usuarientitatid from
(select p.datafinal, u.entitatid, p.usuarientitatid, p.usuariaplicacioid, max(f.numfirmadocument) as valor,
 'usuariEntitatID='||p.usuarientitatid||chr(10)||'entitatID='||u.entitatid||chr(10)||'tipusFirmaID='||p.tipusfirmaid||chr(10)||'peticioDeFirmaID='||p.peticiodefirmaid||chr(10)||'tipusDocumentID='||p.tipusdocumentid as parametres
 from pfi_peticiodefirma p, pfi_blocdefirmes b, pfi_firma f, pfi_usuariaplicacio u
 where p.fluxdefirmesid = b.fluxdefirmesid
   and b.blocdefirmesid = f.blocdefirmaid
   and p.tipusestatpeticiodefirmaid = 4
   and p.usuariaplicacioid = u.usuariaplicacioid
group by p.peticiodefirmaid, p.datafinal, p.usuarientitatid, p.tipusfirmaid, p.tipusdocumentid, p.usuariaplicacioid, u.entitatid);


