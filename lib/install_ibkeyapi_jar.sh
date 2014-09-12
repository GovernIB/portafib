#!/bin/sh

mvn install:install-file -Dfile=./signaturacaib.core-3.3.2-api-unsigned.jar -DgroupId=signaturacaib -DartifactId=signaturacaib.core -Dversion=3.3.2 -Dclassifier=api-unsigned -Dpackaging=jar


