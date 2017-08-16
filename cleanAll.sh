#!/bin/bash

env mvn clean -Psqlgen -Pminiappletui -Pclientcert -Pws-portafib-v1 -Pws-portafib-v2 -Pws-portafib-callback-server -Pws-portafirmas  -Pws-portafirmas-callback-server -Pdesenvolupament -Psia -Ppassarelaweb-v1 -Ppassarelaserver-v1 -Ppassarelaweb-v2 -Ppassarelaserver-v2
