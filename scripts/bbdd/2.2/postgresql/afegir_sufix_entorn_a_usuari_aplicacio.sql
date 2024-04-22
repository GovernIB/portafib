BEGIN;

INSERT INTO pfi_usuariaplicacio(usuariaplicacioid,
            entitatid, emailadmin, callbackurl, descripcio, callbackversio, 
            actiu, idiomaid,  logosegellid, politicadepluginfirmaweb, 
            politicacustodia, custodiainfoid, crearusuaris)
  SELECT concat(usuariaplicacioid,'_dev'),entitatid, emailadmin, callbackurl, descripcio, callbackversio, 
       actiu, idiomaid, logosegellid, politicadepluginfirmaweb, 
       politicacustodia, custodiainfoid, crearusuaris
  FROM pfi_usuariaplicacio; -- TEST WHERE usuariaplicacioid='uib_app'; 


-- EXP  Field:usuariaplicacioid | Table: pfi_entitat | Type: 0 
UPDATE pfi_entitat SET usuariaplicacioid=concat(usuariaplicacioid,'_dev') where usuariaplicacioid is not null; -- TEST AND usuariaplicacioid = 'uib_app'; 

-- EXP  Field:usuariaplicacioid | Table: pfi_custodiainfo | Type: 0 
UPDATE pfi_custodiainfo SET usuariaplicacioid=concat(usuariaplicacioid,'_dev') where usuariaplicacioid is not null; -- TEST AND usuariaplicacioid = 'uib_app'; 

-- EXP  Field:usuariaplicacioid | Table: pfi_perfilsperusrapp | Type: 0  
UPDATE pfi_perfilsperusrapp SET usuariaplicacioid=concat(usuariaplicacioid,'_dev') where usuariaplicacioid is not null; -- TEST AND usuariaplicacioid = 'uib_app'; 

-- EXP  Field:usuariaplicacioid | Table: pfi_peticiodefirma | Type: 0 
UPDATE pfi_peticiodefirma SET usuariaplicacioid=concat(usuariaplicacioid,'_dev') where usuariaplicacioid is not null; -- TEST AND usuariaplicacioid = 'uib_app'; 

-- EXP  Field:usuariaplicacioid | Table: pfi_plantillafluxdefirmes | Type: 0  
UPDATE pfi_plantillafluxdefirmes SET usuariaplicacioid=concat(usuariaplicacioid,'_dev') where usuariaplicacioid is not null; -- TEST AND usuariaplicacioid = 'uib_app'; 

-- EXP  Field:usuariaplicacioid | Table: pfi_pluginfirmawebperusrapp | Type: 0 
UPDATE pfi_pluginfirmawebperusrapp SET usuariaplicacioid=concat(usuariaplicacioid,'_dev') where usuariaplicacioid is not null; -- TEST AND usuariaplicacioid = 'uib_app'; 

-- EXP  Field:usuariaplicacioid | Table: pfi_tipusdocument | Type: 0  
UPDATE pfi_tipusdocument SET usuariaplicacioid=concat(usuariaplicacioid,'_dev') where usuariaplicacioid is not null; -- TEST AND usuariaplicacioid = 'uib_app'; 

-- FALTA ESBORRAR ELS USUARIS APLICACIÓ ORIGINALS

COMMIT;



