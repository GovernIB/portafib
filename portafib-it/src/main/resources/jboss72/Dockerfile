# Use latest jboss/base-jdk:11 image as the base
FROM jboss/base-jdk:11

ARG KEYCLOAK_URI_ARG=http://keycloak:8180

ENV WILDFLY_VERSION=14.0.1.Final \
    WILDFLY_SHA1=757d89d86d01a9a3144f34243878393102d57384 \
    JBOSS_HOME=/opt/jboss/wildfly \
    ADMIN_USER=admin \
    ADMIN_PASSWORD=admin \
    KEYCLOAK_URI=$KEYCLOAK_URI_ARG \
    KEYCLOAK_VERSION=6.0.1 \
    ORACLE_JDBC_VERSION=21.3.0.0 \
    PATH=${JBOSS_HOME}/bin:/tmp:${PATH}

USER root

# Add the WildFly distribution to /opt, and make wildfly the owner of the extracted tar content
# Make sure the distribution is available from a well-known place
RUN cd $HOME \
    && curl -O https://download.jboss.org/wildfly/$WILDFLY_VERSION/wildfly-$WILDFLY_VERSION.tar.gz \
    && sha1sum wildfly-$WILDFLY_VERSION.tar.gz | grep $WILDFLY_SHA1 \
    && tar xf wildfly-$WILDFLY_VERSION.tar.gz \
    && mv $HOME/wildfly-$WILDFLY_VERSION $JBOSS_HOME \
    && rm wildfly-$WILDFLY_VERSION.tar.gz \
    && chown -R jboss:0 ${JBOSS_HOME} \
    && chmod -R g+rw ${JBOSS_HOME}

# Ensure signals are forwarded to the JVM process correctly for graceful shutdown
ENV LAUNCH_JBOSS_IN_BACKGROUND true

USER root
WORKDIR /tmp

RUN printenv > env.properties

USER ${JBOSS_USER}
WORKDIR ${JBOSS_HOME}

# Wildfly administration
RUN bin/add-user.sh ${ADMIN_USER} ${ADMIN_PASSWORD} --silent

# Oracle drivers
RUN curl -OL https://repo1.maven.org/maven2/com/oracle/database/jdbc/ojdbc11/${ORACLE_JDBC_VERSION}/ojdbc11-${ORACLE_JDBC_VERSION}.jar
COPY scripts/driver-oracle-install-offline.cli bin
RUN bin/jboss-cli.sh --file=bin/driver-oracle-install-offline.cli --properties=/tmp/env.properties && \
    rm ojdbc11-${ORACLE_JDBC_VERSION}.jar && \
    rm bin/driver-oracle-install-offline.cli

# Wildfly-Keycloak connector. Adaptam el nom de la configuració a standalone-full.xml
RUN curl -L https://downloads.jboss.org/keycloak/${KEYCLOAK_VERSION}/adapters/keycloak-oidc/keycloak-wildfly-adapter-dist-${KEYCLOAK_VERSION}.tar.gz | tar zx &&\
    sed -i "s/standalone.xml/standalone-full.xml/" bin/adapter-install-offline.cli &&\
    bin/jboss-cli.sh --file=bin/adapter-install-offline.cli

COPY scripts/portafib-add-keycloak-offline.cli bin
RUN sed -i "s/<resolve-parameter-values>false<\/resolve-parameter-values>/ <resolve-parameter-values>true<\/resolve-parameter-values>/" bin/jboss-cli.xml &&\
    bin/jboss-cli.sh --file=bin/portafib-add-keycloak-offline.cli --properties=/tmp/env.properties

COPY scripts/portafib-add-queues.cli bin
RUN bin/jboss-cli.sh --file=bin/portafib-add-queues.cli && \
    rm bin/portafib-add-queues.cli

COPY scripts/portafib-add-legacy-security.cli bin
RUN bin/jboss-cli.sh --file=bin/portafib-add-legacy-security.cli && \
    rm bin/portafib-add-legacy-security.cli

COPY scripts/portafib-config-mail.cli bin
RUN bin/jboss-cli.sh --file=bin/portafib-config-mail.cli && \
    rm bin/portafib-config-mail.cli

COPY scripts/add-system-properties.cli bin
RUN bin/jboss-cli.sh --file=bin/add-system-properties.cli && \
    rm bin/add-system-properties.cli

RUN rm -rf standalone/configuration/standalone_xml_history/current

# Crear el directori pel repositori de fitxers de PortaFIB
RUN mkdir /opt/jboss/portafibfiles
COPY filesystem /opt/jboss/portafibfiles
COPY afirma/transformersTemplates /opt/jboss/afirma/transformersTemplates

# Fitxers de configuració referenciats dins el JBoss
RUN mkdir /opt/jboss/config
COPY config /opt/jboss/config

# Datasource i EAR
COPY deployments ${JBOSS_HOME}/standalone/deployments/

EXPOSE 8080 8443 9990
ENTRYPOINT ["/bin/bash", "-c"]

# Fixar els protocols HTTPS a TLSv1.2 es per mor de: https://github.com/GovernIB/pluginsib-signatureweb/issues/1
CMD ["bin/standalone.sh -c standalone-full.xml -Dhttps.protocols=TLSv1.2 -b 0.0.0.0 -bmanagement 0.0.0.0"]
