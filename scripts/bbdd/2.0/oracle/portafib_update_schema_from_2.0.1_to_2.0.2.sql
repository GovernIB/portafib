
-- Eliminar dependència de NotificacioWS respecte PeticioDeFirma #332

ALTER TABLE pfi_notificacio
  DROP CONSTRAINT pfi_notifica_petifirma_fk;
  