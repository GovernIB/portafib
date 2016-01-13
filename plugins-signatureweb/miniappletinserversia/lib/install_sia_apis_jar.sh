#!/bin/bash

mvn install:install-file -Dfile=./gateway-api-2.4.05.jar -DgroupId=com.openlandsw.rss -DartifactId=gateway-api -Dversion=2.4.05 -Dpackaging=jar

mvn install:install-file -Dfile=./gateway-api-comun-2.4.05.jar -DgroupId=com.openlandsw.rss -DartifactId=gateway-api-comun -Dversion=2.4.05 -Dpackaging=jar

mvn install:install-file -Dfile=./hessian-3.2.0.jar -DgroupId=com.caucho -DartifactId=hessian -Dversion=3.2.0 -Dpackaging=jar
