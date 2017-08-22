
========== PROBLEMA dues version en el mATEIX JAR

La idea inicial era posar cada versió de WS (la part dels servidor) dins un jar separat.
+ ERROR 1: El primer problema que em vaig trobar va ser que JBoss no permet dos jars amb el mateix contexte:

Error : Context-Root already exists


+ ERROR 2:Si pos un context root diferent, per exemple @WebContext(contextRoot="/portafib/ws/v2", ...), llavors em dóna l'error següent:

Caused by: java.lang.IllegalStateException: Context root must be the same for all deployed endpoints

+ SOLUCIÓ:
   En aquest jar es defineixen tots els WS i s'afegeixen profiles per compilar les diferents versions o ninguna (crea un jar buit):
   -Ppassarelaweb-v1
   -Ppassarelaserver-v1
   -Pws-portafib-v1
   -Pws-portafib-v2 
   -Ppassarelaweb-v2
   -Ppassarelaserver-v2