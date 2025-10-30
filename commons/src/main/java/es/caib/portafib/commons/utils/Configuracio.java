package es.caib.portafib.commons.utils;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import org.jboss.logging.Logger;

/**
 * 
 * @author anadal
 * 
 */
public class Configuracio implements Constants {

    private static final Logger LOG = Logger.getLogger(Configuracio.class);

    public static final Map<String, Propietat> PROPIETATS_FITXER_PORTAFIB_PROPERTIES = new HashMap<String, Propietat>();
    
    public static final Map<String, Propietat> PROPIETATS_FITXER_SYSTEM_PORTAFIB_PROPERTIES = new HashMap<String, Propietat>();
    
    static {

        final Propietat[] portafib_properties = {

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
                new Propietat(true, "es.caib.portafib.certificateplugin", ""),
                new Propietat(true, "es.caib.portafib.obfuscateusernames", "Optional. Default value true. Permet ofuscar o no els usernames en els desplegables d'usuaris dels fluxos de firmes"),
                new Propietat(true, "es.caib.portafib.revisorcomboboxsusesearch", "Optional. Default value false. En el dialeg de selecció de Revisors permet que el combobox mostri un llistat (false) o afegeixi una barra de cerca(true)"),
                new Propietat(true, "es.caib.portafib.propagatemailerrors", "Serveix per ignorar errors en l'enviament de correus."
                        + " Per defecte val true, que significa que es llança una excepció "
                        + " si hi ha un error en l'enviament de correus. Si val false únicament es guarda l'error al log.")
        };
        
        
        final Propietat[] portafib_system_properties = {

                new Propietat(true, "es.caib.portafib.filesdirectory", "Directori on es guardaran tots els fitxers"),

                new Propietat(true, "es.caib.portafib.filesystemmanagerclass", 
                        "Si volem guardar tots els fitxers en el mateix directori utilitzar"
                        + " 'org.fundaciobit.genapp.common.filesystem.SimpleFileSystemManager' "
                        + " o si volem distribuir els fitxers en subdirectoris, llavors utilitzar la classe "
                        + " org.fundaciobit.genapp.common.filesystem.ThreeFolderFileSystemManager."
                        + " Revisar punt \"Migracio de 1.1.4 a 2.0.0 del 'Manual de Migracions'"),
                
                new Propietat(true, "es.caib.portafib.checkapplicationuserwithuserinformationplugin",
                        "Nou a 3.0.1. Opcional. Valor per defecte true."
                        + " Durant la creació d'un usuari aplicació, si aquesta propietat val true llavors comprova "
                        + "que aquest usuari aplicació existeixi al sistema d'autenticació/autorització"),
                new Propietat(true, "es.caib.portafib.userinformationplugin",
                        "Classe que gestionarà l'obtenció d'informació dels usuaris:" 
                          + "org.fundaciobit.pluginsib.userinformation.keycloak.KeyCloakUserInformationPlugin," 
                          + "org.fundaciobit.pluginsib.userinformation.database.DataBaseUserInformationPlugin o"
                          + "org.fundaciobit.pluginsib.userinformation.ldap.LdapUserInformationPlugin"
                   ),
                new Propietat(true, "es.caib.portafib.pluginsib.userinformation.", "Propietats per configurar els diferents plugins d'informació d'usuari")

        };
        


        for (Propietat p : portafib_properties) {
            PROPIETATS_FITXER_PORTAFIB_PROPERTIES.put(p.clau, p);
        }
        
        for (Propietat p : portafib_system_properties) {
            PROPIETATS_FITXER_SYSTEM_PORTAFIB_PROPERTIES.put(p.clau, p);
        }

    }

    
    
    

    private static Properties portafibProperties;

    private static Properties portafibSystemProperties;
    
    public static void reloadProperties() {
        portafibProperties = null;
        portafibSystemProperties = null;
    }

    public static Properties getPortaFIBProperties() {
        if (portafibProperties == null) {
            portafibProperties = loadPropertiesFromKey("es.caib.portafib.properties");
        }
        return portafibProperties;
    }

    public static Properties getPortaFIBSystemProperties() {
        if (portafibSystemProperties == null) {
            portafibSystemProperties = loadPropertiesFromKey("es.caib.portafib.system.properties");
        }
        return portafibSystemProperties;
    }

    private static Properties loadPropertiesFromKey(String key) {
        String propertyFileName = System.getProperty(key);
        try (Reader reader = new FileReader(propertyFileName)) {
            Properties prop = new Properties();
            prop.load(reader);
            return prop;
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    protected static Long getLongPortaFIBProperty(String key) {
        String value = getPortaFIBProperties().getProperty(key);
        Long valueLong = null;
        if (value != null) {
            try {
                valueLong = Long.parseLong(value);
            } catch (Exception e) {
                LOG.error("Error parsing long value for key " + key, e);
            }
        }
        return valueLong;
    }

    public static Properties getSystemAndFileProperties() {
        Properties properties = new Properties();
        properties.putAll(System.getProperties());
        properties.putAll(getPortaFIBSystemProperties());
        properties.putAll(getPortaFIBProperties());
        return properties;
    }

    public static boolean isCAIB() {
        return "true".equalsIgnoreCase(getPortaFIBProperties().getProperty(PORTAFIB_PROPERTY_BASE + "iscaib"));
    }

    public static String getFilesDirectory() {
        return getPortaFIBSystemProperties().getProperty(PORTAFIB_PROPERTY_BASE + "filesdirectory");
    }

    public static String getFileSystemManagerClass() {
        return getPortaFIBSystemProperties().getProperty(PORTAFIB_PROPERTY_BASE + "filesystemmanagerclass");
    }

    public static boolean isDesenvolupament() {
        return "true".equalsIgnoreCase(getPortaFIBProperties().getProperty(PORTAFIB_PROPERTY_BASE + "development"));
    }

    public static boolean obfuscateUsernames() {
        if ("false"
                .equalsIgnoreCase(getPortaFIBProperties().getProperty(PORTAFIB_PROPERTY_BASE + "obfuscateusernames"))) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isRevisorsComboboxUseSearch() {
        return "true".equalsIgnoreCase(getPortaFIBProperties().getProperty(PORTAFIB_PROPERTY_BASE + "revisorcomboboxsusesearch"));
    }

    public static String getDefaultLanguage() {
        return getPortaFIBProperties().getProperty(PORTAFIB_PROPERTY_BASE + "defaultlanguage", "ca");
    }

    public static Locale getDefaultLocale() {
        return new Locale(getDefaultLanguage());
    }

    // Capçaleres d'entitat durant la signatura WEB  #1058
    // TODO Eliminar
    @Deprecated
    public static String getHeaderBackgroundColor() {
        return getPortaFIBProperties().getProperty(PORTAFIB_PROPERTY_BASE + "headerbackgroundcolor");
    }


    /**
     * Permet indicar si volem mostrar als usuaris un enllaç cap a una APK de Android.
     * Si no existeix o el valor és buid, no es mostrarà cap enllaç.
     * Si el valor és "server", emprarà un APK distribuit amb l'aplicació.
     * Si el valor és una ruta de fitxers, emprarà l'APK indicat a la ruta de fitxers.
     */
    public static String getAndroidApk() {
        return getPortaFIBProperties().getProperty(PORTAFIB_PROPERTY_BASE + "androidapk", null);
    }

    public static byte[] getEncryptKey() {
        return getPortaFIBProperties().getProperty(PORTAFIB_PROPERTY_BASE + "encryptkey", "portafibportafib")
                .getBytes();
    }

    public static String getExportDataPlugins() {
        return getPortaFIBProperties().getProperty(PORTAFIB_PROPERTY_BASE + "exportdataplugins", null);
    }

    /**
     * Indica si s'ha de validar el certificat emprant el Plugin de CheckCertificate quan 
     * l'autenticació es realitza emprant ClientCert
     */
    public static boolean checkCertificateInClientCert() {
        return "true".equalsIgnoreCase(
                getPortaFIBProperties().getProperty(PORTAFIB_PROPERTY_BASE + "checkcertificateinclientcert"));
    }
    
    
    public static boolean propagateMailErrors() {
        
        String pme = getPortaFIBProperties().getProperty(PORTAFIB_PROPERTY_BASE + "propagatemailerrors");
        if (pme == null || pme.trim().isEmpty()) {
            return true;
        } else {
            if ("false".equalsIgnoreCase(pme.trim())) {
                return false;
            } else {
                return true;
            }
        }
    }

    public static String getAppUrl() {
        return getPortaFIBProperties().getProperty(PORTAFIB_PROPERTY_BASE + "url");
    }

    public static String getAppName() {
        return getPortaFIBProperties().getProperty(PORTAFIB_PROPERTY_BASE + "name", "PortaFIB");
    }

    public static boolean isCheckApplicationUserWithUserInformationPlugin() {
        final Properties sysprop = getPortaFIBSystemProperties();
        String check = sysprop.getProperty(PORTAFIB_PROPERTY_BASE + "checkapplicationuserwithuserinformationplugin");
        if (check == null) {
            return true;
        } else {
            return "true".equalsIgnoreCase(check);
        }
    }

}
