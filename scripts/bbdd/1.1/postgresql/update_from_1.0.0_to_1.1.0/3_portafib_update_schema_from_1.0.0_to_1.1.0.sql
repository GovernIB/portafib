
-- ========================================
-- 2015/12/01 Plugin de Custòdia Documental
-- ========================================

ALTER TABLE pfi_custodiainfo ADD COLUMN pluginid bigint NOT NULL DEFAULT 6;
create index pfi_custodiainfo_pluginid_fk_i on pfi_custodiainfo (pluginid);
ALTER TABLE pfi_custodiainfo ADD CONSTRAINT pfi_custodia_plugin_fk FOREIGN KEY (pluginid) REFERENCES pfi_plugin (pluginid);
ALTER TABLE pfi_custodiainfo DROP COLUMN custodiapluginclassid;
