
-- Creació dels tablespaces per PORTAFIB
create tablespace portafib_tbs datafile 'portafib.dbf' size 200M autoextend on;
create temporary tablespace portafib_tbs_temp tempfile 'portafib_temp.dbf' size 20M autoextend on;

-- Creació de l'usuari PORTAFIB
create user PORTAFIB identified by PORTAFIB default tablespace portafib_tbs temporary tablespace portafib_tbs_temp;

-- Permisos per PORTAFIB
grant create session to portafib;
grant create table to portafib;
grant unlimited tablespace to portafib;

