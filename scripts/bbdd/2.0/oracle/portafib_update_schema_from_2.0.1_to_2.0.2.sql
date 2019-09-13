
-- Eliminar dependència de NotificacioWS respecte PeticioDeFirma #332

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