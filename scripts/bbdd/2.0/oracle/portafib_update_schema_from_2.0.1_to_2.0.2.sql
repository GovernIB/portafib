--
-- Eliminar dependència de NotificacioWS respecte PeticioDeFirma #332
--
ALTER TABLE pfi_notificacio
  DROP CONSTRAINT pfi_notifica_petifirma_fk;

-- Afegir la columna amb l'id d'usuari aplicació pel funcionament de les notificacions #332

ALTER TABLE pfi_notificacio
  ADD usuariaplicacioid varchar2(101 char);

UPDATE pfi_notificacio SET usuariaplicacioid =
        (SELECT usuariaplicacioid FROM pfi_peticiodefirma
        WHERE peticiodefirmaid = pfi_notificacio.peticiodefirmaid);

ALTER TABLE pfi_notificacio
   MODIFY (usuariaplicacioid NOT NULL);


--
-- Afegir dins la taula de bitacola els camps necessaris per fer la Bitacola Genèrica #325
--
ALTER TABLE pfi_bitacola ADD objecteid varchar2(50 char);
ALTER TABLE pfi_bitacola ADD tipusobjecte number(10,0);
ALTER TABLE pfi_bitacola  ADD entitatid varchar2(50 char);
ALTER TABLE pfi_bitacola ADD usuariid varchar2(101 char);
ALTER TABLE pfi_bitacola  ADD tipusoperacio number(10,0);
ALTER TABLE pfi_bitacola ADD objecteserialitzat clob;


-- Totes les bitàcoles actuals corresponen a PeticioDeFirma (1), i l'ID de l'objecte és el de la petició.
UPDATE pfi_bitacola SET tipusobjecte = 1, objecteid = peticiodefirmaid;

-- Actualitzar entitatid amb l'entitat de l'usuariaplicació o l'usuarientitat.
UPDATE pfi_bitacola
        SET entitatid =
            (select entitatid from pfi_usuariaplicacio where usuariaplicacioid = pfi_bitacola.usuariaplicacioid)
        WHERE usuariaplicacioid is not null;
UPDATE pfi_bitacola
        SET entitatid =
            (select entitatid from pfi_usuarientitat where usuarientitatid = pfi_bitacola.usuarientitatid)
        WHERE usuarientitatid is not null;

-- Actualitzar login amb l'id d'usuari aplicació, o l'usuaripersonaid del usuarientitat.
UPDATE pfi_bitacola
        SET usuariid = usuariaplicacioid
        WHERE usuariaplicacioid is not null;
UPDATE pfi_bitacola
        SET  usuariid =
            (select usuaripersonaid from pfi_usuarientitat where usuarientitatid = pfi_bitacola.usuarientitatid)
        WHERE usuarientitatid is not null;

-- Intentar mapejar segons la descripció el tipus d'operació
UPDATE pfi_bitacola SET tipusoperacio = 1 WHERE descripcio = 'Petició creada';
UPDATE pfi_bitacola SET tipusoperacio = 2 WHERE descripcio = 'Petició actualitzada';
UPDATE pfi_bitacola SET tipusoperacio = 3 WHERE descripcio like 'Petició esborrada%';

UPDATE pfi_bitacola SET tipusoperacio = 11 WHERE descripcio = 'Petició iniciada';
UPDATE pfi_bitacola SET tipusoperacio = 12 WHERE descripcio like 'Petició rebutjada%';
UPDATE pfi_bitacola SET tipusoperacio = 13 WHERE descripcio = 'Petició finalitzada';

UPDATE pfi_bitacola SET tipusoperacio = 21 WHERE descripcio = 'Petició pausada';
UPDATE pfi_bitacola SET tipusoperacio = 22 WHERE descripcio = 'Petició firmada parcialment';
UPDATE pfi_bitacola SET tipusoperacio = 23 WHERE descripcio = 'Neteja fitxers originals';
UPDATE pfi_bitacola SET tipusoperacio = 24 WHERE descripcio = 'Neteja fitxers adaptats';

UPDATE pfi_bitacola SET tipusoperacio = 31 WHERE descripcio = 'Envidada Notificació [En procés]';
UPDATE pfi_bitacola SET tipusoperacio = 32 WHERE descripcio = 'Envidada Notificació [Pausada]';
UPDATE pfi_bitacola SET tipusoperacio = 33 WHERE descripcio = 'Envidada Notificació [Rebutjada]';
UPDATE pfi_bitacola SET tipusoperacio = 34 WHERE descripcio = 'Envidada Notificació [Firma parcial]';
UPDATE pfi_bitacola SET tipusoperacio = 35 WHERE descripcio = 'Envidada Notificació [Firmada]';
UPDATE pfi_bitacola SET tipusoperacio = 36 WHERE descripcio = 'Envidada Notificació [Invalidat]';

-- Afegir constraints not null
ALTER TABLE pfi_bitacola MODIFY (objecteid NOT NULL);
ALTER TABLE pfi_bitacola MODIFY (tipusobjecte NOT NULL);
ALTER TABLE pfi_bitacola MODIFY (entitatid NOT NULL);
ALTER TABLE pfi_bitacola MODIFY (usuariid NOT NULL);
ALTER TABLE pfi_bitacola MODIFY (tipusoperacio NOT NULL);

-- Esborrar columnes que ja no s'usen
ALTER TABLE pfi_bitacola DROP COLUMN peticiodefirmaid;
ALTER TABLE pfi_bitacola DROP COLUMN usuarientitatid;
ALTER TABLE pfi_bitacola DROP COLUMN usuariaplicacioid;

-- Descripció ja no és una columna obligatoria
ALTER TABLE pfi_bitacola MODIFY (descripcio NULL);
