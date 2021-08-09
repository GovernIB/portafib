FROM areusfundaciobit/jboss52bc

# Crear el directori pel repositori de fitxers de PortaFIB
RUN mkdir /opt/jboss/portafibfiles
COPY filesystem /opt/jboss/portafibfiles

COPY afirma/transformersTemplates /opt/jboss/afirma/transformersTemplates

# Copiar tot dins deploy
COPY config jboss-eap-5.2/jboss-as/server/default/deploy


