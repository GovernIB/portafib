INSERT INTO pfi_estadistica(estadisticaid, tipus, data, entitatid, valor, parametres, usuariaplicacioid) 
 select nextval('pfi_portafib_seq'), 0, datasolicitud, 'fundaciobit', 1.0, 
 CONCAT('usuariEntitatID=', usuarientitatid, chr(10), 'entitatID=', 'fundaciobit', chr(10), 'tipusFirmaID=', tipusfirmaid, chr(10), 'peticioDeFirmaID=',peticiodefirmaid, chr(10),'tipusDocumentID=',tipusdocumentid),
 usuariaplicacioid
 from pfi_peticiodefirma
 where tipusestatpeticiodefirmaid != 0;

INSERT INTO pfi_estadistica(estadisticaid, tipus, data, entitatid, valor, parametres, usuariaplicacioid) 
 select nextval('pfi_portafib_seq'), 1, datafinal, 'fundaciobit', 1.0, 
 CONCAT('usuariEntitatID=', usuarientitatid, chr(10), 'entitatID=', 'fundaciobit', chr(10), 'tipusFirmaID=', tipusfirmaid, chr(10), 'peticioDeFirmaID=',peticiodefirmaid, chr(10),'tipusDocumentID=',tipusdocumentid),
 usuariaplicacioid
 from pfi_peticiodefirma
 where tipusestatpeticiodefirmaid = 4;