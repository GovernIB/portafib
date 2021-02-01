

-- ==============================================================
-- 2021-02-01 Revisar obligatorietat del camp "Callback URL" #520
-- ==============================================================

ALTER TABLE PFI_USUARIAPLICACIO ALTER COLUMN CALLBACKURL DROP NOT NULL;


