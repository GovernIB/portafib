--------------------------------------------------------
-- Archivo creado  - lunes-octubre-14-2013   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table SC_WL_CERTIF
--------------------------------------------------------

   CREATE TABLE SC_WL_CERTIF
   (
    CER_CODUSU VARCHAR(30), 
	CER_EMISOR VARCHAR(1024), 
	CER_SERIAL VARCHAR(128)
   );
--------------------------------------------------------
--  DDL for Table SC_WL_LOGAUT
--------------------------------------------------------

   CREATE TABLE SC_WL_LOGAUT
   (
    LOG_DAT DATE NOT NULL, 
	LOG_TIPO CHAR(1) NOT NULL, 
	LOG_LOGIN VARCHAR(15) NOT NULL, 
	LOG_CODUSU VARCHAR(250), 
	LOG_RESUL CHAR NOT NULL,
	LOG_CERTIF VARCHAR(4000), 
	LOG_DATA DATE, 
	LOG_IP VARCHAR(240)
   );
  
   
--------------------------------------------------------
--  DDL for Table SC_WL_USUARI
--------------------------------------------------------

   CREATE TABLE SC_WL_USUARI
   (
    USU_CODI VARCHAR(512) NOT NULL, 
	USU_PASS VARCHAR(255), 
	USU_DATCAD DATE, 
	USU_NOM VARCHAR(200), 
	USU_NIF VARCHAR(15)
   );
--------------------------------------------------------
--  DDL for Table SC_WL_USUGRU
--------------------------------------------------------

   CREATE TABLE SC_WL_USUGRU
   (	
        UGR_CODUSU VARCHAR(512) NOT NULL, 
	    UGR_CODGRU VARCHAR(50) NOT NULL
   );
   

--------------------------------------------------------
--  DDL for Index USU_WL_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX USU_WL_USUARI_PK ON SC_WL_USUARI (USU_CODI);
--------------------------------------------------------
--  DDL for Index UGR_WL_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX UGR_WL_USUGRU_PK ON SC_WL_USUGRU (UGR_CODUSU, UGR_CODGRU);
--------------------------------------------------------
--  Constraints for Table SC_WL_USUGRU
--------------------------------------------------------

 
  ALTER TABLE SC_WL_USUGRU ADD CONSTRAINT UGR_WL_PK PRIMARY KEY (UGR_CODUSU, UGR_CODGRU);
--------------------------------------------------------
--  Constraints for Table SC_WL_USUARI
--------------------------------------------------------
 
  ALTER TABLE SC_WL_USUARI ADD CONSTRAINT USU_WL_PK PRIMARY KEY (USU_CODI);
