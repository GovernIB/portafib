
La idea inicial era posar cada versió de WS (la part dels servidor) dins un jar separat.
El primer problema que em vaig trobar va ser que JBoss no permet dos jars amb el mateix contexte:

<< FALTA ERROR >>


Si pos un context root diferent, per exemple @WebContext(contextRoot="/portafib/ws/v2", ...), llavors em dóna l'error següent:

Caused by: java.lang.IllegalStateException: Context root must be the same for all deployed endpoints

SOLUCIÓ:
   En aquest jar es defineixen tots els WS i s'afegeix un profile "exclude-ws-portafib-v1" per no compilar la versió 1 dels WS.