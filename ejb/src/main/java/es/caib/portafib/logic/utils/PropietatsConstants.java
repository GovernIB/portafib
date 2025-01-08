package es.caib.portafib.logic.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author anadal(u80067)
 *
 */
public class PropietatsConstants {

    public static final Map<String, Propietat> propietatsEntitat = new HashMap<String, PropietatsConstants.Propietat>();

    private static final Propietat[] PROPIETATS_ENTITAT = {

            new Propietat(true,  "es.caib.portafib.maxpeticiotitlelength",
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
                    "Afegida a la versió 3.0.2. Valor per defecte false. Si val true, als nous usuaris se´ls donarà d'alta en les notificacions de tipus ´Requerit per firmar´. ´Requerit per revisar´ i ´Requerit per validar´")
    };

    public static final Map<String, Propietat> propietatsGlobals = new HashMap<String, PropietatsConstants.Propietat>();

    private static final Propietat[] PROPIETATS_GLOBALS = {

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

    public static final Map<String, Propietat> propietatsSistema = new HashMap<String, PropietatsConstants.Propietat>();

    private static final Propietat[] PROPIETATS_SISTEMA = {

            new Propietat(true, "es.caib.portafib.iscaib",
                    " Propietat que indica als projectes que activin les característiques especials requerides en l'entorn de la CAIB (Govern Balear) si val true. Si no estam dins l'entorn CAIB, llavors ha de valer “false”."),
            // PATTERN
            new Propietat(true, "es.caib.portafib.hibernate.",
                    "Propietats de Configuració Hibernate: Estableix les propietats de configuració de Hibernate. Les dues propietats més importants són:    es.caib.portafib.hibernate.dialect    es.caib.portafib.hibernate.query.substitutions     En PostgreSQL la propietats de substitutions no es definirà però en Oracle aquesta ha de valer “true 1, false 0” ja que s'ha de realitzar el mapeig de booleans a sencers ja que aquest SGBD no suporta booleans."),
            new Propietat(true, "es.caib.portafib.filesdirectory",
                    "Directori d'emmagatzemament de Fitxers: PortaFIB necessita un directori on guardar tots els fitxers ja que aquests no es guarden en base de dades. Per això s'ha de definir la propietat es.caib.portafib.filesdirectory que apunti a un directori existent i amb espai suficient per guardar tots els fitxers. Crearem un directori /portafibfiles i inicialitzarem aquesta propietat a es.caib.portafib.filesdirectory=/portafibfiles. Un exemple de ruta windows podria ser la següent: es.caib.portafib.filesdirectory=c:\\tmp\\portafibfiles. Si estam carregant la BBDD de demo, llavors és el lloc on ficarem els fitxers associats amb les dades de prova, per això executarem la següent comanda $unzip $HOME/portafib/portafibfiles.zip -d /portafibfiles. NOTA IMPORTANT: El directori elegit ha d'estar en la mateixa unitat lògica (o sigui el mateix disc dur i partició) que el directori temporal elegit pel JBoss. Més concretament en Linux, el directori /tmp a vegades apunta a una unitat virtual en memòria simulant ser una altre partició, cosa que fa que PortaFIB doni errors: fer que /tmp sigui un directori real."),
            new Propietat(true, "es.caib.portafib.filesystemmanagerclass",
                    "Nou a 2.0.0  Opcional. Per defecte val org.fundaciobit.genapp.common.filesystem.SimpleFileSystemManager. Modificant aquesta propietat  es permet gestionar com es guarden els fitxers en el directori de fitxers definit per la propietat “es.caib.portafib.filesdirectory”. Amb la següent propietat podem definir si volem tots els arxius en un mateix directori (classe org.fundaciobit.genapp.common.filesystem.SimpleFileSystemManager, comportament actual) o si els vol distribuir en una estructura de 3 subdirectoris(classe org.fundaciobit.genapp.common.filesystem.ThreeFolderFileSystemManager). Per exemple, en estructura de 3 directoris, el fitxer amb ID 206395 es guardaria en [“es.caib.portafib.filesdirectory”]\\3\\9\\5\\206395.  Si es té el filesystem clàssic i es vol passar a tres subdirectoris, llavors s'han de seguir les passes descrites en el punt “Migracio de 1.1.4 a 2.0.0” del document “Manual_de_Migracio_de_Versions_de_PortaFIB”"),
            new Propietat(false, "es.caib.portafib.url",
                    "(PortaFIB 1.1.0 Deprecat 12/01/2016: Migrat a Propietats Globals del menú d'Administrador) És l'adreça pública d'accés al portafirmes: es.caib.portafib.url=http://localhost:8080/portafib"),
            new Propietat(false, "es.caib.portafib.email.from",
                    " (PortaFIB 1.1.0 Deprecat 12/01/2016: Migrat a Propietats Globals del menú d'Administrador) És l'adreça d'email des d'on s'enviaran les notificacions per correu als usuaris: es.caib.portafib.email.from=portafib@portafib.org"),
            new Propietat(true, "es.caib.portafib.defaultlanguage",
                    "Idioma per defecte. Valors possibles poden ser “ca” per català i “es” per castellà. es.caib.portafib.defaultlanguage=ca"),
            new Propietat(true, "es.caib.portafib.defaultentity",
                    " (PortaFIB 1.1.0 Deprecat 12/01/2016: Migrat a Propietats Globals del menú d'Administrador) Si val null indicam que l'Administrador d'Entitat ha de donar d'alta la persona i després l'usuari-entitat associat a aquella persona. Si aquest valor conté l'identificador d'una entitat, llavors els usuaris autenticats, automàticament seran registrats com a persones i associats a aquesta entitat.   En l'entorn de la CAIB (Govern Balear) quan la propietat es.caib.portafib.iscaib=true, llavors sempre l'usuari es dóna d'alta automàticament en l'entitat “caib” independentment del valor d'aquesta propietat. "),
            new Propietat(true, "es.caib.portafib.defaultrolesincreation",
                    " (PortaFIB 1.1.0 Deprecat 12/01/2016: Migrat a Propietats Globals del menú d'Administrador) S'utilitza conjuntament amb la propietat \"es.caib.portafib.defaultentity\". Indica els roles virtuals a asssignar per defecte a l'usuari-entitat quan aquest es crea automàticament. Es tracta d'una llista de roles separats per comes. Els valors possibles són: Sol·licitant: ROLE_SOLI Destinatari: ROLE_DEST Delegat: ROLE_DELE Col·laborador: ROLE_COLA   En l'entorn de la CAIB (Govern Balear) quan la propietat es.caib.portafib.iscaib=true, llavors a l'usuari-entitat sempre se li assigna el rol Destinatari (ROLE_DEST) independentment del valor d'aquesta propietat. "),
            new Propietat(true, "es.caib.portafib.development",
                    "Propietat que fa que es mostrin per pantalla i per log més informació de la requerida. Aquest valor es carrega en calent, per la qual cosa en qualsevol moment sense haver d'aturar el servidor es pot activar o desactivar per fer una depuració ràpida. Valors possibles són true o false: es.caib.portafib.development=false"),
            new Propietat(false, "es.caib.portafib.checknifcertificate",
                    " (PortaFIB 1.1.0  Deprecat 13/11/2015: Mirar propietat \"Comprovar NIF després de Firmar\" dins l'Entitat) Si val true quan es firma un document comprova que el DNI de la persona que ha firma (DNI del certificat digital) s'ajusta al DNI de la persona que realment ha de firmar. S'assigna a false en mode desenvolupament per poder fer tests amb certificats i usuaris de  proves. es.caib.portafib.checknifcertificate=true"),
            new Propietat(false, "es.caib.portafib.maxuploadsizeinbytes",
                    " (PortaFIB 1.1.0 Deprecat 12/01/2016: Migrat a Propietats Globals del menú d'Administrador) Tamany màxim de pujada de fitxers en bytes. No definit o amb valors buit significa sense límit ( es.caib.portafib.maxuploadsizeinbytes=)"),
            new Propietat(false, "es.caib.portafib.maxfitxeradaptatsizeinbytes",
                    " (PortaFIB 1.1.0 Deprecat 12/01/2016: Migrat a Propietats Globals del menú d'Administrador) Tamany màxim del fitxer PDF una vegada se li han afegit els annexes i taula de firmes. No definit significa sense límit (es.caib.portafib.maxfitxeradaptatsizeinbytes=)"),
            new Propietat(true, "es.caib.portafib.encryptkey",
                    "Clau per encriptar l'identificador del fitxers a descarregar (IMPORTANT tamany de 16 caràcters): es.caib.portafib.encryptkey=portafibportafib"),
            new Propietat(true, "es.caib.portafib.androidapk",
                    "Permet indicar si volem mostrar als usuaris un enllaç cap a una APK de Android. Si no existeix o el valor és buid, no es mostrarà cap enllaç. Si el valor és \"server\", emprarà un APK distribuit amb l'aplicació. Si el valor és una ruta de fitxers, emprarà l'APK indicat a la ruta de fitxers: es.caib.portafib.androidapk=server"),
            new Propietat(false, "es.caib.portafib.name",
                    " (PortaFIB 1.1.0 Deprecat 12/01/2016: Eliminada ja que no s'usava) Nom de l'aplicació PortaFIB: es.caib.portafib.name=PortaFIB"),
            new Propietat(false, "es.caib.portafib.editableuser",
                    " (PortaFIB 1.1.0 Deprecat 12/01/2016: Migrat a Propietats Globals del menú d'Administrador) Si està a true permet als usuaris editar l'email  dels usuari-persona i usuaris-entitats, així com el logo dels usuaris-entitat. En cas contrari, únicament és l'administrador d'entitat que pot fer canvis en aquest camps es.caib.portafib.editableuser=false"),
            new Propietat(false, "es.caib.portafib.defaultsignalgorithmid",
                    " (PortaFIB 1.1.0  Deprecat 13/11/2015: Mirar camp \"Algorisme de Firma\" dins l'Entitat) Camp opcional. Defineix l'identificador de l'algorisme a utilitzar per defecte durant la firma de documents o fitxers. Fa referencia al camp ID de la taula pfi_algorismedefirma. Els valors possibles d'una instal·lació per defecte són: 0 = SHA1withRSA 1 = SHA256withRSA 2 = SHA384withRSA 3 = SHA512withRSA"),
            new Propietat(true, "es.caib.portafib.exportdataplugins",
                    "Llistat de Plugins pel l'exportació de dades en els llistats (excel, ods, csv, ...). Exemple: es.caib.portafib.exportdataplugins=org.fundaciobit.plugins.exportdata.cvs.CSVPlugin,org.fundaciobit.plugins.exportdata.ods.ODSPlugin,org.fundaciobit.plugins.exportdata.excel.ExcelPlugin"),
            new Propietat(false, "es.caib.portafib.numberoferrorsinnotificationtosendmail",
                    " (PortaFIB 1.1.0 Deprecat 12/01/2016: Migrat a Propietats Globals del menú d'Administrador) Opcional. Indica  a partir de quants d'errors en una notificació callback s'enviarà un correu al responsable de l'usuari aplicació. Si no es defineix llavors no s'envia cap correu."),
            new Propietat(false, "es.caib.portafib.numberoferrorstopausenotification",
                    " (PortaFIB 1.1.0 Deprecat 12/01/2016: Migrat a Propietats Globals del menú d'Administrador) Opcional. Indica a partir de quants d'errors en una notificació callback aquesta automàticament es pausarà. Si no es defineix llavors no es pausarà automàticament."),
            new Propietat(false, "es.caib.portafib.notificationtimelapse",
                    " (PortaFIB 1.1.0 Deprecat 12/01/2016: Migrat a Propietats Globals del menú d'Administrador) Opcional. Valor per defecte 60000ms (1 minut). Ha de ser major de 15000. Temps mínim que s'espera abans de reintentar una notificació ws fallida en ms Exemple (15 segons):  es.caib.portafib.notificationtimelapse=15000"),
            new Propietat(false, "es.caib.portafib.applet.signerClass",
                    " (PortaFIB 1.1.0 Deprecat 10/11/2015: S'usen els Mòduls de Firma) Indica al PortaFIB quina API emprarem per firmar els documents. Valors possibles són: Firma de documents emprant @firma: es.caib.portafib.applet.signers.AfirmaSigner Firma emprant IB-KEY: es.caib.portafib.applet.signers.IBKeySigner"),
            new Propietat(false, "es.caib.portafib.automaticredirect",
                    " (PortaFIB 1.1.0 Deprecat 12/01/2016: Migrat a Propietats Globals del menú d'Administrador) Si el valor és true llavors redirecciona segons el contexte:        (a) Si entra amb http dins /portafib/s llavors redirecciona a /portafib        (b) Si entra amb https dins /portafib i existeix /portafib/s llavors redirecciona a /portafib/s Si el valor és false, llavors no intenta fer cap redirecció."),
            // PATTERN {entitat_id}.{idioma}
            new Propietat(false, "es.caib.portafib.firmatperformat.",
                    " (PortaFIB 1.1.0 Deprecat 13/11/2015: Mirar propietat \"Format de 'Firmat Per'\" de l'Entitat)Opcional. Format del camp \"Firmat Per\" de la taula de firmes definit per entitat i per idioma. Els camps disponibles són (s'obtenen del certificat amb el que s'ha fimat): {0} = NOM {1} = LONGITUD NIF {2} = NIF {3} = EMISSOR {4} = LONGITUD CARREC_CERTIFICAT {5} = CARREC_CERTIFICAT {6} = LONGITUD UNITAT_ADMINISTRATIVA {7} = UNITAT_ADMINISTRATIVA  Exemple de formats per l'entitat caib pels idiomes català i castellà: es.caib.portafib.firmatperformat.caib.ca={0}{4,choice,0#|1&lt; - C\u00E0rrec {5}}{6,choice,0#|1&lt; - Unitat {7}} es.caib.portafib.firmatperformat.caib.es={0}{4,choice,0#|1&lt; - Cargo {5}}{6,choice,0#|1&lt; - Unidad {7}} NOTA: S'han d'escapar els accents i caràcters especials a XML. Per exemple 'à'  \u00E0 o '<'  &lt;"),
            // PATTERN {entitat_id}.{idioma}
            new Propietat(false, "es.caib.portafib.motiudelegacioformat.",
                    " (PortaFIB 1.1.0 Deprecat 13/11/2015: Mirar propietat \"Format del motiu per delegació\" de l'Entitat) Opcional. Format del camp \"Motiu\" de la taula de firmes quan es tracta d'una delegació definit per entitat i idioma. Els paràmetres disponibles són: {0} Nom del delegat {1} NIF del delegat {2} Nom del destinatari {3} NIF del destinatari {4} Motiu de la delegació {5} Motiu de la petició de firma Exemple de formats per l'entitat caib pels idiomes català i castellà: es.caib.portafib.motiudelegacioformat.caib.ca=Firma {0} ({1}) per delegaci\u00F3 de {2} ({3}). Motiu: {4} es.caib.portafib.motiudelegacioformat.caib.es=Firma {0} ({1}) por delegaci\u00F3n de {2} ({3}). Motivo: {4} NOTA: S'han d'escapar els accents i caràcters especials a XML emprant Unicode. Per exemple 'ó' \\u00F3"),
            new Propietat(false, "es.caib.portafib.entitatidforagentssql",
                    " (PortaFIB 1.1.0 Deprecat 12/01/2016: Migrat a Propietats Globals del menú d'Administrador) Opcional excepte en entorns de la CAIB. Entitat sobre la qual s'aplicaran les accions del “Agents Seycon”. Veure punt “” per més informació."),
            new Propietat(false, "es.caib.portafib.passwordforagentssql",
                    " (PortaFIB 1.1.0 Deprecat 12/01/2016: Migrat a Propietats Globals del menú d'Administrador) Opcional excepte en entorns de la CAIB. Contrasenya (o clau de pas) per comprovar que les peticions http realment provenen d'un trigger de BBDD. Veure punt “” per més informació."),
            new Propietat(false, "es.caib.portafib. maxitemstoshowinautocomplete",
                    " (PortaFIB 1.1.0 Deprecat 12/01/2016: Migrat a Propietats d'Entitat del menú d'Administrador d'Entitat) Opcional. Valor per defecte 10. En els formularis de cerques dinàmiques d'usuari, indica el màxim de resultats permesos per mostrar resultats de l'usuari. "),
            new Propietat(false, "es.caib.portafib. mincharstostartautocomplete",
                    " (PortaFIB 1.1.0 Deprecat 12/01/2016: Migrat a Propietats d'Entitat del menú d'Administrador d'Entitat) Opcional. Valor per defecte 2. En els formularis de cerques dinàmiques d'usuari, indica el mínim de caràcters que ha d'escriure l'usuari abans de que li apareguin els resultats de la cerca. En entitats amb molts d'usuaris es recomana incrementar aquest valora a 3 o 4 amb la finalitat de reduir càrrega de xarxa, processador i bbdd. Relacionat amb la propietat  es.caib.portafib.maxitemstoshowinautocomplete"),
            // // PATTERN {entitat_id}.{idioma}
            new Propietat(false, "es.caib.portafib.defaultcustodymessage.",
                    " (PortaFIB 1.1.0 Deprecat 13/11/2015: Mirar camp \"Cutòdia per Defecte\" de l'Entitat) Indica un missatge per defecte de la custòdia de les peticions de Firma, definit per entitat i per idioma. Exemple: es.caib.portafib.defaultcustodymessage.fundaciobit.ca=Data:{3} URL de validaci\u00F3: {0} es.caib.portafib.defaultcustodymessage.fundaciobit.es=Fecha:{3} URL de validaci\u00F3n: {0}"),
            new Propietat(false, "es.caib.portafib.maxtimelockedsigninms",
                    " (PortaFIB 1.1.0 Deprecat 12/01/2016: Migrat a Propietats d'Entitat del menú d'Administrador d'Entitat) Opcional. Indica Temps de validesa del Token de Firma només quan hi ha múltiples firmes en un bloc o hi ha delegats definits. Es a dir, el temps màxim que un firmant pot tenir bloquejat un document mentre es realitza el procés de firma. Valor per defecte 3*60*1000, o sigui 3 minuts. Quan la firma es única en el bloc i no hi ha delegats definits llavors no hi ha bloqueig de temps"),
            new Propietat(false, "es.caib.portafib.emailsgroupedsendercronexpression",
                    " (PortaFIB 1.1.0 Deprecat 12/01/2016: Migrat a Propietats Globals del menú d'Administrador) Opcional. Expressió cron que indica cada quan s'ha d'executar l'enviador de correus quan s'han definit enviament d'avisos agrupats. Per defecte s'executa cada dia a les 6:00 (). Exemple:  L'executa cada dos minuts: 0 0/2 * 1/1 * ? * L'executa cada dia a les 6:00: 0 0 6 1/1 * ? * Veure www.cronmaker.com per altres valors."),
            new Propietat(true, "es.caib.portafib.checkcertificateinclientcert",
                    "Opcional. Indica si s'ha de validar el certificat emprant el Plugin de CheckCertificate quan l'autenticació es realitza emprant ClientCert. Valor per defecte false. Exemple: es.caib.portafib.checkcertificateinclientcert=true"),
            new Propietat(false, "es.caib.portafib.signaturemodule.absoluteurl",
                    " (PortaFIB 1.1.0 Deprecat 12/01/2016: Migrat a Propietats Globals del menú d'Administrador) Opcional. Si no es defineix llavors obté la URL absoluta de la petició (Pot haver-hi  problemes si el Apache-Proxy no té activat \"ProxyPreserveHost On\"). Si és defineix s'utilitzarà aquesta URL com ruta absoluta en els plugins de firma web que ho requereixin (JavaWebStart, SIA, ...). Serveix per Plugins de Firma que han d'accedir externament al Servidor de PortaFIB. Exemple: es.caib.portafib.signaturemodule.absoluteurl= http://portafib.ibit.org/portafib"),
            new Propietat(true, "es.caib.portafib.documentconverterplugin", "Classe Plugin de Conversió de Documents"),
            new Propietat(true, "es.caib.portafib.plugins.documentconverter.openoffice.host",
                    "Paràmetres del Plugin de Conversió de Documents"),
            new Propietat(true, "es.caib.portafib.plugins.documentconverter.openoffice.port",
                    "Paràmetres del Plugin de Conversió de Documents"),

            new Propietat(true, "es.caib.portafib.userinformationplugin", ""),
            new Propietat(true, "es.caib.portafib.certificateplugin", "")

    };

    public static final int TIPUS_PROPIETAT_GLOBAL = 0;

    public static final int TIPUS_PROPIETAT_ENTITAT = 1;

    public static final int TIPUS_PROPIETAT_SISTEMA = 2;

    /**
     * 
     * @author anadal(u80067)
     *
     */
    public static class Propietat {

        public final boolean activa;
        public final String clau;
        public final String descripcio;

        public Propietat(boolean activa, String clau, String descripcio) {
            super();
            this.activa = activa;
            this.clau = clau;
            this.descripcio = descripcio;
        }

    }

    static {

        for (Propietat p : PROPIETATS_ENTITAT) {
            propietatsEntitat.put(p.clau, p);
        }

        for (Propietat p : PROPIETATS_GLOBALS) {
            propietatsGlobals.put(p.clau, p);
        }

        for (Propietat p : PROPIETATS_SISTEMA) {
            propietatsSistema.put(p.clau, p);
        }

    }

}
