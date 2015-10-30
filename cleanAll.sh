#!/bin/bash

env mvn clean -Psqlgen -Papplet -Pclientcert -Pws-portafib -Pws-portafib-callback-server -Pws-portafirmas  -Pws-portafirmas-callback-server  -Pdesenvolupament -Pproduccio 
