# Imatge basada en: https://github.com/GovernIB/docker-imatges/tree/docker-imatges-1.0/keycloak-6.0.1
# que únicament canvia el fitxer JSON d'importació per afegir-hi els usuaris de l'entorn de Proves de PortaFIB
FROM jboss/keycloak:6.0.1

ENV KEYCLOAK_USER=admin \
    KEYCLOAK_PASSWORD=admin \
    KEYCLOAK_IMPORT=/tmp/import-goib-portafib-realm.json

WORKDIR /opt/jboss/

COPY scripts/import-goib-portafib-realm.json /tmp

USER jboss

EXPOSE 8180

CMD ["-b 0.0.0.0 -bmanagement 0.0.0.0 -Djboss.socket.binding.port-offset=100"]