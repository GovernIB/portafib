package es.caib.portafib.logic.utils;

import java.util.HashMap;
import java.util.Map;

import es.caib.portafib.commons.utils.Configuracio;
import es.caib.portafib.commons.utils.Propietat;

/**
 * 
 * @author anadal(u80067)
 *
 */
public class PropietatsConstants {

    public static final int TIPUS_PROPIETAT_BBDD_GLOBAL = 0;

    public static final int TIPUS_PROPIETAT_BBDD_PER_ENTITAT = 1;

    public static final int TIPUS_PROPIETAT_FITXER_PORTAFIB_PROPERTIES = 2;

    public static final int TIPUS_PROPIETAT_FITXER_PORTAFIB_SYSTEM_PROPERTIES = 3;
    
    public static final int TIPUS_PROPIETAT_JAVA = 4;

    public static final Map<String, Propietat> PROPIETATS_BBDD_PER_ENTITAT = new HashMap<String, Propietat>();

    public static final Map<String, Propietat> PROPIETATS_BBDD_GLOBALS = new HashMap<String, Propietat>();

    public static final Map<String, Propietat> PROPIETATS_FITXER_PORTAFIB_PROPERTIES = Configuracio.PROPIETATS_FITXER_PORTAFIB_PROPERTIES;

    public static final Map<String, Propietat> PROPIETATS_FITXER_SYSTEM_PORTAFIB_PROPERTIES = Configuracio.PROPIETATS_FITXER_SYSTEM_PORTAFIB_PROPERTIES;

    static {

        final Propietat[] propietats_bbdd_per_entitat = {

                new Propietat(true, "es.caib.portafib.maxpeticiotitlelength",
                        " Opcional. Valor per defecte 0. Indica la longitud màxima del titol de una peticio de firma si el valor està fixat."),
                new Propietat(true, "es.caib.portafib.maxitemstoshowinautocomplete",
                        " Opcional. Valor per defecte 10. En els formularis de cerques dinàmiques d'usuari,           indica el màxim de resultats permesos per mostrar resultats de l'usuari."),
                new Propietat(true, "es.caib.portafib.mincharstostartautocomplete",
                        " Opcional. Valor per defecte 2. En els formularis de cerques dinàmiques d'usuari,  indica el mínim de caràcters que ha d'escriure l'usuari abans de que li apareguin els resultats de la cerca. En entitats amb molts d'usuaris es recomana incrementar aquest valora a 3 o 4 amb la finalitat de reduir càrrega de xarxa, servidor i bbdd."),
                new Propietat(true, "es.caib.portafib.maxtimelockedsigninms",
                        " Opcional. Indica Temps de validesa del Token de Firma només quan hi ha múltiples firmes en un bloc o hi ha delegats definits. Es a dir, el temps màxim que un firmant pot tenir bloquejat un document mentre es realitza el procés de firma. Valor per defecte 3*60*1000 (180000), o sigui 3 minuts. Quan la firma es única en el bloc i no hi ha delegats definits llavors no hi ha bloqueig de temps. Nou a la versió 2.0.0  Es recomana posar uns 10 minuts (600000ms)"),
                new Propietat(true, "es.caib.portafib.notificationwhencreatedelegaciocolaboracio",
                        " Nou a la versió 1.1.1 .Opcional. Valor per defecte false. Indica si s’han d’enviar avisos via correu electrònic als delegats o col·laboradors quan són assignats per un destinatari. Existeix la mateixa propietat global que és usada com a valor per defecte (Veure punt  )"),
                new Propietat(true, "es.caib.portafib.descripciotipusvisible",
                        "Valor booleà per indiciar si es vol mostrar el camp descripció del tipus de document sempre, o nomes quan sigui 'altres'"),
                new Propietat(true, "es.caib.portafib.avisosfirmespendents.diesabans",
                        " Nou a la versió 1.1.1 .Opcional. Fa que s'enviïn correus als que tenen peticions de firma pendents. Indica el número de dies abans de la caducitat de la petició en que s'han de començar a enviar correus. Relacionat amb la PropietatsGlobal es.caib.portafib.avisosfirmespendents.cron (Veure punt  )"),
                new Propietat(true, "es.caib.portafib.autofirmaallowed",
                        " Nou a la versió 1.1.1 Opcional. Serveix per forçar la visibilitat de l'opció del Menú d'Inici. Aquí s'enumeren els diferents comportaments segons el valor: * true: sempre mostra l'opció de menú. * false: mai mostra l'opció  de menú. * null o no definit: consulta el role real PFI_AUTOFIRMA i es mostra si l'usuari té aquest rol i l'oculta si l'usuari no té aquest rol."),
                new Propietat(false, "es.caib.portafib.ignorecheckpostsign",
                        " Eliminat a la versió 2.0.0  Nou a la versió 1.1.3 Opcional. Serveix per indicar a PortaFIB que revisi o no revisisi la manipulació del PDF firmat. En resum: * true: no revisa si la part original del PDF s'ha modificat * false, null o no definit: revisa si la part original del PDF s'ha modificat"),
                new Propietat(false, "es.caib.portafib.transformpdfa",
                        " Eliminat a la versió 2.0.1 Nou a la versió 2.0.0  Opcional. Amb firmes PAdES, si aquest valor es true i el tipus de PDF és PDF/A1 o PDF/A2 o PDF/A3, llavors es transforma el PDF per a que pugui ser adaptat (taula de firmes, ....), però perd la condició de PDF/A"),
                new Propietat(false, "es.caib.portafib.forcecleanpdf",
                        " Eliminat a la versió 2.0.1 Nou a la versió 2.0.0  Opcional. Amb firmes PAdES, si aquesta propietat val true llavors es fa neteja del PDF per a que no tengui problemes amb el plugins de firma. Això implica que algunes característiques del PDF original es perdin."),
                new Propietat(false, "es.caib.portafib.alwayscreaterevision",
                        "Eliminat a la versió 2.0.1 Nou a la versió 2.0.0.1/2.0.1 Opcional. Amb fimes PAdES, si aquesta propietat val true (per defecte i recomanat), la firma sempre és genera mitjançant una revisió. Això permet validar el contingut a baix nivell del fitxer original amb el del fitxer signat. Però la creació de reivisions pot provocar que en determinats fitxers anteriors a la versió PDF 1.7 es generin signatures  no vàlides amb l'error 'urn:afirma:dss:1.0:profile:XSS:resultminor:PadESInvalidContentsKey' Fixant aquest propietat a `false` permet generar una firma vàlida per aquests fitxers. Però caldrà desactivar l'opció. Comprovar que no s'hagi modificat durant la firma de l'entitat."),
                new Propietat(true, "es.caib.portafib.acceptTransformPDFA",
                        " Nou a la versió 2.0.1 Opcional. Per defecte false. En firmes PAdES, si el tipus de PDF és PDF/A1 o PDF/A2 o PDF/A3 i si a més es requereix Estampar o Afegir Taula de Firmes o Annexar Documents, llavors això implica una transformació del PDF que a la vegada implica una pèrdua de la condició de PDF/A. Si val true s'accepta transformar el PDF/A i perdre a  la condició de PDF/A. Si val false es llança una excepció indicant que no es permeten Estampacions o Taules de Firmes o Annexes  en PDF/A."),
                new Propietat(false, "es.caib.portafib.revisordedestinatari.restretornarrevisorsglobals",
                        "Eliminat a la versió 3.0.1. Creat a la versió 3.0.0 Opcional. Valor per defecte false. Si val true en la consulta al servei rest de RevisorDeDestinatari també retorna els Revisors Globals."),
                new Propietat(true, "es.caib.portafib.addnotificationstonewuser",
                        "Afegida a la versió 3.0.2. Valor per defecte false. Si val true, als nous usuaris se´ls donarà d'alta en les notificacions de tipus ´Requerit per firmar´. ´Requerit per revisar´ i ´Requerit per validar´") };

        final Propietat[] propietats_bbdd_globals = {

                new Propietat(true, "es.caib.portafib.editableuser",
                        "Opcional.Si està a true permet als usuaris editar l'email  dels usuari-persona i usuaris-entitats, així com el logo dels usuaris-entitat. En cas contrari, únicament és l'administrador d'entitat que pot fer canvis en aquests camps."),
                new Propietat(true, "es.caib.portafib.numberoferrorsinnotificationtosendmail",
                        "Opcional. Indica  a partir de quants d'errors en una notificació callback s'enviarà un correu al responsable de l'usuari aplicació. Si no es defineix llavors no s'envia cap correu."),
                new Propietat(true, "es.caib.portafib.numberoferrorstopausenotification",
                        "Opcional. Indica a partir de quants d'errors en una notificació callback aquesta automàticament es pausarà. Si no es defineix llavors no es pausarà automàticament."),
                new Propietat(true, "es.caib.portafib.notificationtimelapse",
                        "Opcional. Valor per defecte 60000ms (1 minut). Ha de ser major de 15000. Temps mínim que s'espera abans de reintentar una notificació ws fallida en ms. Exemple (15 segons): 15000"),
                new Propietat(true, "es.caib.portafib.maxfitxeradaptatsizeinbytes",
                        "Opcional. Tamany màxim del fitxer PDF una vegada se li han afegit els annexes i taula de firmes. No definit significa sense límit o que dependrà del valor definit en l'entitat"),
                new Propietat(true, "es.caib.portafib.url",
                        "És l'adreça pública d'accés al portafirmes. Es requereix fonamentalment per la inclusió de URLs cap a PortaFIB en l'enviament de correus. Exemple: es.caib.portafib.url=http://localhost:8080/portafib"),
                new Propietat(true, "es.caib.portafib.email.from",
                        "És l'adreça d'email des d'on s'enviaran les notificacions per correu als usuaris: es.caib.portafib.email.from=portafib@portafib.org"),
                new Propietat(true, "es.caib.portafib.automaticredirect",
                        "Opcional. Si el valor es false, llavors no fa res. Si el valor és true llavors redirecciona segons el contexte:    (a) Si entra amb http dins portafibs llavors redirecciona a portafib.    (b) Si entra amb https dins portafib i existeix portafib/s llavors redirecciona a portafib/s.            "),
                new Propietat(true, "es.caib.portafib.emailsgroupedsendercronexpression",
                        "Opcional. Expressió cron que indica cada quan s'ha d'executar l'enviador de correus quan s'han definit enviament d'avisos agrupats. Per defecte s'executa cada dia a les 6:00 (). Exemple:  L'executa cada dos minuts: 0 0/2 * 1/1 * ? * L'executa cada dia a les 6:00: 0 0 6 1/1 * ? * Veure www.cronmaker.com per altres valors."),
                new Propietat(true, "es.caib.portafib.defaultentity",
                        "Opcional. Si val null indicam que l'Administrador d'Entitat ha de donar d'alta la persona i després l'usuari-entitat associat a aquella persona. Si aquest valor conté l'identificador d'una entitat, llavors els usuaris autenticats, automàticament seran registrats com a persones i associats a aquesta entitat.  En l'entorn de la CAIB (Govern Balear) quan la propietat es.caib.portafib.iscaib=true, llavors sempre l'usuari es dóna d'alta automàticament en l'entitat “caib” independentment del valor d'aquesta propietat. "),
                new Propietat(true, "es.caib.portafib.defaultrolesincreation",
                        "Opcional. S'utilitza conjuntament amb la propietat \"es.caib.portafib.defaultentity\". Indica els roles virtuals a asssignar per defecte a l'usuari-entitat quan aquest es crea automàticament. Es tracta d'una llista de roles separats per comes. Els valors possibles són: Sol·licitant: ROLE_SOLI Destinatari: ROLE_DEST Delegat: ROLE_DELE Col·laborador: ROLE_COLA  En l'entorn de la CAIB (Govern Balear) quan la propietat es.caib.portafib.iscaib=true, llavors a l'usuari-entitat sempre se li assigna el rol Destinatari (ROLE_DEST) independentment del valor d'aquesta propietat. "),
                new Propietat(true, "es.caib.portafib.entitatidforagentssql",
                        "Opcional excepte en entorns de la CAIB. Entitat sobre la qual s'aplicaran les accions del “Agents Seycon”. Veure punt [Gestió de Rols a traves de triggers Oracle] del manual d'instal·lació per més informació."),
                new Propietat(true, "es.caib.portafib.passwordforagentssql",
                        "Opcional excepte en entorns de la CAIB. Contrasenya (o clau de pas) per comprovar que les peticions http realment provenen d'un trigger de BBDD. Veure punt [Gestió de Rols a traves de triggers Oracle] del manual d'instal·lació per més informació."),
                new Propietat(true, "es.caib.portafib.logouturl",
                        "Opcional. Afegeix una nova opció de menú davall de “Configuració” del menú de la capçalera (superior dreta) que indica una URL que servirà per poder abandonar PortaFIB. Per aplicar canvis requereix aturar servidor."),
                new Propietat(true, "es.caib.portafib.signaturemodule.absoluteurl",
                        "Modificat a la versió 2.0.0 Opcional. És utilitzada pels Mòduls de Firma que necessitin accedir al PortaFIB de forma remota. En principi és idèntica a la propietat  «es.caib.portafib.url». S'utilitza quan la URL Base del Perfil de Firma no està definida."),
                new Propietat(true, "es.caib.portafib.flowtemplate.absoluteurl",
                        "Opcional. És utilitzada per l'API REST de Plantilla de Flux quan s'ha d'enviar la ruta al PortaFIB."),
                new Propietat(true, "es.caib.portafib.notificationwhencreatedelegaciocolaboracio",
                        "Nou a la versió 1.1.1 .Opcional. Indica si s’han d’enviar avisos via correu electrònic als delegats o col·laboradors quan són assignats per un destinatari. Existeix la mateix propietat però per aplicar a una sola entitat (Veure punt .-)"),
                new Propietat(true, "es.caib.portafib.avisosfirmespendents.cron",
                        "Nou a la versió 1.1.1 . Indica la freqüència, emprant una expressió cron (pe 0 0 5 1/1 * ? *), en que s''enviaran correus a la gent que té peticions de firma pendents. Veure http://www.cronmaker.com. Requereix reiniciar el servidor si passam de no estar definida a estar definida. Relacionat amb propietat d'entitat  es.caib.portafib.avisosfirmespendents.diesabans (Veure punt )"),
                new Propietat(true, "es.caib.portafib.activeusuarientitatafteragentseyconcreation",
                        "Nou a la versió 1.1.2 Opcional. En entorns CAIB, quan un agent seycon dóna d´alta un usuari a PortaFIB, emprant aquesta propietat podem decidir si aquest usuari-entitat es crearà activat (true) o desactivat (false o no definit)"),
                new Propietat(true, "es.caib.portafib.disablesignaturestable",
                        "Nou a la versió 2.0.0 Opcional. Desactiva la taula de firmes de les peticions de firma que provenen de l'API WS de PortaFIB, WS de Portafirmas antic i a traves de Passarel·la de firma."),
                new Propietat(true, "es.caib.portafib.portafiburlforexternalsignatures",
                        "Nou a la versió 2.0.1 Opcional. Requerida si és fa ús de l'API Rest de ExternalSignature per informar a usuaris de terceres aplicacions de les tasques pendents sobre peticions de firma que tenen dins PortaFIB"),
                new Propietat(true, "es.caib.portafib.strictvalidation",
                        "Si val true indica que les validacions per tipus XAdES i CAdES s'han de fer si o si, i en el cas de no haver-hi validador, llavors llançar un error. Si val false i no hi ha validador per algun tipus de xequeig (validador de firma, de nif firmant  o de document original modificat) llavors es marcarà aquell xequeig a false però  no fallarà."),
                new Propietat(true, "es.caib.portafib.maxuploadsizeinbytes",
                        "Tamany màxim de pujada de fitxers en bytes. No definit significa sense límit."),
                new Propietat(false, "es.caib.portafib.compactmenuoptionsofaden",
                        "Deprecat a la 3.0.3. Nou a la versió 2.0.1. Opcional. Per defecte false. Per entorn CAIB sempre val true. En entorn NO CAIB si val true indica que varies opcions del menú d’Administrador d’Entitat associades a Llistat de Peticions de Firma no es mostraran."),
                new Propietat(true, "es.caib.portafib.dniPattern",
                        "Nou a la versió 2.0.21. Opcional. Llista de expressions regulars, separades per bots de línia que s'empraran per extreure el NIF/NIE de dins el serialNumber del Subject dels certificats. el NIF/NIE ha d'estar dins el primer grup. Exemple: \"^IDCES-([0-9]{8}[A-Z])$\", \"^PNOES-([0-9]{8}[A-Z])$\", \"^([0-9]{8}[A-Z])$\"")

        };

        for (Propietat p : propietats_bbdd_per_entitat) {
            PROPIETATS_BBDD_PER_ENTITAT.put(p.clau, p);
        }

        for (Propietat p : propietats_bbdd_globals) {
            PROPIETATS_BBDD_GLOBALS.put(p.clau, p);
        }

    }

}
