
--######################################################################
--#####  Fer funcionals els Revisors de Destinatari #824  
--######################################################################

CREATE SEQUENCE pfi_revisordedestinatari_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1000
  CACHE 1;

CREATE TABLE pfi_revisordedestinatari
(
   revisordedestinatariid bigint NOT NULL DEFAULT nextval('pfi_revisordedestinatari_seq'), 
   destinatariid character varying(101) NOT NULL, 
   revisorid character varying(101) NOT NULL, 
   CONSTRAINT pfi_revisordedestinatari_pk PRIMARY KEY (revisordedestinatariid), 
   CONSTRAINT pfi_revdedest_usrentitat_de_fk FOREIGN KEY (destinatariid) REFERENCES pfi_usuarientitat (usuarientitatid) ON UPDATE NO ACTION ON DELETE NO ACTION, 
   CONSTRAINT pfi_revdedest_usrentitat_re_fk FOREIGN KEY (revisorid) REFERENCES pfi_usuarientitat (usuarientitatid) ON UPDATE NO ACTION ON DELETE NO ACTION, 
   CONSTRAINT pfi_revdedest_dest_rev_uk UNIQUE (destinatariid, revisorid)
);

create index pfi_revisordedestinatari_pk_i on pfi_revisordedestinatari (revisordedestinatariid);
create index pfi_revdedest_destid_fk_i on pfi_revisordedestinatari (destinatariid);
create index pfi_revdedest_revisorid_fk_i on pfi_revisordedestinatari (revisorid);

