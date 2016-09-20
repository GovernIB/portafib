-- ========================================
-- 2016/09/20 Propietat d'Entitat
-- ========================================

INSERT INTO pfi_propietatglobal(entitatid, clau, valor, descripcio) SELECT entitatid, 'es.caib.portafib.autofirmaallowed', 'true', 'Opcional. Serveix per forçar la visibilitat de l´opció Gestió d´AutoFirmes del Menú d´Inici. Valors: * true: sempre mostra l´opció de menú. * false: mai mostra l´opció de menú. * null: consulta el role PFI_AUTOFIRMA' FROM pfi_entitat;

-- ========================================
-- 2016/08/12 Propietat Global     DAV
-- ========================================

INSERT INTO pfi_propietatglobal(clau, valor, descripcio) VALUES ('es.caib.portafib.notificationwhencreatedelegaciocolaboracio', NULL, 'Opcional. Valors posibles true o false.Indica si s’han d’enviar avisos via correu electrònic als delegats o col·laboradors quan són assignats per un destinatari. La mateixa propietat es pot aplicar en Propietst per Entitat');

INSERT INTO pfi_propietatglobal(clau, valor, descripcio) VALUES ('es.caib.portafib.avisosfirmespendents.cron', NULL, 'Indica la freqüència, emprant una expressió cron (pe 0 0 5 1/1 * ? *), en que s''enviaran correus a la gent que té peticions de firma pendents. Veure www.cronmaker.com. Relacionat amb propietat d''entitat es.caib.portafib.avisosfirmespendents.diesabans');


-- ========================================
-- 2016/08/12 Propietats d'Entitat   DAV
-- ========================================

INSERT INTO pfi_propietatglobal(entitatid, clau, valor, descripcio) SELECT entitatid, 'es.caib.portafib.notificationwhencreatedelegaciocolaboracio', NULL, 'Opcional.Valors posibles true o false.Indica si s’han d’enviar avisos via correu electrònic als delegats o col·laboradors quan són assignats per un destinatari.Si no es defineix s´usa el valor de la mateixa propietat definida en Propietats Globals' FROM pfi_entitat;
  
INSERT INTO pfi_propietatglobal(entitatid, clau, valor, descripcio) SELECT entitatid, 'es.caib.portafib.avisosfirmespendents.diesabans', NULL, 'Fa que s''enviïn correus als que tenen peticions de firma pendents. Indica el numero de dies abans de la caducitat de la petició en que s''han de començar a enviar correus. Relacionat amb la PropietatsGlobal es.caib.portafib.avisosfirmespendents.cron'  FROM pfi_entitat;







