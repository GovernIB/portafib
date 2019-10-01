--
-- Eliminar dependència de NotificacioWS respecte PeticioDeFirma #332
--
ALTER TABLE pfi_notificacio
  DROP CONSTRAINT pfi_notifica_petifirma_fk;

-- Afegir la columna amb l'id d'usuari aplicació pel funcionament de les notificacions #332

ALTER TABLE pfi_notificacio
  ADD COLUMN usuariaplicacioid character varying(101);

UPDATE pfi_notificacio SET usuariaplicacioid =
        (SELECT usuariaplicacioid FROM pfi_peticiodefirma
        WHERE peticiodefirmaid = pfi_notificacio.peticiodefirmaid);

ALTER TABLE pfi_notificacio
   ALTER COLUMN usuariaplicacioid SET NOT NULL;


--
-- Afegir dins la taula de bitacola els camps necessaris per fer la Bitacola Genèrica #325
--
ALTER TABLE pfi_bitacola ADD COLUMN objecteid character varying(50);
ALTER TABLE pfi_bitacola ADD COLUMN tipusobjecte integer;
ALTER TABLE pfi_bitacola  ADD COLUMN entitatid character varying(50);
ALTER TABLE pfi_bitacola ADD COLUMN usuariid character varying(101);
ALTER TABLE pfi_bitacola  ADD COLUMN tipusoperacio integer;
ALTER TABLE pfi_bitacola ADD COLUMN objecteserialitzat text;


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
ALTER TABLE pfi_bitacola ALTER COLUMN objecteid SET NOT NULL;
ALTER TABLE pfi_bitacola ALTER COLUMN tipusobjecte SET NOT NULL;
ALTER TABLE pfi_bitacola ALTER COLUMN entitatid SET NOT NULL;
ALTER TABLE pfi_bitacola ALTER COLUMN usuariid SET NOT NULL;
ALTER TABLE pfi_bitacola ALTER COLUMN tipusoperacio SET NOT NULL;

-- Esborrar columnes que ja no s'usen
ALTER TABLE pfi_bitacola DROP COLUMN peticiodefirmaid;
ALTER TABLE pfi_bitacola DROP COLUMN usuarientitatid;
ALTER TABLE pfi_bitacola DROP COLUMN usuariaplicacioid;

-- Descripció ja no és una columna obligatoria
ALTER TABLE pfi_bitacola ALTER COLUMN descripcio DROP NOT NULL;

--
--  Fer que els usuaris aplicació s'autentiquin a traves de JBoss i no emprant contrasenya de BBDD #277
--

-- ALERTA! ALETRA! ALERTA!
--
-- En entorns "NO CAIB", que emprin l'autenticació d'usuaris aplicació mitjançant PortaFIB en primer lloc
-- caldrà exportar les dades de contrasenyes i roles al sistema d'autenticació emprant les següents selects.

-- Exporta usuaris i contrasenyes
-- SELECT usuariaplicacioid, contrasenya FROM pfi_usuariaplicacio;
-- Exporta roles d'usuari
-- SELECT usuariaplicacioid, roleid FROM pfi_roleusuariaplicacio;

-- Si s'empren les taules auxiliars de SEYCON per mantenir els noms d'usuaris les comandes per insertar serien:
-- INSERT INTO sc_wl_usuari SET usu_codi = <id usuariaplicacio>, usu_pass = <contrasenya>
-- INSERT INTO sc_wl_usugru SET ugr_codusu = <id usuariaplicacio>, ugr_codgru = <id role>

ALTER TABLE pfi_usuariaplicacio
  DROP COLUMN contrasenya;

DROP TABLE pfi_roleusuariaplicacio;
