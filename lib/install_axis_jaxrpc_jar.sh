#!/bin/bash

mvn install:install-file -Dfile=./axis-jaxrpc-without-qname-1.3.jar -DgroupId=axis -DartifactId=axis-jaxrpc-without-qname -Dversion=1.3 -Dpackaging=jar
