
--######################################################################
--#####  Fer funcionals els Revisors de Destinatari #824  
--######################################################################

create sequence pfi_revisordedestinatari_seq start with 1000 increment by  1;
grant select on pfi_revisordedestinatari_seq to www_portafib;

create table pfi_revisordedestinatari (
       revisordedestinatariid number(19,0) not null,
        destinatariid varchar2(101 char) not null,
        revisorid varchar2(101 char) not null
);

alter table pfi_revisordedestinatari add constraint pfi_revisordedestinatari_pk primary key (revisordedestinatariid);

alter table pfi_revisordedestinatari 
       add constraint pfi_revdedest_usrentitat_de_fk 
       foreign key (destinatariid) 
       references pfi_usuarientitat;

alter table pfi_revisordedestinatari 
       add constraint pfi_revdedest_usrentitat_re_fk 
       foreign key (revisorid) 
       references pfi_usuarientitat;

alter table pfi_revisordedestinatari add constraint pfi_revdedest_dest_rev_uk unique (destinatariid, revisorid);

-- Pareix ser que Oracle genera automàticament l'index per les PK
--create index pfi_revisordedestinatari_pk_i on pfi_revisordedestinatari (revisordedestinatariid);
create index pfi_revdedest_destid_fk_i on pfi_revisordedestinatari (destinatariid);
create index pfi_revdedest_revisorid_fk_i on pfi_revisordedestinatari (revisorid);

grant select,insert,delete,update on pfi_revisordedestinatari to www_portafib;


