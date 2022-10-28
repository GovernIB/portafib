INSERT INTO pfi_propietatglobal (propietatglobalid, clau, descripcio, entitatid, valor)
SELECT NEXTVAL('pfi_propietatglobal_seq') AS propietaglobalid ,'es.caib.portafib.descripciotipusvisible' AS clau, 
        'Valor booleà per indiciar si es vol mostrar el camp descripció del tipus de document sempre, o nomes quan siguie "altres"' AS descripcio,
        entitatid, 'false' AS valor
        FORM pfi_entitat;