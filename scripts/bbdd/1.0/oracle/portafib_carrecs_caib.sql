

--   ============= IMPORTANT =============
--   S'ha de moficicar la IP i el port de les funcions  utl_http.request
--   que es criden més abaix per apuntar al servidor de PortaFIB

CREATE TABLE pfi_carrecscaib (
  codusu varchar2(100),
  nomrol varchar2(100) not null,
  valordomini varchar2(100) not null,
  agentsql varchar2(100) not null,
  nom varchar2(200)
);



--  ============= ERROR PLS-00201 =============
-- Si oracle llança un error "PLS-00201: identifier 'UTL_HTTP' must be declared"
-- o "PLS-00201: el identificador 'UTL_HTTP' se debe declarar" llavors s'ha 
-- d'executar el següent:
--   sqlplus "/ as sysdba"
--   SQl> GRANT EXECUTE ON SYS.UTL_HTTP TO portafib;
--   
-- on portafib és l'usuari que executarà aquest sql.



create or replace
TRIGGER INSERT_CARREC
AFTER INSERT ON pfi_carrecscaib FOR EACH ROW
DECLARE
 val VARCHAR2(32000);
 nomansi VARCHAR2(255);
BEGIN
  nomansi := CONVERT(:new.nom, 'US7ASCII', 'WE8ISO8859P1');
  nomansi :=  REPLACE(nomansi, ' ', '_');
  val := utl_http.request('http://10.215.216.175:8080/portafib/carrecscaib?tipus=insert' || chr(38) || 'codusu=' || :new.codusu || chr(38) || 'nomrol=' || :new.nomrol || chr(38) || 'valordomini=' || :new.valordomini || chr(38) || 'agentsql=' || :new.agentsql  || chr(38) || 'nom=' || nomansi);
  dbms_output.put_line(val);
END;

create or replace
TRIGGER UPDATE_CARREC
AFTER UPDATE ON pfi_carrecscaib FOR EACH ROW
DECLARE
 val VARCHAR2(32000);
 nomansi VARCHAR2(255);
BEGIN
  if :new.codusu IS NULL THEN
    val := utl_http.request('http://10.215.216.175:8080/portafib/carrecscaib?tipus=disable' || chr(38) || 'nomrol=' ||  :new.nomrol || chr(38) || 'valordomini='  || :new.valordomini || chr(38) || 'agentsql=' || :new.agentsql);
  else
    nomansi := CONVERT(:new.nom, 'US7ASCII', 'WE8ISO8859P1');
	nomansi :=  REPLACE(nomansi, ' ', '_');
    val := utl_http.request('http://10.215.216.175:8080/portafib/carrecscaib?tipus=update' || chr(38) || 'codusu=' || :new.codusu || chr(38) || 'nomrol=' ||  :new.nomrol || chr(38) || 'valordomini=' || :new.valordomini || chr(38) || 'agentsql=' || :new.agentsql || chr(38) || 'nom=' || nomansi);
  end if;
  dbms_output.put_line(val);
END;

create or replace 
TRIGGER DELETE_CARREC
AFTER DELETE ON pfi_carrecscaib FOR EACH ROW
DECLARE
 val VARCHAR2(32000);
BEGIN
  val := utl_http.request('http://10.215.216.175:8080/portafib/carrecscaib?tipus=delete' || chr(38) || 'nomrol=' ||  :old.nomrol || chr(38) || 'valordomini=' ||  :old.valordomini || chr(38) || 'agentsql=' ||  :old.agentsql);
  dbms_output.put_line(val);
END;
