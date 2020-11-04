<%@ page language="java" pageEncoding="UTF-8"
%><%@ include file="/WEB-INF/jsp/moduls/includes.jsp" 
%>
<div class="clear"></div>
<div class="spacer"></div>
<div>

<h2>PortaFIB App</h2>
<p>Per obtenir el fitxer d'instal·lació de la App pitji al següent enllaç:
 <a href="<c:url value="/doc/portafibapp.apk"/>" title="PortaFIB App">PortaFIB App</a>
</p>

<h3>Instruccions d'instal·lació</h3>

<p>Una vegada guardat l'arxiu dins el mòbil, accedirem a les descàrregues mitjançant el gestor de fitxers allà on l'haguem guardat.</p>
<p class="aligncenter"><img src="<c:url value="/img/appinfo/00_gestor_fitxers.jpg" />" alt="Gestor de fitxers" /></p>

<p>Picam damunt l'arxiu "portafibapp.apk" per instal·lar-lo i, si no ho hem activat ja amb altres ocasions, ens sortirà un avís indicant
que cal autoritzar la instal·lació d'aplicacions des de fonts desconegudes.</p>
<p class="aligncenter"><img src="<c:url value="/img/appinfo/01_fonts_desconegudes.jpg" />" alt="Avís fonts desconegudes" /></p>

<p>Si seguim l'opció que ens ofereix per anar a la configuració ens sortirà una opció que podem activar per permetre la instal·lació d'aplicacions</p>
<p class="aligncenter"><img src="<c:url value="/img/appinfo/02_fonts_desconegudes.jpg" />" alt="Permetre instal·lar aplicacions" /></p>

<p>Si tornam a la pantalla on erem, ara ja ens surt l'opció de instal·lar l'aplicació.</p>
<p class="aligncenter"><img src="<c:url value="/img/appinfo/03_instalar.jpg" />" alt="Instal·lar" /></p>

<p>Es possible que ens surti aquest avís indicant que no es coneix el desenvolupador de l'aplicació. Haurem de picar instal·lar igualment.</p>
<p class="aligncenter"><img src="<c:url value="/img/appinfo/04_avis_desenvolupador.jpg" />" alt="Avís desenvolupador" /></p>

<p>Si ens ha sortit el missatge anterior, també ens donarà l'opció de enviar l'aplicació a analitzar. En aquest cas podem triar qualsevol de les opcions.</p>
<p class="aligncenter"><img src="<c:url value="/img/appinfo/05_avis_analitzar.jpg" />" alt="Avís analitzar" /></p>

<p>Una vegada instal·lada l'aplicació, ja la podrem obrir.</p>
<p class="aligncenter"><img src="<c:url value="/img/appinfo/06_instalat.jpg" />" alt="Instal·lat" /></p>

<p>Picant els tres puntets veurem les opcions del menú, "Configuració" i "Instal·lar certificat".</p>
<p class="aligncenter"><img src="<c:url value="/img/appinfo/07_menu.jpg" />" alt="Menú"  /></p>

<p>Si no tenim cap certificat instal·lat per autenticar-nos primer caldrà anar a l'opció "Instal·lar certificat", que ens obrirà el gestor de fitxers d'on podrem cercar
el nostre certificat per instal·lar-lo, i assignar-li un nom.</p>

<p>Si anam a les opcions de configuració veurem les següents opcions.</p>
<p class="aligncenter"><img src="<c:url value="/img/appinfo/08_configuracio.jpg" />" alt="Configuració" /></p>

<dl>
<dt>Adreça del servidor</dt>
<dd>La URL amb la que accedirem al servior de PortaFIB. Serà una adreça de l'estil: https://intranet.caib.es/portafib</dd>
<dt>Seleccionar certificat client</dt>
<dd>Ens permetrà triar el certificat amb el que s'autenticarà PortaFIBApp. Si no hi ha cap certificat instal·lat caldrà que prèviament n'instal·lem un. 
Ho podem fer amb l'opció "Instal·lar certificat" del menú.
<dt>Certificat client</dt>
<dd>Aquesta opció no editable. Una vegada seleccionat un certificat amb l'opció "Seleccionar certificat client" apareixerà aquí el nom triat.
<dt>Notificacions actives</dt>
<dd>Si s'activa aquesta opció, períodicament l'aplicació consultarà si hi ha tasques pendents i en cas afirmatiu ens enviarà una notificació.</dd>
</dl>

<p>Si tenim l'adreça del servidor i el certificat client seleccionat, des de la pantalla principal podem "estirar" per avall, i s'actualitzarà la llista
de peticions pendents per cada perfil que tenguem dins PortaFIB. </p>
<p class="aligncenter"><img src="<c:url value="/img/appinfo/09_avisos.jpg" />" alt="Peticions pendents" /></p>

<p>Picant damunt cada botó ens durà a la web de PortaFIB que ens mostrarà les peticions pendents del
perfil corresponent.</p>

</div>

