
--######################################################################
--#####  Revisar fitxers de traduccions i traduccions de genapp #398 
--######################################################################

-- Tipus Documental

INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (1, 'en', 'Resolution');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (2, 'en', 'Agreement');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (3, 'en', 'Contract');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (4, 'en', 'Agreement');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (5, 'en', 'Declaration');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (6, 'en', 'Communication');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (7, 'en', 'Notification');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (8, 'en', 'Publication');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (9, 'en', 'Reception receipt');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (10, 'en', 'Minutes');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (11, 'en', 'Certificate');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (12, 'en', 'Diligence');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (13, 'en', 'Report');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (14, 'en', 'Application');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (15, 'en', 'Denunciation');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (16, 'en', 'Allegation');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (17, 'en', 'Resource');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (18, 'en', 'Communication to the citizen');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (19, 'en', 'Invoice');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (20, 'en', 'Other confiscated');

INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (51, 'en', 'Law.');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (52, 'en', 'Motion');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (53, 'en', 'Instruction.');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (54, 'en', 'Call.');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (55, 'en', 'Order of the day.');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (56, 'en', 'Report of Speech.');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (57, 'en', 'Commission opinion.');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (58, 'en', 'Legislative initiative.');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (59, 'en', 'Question.');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (60, 'en', 'Interpelation.');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (61, 'en', 'Answer.');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (62, 'en', 'Proposition not of law.');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (63, 'en', 'Amendment.');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (64, 'en', 'Proposal for resolution.');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (65, 'en', 'Appearance.');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (66, 'en', 'Request for information.');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (67, 'en', 'Writing.');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (68, 'en', 'Legislative initiative.');
INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (69, 'en', 'Petition.');

INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) VALUES (99, 'en', 'Other types of documents');

INSERT INTO pfi_traducciomap(traducciomapid, idiomaid, valor) SELECT traducciomapid, 'en' , concat('NEED TRANS: 'valor)  FROM pfi_traducciomap where idiomaid = 'ca' AND traducciomapid in (select nom from pfi_tipusdocument where nom < 0  OR nom > 99);

-- Traduccions de Plugins

INSERT INTO pfi_traducciomap(traducciomapid, idiomaid,  valor)  SELECT traducciomapid, 'en' , concat('NEED TRANS: ',valor)  FROM pfi_traducciomap where idiomaid = 'ca' AND traducciomapid in (select nomid from pfi_plugin);

INSERT INTO pfi_traducciomap(traducciomapid, idiomaid,  valor)  SELECT traducciomapid, 'en' , concat('NEED TRANS: ',valor)  FROM pfi_traducciomap where idiomaid = 'ca' AND traducciomapid in (select descripciocurtaid from pfi_plugin);

-- Traduccions d'Entitats

INSERT INTO pfi_traducciomap(traducciomapid, idiomaid,  valor)  SELECT traducciomapid, 'en' , concat('NEED TRANS: ',valor)  FROM pfi_traducciomap where idiomaid = 'ca' AND traducciomapid in (select motiudelegacioid from pfi_entitat);

INSERT INTO pfi_traducciomap(traducciomapid, idiomaid,  valor)  SELECT traducciomapid, 'en' , concat('NEED TRANS: ',valor)  FROM pfi_traducciomap where idiomaid = 'ca' AND traducciomapid in (select firmatperformatid from pfi_entitat);


-- Traduccions de Configuració de Firma

INSERT INTO pfi_traducciomap(traducciomapid, idiomaid,  valor)  SELECT traducciomapid, 'en' , concat('NEED TRANS: ',valor)  FROM pfi_traducciomap where idiomaid = 'ca' AND traducciomapid in (select motiudelegacioid from pfi_usuariaplicacioconfig);

INSERT INTO pfi_traducciomap(traducciomapid, idiomaid,  valor)  SELECT traducciomapid, 'en' , concat('NEED TRANS: ',valor)  FROM pfi_traducciomap where idiomaid = 'ca' AND traducciomapid in (select firmatperformatid from pfi_usuariaplicacioconfig);
