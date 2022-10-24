INSERT INTO pfi_propietatglobal (propietatglobalid, clau, descripcio, entitatid, valor)
SELECT NEXTVAL('pfi_propietatglobal_seq') AS propietaglobalid ,'es.caib.portafib.descripcioTipusVisible' AS clau, 
        'Valor per indiciar si es vol mostrar el camp descripcio del tipus' AS descripcio,
        entitatid, 'false' AS valor
        FORM pfi_entitat;