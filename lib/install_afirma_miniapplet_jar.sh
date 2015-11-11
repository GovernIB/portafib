#!/bin/bash

mvn install:install-file -Dfile=./miniapplet-full_1_3.jar -DgroupId=es.gob.afirma -DartifactId=afirma-ui-miniapplet_1_3 -Dversion=1.3  -Dpackaging=jar
