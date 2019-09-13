WHENEVER SQLERROR EXIT ROLLBACK;

-- Iniciades
INSERT INTO pfi_estadistica(estadisticaid, tipus, data, entitatid, valor, parametres, usuariaplicacioid, usuarientitatid)
 select pfi_portafib_seq.nextval, 0, p.datasolicitud, u.entitatid, 1.0,
 'usuariEntitatID='||p.usuarientitatid||chr(10)||'entitatID='||u.entitatid||chr(10)||'tipusFirmaID='||p.tipusfirmaid||chr(10)||'peticioDeFirmaID='||p.peticiodefirmaid||chr(10)||'tipusDocumentID='||p.tipusdocumentid,
 p.usuariaplicacioid, p.usuarientitatid
 from pfi_peticiodefirma p, pfi_usuariaplicacio u
 where p.tipusestatpeticiodefirmaid != 0
 and p.usuariaplicacioid = u.usuariaplicacioid;

-- Finalitzades
INSERT INTO pfi_estadistica(estadisticaid, tipus, data, entitatid, valor, parametres, usuariaplicacioid, usuarientitatid)
select pfi_portafib_seq.nextval, 1, p.datafinal, u.entitatid, 1.0,
 'usuariEntitatID='||p.usuarientitatid||chr(10)||'entitatID='||u.entitatid||chr(10)||'tipusFirmaID='||p.tipusfirmaid||chr(10)||'peticioDeFirmaID='||p.peticiodefirmaid||chr(10)||'tipusDocumentID='||p.tipusdocumentid,
 p.usuariaplicacioid, p.usuarientitatid
 from pfi_peticiodefirma p, pfi_usuariaplicacio u
 where tipusestatpeticiodefirmaid = 4
 and p.usuariaplicacioid = u.usuariaplicacioid;

-- Rebutjades
INSERT INTO pfi_estadistica(estadisticaid, tipus, data, entitatid, valor, parametres, usuariaplicacioid, usuarientitatid)
select pfi_portafib_seq.nextval, 2, e.datafi, u.entitatid, 1.0,
 'firmaID='||e.firmaid||chr(10)||'datainici='||e.datainici,
 p.usuariaplicacioid, e.usuarientitatid
 from pfi_estatdefirma e, pfi_firma f, pfi_blocdefirmes b, pfi_peticiodefirma p, pfi_usuariaplicacio u
 where e.tipusestatdefirmafinalid = 3
 and e.firmaid = f.firmaid
 and f.blocdefirmaid = b.blocdefirmesid
 and b.fluxdefirmesid = p.fluxdefirmesid
 and p.usuariaplicacioid = u.usuariaplicacioid;
