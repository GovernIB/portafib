BEGIN;

--######################################################################
--#####  08/01/2025  Afegir Elements al Menú d'Administrador d'Aplicacions emprant Anotacions Java #933 
--######################################################################

DELETE FROM pfi_propietatglobal WHERE clau='es.caib.portafib.compactmenuoptionsofaden';

--######################################################################
--#####  16/01/2025  
--######################################################################

INSERT INTO pfi_codibarres(codibarresid, nom, descripcio) VALUES ('org.fundaciobit.pluginsib.barcode.qrcode.QrCodePlugin', 'QrCode', NULL);
INSERT INTO pfi_codibarres(codibarresid, nom, descripcio) VALUES ('org.fundaciobit.pluginsib.barcode.pdf417.Pdf417Plugin', 'Pdf417', NULL);
INSERT INTO pfi_codibarres(codibarresid, nom, descripcio) VALUES ('org.fundaciobit.pluginsib.barcode.barcode128.BarCode128Plugin', 'BarCode128', NULL);

UPDATE pfi_custodiainfo SET codibarresid='org.fundaciobit.pluginsib.barcode.qrcode.QrCodePlugin' WHERE codibarresid='org.fundaciobit.plugins.barcode.qrcode.QrCodePlugin';
UPDATE pfi_custodiainfo SET codibarresid='org.fundaciobit.pluginsib.barcode.pdf417.Pdf417Plugin' WHERE codibarresid='org.fundaciobit.plugins.barcode.pdf417.Pdf417Plugin';
UPDATE pfi_custodiainfo SET codibarresid='org.fundaciobit.pluginsib.barcode.barcode128.BarCode128Plugin' WHERE codibarresid='org.fundaciobit.plugins.barcode.barcode128.BarCode128Plugin';

DELETE FROM pfi_custodiainfo WHERE codibarresid='org.fundaciobit.plugins.barcode.qrcode.QrCodePlugin';
DELETE FROM pfi_custodiainfo WHERE codibarresid='org.fundaciobit.plugins.barcode.pdf417.Pdf417Plugin';
DELETE FROM pfi_custodiainfo WHERE codibarresid='org.fundaciobit.plugins.barcode.barcode128.BarCode128Plugin';


--######################################################################

COMMIT;