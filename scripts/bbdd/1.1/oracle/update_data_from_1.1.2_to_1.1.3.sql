﻿
-- ========================================
-- 2017/06/16 Propietat d'Entitat per ignorar Check de Post Firma
-- ========================================

INSERT INTO pfi_propietatglobal(propietatglobalid, entitatid, clau, valor, descripcio) SELECT pfi_portafib_seq.nextval,entitatid, 'es.caib.portafib.ignorecheckpostsign', 'false', 'Opcional. Serveix per indicar a PortaFIB que revisi(false o no definit) o no revisi(true) la manipulació del PDF firmat' FROM pfi_entitat;