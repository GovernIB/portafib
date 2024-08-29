

--   ============= IMPORTANT =============
--   S'ha de moficicar la IP i el port de les funcions utl_http.request (10.215.216.175:8080)
--   que es criden més abaix per apuntar al servidor de PortaFIB, així com el 
--   valor del paràmetre password (THEPASSWORD) que donarà un poc se seguretat
--   al sistema (aquest password està relacionat amb la propietat 
--   "es.caib.portafib.passwordforagentssql" definida la servidor de PortaFIB).

CREATE TABLE pfi_usuariscaib (
  codusu varchar2(100),
  agentsql varchar2(100) not null
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
TRIGGER INSERT_USUARI
AFTER INSERT ON pfi_usuariscaib FOR EACH ROW
DECLARE
 val VARCHAR2(32000);
BEGIN
  val := utl_http.request('http://10.215.216.175:8080/portafibback/usuariscaib?tipus=insert' || chr(38) || 'password=THEPASSWORD' || chr(38) || 'codusu=' || :new.codusu || chr(38) || 'nomrol=' || chr(38) || 'agentsql=' || :new.agentsql);
  dbms_output.put_line(val);
END;



create or replace 
TRIGGER DELETE_USUARI
AFTER DELETE ON pfi_usuariscaib FOR EACH ROW
DECLARE
 val VARCHAR2(32000);
BEGIN
  val := utl_http.request('http://10.215.216.175:8080/portafibback/usuariscaib?tipus=delete' || chr(38) || 'password=THEPASSWORD' || chr(38) || 'codusu=' || :old.codusu || chr(38) || 'agentsql=' ||  :old.agentsql);
  dbms_output.put_line(val);
END;
