#!/bin/bash

echo off
cat help.txt

env mvn -DskipTests -Pdesenvolupament $@ install 

if [ $? == 0 ]; then
  if [ "$PORTAFIB_DEPLOY_DIR" == "" ];  then

    echo  =================================================================
    echo    Definex la variable d\'entorn PORTAFIB_DEPLOY_DIR apuntant al
    echo    directori de deploy del JBOSS  i automaticament s\'hi copiara
    echo    l\'ear generat.
    echo  =================================================================
  
  else
  
    echo on
    echo --------- COPIANT EAR ---------

    if [ -f "$PORTAFIB_DEPLOY_DIR/portafib_plugins.ear" ]; then
      rm  $PORTAFIB_DEPLOY_DIR/portafib_plugins.ear
    fi

    cp ./ear/target/portafib.ear $PORTAFIB_DEPLOY_DIR

  fi
fi


