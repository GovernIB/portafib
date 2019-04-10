WHENEVER SQLERROR EXIT ROLLBACK;

-- Iniciades
INSERT INTO pfi_estadistica(estadisticaid, tipus, data, entitatid, valor, parametres, usuariaplicacioid, usuarientitatid) 
 select pfi_portafib_seq.nextval, 0, datasolicitud, 'caib', 1.0, 
 'usuariEntitatID='||usuarientitatid||chr(10)||'entitatID='||'caib'||chr(10)||'tipusFirmaID='||tipusfirmaid||chr(10)||'peticioDeFirmaID='||peticiodefirmaid||chr(10)||'tipusDocumentID='||tipusdocumentid,
 usuariaplicacioid, usuarientitatid
 from pfi_peticiodefirma
 where tipusestatpeticiodefirmaid != 0;

-- Finalitzades
INSERT INTO pfi_estadistica(estadisticaid, tipus, data, entitatid, valor, parametres, usuariaplicacioid, usuarientitatid) 
 select pfi_portafib_seq.nextval, 1, datafinal, 'caib', 1.0, 
 'usuariEntitatID='||usuarientitatid||chr(10)||'entitatID='||'caib'||chr(10)||'tipusFirmaID='||tipusfirmaid||chr(10)||'peticioDeFirmaID='||peticiodefirmaid||chr(10)||'tipusDocumentID='||tipusdocumentid,
 usuariaplicacioid, usuarientitatid
 from pfi_peticiodefirma
 where tipusestatpeticiodefirmaid = 4;
 
-- Rebutjades
INSERT INTO pfi_estadistica(estadisticaid, tipus, data, entitatid, valor, parametres, usuariaplicacioid, usuarientitatid) 
 select pfi_portafib_seq.nextval, 2, datafi, 'caib', 1.0, 
 'firmaID='||firmaid||chr(10)||'datainici='||datainici,
 'unknown', usuarientitatid
 FROM pfi_estatdefirma
 where tipusestatdefirmafinalid = 3; --TIPUSESTATDEFIRMAFINAL_REBUTJAT;
