

-- ================================================
-- 2019-10-24 No s'esborren les traduccions associades a Taules #376
-- ================================================

DELETE FROM pfi_traducciomap  WHERE traducciomapid in (
SELECT traduccioid FROM pfi_traduccio
WHERE
-- ENTITAT
traduccioid not in (SELECT motiudelegacioid FROM pfi_entitat WHERE motiudelegacioid is not null)
AND traduccioid not in (SELECT firmatperformatid FROM pfi_entitat WHERE firmatperformatid is not null)
-- PLUGIN
AND traduccioid not in (SELECT nomid FROM pfi_plugin WHERE nomid is not null)
AND traduccioid not in (SELECT descripciocurtaid FROM pfi_plugin WHERE descripciocurtaid is not null)
-- TIPUS DOCUMENT
AND traduccioid not in (SELECT nom FROM pfi_tipusdocument WHERE nom is not null)
-- Configuració USR-APP
AND traduccioid not in (SELECT motiudelegacioid FROM pfi_usuariaplicacioconfig WHERE motiudelegacioid is not null)
AND traduccioid not in (SELECT firmatperformatid FROM pfi_usuariaplicacioconfig WHERE firmatperformatid is not null));

DELETE FROM pfi_traduccio WHERE traduccioid in (
SELECT traduccioid FROM pfi_traduccio
WHERE
-- ENTITAT
traduccioid not in (SELECT motiudelegacioid FROM pfi_entitat WHERE motiudelegacioid is not null)
AND traduccioid not in (SELECT firmatperformatid FROM pfi_entitat WHERE firmatperformatid is not null)
-- PLUGIN
AND traduccioid not in (SELECT nomid FROM pfi_plugin WHERE nomid is not null)
AND traduccioid not in (SELECT descripciocurtaid FROM pfi_plugin WHERE descripciocurtaid is not null)
-- TIPUS DOCUMENT
AND traduccioid not in (SELECT nom FROM pfi_tipusdocument WHERE nom is not null)
-- Configuració USR-APP
AND traduccioid not in (SELECT motiudelegacioid FROM pfi_usuariaplicacioconfig WHERE motiudelegacioid is not null)
AND traduccioid not in (SELECT firmatperformatid FROM pfi_usuariaplicacioconfig WHERE firmatperformatid is not null));
  
  