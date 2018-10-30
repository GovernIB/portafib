
-- Iniciades
INSERT INTO pfi_estadistica(estadisticaid, tipus, data, entitatid, valor, parametres, usuariaplicacioid, usuarientitatid) 
 select nextval('pfi_portafib_seq'), 0, datasolicitud, 'fundaciobit', 1.0, 
 CONCAT('usuariEntitatID=', usuarientitatid, chr(10), 'entitatID=', 'fundaciobit', chr(10), 'tipusFirmaID=', tipusfirmaid, chr(10), 'peticioDeFirmaID=',peticiodefirmaid, chr(10),'tipusDocumentID=',tipusdocumentid),
 usuariaplicacioid, usuarientitatid
 from pfi_peticiodefirma
 where tipusestatpeticiodefirmaid != 0;

-- Finalitzades
INSERT INTO pfi_estadistica(estadisticaid, tipus, data, entitatid, valor, parametres, usuariaplicacioid, usuarientitatid) 
 select nextval('pfi_portafib_seq'), 1, datafinal, 'fundaciobit', 1.0, 
 CONCAT('usuariEntitatID=', usuarientitatid, chr(10), 'entitatID=', 'fundaciobit', chr(10), 'tipusFirmaID=', tipusfirmaid, chr(10), 'peticioDeFirmaID=',peticiodefirmaid, chr(10),'tipusDocumentID=',tipusdocumentid),
 usuariaplicacioid, usuarientitatid
 from pfi_peticiodefirma
 where tipusestatpeticiodefirmaid = 4;
 
-- Rebutjades
INSERT INTO pfi_estadistica(estadisticaid, tipus, data, entitatid, valor, parametres, usuariaplicacioid, usuarientitatid) 
 select nextval('pfi_portafib_seq'), 2, datafi, 'fundaciobit', 1.0, 
 CONCAT('firmaID=', firmaid, chr(10), 'datainici=', datainici),
 'unknown', usuarientitatid
 FROM pfi_estatdefirma
 where tipusestatdefirmafinalid = 3; --TIPUSESTATDEFIRMAFINAL_REBUTJAT;
 
