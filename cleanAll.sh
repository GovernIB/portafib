#!/bin/bash

env mvn clean -Psqlgen -Pminiappletui -Pws-portafib-v1 -Pws-portafib-callback-server -Pws-portafirmas  -Pws-portafirmas-callback-server -Psia -Pfortress -Ppassarelaweb-v1 -Ppassarelaserver-v1
